package com.ywjc.glsyjs.interfaces.tag.repositories;


import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface AccuratetagRepository extends PagingAndSortingRepository<Accuratetag, String> {


    @Query(value = "select t from Accuratetag t where  t.tagname like ?1 ")
    public Page<Accuratetag> findPageAndSearch( String tagname,Pageable pageable);


    @Query(value = "select t from Accuratetag t where  t.tagname = ?1 ")
    public Accuratetag findTagByTagname(String tagname);


    @Query(value = "select t.* from accuratetagrelation r left join accuratetag t on r.tagId=t.tagid where r.pid=?1 order by r.tagId",nativeQuery = true)
    public List<Accuratetag> findByPid(String pid);


    @Query(value = " select tb.tagid,tb.tagname from (select t.tagid,t.tagname from accuratetagrelation  r left join accuratetag t on r.tagid=t.tagid where r.pid in (?1) )tb group by tb.tagid,tb.tagname ",nativeQuery = true)
    public List<Object> findtags(String[] pids);


    @Query(value = "select r.cid,c.enterprisename from accuratetagrelation r left join  company c on r.cid=c.companyid where   r.tagid in (?1) and  r.cid = ?2 and (tagyear is null or (tagyear is not null and tagyear=?3 )) ", nativeQuery = true)
    public List<Object> findTagcount(String[] tagids,String cid,String year);


    @Query("select distinct t.tagid from Accuratetagrelation t where  t.pid =?1  ")
    public List<String> findTagidByPid(String pid);

    @Query("select distinct t.tagid from Accuratetagrelation t where  t.cid =?1  ")
    public List<String> findTagidByCid(String cid);


    @Query(value = "select distinct r.cid,c.enterprisename from accuratetagrelation r left join  company c on r.cid=c.companyid where   r.tagid in (?1) and  r.cid in (?2) ", nativeQuery = true)
    public List<Object> findcount(List<String> tagids,List<Object> companyids);

    @Query(value = "select distinct p.nplcategory,p.plsubclassname,p.plconditions,p.rdescription,p.ramount,p.minamount,p.maxamount from accuratetagrelation r left join  policydetail p on r.pid=p.pldtailid where   r.tagid in (?1) and  r.pid in (?2) ", nativeQuery = true)
    public List<Object> findPTagcount(List<String> tagids,String[] pids);
}
