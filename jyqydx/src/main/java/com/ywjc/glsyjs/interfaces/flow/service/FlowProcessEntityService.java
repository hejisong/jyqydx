package com.ywjc.glsyjs.interfaces.flow.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public interface FlowProcessEntityService extends BaseService<FlowProcessEntity, String> {

    public void saveFlowProcess(FlowProcessEntity flowProcessEntity);

    public List<FlowProcessEntity> queryListByapplyid(String applyid);

    public FlowProcessEntity queryListByapplyidandstate(String type,String processstate);

    public FlowProcessEntity queryListByapplyidandfid(String applyid,String fid);

    public FlowProcessEntity queryListByapplyidandfid(String applyid,String fid1,String fid2);

    //    初步创建所有匹配政策流程节点
    public String createflowprocessbyapplyid(String applyid, HttpSession session);
    //提交当前节点（状态更新为处理完成）更新下一节点状态为处理中
    public String updateflowprocess(String applyid, HttpSession session);
    //定时任务调用（不传session）
    public String createflowprocessbyapplyidintask(String applyid);

    public String createflowprocessbyapplyidintask(PlycomresultdetailEntity plycomresultdetail);
    //
    public FlowProcessEntity queryListByapplyidandflowname(String applyid, String flowname);

    public String queryAuditidByApplyid(String applyid, String fid);

    public void updateProcessstate(String processstate,String auditid);

    public void updateNextProcessState(String processstate,String auditid);

    public void saveFlowAuditInfo(String infoid, String auditid, String deptid, String deptname, String auditperid, String auditpername, String auditstate, String auditamount, String auditidea, Date audittime,String flowstate,String nextnode);

    void updateCredential(String auditid, String uploadfilename, String uploadfileid,String processstate);
}
