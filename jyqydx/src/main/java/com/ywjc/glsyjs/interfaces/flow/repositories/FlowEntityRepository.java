package com.ywjc.glsyjs.interfaces.flow.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.PhonerodEntity;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FlowEntityRepository extends BaseRepository<FlowEntity, String> {


	@Query(value = "select f.* from om_flow f where f.type = ?1 and f.isbegin = '1'",nativeQuery=true)
	public FlowEntity queryBeginFlowByType(String type);

	@Query(value = "select f.* from om_flow f where f.id = ?1",nativeQuery=true)
	public FlowEntity queryCurrentFlowById(int id);

	@Query(value = "select f.* from om_flow f where f.id = ?1",nativeQuery=true)
	public FlowEntity queryNextFlowByNextid(int nextid);

	@Query(value = "select c.* from company c where c.enterprisename like ?1 or c.uscreditcode like ?1",nativeQuery=true)
	public List<CompanyEntity> queryCompanyListBySearchName(String searchName);

	@Query(value = "select f.* from om_flow f where f.type = ?1",nativeQuery=true)
	public List<FlowEntity> queryFlowListBytype(String type);

	@Query(value = "select f.* from om_flow f where f.type = ?1 and f.flowname =?2",nativeQuery=true)
	public FlowEntity queryFlowBytypeandname(String type,String flowname);

}
