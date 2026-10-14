package com.ywjc.glsyjs.interfaces.flow.service.impl;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.repositories.FlowProcessEntityRepository;
import com.ywjc.glsyjs.interfaces.flow.service.FlowEntityService;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.task.FlowCreatetask;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("flowProcessEntityServiceImpl")
public
class FlowProcessEntityServiceImpl extends BaseServiceImpl<FlowProcessEntity, String> implements
        FlowProcessEntityService {
    private static Logger log = Logger.getLogger(FlowProcessEntityServiceImpl.class);

    @Resource(name = "flowProcessEntityRepository")
    private FlowProcessEntityRepository flowProcessEntityRepository;

    @Resource(name = "flowEntityServiceImpl")
    private FlowEntityService flowEntityService;

    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;


    @Resource(name = "flowProcessEntityRepository")
    public void setReposity(PagingAndSortingRepository<FlowProcessEntity, String> flowProcessEntityRepository) {
        super.setReposity(flowProcessEntityRepository);
    }

    @Override
    public void saveFlowProcess(FlowProcessEntity flowProcessEntity) {
        save(flowProcessEntity);
    }

    @Override
    public List<FlowProcessEntity> queryListByapplyid(String applyid) {
        return flowProcessEntityRepository.queryListByapplyid(applyid);
    }

    @Override
    public FlowProcessEntity queryListByapplyidandstate(String applyid, String processstate) {
        List<FlowProcessEntity> list = flowProcessEntityRepository.queryListByapplyidandstate(applyid, processstate);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public FlowProcessEntity queryListByapplyidandfid(String applyid, String fid) {
        List<FlowProcessEntity> list = flowProcessEntityRepository.queryListByapplyidandfid(applyid, fid);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public FlowProcessEntity queryListByapplyidandfid(String applyid,String fid1,String fid2){
        List<FlowProcessEntity> list = flowProcessEntityRepository.queryListByapplyidandfid(applyid, fid1,fid2);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public FlowProcessEntity queryListByapplyidandflowname(String applyid, String flowname) {
        List<FlowProcessEntity> list = flowProcessEntityRepository.queryListByapplyidandflowname(applyid, flowname);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }


    //    初步创建所有匹配政策流程节点
    @Override
    public String createflowprocessbyapplyid(String applyid, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String userid = (String) session.getAttribute("userid");
        PlycomresultdetailEntity plydetail = plycomresultdetailEntityService.find(applyid);
        if (plydetail != null) {
            //思考修改 查询出所有流程节点一次创建（状态为为处理）当前节点为处理中；政策匹配节点为处理完成
            String type = "";
            if (StringUtils.isNotBlank(plydetail.getEnjoypolicycategory())) {
                if ("1".equals(plydetail.getEnjoypolicycategory())) {
                    type = "msjx";
                } else if ("2".equals(plydetail.getEnjoypolicycategory())) {
                    type = "bczl";
                }
            }
            List<FlowEntity> flowlist = flowEntityService.queryFlowListBytype(type);
            if (flowlist != null && flowlist.size() > 0) {
                for (FlowEntity flow : flowlist) {
                    FlowProcessEntity flowprocess = new FlowProcessEntity();
                    String id = UUID.randomUUID().toString();
                    id = id.replace("-", "");
                    flowprocess.setAuditid(id);
                    flowprocess.setApplyid(applyid);
                    flowprocess.setFid(flow.getId());
                    flowprocess.setFlowname(flow.getFlowname());
                    if ("1".equals(flow.getIsbegin())) {
                        flowprocess.setProcessstate("2");
                    } else {
                        if ("msjx".equals(type)) {
                            if ("奖励兑现".equals(flow.getFlowname())) {
                                flowprocess.setProcessstate("1");
                            } else {
                                flowprocess.setProcessstate("0");
                            }
                        } else {
                            if ("企业资料补录".equals(flow.getFlowname())) {
                                flowprocess.setProcessstate("1");
                            } else {
                                flowprocess.setProcessstate("0");
                            }
                        }
                    }
                    flowprocess.setCreateperid(userid);
                    flowprocess.setCreatepername(username);
                    flowprocess.setCreatetime(new Date());
                    flowprocess.setUpdateperid(userid);
                    flowprocess.setUpdatepername(username);
                    flowprocess.setUpdatetime(new Date());
                    saveFlowProcess(flowprocess);
                }
            }
        }
        return "success";
    }

    //提交当前节点（状态更新为处理完成）更新下一节点状态为处理中
    @Override
    public String updateflowprocess(String applyid, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String userid = (String) session.getAttribute("userid");
        //提交当前节点（状态更新为处理完成）更新下一节点状态为处理中
        PlycomresultdetailEntity plydetail = plycomresultdetailEntityService.find(applyid);
        if (plydetail != null) {
            //查询当前节点
            FlowProcessEntity flowprocessentity = this.queryListByapplyidandstate(applyid, "1");
            //结束当前节点
            if (flowprocessentity != null) {
                flowprocessentity.setProcessstate("2");
                flowprocessentity.setUpdateperid(userid);
                flowprocessentity.setUpdatepername(username);
                flowprocessentity.setUpdatetime(new Date());
                this.update(flowprocessentity);
                if (StringUtils.isNotBlank(flowprocessentity.getFid())) {
                    FlowEntity flow = flowEntityService.find(flowprocessentity.getFid());
                    //更新下一节点为处理中
                    if (flow != null && StringUtils.isNotBlank(flow.getNextid())) {
//                        FlowEntity newflow=flowEntityService.find(flow.getNextid());
                        FlowProcessEntity newfolw = this.queryListByapplyidandfid(applyid, flow.getNextid());
                        newfolw.setProcessstate("1");
                        newfolw.setUpdateperid(userid);
                        newfolw.setUpdatepername(username);
                        newfolw.setUpdatetime(new Date());
                        this.update(newfolw);
                    }
                }
            }
        }
        return "success";
    }


    @Override
    public String createflowprocessbyapplyidintask(String applyid) {
        try {
            String username = "admin";
            String userid = "4FF1241FC67C499E9BC316E7FB703AFD";
            PlycomresultdetailEntity plydetail = plycomresultdetailEntityService.find(applyid);
            if (plydetail != null) {
                //思考修改 查询出所有流程节点一次创建（状态为为处理）当前节点为处理中；政策匹配节点为处理完成
                String type = "";
                if (StringUtils.isNotBlank(plydetail.getEnjoypolicycategory())) {
                    if ("1".equals(plydetail.getEnjoypolicycategory())) {
                        type = "msjx";
                    } else if ("2".equals(plydetail.getEnjoypolicycategory())) {
                        type = "bczl";
                    }
                }
                List<FlowEntity> flowlist = flowEntityService.queryFlowListBytype(type);
                if (flowlist != null && flowlist.size() > 0) {
                    for (FlowEntity flow : flowlist) {
                        FlowProcessEntity flowprocess = new FlowProcessEntity();
                        String id = UUID.randomUUID().toString();
                        id = id.replace("-", "");
                        flowprocess.setAuditid(id);
                        flowprocess.setApplyid(applyid);
                        flowprocess.setFid(flow.getId());
                        flowprocess.setFlowname(flow.getFlowname());
                        if ("1".equals(flow.getIsbegin())) {
                            flowprocess.setProcessstate("2");
                        } else {
                            if ("msjx".equals(type)) {
                                if ("奖励兑现".equals(flow.getFlowname())) {
                                    flowprocess.setProcessstate("1");
                                } else {
                                    flowprocess.setProcessstate("0");
                                }
                            } else {
                                if ("企业资料补录".equals(flow.getFlowname())) {
                                    flowprocess.setProcessstate("1");
                                } else {
                                    flowprocess.setProcessstate("0");
                                }
                            }
                        }
                        flowprocess.setCreateperid(userid);
                        flowprocess.setCreatepername(username);
                        flowprocess.setCreatetime(new Date());
                        flowprocess.setUpdateperid(userid);
                        flowprocess.setUpdatepername(username);
                        flowprocess.setUpdatetime(new Date());
                        saveFlowProcess(flowprocess);
                    }
                }
                //免申即享生成流程的同事推送钉钉审核
                if ("1".equals(plydetail.getEnjoypolicycategory())) {
                    plycomresultdetailEntityService.checkToDingDing(plydetail, null);
                }

            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return e.getMessage();
        }

    }

    @Override
    public String createflowprocessbyapplyidintask(PlycomresultdetailEntity plydetail) {
        try {
            String username = "admin";
            String userid = "4FF1241FC67C499E9BC316E7FB703AFD";
            if (plydetail != null) {
                //思考修改 查询出所有流程节点一次创建（状态为为处理）当前节点为处理中；政策匹配节点为处理完成
                String type = "";
                if (StringUtils.isNotBlank(plydetail.getEnjoypolicycategory())) {
                    if ("1".equals(plydetail.getEnjoypolicycategory())) {
                        type = "msjx";
                    } else if ("2".equals(plydetail.getEnjoypolicycategory())) {
                        type = "bczl";
                    }
                }
                List<FlowEntity> flowlist = flowEntityService.queryFlowListBytype(type);
                if (flowlist != null && flowlist.size() > 0) {
                    for (FlowEntity flow : flowlist) {
                        FlowProcessEntity flowprocess = new FlowProcessEntity();
                        String id = UUID.randomUUID().toString();
                        id = id.replace("-", "");
                        flowprocess.setAuditid(id);
                        flowprocess.setApplyid(plydetail.getPlycomresultdetailid());
                        flowprocess.setFid(flow.getId());
                        flowprocess.setFlowname(flow.getFlowname());
                        if ("1".equals(flow.getIsbegin())) {
                            flowprocess.setProcessstate("2");
                        } else {
                            if ("msjx".equals(type)) {
                                if ("部门审批".equals(flow.getFlowname())) {
                                    flowprocess.setProcessstate("1");
                                } else {
                                    flowprocess.setProcessstate("0");
                                }
                            } else {
                                if ("企业资料补录".equals(flow.getFlowname())) {
                                    flowprocess.setProcessstate("1");
                                } else {
                                    flowprocess.setProcessstate("0");
                                }
                            }
                        }
                        flowprocess.setCreateperid(userid);
                        flowprocess.setCreatepername(username);
                        flowprocess.setCreatetime(new Date());
                        flowprocess.setUpdateperid(userid);
                        flowprocess.setUpdatepername(username);
                        flowprocess.setUpdatetime(new Date());
                        saveFlowProcess(flowprocess);
                    }
                }
                //免申即享生成流程的同事推送钉钉审核
                if ("1".equals(plydetail.getEnjoypolicycategory())) {
                    plycomresultdetailEntityService.checkToDingDing(plydetail, null);
                    plydetail.setCashflag("1");//提交审批
                    plydetail.setUpdatetime(new Date());
                    plycomresultdetailEntityService.update(plydetail);
                }

            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return e.getMessage();
        }

    }


    @Override
    public String queryAuditidByApplyid(String applyid, String fid) {
        return flowProcessEntityRepository.queryAuditidByApplyid(applyid, fid);
    }


    @Override
    public void updateProcessstate(String processstate, String auditid) {
        flowProcessEntityRepository.updateProcessState(processstate, auditid);
    }

    @Override
    public void updateNextProcessState(String processstate, String auditid) {
        flowProcessEntityRepository.updateNextProcessState(processstate, auditid);
    }


    @Override
    public void saveFlowAuditInfo(String infoid, String auditid, String deptid, String deptname, String auditperid, String auditpername, String auditstate, String auditamount, String auditidea, Date audittime,String flowstate,String nextnode) {
        flowProcessEntityRepository.insertFlowAuditInfo(infoid, auditid, deptid, deptname, auditperid, auditpername, auditstate, auditamount, auditidea, audittime,flowstate,nextnode);
    }

    @Override
    public void updateCredential(String auditid, String uploadfilename, String uploadfileid,String processstate) {
        flowProcessEntityRepository.updateCredential(auditid, uploadfilename,uploadfileid,processstate);
    }
}
