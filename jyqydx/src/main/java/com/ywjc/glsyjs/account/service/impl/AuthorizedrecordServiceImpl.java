package com.ywjc.glsyjs.account.service.impl;

import com.ywjc.glsyjs.account.entity.AuthorizedrecordEntity;
import com.ywjc.glsyjs.account.repository.AuthorizedrecordRepository;
import com.ywjc.glsyjs.account.service.AuthorizedrecordService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.accesstoken.repositories.UsercompanyEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("authorizedrecordServiceImpl")
public class AuthorizedrecordServiceImpl extends BaseServiceImpl<AuthorizedrecordEntity, String> implements
        AuthorizedrecordService {
    @Resource(name = "authorizedrecordRepository")
    private AuthorizedrecordRepository repository;
    @Resource(name = "usercompanyEntityRepository")
    private UsercompanyEntityRepository usercompanyEntityRepository;


    @Resource(name = "authorizedrecordRepository")
    public void setReposity(
            PagingAndSortingRepository<AuthorizedrecordEntity, String> repository) {
        super.setReposity(repository);
    }
    @Override
    public List<AuthorizedrecordEntity> findlistBycompanyid(String companyid) {
        return repository.findlistBycompanyid(companyid);
    }

    @Override
    public AuthorizedrecordEntity findlistBycompanyidLast(String companyid){
        List<AuthorizedrecordEntity> infos=repository.findlistBycompanyidLast(companyid);
        if(infos!=null&&infos.size()>0){
            return  infos.get(0);
        }
        return null;
    }

    @Override
    public Page<AuthorizedrecordEntity> findpageListBycompanyid(String companyid , Pageable pageable){
        return repository.findpageListBycompanyid(companyid,pageable);
    }

    @Override
    public List<Object[]> findCompanyuserlistBynameorphone(String personname,String phonenum) {
        return repository.findCompanyuserlistBynameorphone(personname,phonenum);
    }

    @Override
    public List<Object[]>  findCompanyuserSq(String companyid,String personname,String phonenum){
        return repository.findCompanyuserSq(companyid,personname,phonenum);
    }

    @Override
    @Transactional
    public void updateshqu(String companyid) {
        //将关联表状态不是法人的授权记录也清除
        usercompanyEntityRepository.updateByCompanyId(companyid);
        repository.updateshqu(companyid);
    }

    @Override
    public Integer findUserIdAndState(String userid, String state) {
        return repository.findUserIdAndState(userid,state);

    }
    @Override
    public List<AuthorizedrecordEntity> findsqList(String companyid,String userid){
        return repository.findsqList(companyid,userid);
    }
}
