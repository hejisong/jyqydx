package com.ywjc.glsyjs.interfaces.tag.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.tag.entity.TagRelationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TagRelationRepository extends BaseRepository<TagRelationEntity, String> {


    @Query(value = "SELECT tr.*,t.tagname,c.enterprisename,p.policyname FROM TagRelation tr " +
            "LEFT JOIN Tag t ON tr.tagid=t.tagid " +
            "LEFT JOIN Company c ON tr.cid=c.companyid " +
            "LEFT JOIN PolicyFile p ON tr.pid=p.plfidid " +
            "WHERE t.tagname LIKE ?1 OR " +
            "c.enterprisename LIKE ?1 OR " +
            "p.policyname LIKE ?1 limit ?2,?3 ", nativeQuery = true)
    public List<Object[]> queryPageBySearch(String search, int offset, int pageSize);

    @Modifying
    @Query(value = "update TagRelation tr set tr.tagid = ?1,tr.cid = ?2,tr.pid = ?3 where tr.id = ?4", nativeQuery = true)
    int update(String tagid, String cid, String pid, String id);


    @Query(value = "delete from tagrelation where id = ?1", nativeQuery = true)
    @Modifying
    int deleteTagRelation(String id);

    @Query(value = "select tr.* from tagrelation tr where (tr.tagid is null or tr.tagid=?1) and (tr.pid is null or tr.pid=?2) and (tr.cid is null or tr.cid=?3) limit 1 ", nativeQuery = true)
    TagRelationEntity queryOne(String tagid, String pid, String cid);


    @Query(value = "select tr.* from tagrelation tr where   tr.tagid=?1 and tr.pid=?2 ", nativeQuery = true)
    TagRelationEntity queryByPid(String tagid, String pid);


    @Query(value = "select tr.* from tagrelation tr where   tr.tagid=?1 and tr.cid=?2 ", nativeQuery = true)
    TagRelationEntity queryByCid(String tagid, String cid);

    @Query(value = "SELECT count(0) FROM TagRelation tr " +
            "LEFT JOIN Tag t ON tr.tagid=t.tagid " +
            "LEFT JOIN Company c ON tr.cid=c.companyid " +
            "LEFT JOIN PolicyFile p ON tr.pid=p.plfidid " +
            "WHERE t.tagname LIKE ?1 OR " +
            "c.enterprisename LIKE ?1 OR " +
            "p.policyname LIKE ?1 ", nativeQuery = true)
    int queryPageBySearchTotal(String search);

    @Query(value = "SELECT tr.pid FROM TagRelationEntity tr WHERE   tr.tagid in (:tags)   ")
    List<String> queryPolicyfileByTag(@Param("tags") List<String> tags);

    @Query(value = "SELECT tr.*,t.tagname,c.enterprisename,p.policyname,p.filecontent FROM TagRelation tr LEFT JOIN Tag t ON tr.tagid=t.tagid LEFT JOIN Company c ON tr.cid=c.companyid LEFT JOIN PolicyFile p ON tr.pid=p.plfidid WHERE (t.tagname LIKE ?4 OR c.enterprisename LIKE ?4 OR p.policyname LIKE ?4) AND ((tr.tagid=?1 or tr.tagid is null) AND  (tr.pid=?2 or tr.pid is not null) AND  (tr.cid=?3 or tr.cid is null) )order by createTime limit ?5,?6 ", nativeQuery = true)
    List<Object[]> queryMany(String tagid, String pid, String cid, String search, int offset, int pageSize);

    @Query(value = "SELECT tr.tagid FROM TagRelationEntity tr WHERE   (tr.cid=?1 ) group by tr.tagid")
    List<String> queryTagByCompany(String cid);

    @Query(value = "SELECT t.tagid FROM TagEntity t  ")
    List<String> queryAllTag();


    @Query("select distinct t.tagid from TagRelationEntity t where  t.pid =?1  ")
    public List<String> findTagidByPid(String pid);


    @Query("select distinct t.tagid from TagRelationEntity t where  t.cid =?1  ")
    public List<String> findTagidByCid(String cid);

    @Query("select t.tagid from TagRelationEntity t where   t.tagid=?1 and  t.cid =?2 ")
    public List<Object> findtag(String tagid,String cid);


    @Query(value = "select * from tagrelation  where   tagid in (?1) and  cid in (?2) ", nativeQuery = true)
    public List<Object> findcount1(List<String> tagids,List<Object> companyids);


    @Query(value = "select distinct r.cid,c.enterprisename from tagrelation r left join  company c on r.cid=c.companyid where   r.tagid in (?1) and  r.cid in (?2) ", nativeQuery = true)
    public List<Object> findcount(List<String> tagids,List<Object> companyids);


    @Query(value = "select r.cid,c.enterprisename from tagrelation r left join  company c on r.cid=c.companyid where   r.tagid in (?1) and  r.cid = ?2 ", nativeQuery = true)
    public List<Object> findTagcount(String[] tagids,String cid);

}
