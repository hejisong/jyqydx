package com.ywjc.glsyjs.interfaces.policyfile.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.policyfile.entity.Policyfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PolicyfileRepository extends BaseRepository<Policyfile, String> {

	@Query(value = "select p.* from policyfile p where  p.state='2'  and  if(?4 !='',p.FILELEVEL = ?4,1=1)  and  if(?1 !='',p.policyname LIKE ?1 or p.filecontent like ?1,1=1 ) and if(?2 !='',p.releasetime >= ?2,1=1) and if(?3 !='',p.releasetime <= ?3,1=1) and  if(?5 !='',p.FILETYPE = ?5,1=1) ORDER BY p.serialnum+0 asc",nativeQuery=true)
	public List<Policyfile> queryPolicyfileList(String policyname,String beginDate,String endDate,String fileLevel,String fileType);


	@Query("select p from Policyfile p where p.policyname like ?1  and p.filecontent like ?2  and p.filelevel like ?3 and p.state like ?4 and (p.state <> '0' or p.state is null) order by p.releasetime desc ")
	public Page<Policyfile> queryPageinfo(String policyname, String content,  String filelevel,String state, Pageable pageable);




	@Query(value =" select * from  policyfile p where p.plfidid in( select DISTINCT d.plfidid from policydetail d left join tagrelation r on r.pid=d.pldtailid where r.tagid= ?1 ) and (p.state !=  '0' or p.state is null)  order by p.serialnum+0 ", nativeQuery=true)
	public List<Policyfile> queryPolicyfileBytag(String tagid);


	@Query(value =" select count(*) from  policyfile  where (state !=  '0' or state is null) and filecontent is not null  ", nativeQuery=true)
	public String counttotal();

	@Query(value =" select * from  policyfile  where (state !=  '0' or state is null) and filecontent is not null  order by releasetime desc LIMIT  2  ", nativeQuery=true)
	public  List<Policyfile> totalAllTwo();

	@Query(value =" select * from  policyfile  where (state !=  '0' or state is null)  and filecontent is not null order by releasetime desc   ", nativeQuery=true)
	public  List<Policyfile> totalAll();


	@Query(value =" select max(serialnum) from  policyfile  where (state !=  '0' or state is null)   ", nativeQuery=true)
	public String findMaxNum();


	@Query(value =" select p.policyname,p.fwunits,p.fwtitanic,p.releasetime,date_format(p.DISPATCHTIME, '%Y-%m-%d'),CASE p.filelevel WHEN '1' THEN '市级政策'  ELSE '区级政策' END,CASE p.state when '1' THEN '已录入' WHEN '2' THEN  '已发布' ELSE '撤销发布' END  ,p.plfidid from  policyfile p  where p.policyname like ?1  and p.filecontent like ?2  and p.filelevel like ?3  and (p.state <> '0' or p.state is null) order by p.releasetime desc   ", nativeQuery=true)
	public List<Object[]> queryPolicyfileAll(String policyname, String content,  String filelevel);

	@Query(value =" select p.policyname,p.fwunits,p.fwtitanic,p.releasetime,date_format(p.DISPATCHTIME, '%Y-%m-%d'),CASE p.filelevel WHEN '1' THEN '市级政策'  ELSE '区级政策' END,CASE p.state when '1' THEN '已录入' WHEN '2' THEN  '已发布' ELSE '撤销发布' END  ,p.plfidid from  policyfile p  where p.policyname like ?1  and p.filecontent like ?2  and p.filelevel like ?3  and p.state = ?4 order by p.releasetime desc   ", nativeQuery=true)
	public List<Object[]> queryPolicyfileAllState(String policyname, String content,  String filelevel,String state);

}
