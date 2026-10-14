package com.ywjc.glsyjs.sys.service.impl;

import cn.hutool.core.date.DateField;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.tag.repositories.TagRepository;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.repositories.PolicyFileEntityRepository;
import com.ywjc.glsyjs.sys.service.PolicyFileEntityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("policyFileEntityServiceImpl")
public class PolicyFileEntityServiceImpl extends BaseServiceImpl<PolicyFileEntity, String> implements
        PolicyFileEntityService {

    @Resource(name = "policyFileEntityRepository")
    private PolicyFileEntityRepository repository;
    @Resource(name = "queryServiceImpl")
    private QueryService queryService;

    @Resource(name = "tagRepository")
    private TagRepository tagRepository;

    @Resource(name = "policyFileEntityRepository")
    public void setReposity(
            PagingAndSortingRepository<PolicyFileEntity, String> repository) {
        super.setReposity(repository);
    }
    @Override
    @Transactional(readOnly = true)
    public List<PolicyFileEntity> findPolicyFileEntitiesByall() {
        return repository.findPolicyFileEntitiesByall();
    }


    @Override
    @Transactional(readOnly = true)
    public List<PolicyFileEntity> findPolicyFiles() {
        return repository.findPolicyFiles();
    }

    @Override
    public List<Object> findMSfiles(){
       return repository.findMSfiles();
    }

    @Override
    public List<Object> findAccuratefiles(){
        return repository.findAccuratefiles();
    }


    @Override
    public Page<Object> findPolicyFiles(Date startdate, Date enddate, String level, String tagid,String key, Pageable pageable){
        List<Object> values = new ArrayList<Object>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String columsql="  p.plfidid,p.policyname,p.fwtitanic,p.fwunits,p.releasetime,p.filecontent,p.filelevel,p.filetype,p.state ";
        String sql= "  from policyfile p where p.state='2'   ";
        if(startdate!=null){
            sql = sql + " and p.releasetime >= ? ";
            values.add(sdf.format(startdate));

        }
        if(enddate!=null){
            sql = sql + " and p.releasetime <= ? ";
            values.add(sdf.format(enddate));
        }

        if(StringUtils.isNotBlank(level)){
            sql = sql + " and p.filelevel like ? ";
            level = "%" + level.trim() + "%";
            values.add(level);
        }
        if(StringUtils.isNotBlank(tagid)){
            String plfidids="";
            String[] tagids=tagid.split(",");
            List<String> fileids=tagRepository.findplfidids(tagids);
            if(fileids!=null&&fileids.size()>0){
                for (String fileid:fileids) {
                    plfidids+="'"+fileid+"',";
                }
                if(StringUtils.isNotBlank(plfidids)){
                    plfidids=plfidids.substring(0,plfidids.length()-1);

                }
            }else{
                plfidids="''";
            }

            sql = sql + " and p.plfidid in ( "+plfidids+" ) ";



        }

        if(StringUtils.isNotBlank(key)){
            sql = sql + " and ( ";
            String[] contents=key.split("、");
            int i=0;
            for (String c:contents) {
                if(i==0){
                    sql = sql + "  p.policyname like ? or p.filecontent like ? ";
                }else{
                    sql = sql + "  or p.policyname like ? or p.filecontent like ? ";
                }

                c = "%" + c.trim() + "%";
                values.add(c);
                values.add(c);
                i++;
            }
            sql = sql + " ) ";

        }

        String order=" order by p.releasetime desc ";
        Page<Object> page = queryService.queryObjectForPage(columsql,sql,order,values,pageable);
        return  page;
    }

    @Override
    public List<Object> findPolicyFiles(Date startdate,Date enddate,String level,String content){
        List<Object> values = new ArrayList<Object>();
        String sql= " select p.plfidid,p.policyname,p.fwtitanic,p.fwunits,p.releasetime,p.filecontent from PolicyFile p where p.state='2'   ";

        if(startdate!=null){
            sql = sql + " and p.releasetime >= ? ";
            values.add(startdate);
        }
        if(enddate!=null){
            sql = sql + " and p.releasetime <= ? ";
            values.add(enddate);
        }

        if(StringUtils.isNotBlank(level)){
            sql = sql + " and p.filelevel like ? ";
            level = "%" + level.trim() + "%";
            values.add(level);
        }
        if(StringUtils.isNotBlank(content)){
            sql = sql + " and ( ";
            String[] contents=content.split("、");
            int i=0;
            for (String c:contents) {
                if(i==0){
                    sql = sql + "  p.policyname like ? or p.filecontent like ? ";
                }else{
                    sql = sql + "  or p.policyname like ? or p.filecontent like ? ";
                }

                c = "%" + c.trim() + "%";
                values.add(c);
                values.add(c);
                i++;
            }
            sql = sql + " ) ";

        }

        sql = sql + " order by p.serialnum+0 ";
        List<Object> info =  queryService.queryForObject(sql,values);
        return  info;
    }

    @Override
    public List<PolicyFileEntity> findPolicyFilesByCondition(Date startdate, Date enddate, String level,String content){
        level= StringUtils.isNotBlank(level)?"%"+level+"%":"%%";
        content= StringUtils.isNotBlank(content)?"%"+content+"%":"%%";
        if(startdate!=null&&enddate!=null){
            return repository.findPolicyFilesBydate(startdate,enddate,level,content);
        }else if(startdate!=null){
            return repository.findPolicyFilesByStartdate(startdate,level,content);
        }else if(enddate!=null){
            return repository.findPolicyFilesByEnddate(enddate,level,content);
        }
        return repository.findPolicyFilesByNodate(level,content);
    }

    @Override
    public List<Object> findFiletypeSum(){
         return repository.findFiletypeSum();
    }
    @Override
    public List<Object> findPolicyFilesByFiletype(String filetype){
        return repository.findPolicyFilesByFiletype(filetype);
    }
    @Override
    public Page<Object> findPolicyFilesByFiletype(String filetype, Pageable pageable){
        return repository.findPolicyFilesByFiletype( filetype,  pageable);
    }

    @Override
    public List<PolicyFileEntity> findPolicyFilesByTag(String tagid){
        return repository.findPolicyFilesByTag(tagid);
    }

    @Override
    public Page<PolicyFileEntity> findPolicyFilename(String policyname,Pageable pageable){
        return repository.findPolicyFilename(policyname,pageable);
    }

    @Override
    public List<PolicyFileEntity> findByFilelevel(String filelevel){
        filelevel= StringUtils.isNotBlank(filelevel)?"%"+filelevel+"%":"%%";
        return repository.findByFilelevel(filelevel);
    }



}
