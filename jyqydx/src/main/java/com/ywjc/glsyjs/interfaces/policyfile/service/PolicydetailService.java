package com.ywjc.glsyjs.interfaces.policyfile.service;

import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PolicydetailService extends BaseService<PolicydetailEntity, String> {

    public List<PolicydetailEntity> findPolicyFiles(String plfidid);

    public List<Object[]> findDetails(String plfidid);

    public List<PolicydetailEntity> findPolicyFilesTag(String plfidid,String tagid);

    public List<PolicydetailEntity> findPolicyDetailTag(String plfidid,String[] tagid);

    public List<PolicydetailEntity> findPolicyDetails(String[] plfidid);

    public List<PolicydetailEntity> findBypldtailids(String[] pldtailids);

    public Page<PolicydetailEntity> findPolicyFilesPage(String plfidid,String isdelete, Pageable pageable);

    public  List<Object> findPolicydetails(String content);

    public List<PolicydetailEntity> findPolicyFilesByTag(String tagid);

    List<PolicydetailEntity> findPolicyFiles(String plfid, String policycategorynum, String policysubclassno);

    List<String> findPolicyCategory();


    public List<PolicydetailEntity> findPolicydetails(String plfidid,String policycategory);


    public Page<PolicydetailEntity> findPolicyFile(String plfidid,String policycategory,Pageable pageable);


    public List<Object> findInfopolicydeclare(Policydeclare policydeclare, String plfidid, String dl, String key);

    public String findMax(String plfidid);

    public List<Object[]> findtypecountall(String plfidid);


    public Object[] importInfoExcel(Workbook excelwork,String plfidid );


    public Page<Object> findInfos(String plfidid,String filelevel,String policycategory,Pageable pageable);

    public List<PolicydetailEntity> findMsjx();

    public List<PolicydetailEntity> findMSfilesByplfidid(String plfidid);


    public List<PolicydetailEntity> findAccuratePolicydetail();


    public List<PolicydetailEntity> findAccuratePolicydetailByplfidid(String plfidid);

}
