package com.ywjc.glsyjs.interfaces.plycomresult.repositories;

import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface PlycomresultDetailEntityRepository extends BaseRepository<PlycomresultdetailEntity, String> {


	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 and p.cashflag in  ('1','2','3','4','5','6','7') order by p.inputtime desc")
	public List<PlycomresultdetailEntity> findPlycomresultdetailByCompanyid(String companyid);

	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 and (p.cashflag is null or p.cashflag='' or p.cashflag in ('0','1','3','4','5','6','7')  ) order by p.inputtime desc")
	public List<PlycomresultdetailEntity> findDXPGByCompanyid(String companyid);

	@Query(value = "select u.* from uploadfile u inner join policyfile p on u.id = p.attachment where p.plfidid = ?1",nativeQuery = true)
	public Uploadfile findPolicyfile(String plfidid);

	/*@Query("select p from CompanyEntity p where p.companyid = ?1 and p.enjoypolicycategory = ?2")
	public List<PlycomresultdetailEntity> findPlycomresultdetailByEnjoypolicycategory(String companyid,String enjoypolicycategory);

	*/
	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 and p.enjoypolicycategory =?2")
	public List<PlycomresultdetailEntity> findPlycomresultByCompanyidandtype(String companyid,String enjoypolicycategory);

	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 and p.enjoypolicycategory =?2 and (p.cashflag is null or p.cashflag='' or p.cashflag in ('0','3','4') ) order by p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findPage(String companyid,String enjoypolicycategory,Pageable pageable);


	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 and p.enjoypolicycategory ='1' order by p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findPageMSJX(String companyid,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 and p.enjoypolicycategory =?2 and p.cashflag in ('1','2','3','4','5','6')  ")
	public Page<PlycomresultdetailEntity> findPageForWarn(String companyid,String enjoypolicycategory,Pageable pageable);

	@Query(value = "select p.ramount,p.pevaluationdepartment from policydetail p where  p.pldtailid = ?1",nativeQuery = true)
	public  Object[][] findPolicydetailBypldtailid(String pldtailid);

	@Query(value = "select p.companyid,p.enjoypolicycategory,count(enjoypolicycategory) as typecount from Plycomresultdetail p where p.companyid = ?1 and (p.cashflag is null or p.cashflag='' or p.cashflag in ('0','3','4') ) group by p.companyid,p.enjoypolicycategory",nativeQuery = true)
	public List<Object[]> findtypecount(String companyid);


	@Query(value = "select p.companyid,p.enjoypolicycategory,count(enjoypolicycategory) as typecount from Plycomresultdetail p where p.companyid = ?1  group by p.companyid,p.enjoypolicycategory",nativeQuery = true)
	public List<Object[]> findtypecount1(String companyid);

	@Query(value = "select p.companyid,p.enjoypolicycategory,count(enjoypolicycategory) as typecount from Plycomresultdetail p where p.companyid = ?1 and  p.cashflag in ('1','2','3','4','5','6')  group by p.companyid,p.enjoypolicycategory",nativeQuery = true)
	public List<Object[]> findtypecountwarn(String companyid);

	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 ")
	public List<PlycomresultdetailEntity> findwddxByCompanyid(String companyid);

	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 and p.cashflag in ('1','2','3','4','5','6','7') order by p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findwddxPage(String companyid,Pageable pageable);

	//没时间
	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag like ?2 and  p.cashflag is not null and p.cashflag <> '0' and p.cashflag <>'' and p.enjoypolicycategory in ('2') order by p.enterprisename desc nulls last ")
	public Page<PlycomresultdetailEntity> findPage1(String enterprisename, String cashflag,Pageable pageable);

	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag like ?2 order by p.updatetime desc ")
	public Page<PlycomresultdetailEntity> findPagenew1(String enterprisename, String cashflag,Pageable pageable);

	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag in  ('1','2','3','4','5','6','7') order by p.updatetime desc ")
	public Page<PlycomresultdetailEntity> findPagenewAll(String enterprisename,Pageable pageable);

	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag like ?2  and p.updatetime >=?3 and p.updatetime <=?4 order by p.updatetime desc  nulls last  ")
	public Page<PlycomresultdetailEntity> findPagenew2(String enterprisename, String cashflag, String begintime, String endtime,Pageable pageable);

	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag in  ('1','2','3','4','5','6') and p.updatetime >=?2 and p.updatetime <=?3 order by p.updatetime desc  nulls last  ")
	public Page<PlycomresultdetailEntity> findPagenew2All(String enterprisename,  String begintime, String endtime,Pageable pageable);


	//有时间
	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag like ?2 and p.cashtime >=?3 and p.cashtime <=?4 and p.enjoypolicycategory in ('2') order by p.enterprisename desc ")
	public Page<PlycomresultdetailEntity> findPage2(String enterprisename, String cashflag, String begintime, String endtime,Pageable pageable);

	//没时间
	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag like ?2  and p.enjoypolicycategory in ('1','2') order by p.enterprisename desc ")
	public Page<PlycomresultdetailEntity> findpaymentPage1(String enterprisename, String cashflag,Pageable pageable);


	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag in ('2','5','6') and p.enjoypolicycategory in ('1','2') order by p.enterprisename desc ")
	public Page<PlycomresultdetailEntity> findpaymentPage1all(String enterprisename,Pageable pageable);

	//有时间
	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag like ?2 and p.cashtime >=?3 and p.cashtime <=?4   and p.enjoypolicycategory in ('1','2') order by p.enterprisename desc ")
	public Page<PlycomresultdetailEntity> findpaymentPage2(String enterprisename, String cashflag, String begintime, String endtime,Pageable pageable);


	@Query(value = "select p from PlycomresultdetailEntity p where p.enterprisename like ?1 and p.cashflag in ('2','5','6') and p.cashtime >=?2 and p.cashtime <=?3   and p.enjoypolicycategory in ('1','2') order by p.enterprisename desc ")
	public Page<PlycomresultdetailEntity> findpaymentPage2All(String enterprisename, String begintime, String endtime,Pageable pageable);



	@Query("select p from PlycomresultdetailEntity p where p.isflow = ?1 ")
	public List<PlycomresultdetailEntity> findlistByIsflow(String isflow);

	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 and p.companypatentid = ?2 ")
	public PlycomresultdetailEntity findPlycomresultByCompany(String companyid,String companypatentid);

	@Query("select p from PlycomresultdetailEntity p where p.companyid = ?1 and p.pldtailid = ?2 and p.particularyear = ?3 ")
	public PlycomresultdetailEntity findPlycomresult(String companyid,String pldtailid,String particularyear);

	@Query(value = "select p.enterprisename,p.uscreditcode,p.policycategoryname,p.policyconditions,p.rdescription,p.amount,date_format(p.inputtime, '%Y-%m-%d'),CASE p.cashflag WHEN '0' THEN '已匹配'  WHEN '1' THEN '待审批'  WHEN '7' THEN '审批中'  WHEN '2' THEN '审批通过'  WHEN '3' THEN '已退回'  WHEN '4' THEN '审核不通过' WHEN '5' THEN '已支付'  ELSE '已上传凭证' END,p.policysubclassname from plycomresultdetail p where     p.enjoypolicycategory in ('1','2')  order by p.inputtime desc  ",nativeQuery=true)
	public List<Object[]> findinfoAll();


	@Query(value = "select p.enterprisename,p.uscreditcode,c.account,p.policycategoryname,p.policyconditions,p.rdescription,p.amount,date_format(p.inputtime, '%Y-%m-%d'),CASE p.cashflag   WHEN '2' THEN '审批通过'   WHEN '5' THEN '已支付'  ELSE '已上传凭证' END,p.policysubclassname from plycomresultdetail p  left join company c on p.companyid=c.companyid where   p.enterprisename like ?1  and p.cashtime >=?2 and p.cashtime <=?3     and p.cashflag in  ('2','5','6') and   p.enjoypolicycategory in ('1','2') order by p.inputtime desc ",nativeQuery=true)
	public List<Object[]> findpayinfoAll(String enterprisename, String begintime, String endtime);


	@Query(value = "select p.enterprisename,p.uscreditcode,c.account,p.policycategoryname,p.policyconditions,p.rdescription,p.amount,date_format(p.inputtime, '%Y-%m-%d'),CASE p.cashflag WHEN '0' THEN '已匹配'  WHEN '1' THEN '待审批'  WHEN '7' THEN '审批中'  WHEN '2' THEN '审批通过'  WHEN '3' THEN '已退回'  WHEN '4' THEN '审核不通过' WHEN '5' THEN '已支付'  ELSE '已上传凭证' END,p.policysubclassname from plycomresultdetail p  left join company c on p.companyid=c.companyid where   p.enjoypolicycategory in ('1','2')  order by p.inputtime desc ",nativeQuery=true)
	public List<Object[]> findonfileinfoAll();


	@Query(value = "update plycomresultdetail set cashflag = ?1 ,updatetime = now() where plycomresultdetailid = ?2 ",nativeQuery=true)
	@Modifying
	void updateCashflag(String cashflag, String plycomresultdetailid);

	@Query(value = "select count(*) from  plycomresultdetail where cashflag = '5'  ",nativeQuery=true)
	public String counttotal();




	@Query(value = "select sum(amount) from plycomresultdetail where cashflag='5'  ",nativeQuery=true)
	public String paytotal();

	@Query(value = "select sum(amount) from plycomresultdetail where cashflag in('2','6')  ",nativeQuery=true)
	public String notpaytotal();


	@Query(value = "select  CompanyID,enterprisename,sum(amount) from plycomresultdetail where cashflag ='5' group by CompanyID  order by sum(amount) desc LIMIT  6 ",nativeQuery=true)
	public List<Object> paycorps();

	@Query(value = "select  CompanyID,enterprisename,count(1) from plycomresultdetail where cashflag ='5' group by CompanyID  order by count(1) desc LIMIT  6 ",nativeQuery=true)
	public List<Object> policycorpscount();


	@Query(value = "select  enjoypolicycategory,sum(amount) from plycomresultdetail where cashflag in ('2','5','6') group by enjoypolicycategory  order by sum(amount) desc ",nativeQuery=true)
	public List<Object> policyTypecount();

	@Query(value = "select  cashflag,count(1) from plycomresultdetail where  enjoypolicycategory in ('1','2')  group by cashflag ",nativeQuery=true)
	public List<Object> policycashflagcount();

	@Query(value = "select d.feaunit,count(1) from plycomresultdetail p LEFT JOIN policydetail d on p.pldtailid=d.pldtailid where cashflag in ('2','5','6') and  particularyear=?1 group by d.feaunit ",nativeQuery=true)
	public List<Object> policyOrgcount(String particularyear);


	@Query(value = "select d.feaunit,sum(amount) from plycomresultdetail p LEFT JOIN policydetail d on p.pldtailid=d.pldtailid where cashflag='5' group by d.feaunit  ",nativeQuery=true)
	public List<Object> payOrgsum();

	@Query(value = "select d.policyname,sum(amount) from plycomresultdetail p LEFT JOIN policyfile d on p.plfidid=d.plfidid where cashflag='5' group by d.policyname order by sum(amount) limit 5  ",nativeQuery=true)
	public List<Object> payFileum();


	@Query("select p from PlycomresultdetailEntity p where p.pldtailid in ?1 and p.companyid in ?2 and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx(String[] pldtailids, String[] companyids,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where p.pldtailid in ?1 and p.companyid in ?2 and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx11(List<String> pldtailids1, List<String> companyids1,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where p.pldtailid in ?1 and (p.companyid in (?2)  or p.companyid in (?3)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx12(List<String> pldtailids1, List<String> companyids1, List<String> companyids2,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where p.pldtailid in ?1 and (p.companyid in (?2)  or p.companyid in (?3) or p.companyid in (?4)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx13(List<String> pldtailids1, List<String> companyids1, List<String> companyids2,List<String> companyids3,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where p.pldtailid in ?1 and (p.companyid in (?2)  or p.companyid in (?3) or p.companyid in (?4)  or p.companyid in (?5)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx14(List<String> pldtailids1, List<String> companyids1, List<String> companyids2,List<String> companyids3,List<String> companyids4,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where p.pldtailid in ?1 and (p.companyid in (?2)  or p.companyid in (?3) or p.companyid in (?4)  or p.companyid in (?5) or p.companyid in (?6)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx15(List<String> pldtailids1, List<String> companyids1, List<String> companyids2,List<String> companyids3,List<String> companyids4,List<String> companyids5,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where p.pldtailid in ?1 and (p.companyid in (?2)  or p.companyid in (?3) or p.companyid in (?4)  or p.companyid in (?5) or p.companyid in (?6) or p.companyid in (?7)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx16(List<String> pldtailids1, List<String> companyids1, List<String> companyids2,List<String> companyids3,List<String> companyids4,List<String> companyids5,List<String> companyids6,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where p.pldtailid in ?1 and (p.companyid in (?2)  or p.companyid in (?3) or p.companyid in (?4)  or p.companyid in (?5) or p.companyid in (?6) or p.companyid in (?7) or p.companyid in (?8)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx17(List<String> pldtailids1, List<String> companyids1, List<String> companyids2,List<String> companyids3,List<String> companyids4,List<String> companyids5,List<String> companyids6,List<String> companyids7,Pageable pageable);


	@Query("select p from PlycomresultdetailEntity p where (p.pldtailid in ?1 or p.pldtailid in (?2)) and p.companyid in (?3) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx21(List<String> pldtailids1,List<String> pldtailids2, List<String> companyids1,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where (p.pldtailid in ?1 or p.pldtailid in (?2)) and (p.companyid in (?3)  or p.companyid in (?4)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx22(List<String> pldtailids1,List<String> pldtailids2, List<String> companyids1, List<String> companyids2,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where (p.pldtailid in ?1 or p.pldtailid in (?2)) and (p.companyid in (?3)  or p.companyid in (?4) or p.companyid in (?5)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx23(List<String> pldtailids1,List<String> pldtailids2, List<String> companyids1, List<String> companyids2,List<String> companyids3,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where (p.pldtailid in ?1 or p.pldtailid in (?2)) and (p.companyid in (?3)  or p.companyid in (?4) or p.companyid in (?5)  or p.companyid in (?6)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx24(List<String> pldtailids1,List<String> pldtailids2, List<String> companyids1, List<String> companyids2,List<String> companyids3,List<String> companyids4,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where (p.pldtailid in ?1 or p.pldtailid in (?2)) and (p.companyid in (?3)  or p.companyid in (?4) or p.companyid in (?5)  or p.companyid in (?6) or p.companyid in (?7)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx25(List<String> pldtailids1,List<String> pldtailids2, List<String> companyids1, List<String> companyids2,List<String> companyids3,List<String> companyids4,List<String> companyids5,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where (p.pldtailid in ?1 or p.pldtailid in (?2)) and (p.companyid in (?3)  or p.companyid in (?4) or p.companyid in (?5)  or p.companyid in (?6) or p.companyid in (?7) or p.companyid in (?8)) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx26(List<String> pldtailids1, List<String> pldtailids2,List<String> companyids1, List<String> companyids2,List<String> companyids3,List<String> companyids4,List<String> companyids5,List<String> companyids6,Pageable pageable);

	@Query("select p from PlycomresultdetailEntity p where (p.pldtailid in ?1 or p.pldtailid in (?2)) and (p.companyid in (?3)  or p.companyid in (?4) or p.companyid in (?5)  or p.companyid in (?6) or p.companyid in (?7) or p.companyid in (?8) or p.companyid in ?9) and p.enjoypolicycategory='1'  order by  p.inputtime desc ")
	public Page<PlycomresultdetailEntity> findinfoMsjx27(List<String> pldtailids1,List<String> pldtailids2, List<String> companyids1, List<String> companyids2,List<String> companyids3,List<String> companyids4,List<String> companyids5,List<String> companyids6,List<String> companyids7,Pageable pageable);

}
