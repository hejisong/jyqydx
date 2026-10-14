package com.ywjc.glsyjs.sys.repositories;

import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PolicyFileEntityRepository extends PagingAndSortingRepository<PolicyFileEntity, String> {

    @Query("select p from PolicyFileEntity p where p.state <> '0' ")
    public List<PolicyFileEntity> findPolicyFileEntitiesByall();


    @Query(value="select p.* from PolicyFile p where p.state='2' order by p.serialnum+0 ",nativeQuery = true)
    public List<PolicyFileEntity> findPolicyFiles();


    @Query(value="select p.* from PolicyFile p where p.filecontent!='' and p.releasetime >= ?1 and p.filelevel like ?2 and (p.policyname like ?3 or p.filecontent like ?3) and p.state != '0' order by p.serialnum+0 ",nativeQuery = true)
    public List<PolicyFileEntity> findPolicyFilesByStartdate(Date startdate, String level,String content);

    @Query(value="select p.* from PolicyFile p where p.filecontent!='' and p.releasetime <= ?1 and p.filelevel like ?2 and (p.policyname like ?3 or p.filecontent like ?3) and p.state != '0' order by p.serialnum+0 ",nativeQuery = true)
    public List<PolicyFileEntity> findPolicyFilesByEnddate(Date enddate, String level,String content);

    @Query(value="select p.* from PolicyFile p where p.filecontent!='' and  p.filelevel like ?1 and (p.policyname like ?2 or p.filecontent like ?2) 　and p.state != '0' order by p.serialnum+0 ",nativeQuery = true)
    public List<PolicyFileEntity> findPolicyFilesByNodate( String level,String content);

    @Query(value="select p.* from PolicyFile p where p.filecontent!='' and p.releasetime >= ?1 and p.releasetime <= ?2 and p.filelevel like ?3 and (p.policyname like ?4 or p.filecontent like ?4) and p.state != '0' order by p.serialnum+0 ",nativeQuery = true)
    public List<PolicyFileEntity> findPolicyFilesBydate(Date startdate,Date enddate, String level,String content);


    @Query(value="select filetype,count(1) from PolicyFile  where  state != '0' and filecontent!='' group by filetype ",nativeQuery = true)
    public List<Object> findFiletypeSum();

    @Query(value="select p.plfidid,p.policyname,p.fwtitanic,p.fwunits,p.releasetime,p.filecontent from PolicyFile p where p.filetype = ?1 and p.state ='2' order by p.releasetime desc  ",nativeQuery = true)
    public List<Object> findPolicyFilesByFiletype(String filetype);

    @Query("select p.plfidid,p.policyname,p.fwtitanic,p.fwunits,p.releasetime,p.filecontent from PolicyFileEntity p where p.filetype = ?1 and p.state ='2' order by p.releasetime desc  ")
    public Page<Object> findPolicyFilesByFiletype(String filetype, Pageable pageable);


    @Query(value="select p.* from policyfile p LEFT JOIN tagrelation r on p.plfidid=r.pid where r.tagid=?1 and p.state <> '0' order by p.releasetime desc  ",nativeQuery = true)
    public List<PolicyFileEntity> findPolicyFilesByTag(String tagid);

    @Query(value="select p.* from PolicyFile p where p.PLFIDID in (:id) and p.state='2' order by p.serialnum+0 ",nativeQuery = true)
    List<PolicyFileEntity> queryPolicyFileById(@Param("id") List<String> queryPolicyfileByTag);

    @Query("select p from PolicyFileEntity p where p.policyname like ?1 and p.state <> '0' order by p.releasetime desc  ")
    public Page<PolicyFileEntity> findPolicyFilename(String policyname,Pageable pageable);

    @Query("select p from PolicyFileEntity p where p.filelevel like ?1 and p.state <> '0' order by p.releasetime desc  ")
    public List<PolicyFileEntity> findByFilelevel(String filetype);

    @Query("select p.plfidid from Policyfile p where p.state ='0'  ")
    public List<String> querdelete();

    @Query(value="select plfidid,policyname from policyfile where plfidid in (select DISTINCT plfidid from policydetail where  cjtype like '%免申即享%' ) and (state!='0' or state is null) order by serialnum+0 ",nativeQuery = true)
    public List<Object> findMSfiles();



    @Query(value="select plfidid,policyname from policyfile where plfidid in (select DISTINCT plfidid from policydetail where pldtailid in (select pid from accuratetagrelation where pid is not null )) and (state!='0' or state is null) order by serialnum+0 ",nativeQuery = true)
    public List<Object> findAccuratefiles();





}
