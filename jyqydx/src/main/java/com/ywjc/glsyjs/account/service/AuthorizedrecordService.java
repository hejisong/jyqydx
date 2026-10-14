package com.ywjc.glsyjs.account.service;

import com.ywjc.glsyjs.account.entity.AuthorizedrecordEntity;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorizedrecordService extends BaseService<AuthorizedrecordEntity, String> {
    //根据企业id查询企业授权列表
    public List<AuthorizedrecordEntity> findlistBycompanyid(String companyid);

    public AuthorizedrecordEntity findlistBycompanyidLast(String companyid);

    public Page<AuthorizedrecordEntity> findpageListBycompanyid(String companyid , Pageable pageable);

    //根据人员名称或手机号查询用户列表
    public List<Object[]>  findCompanyuserlistBynameorphone(String personname,String phonenum);
    //根据企业id取消所有已授权记录
    public void updateshqu(String companyid);

    Integer findUserIdAndState(String userid, String state);

    public List<Object[]>  findCompanyuserSq(String companyid,String personname,String phonenum);


    public List<AuthorizedrecordEntity> findsqList(String companyid,String userid);
}
