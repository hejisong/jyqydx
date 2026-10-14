package com.ywjc.glsyjs.interfaces.plycomresult.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.account.entity.AuthorizedrecordEntity;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.AuthorizedrecordService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.interfaces.cloudpivot.vo.Attachment;
import com.ywjc.glsyjs.interfaces.cloudpivot.vo.SubmitData;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.repositories.PlycomresultDetailEntityRepository;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.utils.FlowUtil;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("plycomresultdetailEntityServiceImpl")
public class PlycomresultdetailEntityServiceImpl extends BaseServiceImpl<PlycomresultdetailEntity, String> implements
        PlycomresultdetailEntityService {


    @Resource(name = "queryServiceImpl")
    private QueryService queryService;

    @Resource(name = "policydetailServiceImpl")
    private PolicydetailService policydetailService;

    @Resource(name = "organinfoServiceImpl")
    private OrganinfoService organinfoService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "uploadfileServiceImpl")
    private UploadfileService uploadfileService;

    @Resource(name = "authorizedrecordServiceImpl")
    private AuthorizedrecordService authorizedrecordService;

    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;

    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;


    @Resource(name = "plycomresultDetailEntityRepository")
    private PlycomresultDetailEntityRepository plycomresultDetailEntityRepository;

    @Resource(name = "plycomresultDetailEntityRepository")
    public void setReposity(PagingAndSortingRepository<PlycomresultdetailEntity, String> plycomresultEntityRepository) {
        super.setReposity(plycomresultEntityRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlycomresultdetailEntity> findPlycomresultdetailByCompanyid(String companyid) {
        return plycomresultDetailEntityRepository.findPlycomresultdetailByCompanyid(companyid);
    }
    @Override
    @Transactional(readOnly = true)
    public List<PlycomresultdetailEntity> findPlycomresultByCompanyidandtype(String companyid,String enjoypolicycategory) {
        return plycomresultDetailEntityRepository.findPlycomresultByCompanyidandtype(companyid,enjoypolicycategory);
    }
    @Override
    public Page<PlycomresultdetailEntity> findPage(String companyid,String enjoypolicycategory,Pageable pageable){
        if("1".equals(enjoypolicycategory)){
            return plycomresultDetailEntityRepository.findPageMSJX(companyid,pageable);
        }
        return plycomresultDetailEntityRepository.findPage(companyid,enjoypolicycategory,pageable);

    }

    @Override
    public Page<PlycomresultdetailEntity> findPageForWarn(String companyid,String enjoypolicycategory,Pageable pageable){
        return plycomresultDetailEntityRepository.findPageForWarn(companyid,enjoypolicycategory,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> findtypecount(String companyid){
        return plycomresultDetailEntityRepository.findtypecount(companyid);
    }



    public List<Object[]> findtypecountMSJX(String companyid){
        return plycomresultDetailEntityRepository.findtypecount1(companyid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> findtypecountwarn(String companyid){
        return plycomresultDetailEntityRepository.findtypecountwarn(companyid);
    }

    @Override
    @Transactional(readOnly = true)
    public Object[][] findPolicydetailBypldtailid(String pldtailid) {
        return plycomresultDetailEntityRepository.findPolicydetailBypldtailid(pldtailid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlycomresultdetailEntity> findPlycomresultdetailList(String companyid) {
        return plycomresultDetailEntityRepository.findPlycomresultdetailByCompanyid(companyid);
    }

    @Override
    @Transactional(readOnly = true)
    public PlycomresultdetailEntity find(String id) {
        return super.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlycomresultdetailEntity> findwddxByCompanyid(String companyid) {
        return plycomresultDetailEntityRepository.findwddxByCompanyid(companyid);
    }

    @Override
    public Page<PlycomresultdetailEntity> findwddxPage(String companyid,Pageable pageable){
        return plycomresultDetailEntityRepository.findwddxPage(companyid,pageable);
    }

    @Override
    public PlycomresultdetailEntity findPlycomresultByCompany(String companyid,String companypatentid){
        return plycomresultDetailEntityRepository.findPlycomresultByCompany(companyid,companypatentid);
    }

    public PlycomresultdetailEntity findPlycomresult(String companyid,String pldtailid,String particularyear){
        return plycomresultDetailEntityRepository.findPlycomresult(companyid,pldtailid,particularyear);
    }

    @Override
    public List<Object[]> findinfoAll(){
        return plycomresultDetailEntityRepository.findinfoAll();
    }
    @Override
    public List<Object[]> findinfoAll(String enterprisename, String begintime, String endtime,String cashflag){
        List<Object> values = new ArrayList<Object>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startdate=null;
        Date enddate=null;
        if (StringUtils.isNotBlank(begintime)) {
            try {
                begintime=begintime+" 00:00:00";
                startdate=sdf.parse(begintime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(endtime)) {
            try {
                endtime=endtime+" 23:59:59";
                enddate=sdf.parse(endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        String sql= " select p.enterprisename,p.uscreditcode,p.policycategoryname,p.policyconditions,p.rdescription,p.amount,date_format(p.inputtime, '%Y-%m-%d'),CASE p.cashflag WHEN '0' THEN '已匹配'  WHEN '1' THEN '待审批'  WHEN '7' THEN '审批中'  WHEN '2' THEN '审批通过'  WHEN '3' THEN '已退回'  WHEN '4' THEN '审核不通过' WHEN '5' THEN '已支付'  ELSE '已上传凭证' END,p.policysubclassname from plycomresultdetail p where     p.enjoypolicycategory in ('1','2')  ";
        if(StringUtils.isNotBlank(enterprisename)){
            sql = sql + " and p.enterprisename like ? ";
            enterprisename = "%" + enterprisename.trim() + "%";
            values.add(enterprisename);
        }


        if(startdate!=null){
            sql = sql + " and p.inputtime >= ? ";
            values.add(sdf.format(startdate));

        }
        if(enddate!=null){
            sql = sql + " and p.inputtime <= ? ";
            values.add(sdf.format(enddate));
        }

        if(StringUtils.isNotBlank(cashflag)){
            sql = sql + " and p.cashflag like ? ";
            cashflag = "%" + cashflag.trim() + "%";
            values.add(cashflag);
        }
        sql=sql+" order by p.inputtime desc  ";
        List<Object[]> info = queryService.query(sql,values);
        return  info;
    }

    @Override
    public List<Object[]> findpayinfoAll(String enterprisename, String begintime, String endtime,String cashflag){
//        enterprisename=StringUtils.isNotBlank(enterprisename)?"%"+enterprisename+"%":"%%";
//        if(StringUtils.isBlank(cashflag)){
//            return plycomresultDetailEntityRepository.findpayinfoAll(enterprisename,begintime,endtime);
//        }
//        return plycomresultDetailEntityRepository.findpayinfocashflag(enterprisename,begintime,endtime,cashflag);
        List<Object> values = new ArrayList<Object>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startdate=null;
        Date enddate=null;
        if (StringUtils.isNotBlank(begintime)) {
            try {
                begintime=begintime+" 00:00:00";
                startdate=sdf.parse(begintime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(endtime)) {
            try {
                endtime=endtime+" 23:59:59";
                enddate=sdf.parse(endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        String sql= " select  p.enterprisename,p.uscreditcode,c.account,p.policycategoryname,p.policyconditions,p.rdescription,p.amount,date_format(p.inputtime, '%Y-%m-%d'),CASE p.cashflag   WHEN '2' THEN '审批通过'   WHEN '5' THEN '已支付'  ELSE '待支付' END,p.policysubclassname from plycomresultdetail p  left join company c on p.companyid=c.companyid  where  p.enjoypolicycategory in ('1','2')  ";
        if(StringUtils.isNotBlank(enterprisename)){
            sql = sql + " and p.enterprisename like ? ";
            enterprisename = "%" + enterprisename.trim() + "%";
            values.add(enterprisename);
        }


        if(startdate!=null){
            sql = sql + " and p.inputtime >= ? ";
            values.add(sdf.format(startdate));

        }
        if(enddate!=null){
            sql = sql + " and p.inputtime <= ? ";
            values.add(sdf.format(enddate));
        }

        if(StringUtils.isNotBlank(cashflag)){
            sql = sql + " and p.cashflag like ? ";
            cashflag = "%" + cashflag.trim() + "%";
            values.add(cashflag);
        }else{
            sql = sql + " and p.cashflag in  ('2','5','6') ";
        }



        sql=sql+" order by p.inputtime desc  ";
        List<Object[]> info = queryService.query(sql,values);
        return  info;
    }
    @Override
    public List<Object[]> findonfileinfoAll(String enterprisename, String begintime, String endtime,String cashflag){
        List<Object> values = new ArrayList<Object>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startdate=null;
        Date enddate=null;
        if (StringUtils.isNotBlank(begintime)) {
            try {
                begintime=begintime+" 00:00:00";
                startdate=sdf.parse(begintime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(endtime)) {
            try {
                endtime=endtime+" 23:59:59";
                enddate=sdf.parse(endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        String sql= " select p.enterprisename,p.uscreditcode,c.account,p.policycategoryname,p.policyconditions,p.rdescription,p.amount,date_format(p.inputtime, '%Y-%m-%d'),CASE p.cashflag WHEN '0' THEN '已匹配'  WHEN '1' THEN '待审批'  WHEN '7' THEN '审批中'  WHEN '2' THEN '审批通过'  WHEN '3' THEN '已退回'  WHEN '4' THEN '审核不通过' WHEN '5' THEN '已支付'  ELSE '待支付' END,p.policysubclassname from plycomresultdetail p  left join company c on p.companyid=c.companyid where   p.enjoypolicycategory in ('1','2')  ";
        if(StringUtils.isNotBlank(enterprisename)){
            sql = sql + " and p.enterprisename like ? ";
            enterprisename = "%" + enterprisename.trim() + "%";
            values.add(enterprisename);
        }


        if(startdate!=null){
            sql = sql + " and p.inputtime >= ? ";
            values.add(sdf.format(startdate));

        }
        if(enddate!=null){
            sql = sql + " and p.inputtime <= ? ";
            values.add(sdf.format(enddate));
        }

        if(StringUtils.isNotBlank(cashflag)){
            sql = sql + " and p.cashflag like ? ";
            cashflag = "%" + cashflag.trim() + "%";
            values.add(cashflag);
        }



        sql=sql+" order by p.inputtime desc  ";
        List<Object[]> info = queryService.query(sql,values);
        return  info;
    }

    @Override
    public List<Object[]> findonfileinfoAll(){
        return plycomresultDetailEntityRepository.findonfileinfoAll();
    }

    @Override
    public Page<Object> findinfoPage(String enterprisename, String begintime, String endtime,String cashflag,Pageable pageable){
        List<Object> values = new ArrayList<Object>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startdate=null;
        Date enddate=null;
        if (StringUtils.isNotBlank(begintime)) {
            try {
                begintime=begintime+" 00:00:00";
                startdate=sdf.parse(begintime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(endtime)) {
            try {
                endtime=endtime+" 23:59:59";
                enddate=sdf.parse(endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        String columsql="  p.plycomresultdetailid,p.enterprisename,p.uscreditcode,p.policyname,p.policycategoryname,p.policysubclassname,p.policyconditions,p.rdescription,p.amount,p.cashflag,p.inputtime,p.companyid,p.enjoypolicycategory,c.account  ";
        String sql= "  from plycomresultdetail p left join company c on p.companyid=c.companyid where  p.enjoypolicycategory in ('1','2')  ";
        if(StringUtils.isNotBlank(enterprisename)){
            sql = sql + " and p.enterprisename like ? ";
            enterprisename = "%" + enterprisename.trim() + "%";
            values.add(enterprisename);
        }


        if(startdate!=null){
            sql = sql + " and p.inputtime >= ? ";
            values.add(sdf.format(startdate));

        }
        if(enddate!=null){
            sql = sql + " and p.inputtime <= ? ";
            values.add(sdf.format(enddate));
        }

       if(StringUtils.isNotBlank(cashflag)){
            sql = sql + " and p.cashflag like ? ";
            cashflag = "%" + cashflag.trim() + "%";
            values.add(cashflag);
        }else{
            sql = sql + " and p.cashflag in  ('0','1','2','3','4','5','6','7') ";
        }



        String order=" order by p.inputtime desc  ";
        Page<Object> page = queryService.queryObjectForPage(columsql,sql,order,values,pageable);
       // queryService.queryForPage(sql,order,values,PlycomresultdetailEntity.class,pageable);
        return  page;
    }

    @Override
    public Page<PlycomresultdetailEntity> findinfoMsjx(String[] pldtailids, String[] companyids,Pageable pageable){
        List<String> pldtailidslist = Arrays.asList(pldtailids);
        List<String> companyidslist = Arrays.asList(companyids);
        if (pldtailidslist.size() > 0) {
            if(pldtailidslist.size()<=1000){
                if(pldtailidslist.size()<=1000){
                    if (companyidslist.size() > 0) {
                        if(companyidslist.size()<=1000){
                            return  plycomresultDetailEntityRepository.findinfoMsjx11(pldtailidslist,companyidslist,pageable);

                        }else if(companyidslist.size()<=2000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx12(pldtailidslist,companyidslist1,companyidslist2,pageable);

                        } else if(companyidslist.size()<=3000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,2000);
                            List<String> companyidslist3=companyidslist.subList(2000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx13(pldtailidslist,companyidslist1,companyidslist2,companyidslist3,pageable);

                        }else if(companyidslist.size()<=4000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,2000);
                            List<String> companyidslist3=companyidslist.subList(2000,3000);
                            List<String> companyidslist4=companyidslist.subList(3000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx14(pldtailidslist,companyidslist1,companyidslist2,companyidslist3,companyidslist4,pageable);
                        }else if(companyidslist.size()<=5000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,2000);
                            List<String> companyidslist3=companyidslist.subList(2000,3000);
                            List<String> companyidslist4=companyidslist.subList(3000,4000);
                            List<String> companyidslist5=companyidslist.subList(4000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx15(pldtailidslist,companyidslist1,companyidslist2,companyidslist3,companyidslist4,companyidslist5,pageable);
                        }else if(companyidslist.size()<=6000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,2000);
                            List<String> companyidslist3=companyidslist.subList(2000,3000);
                            List<String> companyidslist4=companyidslist.subList(3000,4000);
                            List<String> companyidslist5=companyidslist.subList(4000,5000);
                            List<String> companyidslist6=companyidslist.subList(5000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx16(pldtailidslist,companyidslist1,companyidslist2,companyidslist3,companyidslist4,companyidslist5,companyidslist6,pageable);
                        }

                    }



                }else if(pldtailidslist.size()<=2000){
                    List<String> pldtailidslist1=pldtailidslist.subList(0,1000);
                    List<String> pldtailidslist2=pldtailidslist.subList(1000,pldtailidslist.size());

                    if (companyidslist.size() > 0) {
                        if(companyidslist.size()<=1000){
                            return  plycomresultDetailEntityRepository.findinfoMsjx21(pldtailidslist1,pldtailidslist2,companyidslist,pageable);

                        }else if(companyidslist.size()<=2000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx22(pldtailidslist1,pldtailidslist2,companyidslist1,companyidslist2,pageable);

                        } else if(companyidslist.size()<=3000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,2000);
                            List<String> companyidslist3=companyidslist.subList(2000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx23(pldtailidslist1,pldtailidslist2,companyidslist1,companyidslist2,companyidslist3,pageable);

                        }else if(companyidslist.size()<=4000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,2000);
                            List<String> companyidslist3=companyidslist.subList(2000,3000);
                            List<String> companyidslist4=companyidslist.subList(3000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx24(pldtailidslist1,pldtailidslist2,companyidslist1,companyidslist2,companyidslist3,companyidslist4,pageable);
                        }else if(companyidslist.size()<=5000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,2000);
                            List<String> companyidslist3=companyidslist.subList(2000,3000);
                            List<String> companyidslist4=companyidslist.subList(3000,4000);
                            List<String> companyidslist5=companyidslist.subList(4000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx25(pldtailidslist1,pldtailidslist2,companyidslist1,companyidslist2,companyidslist3,companyidslist4,companyidslist5,pageable);
                        }else if(companyidslist.size()<=6000){
                            List<String> companyidslist1=companyidslist.subList(0,1000);
                            List<String> companyidslist2=companyidslist.subList(1000,2000);
                            List<String> companyidslist3=companyidslist.subList(2000,3000);
                            List<String> companyidslist4=companyidslist.subList(3000,4000);
                            List<String> companyidslist5=companyidslist.subList(4000,5000);
                            List<String> companyidslist6=companyidslist.subList(5000,companyidslist.size());
                            return  plycomresultDetailEntityRepository.findinfoMsjx26(pldtailidslist1,pldtailidslist2,companyidslist1,companyidslist2,companyidslist3,companyidslist4,companyidslist5,companyidslist6,pageable);
                        }

                    }

                }

            }

        }

        return  null;
    }

    @Override
    public Page<Object> findpaymentlistPage(String enterprisename, String begintime, String endtime,String cashflag,Pageable pageable){
        List<Object> values = new ArrayList<Object>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startdate=null;
        Date enddate=null;
        if (StringUtils.isNotBlank(begintime)) {
            try {
                begintime=begintime+" 00:00:00";
                startdate=sdf.parse(begintime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(endtime)) {
            try {
                endtime=endtime+" 23:59:59";
                enddate=sdf.parse(endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        String columsql="  p.plycomresultdetailid,p.enterprisename,p.uscreditcode,p.policyname,p.policycategoryname,p.policysubclassname,p.policyconditions,p.rdescription,p.amount,p.cashflag,p.inputtime,p.companyid,p.enjoypolicycategory,c.account  ";
        String sql= "  from plycomresultdetail p  left join company c on p.companyid=c.companyid where  p.enjoypolicycategory in ('1','2')  ";
        if(StringUtils.isNotBlank(enterprisename)){
            sql = sql + " and p.enterprisename like ? ";
            enterprisename = "%" + enterprisename.trim() + "%";
            values.add(enterprisename);
        }


        if(startdate!=null){
            sql = sql + " and p.inputtime >= ? ";
            values.add(sdf.format(startdate));

        }
        if(enddate!=null){
            sql = sql + " and p.inputtime <= ? ";
            values.add(sdf.format(enddate));
        }

        if(StringUtils.isNotBlank(cashflag)){
            sql = sql + " and p.cashflag like ? ";
            cashflag = "%" + cashflag.trim() + "%";
            values.add(cashflag);
        }else{
            sql = sql + " and p.cashflag in  ('2','5','6') ";
        }



        String order=" order by p.inputtime desc  ";
        Page<Object> page = queryService.queryObjectForPage(columsql,sql,order,values,pageable);
        // queryService.queryForPage(sql,order,values,PlycomresultdetailEntity.class,pageable);
        return  page;
    }


    @Override
    public Page<PlycomresultdetailEntity> findoverdaylist(String enterprisename, String begintime, String endtime,String cashflag,Pageable pageable) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        enterprisename = StringUtils.isBlank(enterprisename) ? "%%" : "%" + enterprisename + "%";
        begintime = StringUtils.isBlank(begintime) ? "" :begintime+" 00:00:00";
        endtime = StringUtils.isBlank(endtime) ? "" :endtime+" 23:59:59";



        if (StringUtils.isBlank(begintime) && StringUtils.isBlank(endtime)) {
//            return plycomresultDetailEntityRepository.findPage1(enterprisename, cashflag, pageable);
            if(StringUtils.isNotBlank(cashflag)){
                cashflag = StringUtils.isBlank(cashflag) ? "%%" : "%" + cashflag + "%";
                return plycomresultDetailEntityRepository.findPagenew1(enterprisename, cashflag, pageable);
            }else{
                return plycomresultDetailEntityRepository.findPagenewAll(enterprisename, pageable);
            }

        }else{
//            return plycomresultDetailEntityRepository.findPage2(enterprisename, cashflag,begintime,endtime,pageable);
            if(StringUtils.isNotBlank(cashflag)) {
                cashflag = StringUtils.isBlank(cashflag) ? "%%" : "%" + cashflag + "%";
                return plycomresultDetailEntityRepository.findPagenew2(enterprisename, cashflag,begintime,endtime,pageable);
            }else{
                return plycomresultDetailEntityRepository.findPagenew2All(enterprisename,begintime,endtime,pageable);
            }

        }


    }

    @Override
    public Page<PlycomresultdetailEntity> findpaymentlist(String enterprisename, String begintime, String endtime,String cashflag,Pageable pageable) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        enterprisename = StringUtils.isBlank(enterprisename) ? "%%" : "%" + enterprisename + "%";
        begintime = StringUtils.isBlank(begintime) ? "" :begintime+" 00:00:00";
        endtime = StringUtils.isBlank(endtime) ? "" :endtime+" 23:59:59";

        if (StringUtils.isBlank(begintime) && StringUtils.isBlank(endtime)) {
            if(StringUtils.isNotBlank(cashflag)){
                cashflag = StringUtils.isBlank(cashflag) ? "%%" : "%" + cashflag + "%";
                return plycomresultDetailEntityRepository.findpaymentPage1(enterprisename, cashflag, pageable);
            }else{
                return plycomresultDetailEntityRepository.findpaymentPage1all(enterprisename, pageable);
            }

        }else{
            if(StringUtils.isNotBlank(cashflag)){
                cashflag = StringUtils.isBlank(cashflag) ? "%%" : "%" + cashflag + "%";
                return plycomresultDetailEntityRepository.findpaymentPage2(enterprisename, cashflag,begintime,endtime,pageable);

            }else{
                return plycomresultDetailEntityRepository.findpaymentPage2All(enterprisename,begintime,endtime,pageable);
            }

        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlycomresultdetailEntity> findlistByIsflow(String isflow) {
        return plycomresultDetailEntityRepository.findlistByIsflow(isflow);
    }

    @Override
    public void  checkToDingDing(PlycomresultdetailEntity plycomresultdetail,List<String> submitFileList){

        // 上传文件
        //附件
        List<Attachment> attachments = new ArrayList<>();
        if(submitFileList!=null&&submitFileList.size()>0){
            for (String fileid:submitFileList) {
                Uploadfile file=uploadfileService.find(fileid);
                String filepath = ConfigUtil.getConfigVal("file_save_path") + "/doc/upload_" + file.getId() + "."
                        + file.getFileext();
                File f=new File(filepath);
                attachments.add(FlowUtil.uploadFile(f,file.getFilename()));
            }
        }




        SubmitData submitData = new SubmitData();
        //企业名称
        submitData.setENTERPRISENAME(plycomresultdetail.getEnterprisename());
        //统一社会信用代码
        submitData.setUSCREDITCODE(plycomresultdetail.getUscreditcode());
        //政策大类名称
        submitData.setNPLCATEGORY(plycomresultdetail.getPolicycategoryname());
        //政策小类名称
        submitData.setPLSUBCLASSNAME(plycomresultdetail.getPolicysubclassname());
        //政策条件
        submitData.setPLCONDITIONS(plycomresultdetail.getPolicyconditions());
        //奖励描述
        submitData.setRDESCRIPTION(plycomresultdetail.getRdescription());
        //政策名称
        submitData.setPOLICYNAME(plycomresultdetail.getPolicyname());
        //奖励金额
        submitData.setRAMOUNT(plycomresultdetail.getAmount());
        //授权人或法人
        AuthorizedrecordEntity au= authorizedrecordService.findlistBycompanyidLast(plycomresultdetail.getCompanyid());
        if(au!=null){
            //申请人姓名
            submitData.setApplyname(au.getAuthorizedperson());
            //申请人身份证号码
            submitData.setApplycard("");
        }else{
            CompanyEntity companyEntity=companyEntityService.queryCompanyByid(plycomresultdetail.getCompanyid());
            //申请人姓名
            submitData.setApplyname(companyEntity.getLrepresentative());
            //申请人身份证号码
            submitData.setApplycard(companyEntity.getBusinessregisnum());
        }

        //申请id
        submitData.setApplyid(plycomresultdetail.getPlycomresultdetailid());
        // 申请时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        submitData.setApplydate(sdf.format(new Date()));

        //政策明细
        PolicydetailEntity policydetail=policydetailService.find(plycomresultdetail.getPldtailid());
        //政策审批
        HashMap<String, String> user_department_map = new HashMap<>();
        HashMap<String, String> firstuser_map = new HashMap<>();


        Organinfo organinfo=organinfoService.find("2090");
        List<User> users=userService.findCheckUsers(organinfo.getId());
        if(users!=null&&users.size()>0){
            for (User user:users) {
                //初审人员
                firstuser_map.put("id", user.getDduserid());
                firstuser_map.put("type", "3");
            }
        }

        submitData.setFirstuser(JSON.toJSONString(firstuser_map));
//        submitData.setUser_department(JSON.toJSONString(user_department_map));
        submitData.setAttachment(attachments);
        //数据提交
        JSONObject result= FlowUtil.submitData(submitData);
        System.out.println(result);

        //更改流程状态
        FlowProcessEntity flowprocess=flowProcessEntityService.queryListByapplyidandfid(plycomresultdetail.getPlycomresultdetailid(),"6","1");
        if(flowprocess!=null){
            flowprocess.setProcessstate("1");
            flowProcessEntityService.update(flowprocess);
            //生成审批意见
            String infoid = UUID.randomUUID().toString();
            flowProcessEntityService.saveFlowAuditInfo(infoid, flowprocess.getAuditid(), "园区初审",
                    "园区初审", null, null,
                    "0", null, null,
                    null,"1",null);
        }
        plycomresultdetail.setCashflag("7");
        update(plycomresultdetail);



    }


    @Override
    public void updateCashflag(String cashflag, String plycomresultdetailid) {
        plycomresultDetailEntityRepository.updateCashflag(cashflag, plycomresultdetailid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlycomresultdetailEntity> findDXPGByCompanyid(String searchid) {
        return plycomresultDetailEntityRepository.findDXPGByCompanyid(searchid);
    }


    @Override
    public String counttotal(){
       return  plycomresultDetailEntityRepository.counttotal();
    }

    @Override
    public String paytotal(){
        return  plycomresultDetailEntityRepository.paytotal();
    }
    @Override
    public String notpaytotal(){
        return  plycomresultDetailEntityRepository.notpaytotal();
    }

    @Override
    public List<Object> paycorps(){
        return  plycomresultDetailEntityRepository.paycorps();
    }
    @Override
    public List<Object> policycorpscount(){
        return  plycomresultDetailEntityRepository.policycorpscount();
    }

    @Override
    public List<Object> policyTypecount(){
        return  plycomresultDetailEntityRepository.policyTypecount();
    }
    @Override
    public List<Object> policycashflagcount(){
        return  plycomresultDetailEntityRepository.policycashflagcount();
    }

    @Override
    public List<Object> policyOrgcount(String particularyear){
        return  plycomresultDetailEntityRepository.policyOrgcount(particularyear);
    }

    @Override
    public List<Object> payOrgsum(){
        return  plycomresultDetailEntityRepository.payOrgsum();
    }

    @Override
    public List<Object> payFileum(){
        return  plycomresultDetailEntityRepository.payFileum();
    }

}
