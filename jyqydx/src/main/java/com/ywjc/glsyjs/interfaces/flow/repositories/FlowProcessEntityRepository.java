package com.ywjc.glsyjs.interfaces.flow.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface FlowProcessEntityRepository extends BaseRepository<FlowProcessEntity, String> {


	@Query(value = "update om_flow_process set processstate = ?1 ,updateperid = ?2,updatepername = ?3,updatetime = now() where auditid = ?4 ",nativeQuery=true)
	@Modifying
	void updateFlowProcessEntity(String processstate, String updateperid,String updatepername,String auditid);

	@Query(value = "insert into om_flow_process(auditid,applyid,fid,flowname,processstate,createperid,createpername,createtime)VALUES(?1,?2,?3,?4,?5,?6,?7,now())",nativeQuery=true)
	@Modifying
	void insertFlowProcessEntity(String auditid, String applyid, String fid,String flowname,String processstate,String createperid,String createpername);

	@Query(value = "select f from FlowProcessEntity f where f.applyid = ?1 order by  f.fid  asc")
	public List<FlowProcessEntity> queryListByapplyid(String applyid);

	@Query(value = "select f from FlowProcessEntity f where f.applyid = ?1 and f.processstate=?2  order by  f.fid  asc ")
	public List<FlowProcessEntity> queryListByapplyidandstate(String applyid,String processstate);

	@Query(value = "select f from FlowProcessEntity f where f.applyid = ?1 and f.fid=?2")
	public List<FlowProcessEntity> queryListByapplyidandfid(String applyid,String fid);

	@Query(value = "select f from FlowProcessEntity f where f.applyid = ?1 and (f.fid=?2 or f.fid=?3) ")
	public List<FlowProcessEntity> queryListByapplyidandfid(String applyid,String fid1,String fid2);

	@Query(value = "select f from FlowProcessEntity f where f.applyid = ?1 and f.flowname=?2")
	public List<FlowProcessEntity> queryListByapplyidandflowname(String applyid,String flowname);

	@Query(value = "update om_flow_process set processstate = ?1 ,updatetime = now() where auditid = ?2 ",nativeQuery=true)
	@Modifying
	void updateProcessState(String processstate, String auditid);

	@Query(value = "update om_flow_process set processstate = ?1 ,createtime = now(),updatetime = now() where auditid = ?2 ",nativeQuery=true)
	@Modifying
	void updateNextProcessState(String processstate, String auditid);

	@Query(value = "select ofp.auditid from om_flow_process ofp where ofp.applyid = ?1 and ofp.fid = ?2 ", nativeQuery = true)
	public String queryAuditidByApplyid(String applyid,String fid);

	@Query(value = "insert into flowauditinfo(infoid,auditid,deptid,deptname,auditperid,auditpername,auditstate,auditamount,auditidea,audittime,flowstate,nextnode)VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12)",nativeQuery=true)
	@Modifying
	void insertFlowAuditInfo(String infoid, String auditid, String deptid, String deptname, String auditperid, String auditpername, String auditstate, String auditamount, String auditidea, Date audittime,String flowstate,String nextnode);

	@Query(value = "update om_flow_process set uploadfilename=?2,uploadfileid=?3,updatetime = now(),processstate=?4 where auditid = ?1 ",nativeQuery=true)
	@Modifying
	void updateCredential(String auditid, String uploadfilename, String uploadfileid,String processstate);
}
