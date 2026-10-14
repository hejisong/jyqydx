package com.ywjc.glsyjs.interfaces.accesstoken.service.impl;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.repositories.UsercompanyEntityRepository;
import com.ywjc.glsyjs.interfaces.accesstoken.service.UsercompanyEntityService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("usercompanyEntityServiceImpl")
public class UsercompanyEntityServiceImpl extends BaseServiceImpl<UsercompanyEntity, String> implements
        UsercompanyEntityService {

    @Resource(name = "usercompanyEntityRepository")
    private UsercompanyEntityRepository usercompanyEntityRepository;

    @Resource(name = "usercompanyEntityRepository")
    public void setReposity(PagingAndSortingRepository<UsercompanyEntity, String> usercompanyEntityRepository) {
        super.setReposity(usercompanyEntityRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsercompanyEntity> findCompanyByDecryptInfo(String username, String linknum, String linktel){
        return usercompanyEntityRepository.findCompanyByDecryptInfo(username,linknum,linktel);
    }

    @Override
    @Transactional(readOnly = true)
    public UsercompanyEntity findCompanyByDecryptInfo(String username, String linknum, String linktel,String companyid){
        return usercompanyEntityRepository.findCompanyByDecryptInfo(username,linknum,linktel,companyid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUserByDecryptInfo(String username, String linknum, String linktel) {
        return usercompanyEntityRepository.findUserByDecryptInfo(username,linknum,linktel);
    }

    @Override
    public User findUserFromNj(String username, String linknum, String linktel){
        return usercompanyEntityRepository.findUserFromNj(username,linknum,linktel);
    }

    @Override
    public void saveUserCompany(UsercompanyEntity usercompanyEntity) {
        save(usercompanyEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsercompanyEntity> findCompanyByPhone(String phone){
        return usercompanyEntityRepository.findCompanyByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> findCompanyByCompanyname(String enterprisename){
        return usercompanyEntityRepository.findCompanyByCompanyname(enterprisename);
    }
    @Override
    @Transactional(readOnly = true)
    public UsercompanyEntity findCompanyByPhoneandname(String username,String phone){
        List<UsercompanyEntity>  usercoms=usercompanyEntityRepository.findCompanyByPhoneandname(username,phone);
        if(usercoms!=null&&usercoms.size()>0){
            return usercoms.get(0);
        }else{
            return null;
        }
    }
    @Override
    @Transactional
    public void deleteBycompanyid(String companyid){
        usercompanyEntityRepository.deleteBycompanyid(companyid);
    }

    @Override
    public UsercompanyEntity findCompanyByNameAndLinknum(String username, String linknum) {
        return usercompanyEntityRepository.findCompanyByNameAndLinknum(username,linknum);
    }

    @Override
    public UsercompanyEntity findByuseridAndCompanyid(String userid, String companyid) {
        return usercompanyEntityRepository.findByuseridAndCompanyid(userid,companyid);

    }

    @Override
    public UsercompanyEntity findCompany(String username, String linknum,String companyid){
        return usercompanyEntityRepository.findCompany(username,linknum,companyid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsercompanyEntity> findUserByCompany(String companyid,String username){
        return usercompanyEntityRepository.findUserByCompany(companyid,username);
    }


}
