package com.ywjc.glsyjs.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.common.entity.Uploadfile;

public interface UploadfileRepository extends PagingAndSortingRepository<Uploadfile, String> {
	
	public List<Uploadfile> findByIson(String ison);
	
	@Query(value="update uploadfile set ison = ?1 where id in ?2",nativeQuery=true)
	@Modifying
	public void updateFileIson(String ison,String[] ids);
	
	@Query(value="update uploadfile set ison = ?1 where id = ?2",nativeQuery=true)
	@Modifying
	public void updateFileIson(String ison,String ids);
	
	@Query(value="delete uploadfile where ison = '0'",nativeQuery=true)
	@Modifying
	public void deleteUnIsonFile();
	
	@Query(value="update uploadfile set ison = '0' where id not in(?1)",nativeQuery=true)
	@Modifying
	public void updateFileIson(String ids);

	@Query(value = "select u.* from uploadfile u inner join policyfile p on u.id = p.attachment where p.plfidid = ?1",nativeQuery = true)
	public Uploadfile findPolicyfile(String plfidid);

	@Query(value = "select u.* from uploadfile u inner join policyfile p where u.id is not null and  p.uploadfileid like CONCAT('%',u.id,'%')  and p.plfidid =?",nativeQuery = true)
	public List<Uploadfile> findPolicyfiles(String plfidid);

	@Query(value = "select u.* from uploadfile u inner join om_flow_process_asset o on u.id = o.assetid where o.assettype = ?1",nativeQuery = true)
	public List<Uploadfile> findFlowProcessAsset(String assettype);

	@Query(value = "select u.* from uploadfile u where u.fujianfileid = ?1 and u.plycomresultid =?2 ",nativeQuery = true)
	public List<Uploadfile> findUploadfilesbyfujianfileid(String fujianfileid,String plycomresultid);
}
