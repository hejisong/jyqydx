package com.ywjc.glsyjs.interfaces.suggestions.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.suggestions.entity.SuggestionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SuggestionsRepository extends BaseRepository<SuggestionsEntity, String> {

    @Query(value = "select s from SuggestionsEntity s where s.id = ?1")
    public SuggestionsEntity queryOneSuggestions(String id);

    @Query(value = "select s from SuggestionsEntity s where s.title like ?1 and s.infotype =?2 and  s.isshow='1'  order by s.createtime desc")
    public List<SuggestionsEntity> querySuggestionsList(String title, String infotype);

    @Query(value = "select s from SuggestionsEntity s where s.title like ?1 and s.infotype =?2 and (  s.isshow='1'  or s.companyid=?3 )  order by s.createtime desc")
    public List<SuggestionsEntity> querySuggestionsListByCompanyid(String title, String infotype, String companyid);

    @Query("select s from SuggestionsEntity s where s.title like ?1 and s.isshow='1' and (s.infotype is null or s.infotype='1' ) order by s.updatetime desc")
    public Page<SuggestionsEntity> queryPage(String title, Pageable pageable);

    @Query("select s from SuggestionsEntity s where s.title like ?1 and (s.companyid=?2 or s.isshow='1') and (s.infotype is null or s.infotype='1' ) order by s.updatetime desc ")
    public Page<SuggestionsEntity> queryPageByCompany(String title, String companyid, Pageable pageable);

    @Query("select s from SuggestionsEntity s where s.title like ?1 and s.isshow='1' and s.infotype in('2','3')  order by s.updatetime desc ")
    public Page<SuggestionsEntity> queryPagecomplaint(String title, Pageable pageable);

    @Query("select s from SuggestionsEntity s where s.title like ?1 and (s.companyid=?2 or s.isshow='1') and s.infotype in('2','3')  order by s.updatetime desc ")
    public Page<SuggestionsEntity> queryPagecomplaintByCompany(String title, String companyid, Pageable pageable);

    @Query("select s from SuggestionsEntity s where s.companyname like ?1 and s.title like ?2 and (s.infotype is null or s.infotype='1' )  order by s.updatetime desc")
    public Page<SuggestionsEntity> queryPageinfo(String companyname, String title, Pageable pageable);

    @Query("select s from SuggestionsEntity s where s.companyname like ?1 and s.title like ?2  and  s.infotype =?3   order by s.updatetime desc ")
    public Page<SuggestionsEntity> queryPageinfocomplaint(String companyname, String title, String infotype, Pageable pageable);

    @Query("select s from SuggestionsEntity s where s.companyname like ?1 and s.title like ?2  and  s.infotype in('2','3')  order by s.updatetime desc")
    public Page<SuggestionsEntity> queryPageinfocomplaintAll(String companyname, String title, Pageable pageable);

    @Query(value = "update suggestions set readcount = readcount + 1 where id = ?1 ", nativeQuery = true)
    @Modifying
    void updateReadcountByid(String id);

    @Query(value = "update SuggestionsEntity set state = :state,updatetime=:updatetime where id = :id ")
    @Modifying
    int updateState(SuggestionsEntity suggestionsEntity);
}
