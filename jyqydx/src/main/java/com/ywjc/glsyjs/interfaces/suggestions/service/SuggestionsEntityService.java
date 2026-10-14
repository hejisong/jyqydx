package com.ywjc.glsyjs.interfaces.suggestions.service;

import com.ywjc.glsyjs.businesshare.entity.Suggestreply;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.suggestions.entity.SuggestionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SuggestionsEntityService extends BaseService<SuggestionsEntity, String> {

    //查询
    public SuggestionsEntity queryOneSuggestions(String id);

    //查询
    public List<SuggestionsEntity> querySuggestionsList(String title,String infotype);

    public List<SuggestionsEntity> querySuggestionsListByCompanyid(String title,String infotype,String createrid);

    //查询
    public Page<SuggestionsEntity> queryPage(String title, Pageable pageable);

    public Page<SuggestionsEntity> queryPageByCompany(String title,String companyid, Pageable pageable);

    public Page<SuggestionsEntity> queryPagecomplaint(String title, Pageable pageable);

    public Page<SuggestionsEntity> queryPagecomplaintByCompany(String title,String companyid,  Pageable pageable);


    public Page<SuggestionsEntity> queryPageinfo(String companyname,String title, Pageable pageable);

    public Page<SuggestionsEntity> queryPageinfocomplaint(String companyname,String title,String infotype, Pageable pageable);

    public void updateReadcountByid(String id);

    void addReply(Suggestreply suggestreply);

    List<Suggestreply> queryDetail(String id);

    int updateState(SuggestionsEntity suggestionsEntity);
}
