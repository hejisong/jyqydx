package com.ywjc.glsyjs.interfaces.company.service;

import com.ywjc.glsyjs.Policyservices.entity.Intellectmatch;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyEntityService extends BaseService<CompanyEntity, String> {

    //修改企业法人手机号码
    public void updateCompanyPhoneno(String phone, String companyid);

    //查询
    public CompanyEntity queryCompanyByid(String companyid);

    //查询
    public CompanyEntity queryCompanyBycorpname(String corpname);

    public CompanyEntity queryCompany(String corpname,String uscreditcode);

    public CompanyEntity queryCompanyOne(String corpname,String uscreditcode);

    //查询
    public List<CompanyEntity> queryCompanyList(String enterprisename);

    public Page<CompanyEntity> queryCompanyPage(String enterprisename,String uscreditcode, Pageable pageable);

    //查询
    public List<CompanyEntity> queryCompanyListBySearchName(String searchName);

    //查询
    public List<CompanyEntity> queryCompanyListByPhone(String phone);

    public List<CompanyEntity> queryAll();

    //政务大数据接口数据
    public  void updateInfo(CompanyEntity company);

    public  void updateInfoByCorpname(String corpname);

    public List<Object> querytypesenterprises();



    public List<Object> queryCompanys(Intellectmatch match);

    public List<Object> queryCompanysAuto(Intellectmatch match);

    public List<CompanyEntity> queryCompanys(List<Object> companyids);

}