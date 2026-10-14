package com.ywjc.glsyjs.interfaces.policyfile.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import com.ywjc.glsyjs.Policyservices.utils.PolicydetailValidate;
import com.ywjc.glsyjs.common.validate.ValidateBinding;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.repositories.PolicydetailRepository;
import com.ywjc.glsyjs.interfaces.policyfile.repositories.PolicyfileRepository;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.tag.repositories.TagRepository;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.repositories.PolicyFileEntityRepository;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("policydetailServiceImpl")
public class PolicydetailServiceImpl extends BaseServiceImpl<PolicydetailEntity, String>
        implements PolicydetailService {

    @Resource(name = "policydetailRepository")
    private PolicydetailRepository repository;

    @Resource(name = "queryServiceImpl")
    private QueryService queryService;

    @Resource(name = "tagRepository")
    private TagRepository tagRepository;

    @Resource(name = "policyFileEntityRepository")
    private PolicyFileEntityRepository policyFileEntityRepository;

    @Resource(name = "policydetailRepository")
    public void setReposity(
            PagingAndSortingRepository<PolicydetailEntity, String> repository) {
        super.setReposity(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public PolicydetailEntity find(String id) {
        return super.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PolicydetailEntity> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PolicydetailEntity> findList(String[] ids) {
        return super.findList(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PolicydetailEntity> findList(Sort sort) {
        return super.findList(sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PolicydetailEntity> findPage(Pageable pageable) {
        return super.findPage(pageable);
    }

    @Override
    @Transactional
    public void save(PolicydetailEntity entity) {
        super.save(entity);
    }

    @Override
    @Transactional
    public PolicydetailEntity update(PolicydetailEntity entity) {
        PolicydetailEntity policydetail = super.find(entity.getPldtailid());
        super.update(entity);
        return policydetail;
    }

    @Override
    @Transactional
    public PolicydetailEntity update(PolicydetailEntity entity, String... ignoreProperties) {
        PolicydetailEntity policydetail = super.update(entity, ignoreProperties);
        return policydetail;
    }

    @Override
    @Transactional
    public void delete(String id) {
        super.delete(id);
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        super.delete(ids);
    }

    @Override
    @Transactional
    public void delete(PolicydetailEntity entity) {
        super.delete(entity);
    }

    @Override
    @Transactional
    public List<PolicydetailEntity> findPolicyFiles(String plfidid) {
        return repository.findPolicyFiles(plfidid);
    }

    @Override
    @Transactional
    public List<Object[]> findDetails(String plfidid){
        return repository.findDetails(plfidid);
    }

    @Override
    public List<PolicydetailEntity> findPolicyFilesTag(String plfidid,String tagid){
        return repository.findPolicyFilesTag(plfidid,tagid);
    }

    @Override
    public List<PolicydetailEntity> findPolicyDetails(String[] plfidid){
        return repository.findPolicyDetails(plfidid);
    }

    @Override
    public List<PolicydetailEntity> findBypldtailids(String[] pldtailids){
        return repository.findBypldtailids(pldtailids);
    }

    @Override
    public List<PolicydetailEntity> findPolicyDetailTag(String plfidid,String[] tagid){
        return repository.findPolicyDetailTag(plfidid,tagid);
    }

    @Override
    public List<PolicydetailEntity> findPolicydetails(String plfidid,String nplcategory){
        return repository.findPolicydetails(plfidid,nplcategory);

    }

    @Override
    public Page<PolicydetailEntity> findPolicyFilesPage(String plfidid,String isdelete, Pageable pageable){
        if("1".equals(isdelete)){
            return repository.findPolicyFilesPageAll(plfidid,pageable);
        }else{
            return repository.findPolicyFilesPage(plfidid,pageable);
        }

    }

    @Override
    public List<PolicydetailEntity> findMsjx(){
        return repository.findMsjx();
    }

    @Override
    public List<PolicydetailEntity> findMSfilesByplfidid(String plfidid){
        return repository.findMSfilesByplfidid(plfidid);
    }

    @Override
    public List<PolicydetailEntity> findAccuratePolicydetail(){
        return repository.findAccuratePolicydetail();
    }

    @Override
    public List<PolicydetailEntity> findAccuratePolicydetailByplfidid(String plfidid){
        return repository.findAccuratePolicydetailByplfidid(plfidid);
    }

    @Override
    public Page<PolicydetailEntity> findPolicyFile(String plfidid, String policycategory, Pageable pageable){
        //已作废的文件
        List<String> plfidids=policyFileEntityRepository.querdelete();


        if(StringUtils.isNotBlank(plfidid)){
            if(StringUtils.isNotBlank(policycategory)){
                policycategory =StringUtils.isNotBlank(policycategory)?"%"+policycategory.trim()+"%":"%%";
                return repository.findPolicyFile(plfidid,policycategory,pageable);
            }
            return repository.findPolicyFile(plfidid,pageable);
        }
        if(StringUtils.isNotBlank(policycategory)){
            policycategory =StringUtils.isNotBlank(policycategory)?"%"+policycategory.trim()+"%":"%%";
            if(plfidids!=null&&plfidids.size()>0){
                return repository.findPolicyFilenotdelete(policycategory,plfidids,pageable);
            }
            return repository.findPolicyFileAll(policycategory,pageable);
        }
        if(plfidids!=null&&plfidids.size()>0){
            return repository.findPolicyFilenotdelete(plfidids,pageable);
        }
        return repository.findPolicyFileAll(pageable);
    }

    @Override
    public Page<Object> findInfos(String plfidid,String filelevel,String policycategory,Pageable pageable){
        List<Object> values = new ArrayList<Object>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String columsql="  p.pldtailid,p.plfidid,p.nplcategory,p.plsubclassname,p.plconditions,p.rdescription ";
        String sql= "   from policydetail p left join policyfile f on p.plfidid=f.plfidid where (p.state is null  or p.state='' or p.state<>'2' ) and (f.state<>'0' or f.state is null  or f.state='')   ";


        if(StringUtils.isNotBlank(plfidid)){
            sql = sql + " and p.plfidid like ? ";
            plfidid = "%" + plfidid.trim() + "%";
            values.add(plfidid);
        }

        if(StringUtils.isNotBlank(filelevel)){
            sql = sql + " and f.filelevel like ? ";
            filelevel = "%" + filelevel.trim() + "%";
            values.add(filelevel);
        }

        if(StringUtils.isNotBlank(policycategory)){
            sql = sql + " and p.nplcategory like ? ";
            policycategory = "%" + policycategory.trim() + "%";
            values.add(policycategory);
        }

        String order=" order by p.serialnum ,p.nplcategory  ,p.plsubclassname ";
        Page<Object> page = queryService.queryObjectForPage(columsql,sql,order,values,pageable);
        return  page;
    }


    @Override
    public String findMax(String plfidid){
        return repository.findMax(plfidid);
    }



    @Override
    public List<Object> findPolicydetails(String content) {
        List<Object> values = new ArrayList<Object>();
        String sql = " select p.pldtailid,f.policyname,p.nplcategory,p.plsubclassname,p.plconditions,p.rdescription from policydetail p left join policyfile f on p.plfidid=f.plfidid where (p.state is null  or p.state='' or p.state<>'2' ) and (f.state<>'0' or f.state is null  or f.state='') ";

        if (StringUtils.isNotBlank(content)) {
            sql = sql + " and ( ";
            String[] contents = content.split("、");
            int i = 0;
            for (String c : contents) {
                if (i == 0) {
                    sql = sql + "  p.nplcategory like ? or p.plsubclassname like ? or p.plconditions like ? ";
                } else {
                    sql = sql + "  or p.nplcategory like ? or p.plsubclassname like ? or p.plconditions like ? ";
                }

                c = "%" + c.trim() + "%";
                values.add(c);
                values.add(c);
                values.add(c);
                i++;
            }
            sql = sql + " ) ";

        }

        sql = sql + " order by p.serialnum+0 ";
        List<Object> info = queryService.queryForObject(sql, values);
        return info;
    }

    @Override
    public List<PolicydetailEntity> findPolicyFilesByTag(String tagid) {
        return repository.findPolicyFilesByTag(tagid);
    }

    @Override
    public List<PolicydetailEntity> findPolicyFiles(String plfidid, String policycategorynum, String policysubclassno) {
        if (StringUtils.isEmpty(policycategorynum) && StringUtils.isEmpty(policysubclassno)) {
            return repository.findPolicyFiles(plfidid);
        } else if (StringUtils.isEmpty(policycategorynum)) {
            return repository.findPolicyFilesBySubclass(plfidid, policysubclassno);
        } else if (StringUtils.isEmpty(policysubclassno)) {
            return repository.findPolicyFilesByCategory(plfidid, policycategorynum);
        } else
            return repository.findPolicyFiles(plfidid, policycategorynum, policysubclassno);
    }

    @Override
    public List<String> findPolicyCategory() {
        List<Object[]> result = repository.findAllCategory();
        List<String> list = new ArrayList<>();
        for (Object[] strings : result) {
            if (strings != null) {
                String big = strings[0] == null ? "" : strings[0] + "";
                String small = strings[1] == null ? "" : strings[1] + "";
                list.add(big + "-" + small);
            }
        }
        return list;
    }


    @Override
    public List<Object> findInfopolicydeclare(Policydeclare policydeclare, String plfidid, String dl, String key){
        List<String> plfidids=new ArrayList<>();
        String pids="";
        if(StringUtils.isNotBlank(plfidid)){
            plfidids.add(plfidid);
            pids+="'"+plfidid+"',";
        }else{
            if(StringUtils.isNotBlank(policydeclare.getPolicyfileid())){
                String[] plfididstrs=policydeclare.getPolicyfileid().split(",");
                for (String plfididstr :plfididstrs) {
                    plfidids.add(plfididstr);
                    pids+="'"+plfididstr+"',";
                }
            }

        }
        pids=pids.substring(0,pids.length()-1);

        List<Object> values = new ArrayList<Object>();
        String sql = " select p.pldtailid,p.nplcategory,p.plsubclassname,p.plconditions,p.rdescription,p.ramount from policydetail p left join policyfile f on p.plfidid=f.plfidid where (p.state is null  or p.state='' or p.state<>'2' ) and (f.state<>'0' or f.state is null  or f.state='')  and p.plfidid in ("+pids+")  ";
//        values.add(plfidids );
        if (StringUtils.isNotBlank(dl)) {
            sql = sql + " and  p.nplcategory = ? ";
            values.add(dl.trim() );
        }

        if (StringUtils.isNotBlank(key)) {
            sql = sql + " and ( p.nplcategory like ? or p.plsubclassname like ? or p.plconditions like ? or p.rdescription like ? )";
            key = "%" + key.trim() + "%";
            values.add(key);
            values.add(key);
            values.add(key);
            values.add(key);

        }

        sql = sql + " order by p.serialnum,p.nplcategory,p.ramount";
        List<Object> info = queryService.queryForObject(sql, values);
        return info;



    }


    @Override
    @Transactional(readOnly = true)
    public List<Object[]> findtypecountall(String plfidid){
        return  repository.findtypecountall(plfidid);
    }


    @Override
    public Object[] importInfoExcel(Workbook workbook, String plfidid ){
        Object[] r=new Object[3];
        String nplcategory=null;
        String plsubclassname=null;
        String plconditions=null;
        String rdescription=null;
        String ramount=null;
        String thpath=null;
        String pevaluationdepartment=null;
        String feaunit=null;
        String cjtype=null;
        String declareconditions=null;
        ValidateBinding bind=null;
        List<PolicydetailEntity> corps=new ArrayList<>();
        int num=-1;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 循环sheet
            //获取第一张Sheet表
            Sheet sheet =workbook.getSheetAt(i);
            bind= PolicydetailValidate.validateEvalResult(sheet);
            if(bind.hasErrors()){
                r[0]=num;
                r[1]=bind;
                return r;
            }
            //获取Sheet表中所包含的总行数
            int numberrow=sheet.getPhysicalNumberOfRows();
            num=0;
            for(int j=1;j<numberrow;j++){
                Row row = sheet.getRow(j); // 获得工作薄的第j行
                if(row!=null){
                    for(int k=0;k<8;k++){// 获得工作薄的第j行的第k列的值
                        Cell cell = row.getCell(k);
                        if(cell!=null){
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                                if(k==0){//政策大类
                                    nplcategory=publicExcel(cell).trim();
                                }else if(k==1){//政策小类
                                    plsubclassname=publicExcel(cell).trim();
                                }else if(k==2){//政策条件
                                    plconditions=publicExcel(cell).trim();
                                }else if(k==3){//奖励描述
                                    rdescription=publicExcel(cell).trim();
                                }else if(k==4){//奖励金额
                                    ramount=publicExcel(cell).trim();
                                }else if(k==5){//兑付路径
                                    thpath=publicExcel(cell).trim();
                                }else if(k==6){//审批单位
                                    pevaluationdepartment=publicExcel(cell).trim();
                                }else if(k==7){//参与评定部门
                                    feaunit=publicExcel(cell).trim();
                                }else if(k==8){//政策明细类型
                                    cjtype=publicExcel(cell).trim();
                                }else if(k==9){//申报条件
                                    declareconditions=publicExcel(cell).trim();
                                }


                        }
                    }
                    //保存到数据库
                    num=num+1;
                    PolicydetailEntity detail=new PolicydetailEntity();
                    detail.setPldtailid(KeyUtils.getKey());
                    detail.setPlfidid(plfidid);
                    detail.setNplcategory(nplcategory);
                    detail.setPlsubclassname(plsubclassname);
                    detail.setPlconditions(plconditions);
                    detail.setRdescription(rdescription);
                    if(StringUtils.isNotBlank(ramount)){
                        detail.setRamount(Double.valueOf(ramount));
                    }

                    detail.setThpath(thpath);
                    detail.setPevaluationdepartment(pevaluationdepartment);
                    detail.setFeaunit(feaunit);
                    detail.setCjtype(cjtype);
                    detail.setDeclareconditions(declareconditions);
                    String maxnum=findMax(plfidid);
                    int numa;
                    if(StringUtils.isBlank(maxnum)){
                        numa=0;
                    }else{
                        numa=Integer.parseInt(maxnum);
                    }
                    detail.setSerialnum(numa+1);
                    save(detail);

                }
            }
        }

        r[0]=num;
        r[1]=bind;
        r[2]=corps;
        return r;
    }





    private static String publicExcel( Cell cell){
        String value = null;
        if(cell!=null){
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                    value =Double.toString(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    ;
                    break;
                default:
            }
        }

        return value;
    }


}

