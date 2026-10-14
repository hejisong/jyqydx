package com.ywjc.glsyjs.interfaces.plycomresult.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface PlycomresultdetailEntityService extends BaseService<PlycomresultdetailEntity, String> {


    //查询
    public List<PlycomresultdetailEntity> findPlycomresultdetailByCompanyid(String companyid);

    /**
     * 根据企业id和政策类别查询（1 免申即享 2 少量补录即享 3 满足条件即享 4 预告）
     * @param companyid
     * @param enjoypolicycategory
     * @return
     */
    public List<PlycomresultdetailEntity> findPlycomresultByCompanyidandtype(String companyid,String enjoypolicycategory);

    public Page<PlycomresultdetailEntity> findPage(String companyid,String enjoypolicycategory,Pageable pageable);

    public Page<PlycomresultdetailEntity> findPageForWarn(String companyid,String enjoypolicycategory,Pageable pageable);

    public PlycomresultdetailEntity findPlycomresultByCompany(String companyid,String companypatentid);

    public PlycomresultdetailEntity findPlycomresult(String companyid,String pldtailid,String particularyear);

    /**
     * 根据企业id获取各类政策数量
     * @param companyid
     * @return
     */
    public List<Object[]> findtypecount(String companyid);

    public List<Object[]> findtypecountMSJX(String companyid);

    public List<Object[]> findtypecountwarn(String companyid);

    //查询我的兑现列表-免申即享，补充材料即可兑现
    public List<PlycomresultdetailEntity> findPlycomresultdetailList(String companyid);

    public Object[][] findPolicydetailBypldtailid(String pldtailid);

    public List<PlycomresultdetailEntity> findwddxByCompanyid(String companyid);

    public Page<PlycomresultdetailEntity> findwddxPage(String companyid,Pageable pageable);

    public Page<PlycomresultdetailEntity> findoverdaylist(String enterprisename, String begintime, String endtime,String cashflag,Pageable pageable);

    public Page<Object> findinfoPage(String enterprisename, String begintime, String endtime,String cashflag,Pageable pageable);

    public Page<PlycomresultdetailEntity> findinfoMsjx(String[]pldtailids, String[] companyids,Pageable pageable);

    public List<Object[]> findinfoAll();

    public List<Object[]> findonfileinfoAll();

    public List<Object[]> findonfileinfoAll(String enterprisename, String begintime, String endtime,String cashflag);

    public List<Object[]> findinfoAll(String enterprisename, String begintime, String endtime,String cashflag);

    public List<Object[]> findpayinfoAll(String enterprisename, String begintime, String endtime,String cashflag);

    public Page<PlycomresultdetailEntity> findpaymentlist(String enterprisename, String begintime, String endtime,String cashflag,Pageable pageable);

    public Page<Object> findpaymentlistPage(String enterprisename, String begintime, String endtime,String cashflag,Pageable pageable);

    public List<PlycomresultdetailEntity> findlistByIsflow(String isflow);

    //审核流程推送到钉钉
    public void  checkToDingDing(PlycomresultdetailEntity plycomresultdetail,List<String> submitFileList);

    public void updateCashflag(String cashflag,String plycomresultdetailid);

    List<PlycomresultdetailEntity> findDXPGByCompanyid(String searchid);

    public String counttotal();

    public String paytotal();

    public String notpaytotal();

    public List<Object> paycorps();

    public List<Object> policycorpscount();

    public List<Object> policyTypecount();

    public List<Object> policycashflagcount();

    public List<Object> policyOrgcount(String particularyear );

    public List<Object> payOrgsum();

    public List<Object> payFileum();
}
