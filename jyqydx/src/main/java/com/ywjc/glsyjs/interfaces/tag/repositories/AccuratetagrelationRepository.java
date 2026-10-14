package com.ywjc.glsyjs.interfaces.tag.repositories;


import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetagrelation;
import com.ywjc.glsyjs.interfaces.tag.entity.TagRelationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface AccuratetagrelationRepository extends PagingAndSortingRepository<Accuratetagrelation, String> {



    @Modifying
    @Query(value = "delete from accuratetagrelation where tagid = ?1", nativeQuery = true)
    public  void deleteByTagid(String tagid);



    @Query(value = "select t from Accuratetagrelation  t where  t.tagyear like ?1 and  t.tagyear like ?2")
    Page<Accuratetagrelation> findPage(String companyname, String tagyear, Pageable pageable);


    @Query(value = "select tr.* from accuratetagrelation tr where   tr.tagid=?1 and tr.pid=?2 ", nativeQuery = true)
    Accuratetagrelation queryByPid(String tagid, String pid);


    @Query("select distinct t.tagid from Accuratetagrelation t where  t.pid =?1  ")
    public List<String> findTagidByPid(String pid);


    @Query("select distinct t.tagid from Accuratetagrelation t where  t.cid =?1  ")
    public List<String> findTagidByCid(String cid);

    @Query(value = "select tr.* from accuratetagrelation tr where   tr.tagid=?1 and tr.cid=?2 ", nativeQuery = true)
    Accuratetagrelation queryByCid(String tagid, String cid);

    @Query(value = "select t from Accuratetagrelation  t where  t.cid = ?1 and  t.tagid = ?2 and t.tagyear = ?3 ")
    public  Accuratetagrelation findByCorp(String cid,String tagid,String tagyear);



    @Modifying
    @Query(value = "update  accuratetagrelation set cid=?2 where cid = ?1", nativeQuery = true)
    public void updateCompanyid(String cid,String companyid);


	
}
