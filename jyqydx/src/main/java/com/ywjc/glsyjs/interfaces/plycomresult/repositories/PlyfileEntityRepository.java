package com.ywjc.glsyjs.interfaces.plycomresult.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlyfileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PlyfileEntityRepository extends BaseRepository<PlyfileEntity, String> {


	@Query(value="select p.* from Plyfile p where p.pldtailid = ?1 order by p.sn+0 ",nativeQuery=true)
	public List<PlyfileEntity> findPlyfileEntityByPldtailid(String pldtailid);


	@Query(value="delete from  plyfile where pldtailid = ?1 ",nativeQuery=true)
	@Modifying
	public void deleteByPldtailid(String pldtailid);


}
