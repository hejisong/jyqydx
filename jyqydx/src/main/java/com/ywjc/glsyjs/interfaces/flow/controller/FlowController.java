package com.ywjc.glsyjs.interfaces.flow.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.Flowauditinfo;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
import com.ywjc.glsyjs.interfaces.flow.service.FlowauditinfoService;
import com.ywjc.glsyjs.interfaces.oauth.controller.OauthController;
import com.ywjc.glsyjs.interfaces.oauth.entity.OauthEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.utils.CommonUtil;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller("flowController")
@RequestMapping("/interface/flow")
@CrossOrigin
public class FlowController extends BaseController {

    /**
     * 系统日志工具
     */
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;

    @Autowired
    private OauthController oauthController;

    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;

    @Resource(name = "uploadfileServiceImpl")
    private UploadfileService uploadfileService;

    @Resource(name = "flowauditinfoServiceImpl")
    private FlowauditinfoService flowauditinfoService;

    @ResponseBody
    @RequestMapping(value = "/process/{access_token}", method = RequestMethod.POST)
    public MessageUtil insertOmprocess(@PathVariable String access_token, @RequestBody String objects) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        messageUtil = oauthController.checkAccesstoken(access_token);
        try {
            List<FlowProcessEntity> flowProcessEntityList = JSONObject.parseArray(objects, FlowProcessEntity.class);
            for (FlowProcessEntity flowProcessEntity : flowProcessEntityList) {
                String applyid=flowProcessEntity.getApplyid();
                String deptid=flowProcessEntity.getDeptid();
                String deptname=flowProcessEntity.getDeptname();
                String auditperid=flowProcessEntity.getAuditperid();
                String auditpername=flowProcessEntity.getAuditpername();
                String state=flowProcessEntity.getState();
                String auditidea=flowProcessEntity.getAuditidea();
                String nextnode=flowProcessEntity.getNextnode();
                String sjspje=flowProcessEntity.getSJSPJE()+"";
                String flowstate=flowProcessEntity.getFlowstate();
                SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
                Date audittime=flowProcessEntity.getAudittime();
                FlowProcessEntity flowProcess=flowProcessEntityService.queryListByapplyidandfid(flowProcessEntity.getApplyid(),"6","1");
                String auditid=flowProcess.getAuditid();
                PlycomresultdetailEntity plycomresultdetail=plycomresultdetailEntityService.find(applyid);
                // 1-同意 2/3 不同意 驳回
                if ("1".equals(state)) {
                    if("结束".equals(nextnode)){//2处理完成，1处理中
                        flowProcess.setProcessstate("2");
                        flowProcess.setUpdatetime(audittime);
                        //更新兑现结果表plycomresultdetail CASHFLAG 标识
                        if(plycomresultdetail!=null){
                            plycomresultdetail.setCashflag("2");//审批通过
                            plycomresultdetail.setAmount(sjspje);

                        }
                        String nextAuditid = flowProcessEntityService.queryAuditidByApplyid(applyid,"7");
                        if(StringUtils.isBlank(nextAuditid)){
                            nextAuditid = flowProcessEntityService.queryAuditidByApplyid(applyid,"2");
                        }
                        flowProcessEntityService.updateNextProcessState("1", nextAuditid);
                    }
                }else{

                    if("结束".equals(nextnode)){//2处理完成，1处理中
                        flowProcess.setProcessstate("0");
                        String nextAuditid = flowProcessEntityService.queryAuditidByApplyid(applyid,"5");
                        flowProcessEntityService.updateProcessstate("1", nextAuditid);
                    }
                    if("2".equals(state)){
                        plycomresultdetail.setCashflag("3");
                    }else{
                        plycomresultdetail.setCashflag("4");
                    }

                }
                flowProcessEntityService.update(flowProcess);
                plycomresultdetailEntityService.update(plycomresultdetail);
                Flowauditinfo flowauditinfo=flowauditinfoService.findByauditidone(auditid,deptname);
                if(flowauditinfo!=null){
                    flowauditinfo.setDeptid(deptid);
                    flowauditinfo.setDeptname(deptname);
                    flowauditinfo.setAuditperid(auditperid);
                    flowauditinfo.setAuditpername(auditpername);
                    flowauditinfo.setAuditamount(sjspje);
                    flowauditinfo.setAuditidea(auditidea);
                    flowauditinfo.setAudittime( audittime);
                    flowauditinfo.setFlowstate("2");
                    flowauditinfo.setNextnode(nextnode);
                    flowauditinfo.setAuditstate(state);
                    flowauditinfoService.update(flowauditinfo);
                }

                //生成下一个审核节点
                if ("1".equals(state)&&!"结束".equals(nextnode)) {
                    String infoid = UUID.randomUUID().toString();
                    flowProcessEntityService.saveFlowAuditInfo(infoid, auditid, null,
                            nextnode, null,null,
                            "0", null, null,
                            null,"1",null);
                }


            }


        } catch (Exception e) {
            logger.error("insertOmprocess====", e);
        }
        return messageUtil;
    }




   /* @ResponseBody
    @RequestMapping(value = "/process/{access_token}", method = RequestMethod.POST)
    public MessageUtil insertOmprocess(@PathVariable String access_token, @RequestBody String objects) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        messageUtil = oauthController.checkAccesstoken(access_token);
        try {
//            objects = new String(objects.getBytes("iso-8859-1"),"utf-8");
            List<FlowProcessEntity> flowProcessEntityList = JSONObject.parseArray(objects, FlowProcessEntity.class);
            if (!messageUtil.isResult()) {
                return messageUtil;
            } else {
                for (FlowProcessEntity flowProcessEntity : flowProcessEntityList) {
                   *//* String auditid = flowProcessEntityService.queryAuditidByApplyid(flowProcessEntity.getApplyid(),"6");
                    if(StringUtils.isBlank(auditid)){
                        auditid = flowProcessEntityService.queryAuditidByApplyid(flowProcessEntity.getApplyid(),"1");
                    }*//*
                    FlowProcessEntity entity=flowProcessEntityService.queryListByapplyidandfid(flowProcessEntity.getApplyid(),"6");
                    if(entity==null){
                        entity=flowProcessEntityService.queryListByapplyidandfid(flowProcessEntity.getApplyid(),"1");
                    }
                    String auditid=entity.getAuditid();
                    String state = flowProcessEntity.getState();
                    System.out.println("部门======" + flowProcessEntity.getDeptname());
                    logger.info("部门======" + flowProcessEntity.getDeptname());
                    logger.info("审核人姓名======" + flowProcessEntity.getAuditpername());
                    PlycomresultdetailEntity plycomresultdetail=plycomresultdetailEntityService.find(flowProcessEntity.getApplyid());
                    // 1-同意 2/3 不同意 驳回
                    if ("1".equals(state)) {

                        if("2".equals(flowProcessEntity.getFlowstate())){//2处理完成，1处理中
                            flowProcessEntityService.updateProcessstate("2", auditid);
                            if(entity!=null){
                                entity.setUpdatetime(new Date());
                            }

                            //更新兑现结果表plycomresultdetail CASHFLAG 标识
                            if(plycomresultdetail!=null){
                                plycomresultdetail.setCashflag("2");//审批通过
                                plycomresultdetail.setAmount(flowProcessEntity.getSJSPJE()+"");
                                plycomresultdetailEntityService.update(plycomresultdetail);
                            }
                            String nextAuditid = flowProcessEntityService.queryAuditidByApplyid(flowProcessEntity.getApplyid(),"7");
                            flowProcessEntityService.updateNextProcessState("1", nextAuditid);


                        }else{
                            flowProcessEntityService.updateProcessstate("1", auditid);
                            //更新兑现结果表plycomresultdetail CASHFLAG 标识
                            if(plycomresultdetail!=null){
                                plycomresultdetail.setCashflag("7");//审批中
                                plycomresultdetailEntityService.update(plycomresultdetail);
                            }
                        }


//                        plycomresultdetailEntityService.updateCashflag("2", flowProcessEntity.getApplyid());

                    } else {
                        if("2".equals(flowProcessEntity.getFlowstate())){//2处理完成，1处理中
                            flowProcessEntityService.updateProcessstate("0", auditid);
                            if(entity!=null){
                                entity.setUpdatetime(new Date());
                            }
                            String nextAuditid = flowProcessEntityService.queryAuditidByApplyid(flowProcessEntity.getApplyid(),"5");
                            flowProcessEntityService.updateProcessstate("1", nextAuditid);
                        }else{
                            flowProcessEntityService.updateProcessstate("1", auditid);
                        }

                        //更新兑现结果表plycomresultdetail CASHFLAG 标识
                        if(plycomresultdetail!=null){
                            if("2".equals(state)){
                                plycomresultdetail.setCashflag("3");
                            }else{
                                plycomresultdetail.setCashflag("4");
                            }

                            plycomresultdetailEntityService.update(plycomresultdetail);
                        }


                    }
                    //保存审批信息
                    String infoid = UUID.randomUUID().toString();
                    flowProcessEntityService.saveFlowAuditInfo(infoid, auditid, flowProcessEntity.getDeptid(),
                            flowProcessEntity.getDeptname(), flowProcessEntity.getAuditperid(), flowProcessEntity.getAuditpername(),
                            flowProcessEntity.getState(), String.valueOf(flowProcessEntity.getSJSPJE()), flowProcessEntity.getAuditidea(),
                            flowProcessEntity.getCreatetime(),flowProcessEntity.getFlowstate(),flowProcessEntity.getNextnode());
                }
                messageUtil.setErrorMsg("新增成功");
            }
        } catch (Exception e) {
            logger.error("insertOmprocess====", e);
        }
        return messageUtil;
    }*/

    //调用创建一条企业政策的全部流程节点
    @RequestMapping(value = "/createflowprocess", method = RequestMethod.GET, produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public Object[] createflowprocess(String applyid, ModelMap model, HttpSession session,
                                      HttpServletRequest req) {
        Object[] data = new Object[2];
        String message = flowProcessEntityService.createflowprocessbyapplyid(applyid, session);
        System.out.println("解析方法返回信息：" + message);
        //显示登录成功(返回status101，message自己定如登录成功、登录失败)或者登录失败(返回status:102，message自己定)
        if ("success".equals(message)) {
            data[0] = "1";
            data[1] = "成功";
        } else {
            data[0] = "1";
            data[1] = "失败";
        }
        return data;
    }

    //调用提交当前节点
    @RequestMapping(value = "/updateflowprocess", method = RequestMethod.GET, produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public Object[] updateflowprocess(String applyid, ModelMap model, HttpSession session,
                                      HttpServletRequest req) {
        Object[] data = new Object[3];
        String message = flowProcessEntityService.updateflowprocess(applyid, session);
        System.out.println("解析方法返回信息：" + message);
        //显示登录成功(返回status101，message自己定如登录成功、登录失败)或者登录失败(返回status:102，message自己定)
        if ("success".equals(message)) {
            data[0] = "1";
            data[1] = "成功";
        } else {
            data[0] = "1";
            data[1] = "失败";
        }
        return data;
    }

    @RequestMapping(value = "/updateCredential", method = RequestMethod.POST)
    @ResponseBody
    public MessageUtil updateCredential(@RequestBody JSONObject param) {
        MessageUtil messageUtil = new MessageUtil();

        String uploadfilename = param.getString("uploadfilename");
        String uploadfileid = param.getString("uploadfileid");
        String auditid = param.getString("auditid");
        String applyid = param.getString("applyid");
        String plycomresultdetailid = param.getString("plycomresultdetailid");

        String[] files = uploadfileid.split(",");
        String[] names = uploadfilename.split(",");
        for (int i = 0; i < files.length; i++) {
            Uploadfile uploadfile = new Uploadfile();
            uploadfile.setFilename(uploadfilename.split(",")[i]);
            uploadfile.setId(files[i]);
            uploadfile.setUpdatedate(new Date());
            uploadfile.setIson("0");
            uploadfile.setFileext(names[i].split("\\.")[names[i].split("\\.").length - 1]);
            uploadfileService.save(uploadfile);
        }
        flowProcessEntityService.updateCredential(auditid, uploadfilename, uploadfileid,"2");
        String nextAuditid = flowProcessEntityService.queryAuditidByApplyid(applyid,"8");
        flowProcessEntityService.updateProcessstate("1", nextAuditid);
        plycomresultdetailEntityService.updateCashflag("6",plycomresultdetailid);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("凭据上传成功");
        return messageUtil;
    }

}
