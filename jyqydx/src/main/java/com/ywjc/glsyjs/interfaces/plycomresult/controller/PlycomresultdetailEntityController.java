package com.ywjc.glsyjs.interfaces.plycomresult.controller;

import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.Flowauditinfo;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
import com.ywjc.glsyjs.interfaces.flow.service.FlowauditinfoService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlyfileEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlyfileEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.repositories.PolicydetailRepository;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.utils.CommonUtil;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import com.ywjc.glsyjs.interfaces.utils.PropertyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller("plycomresultdetailEntityController")
@RequestMapping("/interface/plycomresultdetail")
@CrossOrigin
public class PlycomresultdetailEntityController extends BaseController {

    /**
     * 系统日志工具
     */
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;

    @Resource(name = "uploadfileServiceImpl")
    private UploadfileService uploadfileService;

    @Resource(name = "plyfileEntityServiceImpl")
    private PlyfileEntityService plyfileEntityService;
    @Resource(name = "policydetailServiceImpl")
    private PolicydetailService policydetailService;
    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;

    @Resource(name = "flowauditinfoServiceImpl")
    private FlowauditinfoService flowauditinfoService;


    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;

    //根据企业名称或统一社会信用代码获取企业列表
    @ResponseBody
    @RequestMapping(value = "/querycompany", method = RequestMethod.GET)
    public MessageUtil queryCompanyList(@RequestParam(value = "searchName") String searchName) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        int count = 0;
        if (StringUtils.isNotBlank(searchName)) {
            searchName = CommonUtil.generateFuzzyString(searchName);
            List<CompanyEntity> companyEntityList = companyEntityService.queryCompanyListBySearchName(searchName);
            result.put("companyEntityList", companyEntityList);
            result.put("count", companyEntityList.size());
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取企业列表成功");
            messageUtil.setResultObj(result);
        } else {
            result.put("count", count);
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("获取企业列表失败,搜索框必填");
            messageUtil.setResultObj(result);
        }
        return messageUtil;
    }

    //根据企业名称或统一社会信用代码获取兑现评估列表
    @ResponseBody
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public MessageUtil plycomresultdetailFindall(@RequestParam(value = "searchid") String searchid, @RequestParam(value = "companyid") String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        /**
         * 比较当前输入的企业名称或统一社会信用代码 和 已绑定的企业是否一致
         * 一致，查询兑现详情
         * 不一致，查询企业信息
         */
        Boolean showFlag = false;
        if (StringUtils.isNotBlank(searchid)) {
            if (searchid.equals(companyid)) {
                showFlag = true;
            }
            List<PlycomresultdetailEntity> plycomresultEntityList = plycomresultdetailEntityService.findDXPGByCompanyid(searchid);
            //免申即享
            List<PlycomresultdetailEntity> withoutRequest = new ArrayList<>();
            //补充材料兑现
            List<PlycomresultdetailEntity> replenishFiles = new ArrayList<>();
            //提升企业能力兑现
            List<PlycomresultdetailEntity> improvingCapacity = new ArrayList<>();
            //政策预告
            List<PlycomresultdetailEntity> predict = new ArrayList<>();
            String file_save_path = PropertyUtil.readProperty("centit.properties", "file_save_path");
            if (CollectionUtils.isNotEmpty(plycomresultEntityList)) {
                for (PlycomresultdetailEntity temp : plycomresultEntityList) {
                    Uploadfile uploadfile = uploadfileService.findPolicyfile(temp.getPlfidid());
                    temp.setUploadfile(uploadfile);
                    if (temp.getEnjoypolicycategory().equals("1")) {
                        withoutRequest.add(temp);
                    } else if (temp.getEnjoypolicycategory().equals("2")) {
                        replenishFiles.add(temp);
                    } else if (temp.getEnjoypolicycategory().equals("3")) {
                        improvingCapacity.add(temp);
                    } else if (temp.getEnjoypolicycategory().equals("4")) {
                        predict.add(temp);
                    }
                }
            }
            result.put("withoutRequest", withoutRequest);
            result.put("replenishFiles", replenishFiles);
            result.put("improvingCapacity", improvingCapacity);
            result.put("predict", predict);
        }
        result.put("showFlag", showFlag);

        messageUtil.setResult(true);
        messageUtil.setErrorMsg("获取兑现评估列表成功");
        messageUtil.setResultObj(result);
        return messageUtil;
    }

    //根据企业名称获取企业详情信息
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public MessageUtil companyDetail(@RequestParam(value = "companyname") String companyname) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        List<CompanyEntity> companyEntityList = companyEntityService.queryCompanyListBySearchName(companyname);
        result.put("companyEntityList", companyEntityList);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("获取企业详情信息成功");
        messageUtil.setResultObj(result);
        return messageUtil;
    }

    //根据政策id获取免申即享兑现详情
    @ResponseBody
    @RequestMapping(value = "/withoutdetail", method = RequestMethod.GET)
    public MessageUtil withoutDetail(@RequestParam(value = "plycomresultdetailid") String plycomresultdetailid) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        if (StringUtils.isNotBlank(plycomresultdetailid)) {
            PlycomresultdetailEntity plycomresultdetailEntity = plycomresultdetailEntityService.find(plycomresultdetailid);
            //政策上传文件
            Object[][] policydetail = plycomresultdetailEntityService.findPolicydetailBypldtailid(plycomresultdetailEntity.getPldtailid());
            JSONObject payment = new JSONObject();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();//new一个Calendar类,把Date放进去
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            calendar.add(Calendar.HOUR, -1);
            if (policydetail!=null &&policydetail[0][1]!=null) {
                payment.put("paycompany", policydetail[0][1].toString());
            }else {
                payment.put("paycompany", "");
            }
            payment.put("payaccount", getBrankNumber("8"));
            payment.put("paytime", df.format(calendar.getTime()));
            if (policydetail[0][0] != null) {
                payment.put("payamount", String.format("%.2f", Double.valueOf(policydetail[0][0].toString())));
                payment.put("collectionamount", String.format("%.2f", Double.valueOf(policydetail[0][0].toString())));
            }
            payment.put("collectioncompany", plycomresultdetailEntity.getEnterprisename());
            payment.put("collectionaccount", getBrankNumber("9"));
            calendar.add(Calendar.MINUTE, +3);
            payment.put("collectiontime", df.format(calendar.getTime()));


            result.put("plycomresultdetailEntity", plycomresultdetailEntity);
            result.put("payment", payment);
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取免申即享兑现详情成功");
            messageUtil.setResultObj(result);
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("获取免申即享兑现详情失败");
        }

        return messageUtil;
    }

    //根据政策id获取政策兑现评估详情
    @ResponseBody
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public MessageUtil plycomresultdetailShow(@RequestParam(value = "plycomresultdetailid") String plycomresultdetailid) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        if (StringUtils.isNotBlank(plycomresultdetailid)) {
            PlycomresultdetailEntity plycomresultdetailEntity = plycomresultdetailEntityService.find(plycomresultdetailid);
            PolicydetailEntity policydetailEntity = policydetailService.find(plycomresultdetailEntity.getPldtailid());
            plycomresultdetailEntity.setPolicydeclar(policydetailEntity.getDeclareconditions());
            if (StringUtils.isNotEmpty(plycomresultdetailEntity.getCompanyid())) {
                CompanyEntity companyEntity = companyEntityService.queryCompanyByid(plycomresultdetailEntity.getCompanyid());
                result.put("recapital", companyEntity.getRecapital());
                result.put("maxamount", policydetailEntity.getMaxamount());
                result.put("minamount", policydetailEntity.getMinamount());
            }

            //政策上传文件
            List<PlyfileEntity> plyfileEntityList = plyfileEntityService.findPlyfileEntityByPldtailid(plycomresultdetailEntity.getPldtailid());
            for (PlyfileEntity plyfileEntity : plyfileEntityList) {
                List<Uploadfile> uploadfiles = uploadfileService.findUploadfilesbyfujianfileid(plyfileEntity.getPlyfileid(), plycomresultdetailid);
                plyfileEntity.setUploadfiles(uploadfiles);
            }

            result.put("plycomresultdetailEntity", plycomresultdetailEntity);
            result.put("plyfileEntityList", plyfileEntityList);
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取兑现评估详情成功");
            messageUtil.setResultObj(result);
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("获取兑现评估详情失败");
        }

        return messageUtil;
    }

    //我的兑现列表接口
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public MessageUtil plycomresultdetailList(@RequestParam(value = "companyid") String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        if (StringUtils.isNotBlank(companyid)) {
            JSONObject result = new JSONObject();
            List<PlycomresultdetailEntity> plycomresultdetailList = plycomresultdetailEntityService.findPlycomresultdetailByCompanyid(companyid);
            List<PlycomresultdetailEntity> plycomresultEntityList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(plycomresultdetailList)) {
                for (PlycomresultdetailEntity temp : plycomresultdetailList) {
                    Uploadfile uploadfile = uploadfileService.findPolicyfile(temp.getPlfidid());
                    temp.setUploadfile(uploadfile);
                    if (temp.getEnjoypolicycategory().equals("1")) {
                        plycomresultEntityList.add(temp);
                    } else if (temp.getEnjoypolicycategory().equals("2")) {
                        plycomresultEntityList.add(temp);
                    }
                }
            }
            result.put("plycomresultdetailList", plycomresultEntityList);
            result.put("count", plycomresultEntityList.size());

            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取我的兑现列表数据成功");
            messageUtil.setResultObj(result);
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("企业id为空");
        }
        return messageUtil;
    }


    //查看进度接口
    @ResponseBody
    @RequestMapping(value = "/planlist", method = RequestMethod.GET)
    public MessageUtil plycomresultdetailPlanList(@RequestParam(value = "plycomresultdetailid") String plycomresultdetailid) {
        MessageUtil messageUtil = new MessageUtil();
        List<FlowProcessEntity> flowProcessEntities = flowProcessEntityService.queryListByapplyid(plycomresultdetailid);
        for (FlowProcessEntity flowProcessEntity : flowProcessEntities) {
            List<Flowauditinfo> flowauditinfos = flowauditinfoService.findByauditid(flowProcessEntity.getAuditid());
            flowProcessEntity.setFlowauditinfos(flowauditinfos);
        }
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("查看进度接口成功");
        messageUtil.setResultObj(flowProcessEntities);

        return messageUtil;
    }

    //查看兑现提醒接口
    @ResponseBody
    @RequestMapping(value = "/findremind", method = RequestMethod.GET)
    public MessageUtil plycomresultdetailFindremind(@RequestParam(value = "companyid") String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        if (StringUtils.isNotBlank(companyid)) {
            List<PlycomresultdetailEntity> plycomresultEntityList = plycomresultdetailEntityService.findPlycomresultdetailByCompanyid(companyid);
            //提升企业能力兑现
            List<PlycomresultdetailEntity> improvingCapacity = new ArrayList<>();
            //政策预告
            List<PlycomresultdetailEntity> predict = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(plycomresultEntityList)) {
                for (PlycomresultdetailEntity temp : plycomresultEntityList) {
                    Uploadfile uploadfile = uploadfileService.findPolicyfile(temp.getPlfidid());
                    temp.setUploadfile(uploadfile);
                    if (temp.getEnjoypolicycategory().equals("3")) {
                        improvingCapacity.add(temp);
                    } else if (temp.getEnjoypolicycategory().equals("4")) {
                        predict.add(temp);
                    }
                }
            }
            result.put("improvingCapacity", improvingCapacity);
            result.put("predict", predict);
            result.put("showFlag", true);
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取兑现提醒数据成功");
            messageUtil.setResultObj(result);
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("获取兑现提醒接口失败，企业id为空");
        }
        return messageUtil;
    }

    private int i = 0;

    private String getBrankNumber(String prefix) {
        i++;
        i = i > 100 ? i % 10 : i;
        String number = "666" + prefix + ((System.currentTimeMillis() / 100) + "").substring(1) + (i % 10);
        return number;
    }
}
