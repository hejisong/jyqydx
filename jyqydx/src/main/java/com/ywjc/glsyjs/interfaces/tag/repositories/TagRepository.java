package com.ywjc.glsyjs.interfaces.tag.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TagRepository extends BaseRepository<TagEntity, String> {


    @Query(value = "select t from TagEntity t where  t.tagname like ?1 ")
    public Page<TagEntity> queryPageBySearch(String search, Pageable pageable);

    @Modifying
    @Query(value = "update tag t set t.tagname = ?2 where t.tagid = ?1", nativeQuery = true)
    int update(String tagid, String tagname);

    @Modifying
    @Query(value = "delete from tag where tagid = ?1", nativeQuery = true)
    int deleteTag(Integer tagid);

    @Modifying
    @Query(value = "delete from tagrelation where tagid = ?1", nativeQuery = true)
    public  void deleteByTagid(String tagid);


    @Modifying
    @Query(value = "delete from tagrelation where tagid = ?1 and cid=?2 ", nativeQuery = true)
    public void deleteByCid(String tagid,String cid);


    @Query("select t from TagEntity t where t.state ='1' order by  t.tagid ")
    public List<TagEntity> findAll();



    @Query(value = "select t.* from tagrelation r left join tag t on r.tagId=t.tagid where r.cid=?1 order by r.tagId",nativeQuery = true)
    public List<TagEntity> findByCompanyid(String companyid);


    @Query(value = "select t.* from tagrelation r left join tag t on r.tagId=t.tagid where r.pid=?1 order by r.tagId",nativeQuery = true)
    public List<TagEntity> findByPid(String pid);

    @Query(value = "select t from TagEntity t where  t.tagid in(:tags)  ")
    List<TagEntity> queryTagsById(@Param("tags") List<String> tags);

    @Query(value = " select tb.tagid,tb.tagname from (select t.tagid,t.tagname from tagrelation  r left join tag t on r.tagid=t.tagid where r.pid in(select pldtailid from policydetail where plfidid =?1 ) )tb group by tb.tagid,tb.tagname ",nativeQuery = true)
    public List<Object> findtag(String plfidid);



    @Query(value = " select pldtailid from policydetail where plfidid in ?1  ",nativeQuery = true)
    public List<String> findpldtailids(String[] plfidids);

    @Query(value = " select tb.tagid,tb.tagname from (select t.tagid,t.tagname from tagrelation  r left join tag t on r.tagid=t.tagid where r.pid in (?1) )tb group by tb.tagid,tb.tagname ",nativeQuery = true)
    public List<Object> findtags( List<String> pldtailids);


    @Query(value = " select tb.tagid from (select t.tagid,t.tagname from tagrelation  r left join tag t on r.tagid=t.tagid where r.cid in (?1) )tb group by tb.tagid ",nativeQuery = true)
    public List<Object> findtagsByComoany(List<Object> companyids);


    @Query(value = " select DISTINCT p.plfidid from policyfile p left join policydetail d on p.plfidid=d.plfidid left join tagrelation r on r.pid=d.pldtailid where r.tagid=?1  ",nativeQuery = true)
    public List<String> findplfidid(String tagid);

    @Query(value = " select DISTINCT p.plfidid from policyfile p left join policydetail d on p.plfidid=d.plfidid left join tagrelation r on r.pid=d.pldtailid where r.tagid in ?1  ",nativeQuery = true)
    public List<String> findplfidids(String[] tagids);

    @Query(value = "select t from TagEntity t where  t.tagname=?1   ")
    public  TagEntity findTagByTagname(String tagname);







}
