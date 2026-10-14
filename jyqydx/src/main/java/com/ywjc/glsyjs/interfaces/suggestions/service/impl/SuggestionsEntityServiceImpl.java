package com.ywjc.glsyjs.interfaces.suggestions.service.impl;

import com.ywjc.glsyjs.businesshare.entity.Suggestreply;
import com.ywjc.glsyjs.businesshare.repositories.SuggestreplyRepository;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.repositories.CompanyEntityRepository;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.suggestions.entity.SuggestionsEntity;
import com.ywjc.glsyjs.interfaces.suggestions.repositories.SuggestionsRepository;
import com.ywjc.glsyjs.interfaces.suggestions.service.SuggestionsEntityService;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("suggestionsEntityServiceImpl")
public class SuggestionsEntityServiceImpl extends BaseServiceImpl<SuggestionsEntity, String> implements
        SuggestionsEntityService {

    @Resource(name = "suggestionsRepository")
    private SuggestionsRepository suggestionsRepository;
    @Resource(name = "suggestreplyRepository")
    private SuggestreplyRepository suggestreplyRepository;

    @Resource(name = "suggestionsRepository")
    public void setReposity(PagingAndSortingRepository<SuggestionsEntity, String> suggestionsRepository) {
        super.setReposity(suggestionsRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public SuggestionsEntity queryOneSuggestions(String id) {
        return suggestionsRepository.queryOneSuggestions(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SuggestionsEntity> querySuggestionsList(String title, String infotype) {
        return suggestionsRepository.querySuggestionsList(title, infotype);
    }
    @Override
    @Transactional(readOnly = true)
    public List<SuggestionsEntity> querySuggestionsListByCompanyid(String title, String infotype,String createrid) {
        return suggestionsRepository.querySuggestionsListByCompanyid(title, infotype,createrid);
    }

    @Override
    public Page<SuggestionsEntity> queryPage(String title, Pageable pageable) {
        return suggestionsRepository.queryPage(title, pageable);
    }

    @Override
    public Page<SuggestionsEntity> queryPageByCompany(String title,String companyid, Pageable pageable){
        return suggestionsRepository.queryPageByCompany(title,companyid,pageable);
    }

    @Override
    public Page<SuggestionsEntity> queryPagecomplaint(String title, Pageable pageable) {
        return suggestionsRepository.queryPagecomplaint(title, pageable);
    }

    @Override
    public Page<SuggestionsEntity> queryPagecomplaintByCompany(String title,String companyid,  Pageable pageable){
        return suggestionsRepository.queryPagecomplaintByCompany(title,companyid,pageable);
    }

    @Override
    public Page<SuggestionsEntity> queryPageinfo(String companyname, String title, Pageable pageable) {
        return suggestionsRepository.queryPageinfo(companyname, title, pageable);
    }

    @Override
    public Page<SuggestionsEntity> queryPageinfocomplaint(String companyname, String title, String infotype, Pageable pageable) {
        if (StringUtils.isNotBlank(infotype)) {
            return suggestionsRepository.queryPageinfocomplaint(companyname, title, infotype, pageable);
        }
        return suggestionsRepository.queryPageinfocomplaintAll(companyname, title, pageable);
    }

    @Override
    public void updateReadcountByid(String id) {
        suggestionsRepository.updateReadcountByid(id);
    }

    @Override
    public void addReply(Suggestreply suggestreply) {
        suggestreplyRepository.save(suggestreply);
    }

    @Override
    public List<Suggestreply> queryDetail(String id) {
        return suggestreplyRepository.findBySuggest(id);
    }

    @Override
    public int updateState(SuggestionsEntity suggestionsEntity) {
        return suggestionsRepository.updateState(suggestionsEntity);
    }
}
