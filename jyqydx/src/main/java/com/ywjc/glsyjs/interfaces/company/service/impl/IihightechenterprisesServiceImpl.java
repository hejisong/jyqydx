package com.ywjc.glsyjs.interfaces.company.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.company.entity.Iihightechenterprises;
import com.ywjc.glsyjs.interfaces.company.repositories.IihightechenterprisesRepository;
import com.ywjc.glsyjs.interfaces.company.service.IihightechenterprisesService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("iihightechenterprisesServiceImpl")
public class IihightechenterprisesServiceImpl extends BaseServiceImpl<Iihightechenterprises, String>
        implements IihightechenterprisesService {

    @Resource(name = "iihightechenterprisesRepository")
    private IihightechenterprisesRepository repository;


    @Resource(name = "iihightechenterprisesRepository")
    public void setReposity(
            PagingAndSortingRepository<Iihightechenterprises, String> repository) {
        super.setReposity(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iihightechenterprises find(String id) {
        return super.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Iihightechenterprises> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Iihightechenterprises> findList(String[] ids) {
        return super.findList(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Iihightechenterprises> findList(Sort sort) {
        return super.findList(sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Iihightechenterprises> findPage(Pageable pageable) {
        return super.findPage(pageable);
    }

    @Override
    @Transactional
    public void save(Iihightechenterprises entity) {
        super.save(entity);
    }

    @Override
    @Transactional
    public Iihightechenterprises update(Iihightechenterprises entity) {
        Iihightechenterprises iihightechenterprises = super.find(entity.getIiheeid());
        super.update(entity);
        return iihightechenterprises;
    }

    @Override
    @Transactional
    public Iihightechenterprises update(Iihightechenterprises entity, String... ignoreProperties) {
        Iihightechenterprises iihightechenterprises = super.update(entity, ignoreProperties);
        return iihightechenterprises;
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
    public void delete(Iihightechenterprises entity) {
        super.delete(entity);
    }


    @Override
    @Transactional
    public List<Iihightechenterprises> findByYear(String recognitionyear){
        return  repository.findByYear(recognitionyear);
    }

    @Override
    @Transactional
    public List<Iihightechenterprises> findBycompanyid(String companyid){
        return  repository.findBycompanyid(companyid);
    }
    @Override
    @Transactional
    public Iihightechenterprises findBycertificateno(String certificateno){
        return  repository.findBycertificateno(certificateno);
    }
    @Override
    @Transactional
    public Page<Iihightechenterprises> queryPageinfo(String companyname, String certificateno, String recognitionyear, Pageable pageable){
         return repository.queryPageinfo(companyname,certificateno,recognitionyear,pageable);
    }

    @Override
    @Transactional
    public List<Iihightechenterprises> findNoCompanyid(){
        return repository.findNoCompanyid();
    }


}
