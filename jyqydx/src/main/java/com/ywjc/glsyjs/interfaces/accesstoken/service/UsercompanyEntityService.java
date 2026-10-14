package com.ywjc.glsyjs.interfaces.accesstoken.service;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.businesshare.entity.Orderagency;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;

import java.util.List;

public interface UsercompanyEntityService extends BaseService<UsercompanyEntity, String> {

    //根据解密之后的用户查询用户表
    public List<UsercompanyEntity> findCompanyByDecryptInfo(String username, String linknum, String linktel);

    public UsercompanyEntity findCompanyByDecryptInfo(String username, String linknum, String linktel,String companyid);

    //查询用户企业信息
    public List<User> findUserByDecryptInfo(String username, String linknum, String linktel);

    public User findUserFromNj(String username, String linknum, String linktel);

    //新增用户和企业关联
    public void saveUserCompany(UsercompanyEntity usercompanyEntity);

    //根据手机号查找企业信息
    public List<UsercompanyEntity> findCompanyByPhone(String phone);

    /**
     * 根据企业名称查询企业信息
     * @param enterprisename
     * @return
     */
    public List<Object[]> findCompanyByCompanyname(String enterprisename);

    //根据用户和企业查询绑定关系
    public UsercompanyEntity findCompanyByPhoneandname(String username,String phone);
//删除除法人外的企业绑定关系
    public void deleteBycompanyid(String companyid);

    UsercompanyEntity findCompanyByNameAndLinknum(String username, String linknum);

    UsercompanyEntity findByuseridAndCompanyid(String userid, String companyid);


    public UsercompanyEntity findCompany(String username, String linknum,String companyid);

    //根据手机号查找企业信息
    public List<UsercompanyEntity> findUserByCompany(String companyid,String username);
}
