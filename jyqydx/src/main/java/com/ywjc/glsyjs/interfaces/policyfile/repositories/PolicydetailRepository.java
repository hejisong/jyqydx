package com.ywjc.glsyjs.interfaces.policyfile.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PolicydetailRepository extends BaseRepository<PolicydetailEntity, String> {

    @Query(value="select * from policydetail d where d.ISDEFINE='确定' and d.CJTYPE !=''  (d.state is null  or d.state='' or d.state<>'2' )  ",nativeQuery = true)
    public List<PolicydetailEntity> findlistbyname();


    @Query(value="select p.* from policydetail p where p.plfidid = ?1 and (p.state is null  or p.state='' or p.state<>'2' )   order by p.serialnum asc ,p.nplcategory  ,p.plsubclassname ",nativeQuery = true)
    public List<PolicydetailEntity> findPolicyFiles(String plfidid);

    @Query(value="select p.nplcategory,p.plsubclassname,p.plconditions,p.rdescription,p.ramount,p.thpath,p.pevaluationdepartment,p.feaunit,p.cjtype,p.declareconditions from policydetail p where p.plfidid = ?1 and (p.state is null  or p.state='' or p.state<>'2' )   order by p.serialnum asc ,p.nplcategory  ,p.plsubclassname ",nativeQuery = true)
    public List<Object[]> findDetails(String plfidid);


    @Query(value="select DISTINCT d.* from policydetail d  left join tagrelation r on r.pid=d.pldtailid where d.plfidid =?1 and r.tagid=?2 and (d.state is null  or d.state='' or d.state<>'2' )  order by d.serialnum asc,d.nplcategory  ,d.plsubclassname  ",nativeQuery = true)
    public List<PolicydetailEntity> findPolicyFilesTag(String plfidid,String tagid);

    @Query(value="select p.* from policydetail p where p.plfidid in ?1 and (p.state is null  or p.state='' or p.state<>'2' )   order by p.serialnum asc ,p.nplcategory  ,p.plsubclassname ",nativeQuery = true)
    public List<PolicydetailEntity> findPolicyDetails(String[] plfidid);

    @Query(value="select p.* from policydetail p where p.pldtailid in ?1 and (p.state is null  or p.state='' or p.state<>'2' )   order by p.serialnum asc ,p.nplcategory  ,p.plsubclassname ",nativeQuery = true)
    public List<PolicydetailEntity> findBypldtailids(String[] pldtailids);


    @Query(value="select DISTINCT d.* from policydetail d  left join tagrelation r on r.pid=d.pldtailid where d.plfidid =?1 and r.tagid in ?2 and (d.state is null  or d.state='' or d.state<>'2' )  order by d.serialnum asc,d.nplcategory  ,d.plsubclassname  ",nativeQuery = true)
    public List<PolicydetailEntity> findPolicyDetailTag(String plfidid,String[] tagid);



    @Query("select p from PolicydetailEntity p where p.plfidid = ?1 and (p.state is null  or p.state='' or p.state<>'2' )   order by p.serialnum asc ,p.nplcategory  ,p.plsubclassname ")
    public Page<PolicydetailEntity> findPolicyFilesPage(String plfidid, Pageable pageable);

    @Query("select p from PolicydetailEntity p where p.plfidid = ?1   order by p.serialnum asc ,p.nplcategory  ,p.plsubclassname ")
    public Page<PolicydetailEntity> findPolicyFilesPageAll(String plfidid, Pageable pageable);

    @Query("select p from PolicydetailEntity p where p.plfidid = ?1 and p.nplcategory=?2 and (p.state is null  or p.state='' or p.state<>'2' )  order by p.serialnum asc ,p.nplcategory  ,p.plsubclassname ")
    public List<PolicydetailEntity> findPolicydetails(String plfidid,String nplcategory);

    @Query("select p from PolicydetailEntity p where p.plfidid = ?1 and p.nplcategory like ?2 and (p.state is null  or p.state='' or p.state<>'2' )  order by p.serialnum asc,p.nplcategory  ,p.plsubclassname  ")
    public Page<PolicydetailEntity> findPolicyFile(String plfidid, String policycategory, Pageable pageable);

    @Query("select p from PolicydetailEntity p where p.plfidid = ?1 and (p.state is null  or p.state='' or p.state<>'2' )   order by p.serialnum asc ,p.nplcategory  ,p.plsubclassname ")
    public Page<PolicydetailEntity> findPolicyFile(String plfidid, Pageable pageable);


    @Query("select p from PolicydetailEntity p where p.nplcategory like ?1 and p.plfidid not in ?2 and (p.state is null  or p.state='' or p.state<>'2' )  order by p.serialnum asc,p.nplcategory  ,p.plsubclassname  ")
    public Page<PolicydetailEntity> findPolicyFilenotdelete(String policycategory, List<String> plfidids, Pageable pageable);

    @Query("select p from PolicydetailEntity p where p.nplcategory like ?1 and p.plfidid not in ?2  and (p.state is null  or p.state='' or p.state<>'2' ) order by p.serialnum asc,p.nplcategory  ,p.plsubclassname  ")
    public Page<PolicydetailEntity> findPolicyFileAll(String policycategory, Pageable pageable);

    @Query("select p from PolicydetailEntity p where p.plfidid not in ?1 and (p.state is null  or p.state='' or p.state<>'2' )  order by p.serialnum asc,p.nplcategory  ,p.plsubclassname  ")
    public Page<PolicydetailEntity> findPolicyFilenotdelete( List<String> plfidids,Pageable pageable);

    @Query("select p from PolicydetailEntity p where  (p.state is null  or p.state='' or p.state<>'2' )  order by p.serialnum asc,p.nplcategory  ,p.plsubclassname  ")
    public Page<PolicydetailEntity> findPolicyFileAll( Pageable pageable);

    @Query(value="select max(serialnum) from policydetail  where plfidid = ?1 ",nativeQuery = true)
    public String findMax(String plfidid);


    @Query(value="select p.* from policydetail p LEFT JOIN tagrelation r on p.pldtailid=r.pid where r.tagid=?1 and (p.state is null  or p.state='' or p.state<>'2' )  order by p.serialnum,p.nplcategory  ,p.plsubclassname  ",nativeQuery = true)
    public List<PolicydetailEntity> findPolicyFilesByTag(String tagid);

    @Query(value="select p.* from policydetail p where p.plfidid = ?1 and p.NPLCATEGORY=?2 and (p.state is null  or p.state='' or p.state<>'2' )  order by p.serialnum asc,p.nplcategory  ,p.plsubclassname  ",nativeQuery = true)
    List<PolicydetailEntity> findPolicyFilesByCategory(String plfidid, String plcategoryno);
    @Query(value="select p.* from policydetail p where p.plfidid = ?1 and p.PLSUBCLASSNAME=?2 and (p.state is null  or p.state='' or p.state<>'2' )   order by p.serialnum asc,p.nplcategory  ,p.plsubclassname  ",nativeQuery = true)
    List<PolicydetailEntity> findPolicyFilesBySubclass(String plfidid, String plsubclassnum);
    @Query(value="select p.* from policydetail p where p.plfidid = ?1 and p.NPLCATEGORY=?2 and  p.PLSUBCLASSNAME=?3 and (p.state is null  or p.state='' or p.state<>'2' )   order by p.serialnum asc,p.nplcategory  ,p.plsubclassname  ",nativeQuery = true)
    List<PolicydetailEntity> findPolicyFiles(String plfidid,String plcategoryno, String plsubclassnum);

    @Query(value="select p.NPLCATEGORY,p.PLSUBCLASSNAME from policydetail p where (p.state is null  or p.state='' or p.state<>'2' )  group by NPLCATEGORY,PLSUBCLASSNAME  ",nativeQuery = true)
    List<Object[]> findAllCategory();


    @Query(value="select distinct p.plfidid from policydetail p where p.pldtailid in (?1) and (p.state is null  or p.state='' or p.state<>'2' )  order by p.serialnum,p.nplcategory  ,p.plsubclassname  ",nativeQuery = true)
    public List<String> queryplfididsBypldtailid( List<String> pldtailids);




    @Query(value = " select cjtype,count(cjtype) from policydetail where plfidid=?  group by cjtype ",nativeQuery = true)
    public List<Object[]> findtypecountall(String plfidid);


    @Query(value = " select * from policydetail where  cjtype like '%免申即享%' and (state is null  or state='' or state in('0','1')) order by plfidid,serialnum ",nativeQuery = true)
    public List<PolicydetailEntity> findMsjx();

    @Query(value = " select * from policydetail where plfidid=?1 and  cjtype like '%免申即享%' and (state is null  or state='' or state in('0','1')) order by serialnum ",nativeQuery = true)
    public List<PolicydetailEntity> findMSfilesByplfidid(String plfidid);



    @Query(value = " select * from policydetail where pldtailid in (select pid from accuratetagrelation where pid is not null ) and (state is null  or state='' or state in('0','1')) order by plfidid, serialnum ",nativeQuery = true)
    public List<PolicydetailEntity> findAccuratePolicydetail();

    @Query(value = " select * from policydetail where pldtailid in (select pid from accuratetagrelation where pid is not null ) and plfidid=?1 and (state is null  or state='' or state in('0','1')) order by plfidid, serialnum ",nativeQuery = true)
    public List<PolicydetailEntity> findAccuratePolicydetailByplfidid(String plfidid);



}
