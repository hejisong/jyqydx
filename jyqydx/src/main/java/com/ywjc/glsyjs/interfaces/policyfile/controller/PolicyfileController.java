package com.ywjc.glsyjs.interfaces.policyfile.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import com.ywjc.glsyjs.Policyservices.utils.KeyMatchUtil;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessAssetEntityService;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlyfileEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlyfileEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.entity.Policyfile;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicyfileService;
import com.ywjc.glsyjs.interfaces.tag.service.TagService;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import com.ywjc.glsyjs.sys.service.PolicyFileEntityService;
import com.ywjc.glsyjs.sys.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller("policyfileController")
@RequestMapping("/interface/policyfile")
@CrossOrigin
public class PolicyfileController extends BaseController {

    /**
     * 系统日志工具
     */
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "policyfileServiceImpl")
    private PolicyfileService policyfileService;

    @Resource(name = "uploadfileServiceImpl")
    private UploadfileService uploadfileService;
    @Resource(name = "policydetailServiceImpl")
    private PolicydetailService policydetailService;
    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;
    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;
    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;
    @Resource(name = "plyfileEntityServiceImpl")
    private PlyfileEntityService plyfileEntityService;
    @Resource(name = "flowProcessAssetEntityServiceImpl")
    private FlowProcessAssetEntityService flowProcessAssetEntityService;
    @Resource(name = "tagServiceImpl")
    private TagService tagService;
    @Resource(name = "policyFileEntityServiceImpl")
    private PolicyFileEntityService policyfileEntityService;

    @ResponseBody
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public MessageUtil findAllPolicyfile(@PageableDefault Pageable pageable,
                                         @RequestParam(value = "key") String key,
                                         @RequestParam(defaultValue = "", value = "fileLevel") String fileLevel,
                                         @RequestParam(defaultValue = "", value = "fileType") String fileType,
                                         @RequestParam(defaultValue = "", value = "tagid") String tagid,
                                         @RequestParam(value = "beginDate", required = false) String beginDate,
                                         @RequestParam(value = "endDate", required = false) String endDate) {
        MessageUtil messageUtil = new MessageUtil();
        Date start_date = null;
        Date end_date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        if (StringUtils.isBlank(beginDate) && StringUtils.isBlank(endDate)) {
//            Long start = DateUtil.getYearBeforeThree();
//            Long end = DateUtil.getYearEndTime();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(start);
//            start_date = calendar.getTime();
//            calendar.setTimeInMillis(end);
//            end_date = calendar.getTime();
//        }
        if (StringUtils.isNotBlank(beginDate)) {
            try {
                start_date = df.parse(beginDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotBlank(endDate)) {
            try {
                end_date = df.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Page<Object> page = policyfileEntityService.findPolicyFiles(start_date, end_date, fileLevel, tagid, key, pageable);
        List<Policyfile> result = new ArrayList<>();
        for (Object file : page.getContent()) {
            Object[] o = (Object[]) file;
            if (StringUtils.isNotBlank(key)) {
                o[1] = KeyMatchUtil.getMatchedString(o[1].toString(), key);
            }
            List<Object> findtag = tagService.findtag(o[0].toString());
            Policyfile policyfile = new Policyfile();
            policyfile.setPlfidid(Objects.isNull(o[0]) ? "" : o[0].toString());
            policyfile.setPolicyname(Objects.isNull(o[1]) ? "" : o[1].toString());
            policyfile.setFwtitanic(Objects.isNull(o[2]) ? "" : o[2].toString());
            policyfile.setFwunits(Objects.isNull(o[3]) ? "" : o[3].toString());
            policyfile.setReleasetime(Objects.isNull(o[4]) ? null : (Date) o[4]);
            policyfile.setFilecontent(Objects.isNull(o[5]) ? "" : o[5].toString());
            policyfile.setFilelevel(Objects.isNull(o[6]) ? "" : o[6].toString());
            policyfile.setFiletype(Objects.isNull(o[7]) ? "" : o[7].toString());
            policyfile.setState(Objects.isNull(o[8]) ? "" : o[8].toString());
            policyfile.setTags(findtag);
            List<Uploadfile> uploadfiles = uploadfileService.findPolicyfiles(policyfile.getPlfidid());
            policyfile.setUploadfiles(uploadfiles);
            result.add(policyfile);
        }
        messageUtil.setTotal(page.getTotalElements());
        messageUtil.setResult(true);
        messageUtil.setResultObj(result);
        messageUtil.setErrorMsg("获取政策文件成功");
        return messageUtil;
    }

    @ResponseBody
    @RequestMapping(value = "/findPolicydetails", method = RequestMethod.GET)
    public MessageUtil findPolicydetails(@RequestParam(value = "content") String content,@RequestParam String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        List<Object> policydetails = policydetailService.findPolicydetails(content);
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR)-1);
        List<Object> result=new ArrayList<>();
        for (Object policydetail : policydetails) {
            Object[] o = (Object[]) policydetail;
            List<Object> list=Arrays.asList(o);
            PlycomresultdetailEntity plycomresultdetail = plycomresultdetailEntityService.findPlycomresult(companyid, o[0].toString(), year);
            if (plycomresultdetail != null) {
                list.add("1");
                result.add(list);
            }
        }
        messageUtil.setResult(true);
        messageUtil.setResultObj(result);
        return messageUtil;
    }

    /**
     * 根据政策文件和标签查询细则
     *
     * @param plfidid
     * @param tagid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findPolicyFileByTagAndPlfId", method = RequestMethod.GET)
    public MessageUtil findPolicyFile(@RequestParam(value = "plfidid") String plfidid,
                                      @RequestParam(value = "companyid") String companyid,
                                      @RequestParam(value = "tagid") String tagid) {
        MessageUtil messageUtil = new MessageUtil();
        List<PolicydetailEntity> policydetails = policydetailService.findPolicyFilesTag(plfidid, tagid);
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR)-1);
        for (PolicydetailEntity policydetail : policydetails) {
            PlycomresultdetailEntity plycomresultdetail = plycomresultdetailEntityService.findPlycomresult(companyid, policydetail.getPldtailid(), year);
            if (plycomresultdetail != null) {
                policydetail.setSbstate("1");
            }
        }
        messageUtil.setResult(true);
        messageUtil.setResultObj(policydetails);
        return messageUtil;
    }

    @ResponseBody
    @RequestMapping(value = "/findPolicydetailsByFileId", method = RequestMethod.GET)
    public MessageUtil findPolicydetailsByFileId(@RequestParam(value = "plfidid") String plfidid, @RequestParam(value = "companyid") String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        List<PolicydetailEntity> policyFiles = policydetailService.findPolicyFiles(plfidid);
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR)-1);
        for (PolicydetailEntity policydetail : policyFiles) {
            PlycomresultdetailEntity plycomresultdetail = plycomresultdetailEntityService.findPlycomresult(companyid, policydetail.getPldtailid(), year);
            if (plycomresultdetail != null) {
                policydetail.setSbstate("1");
            }
        }
        messageUtil.setResult(true);
        messageUtil.setResultObj(policyFiles);
        return messageUtil;
    }

    @ResponseBody
    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public MessageUtil apply(@RequestParam(value = "pldtailid") String pldtailid, @RequestParam(value = "companyid") String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        //申报补充材料的政策
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR)-1);
        PlycomresultdetailEntity plycomresultdetail = plycomresultdetailEntityService.findPlycomresult(companyid, pldtailid, year);
        if (plycomresultdetail == null) {
            plycomresultdetail = new PlycomresultdetailEntity();
            plycomresultdetail.setPlycomresultdetailid(KeyUtils.getKey());
            plycomresultdetail.setCompanyid(companyid);
            PolicydetailEntity policy = policydetailService.find(pldtailid);
            Policyfile file = policyfileService.find(policy.getPlfidid());
            if (file != null) {
                plycomresultdetail.setPolicyname(file.getPolicyname());
            }
            CompanyEntity corp = companyEntityService.queryCompanyByid(companyid);
            plycomresultdetail.setUscreditcode(corp.getUscreditcode());
            plycomresultdetail.setPldtailid(pldtailid);
            plycomresultdetail.setInputtime(new Date());
            plycomresultdetail.setPlfidid(policy.getPlfidid());
            plycomresultdetail.setPolicycategorynum(policy.getPlcategoryno());
            plycomresultdetail.setPolicycategoryname(policy.getNplcategory());
            plycomresultdetail.setPolicysubclassno(policy.getPlsubclassnum());
            plycomresultdetail.setPolicysubclassname(policy.getPlsubclassname());
            plycomresultdetail.setEnterprisenum(corp.getNumber());
            plycomresultdetail.setEnterprisename(corp.getEnterprisename());
            plycomresultdetail.setPolicyconditions(policy.getPlconditions());
            if (policy.getRamount() != null) {
                plycomresultdetail.setAmount(policy.getRamount() + "");
            }

            plycomresultdetail.setRdescription(policy.getRdescription());
            plycomresultdetail.setParticularyear(year);
            plycomresultdetail.setCashflag("0");
            plycomresultdetail.setEnjoypolicycategory("2");
            System.out.println(plycomresultdetail.getPlycomresultdetailid());
            plycomresultdetailEntityService.save(plycomresultdetail);
            System.out.println(plycomresultdetail.getPlycomresultdetailid());
            PlycomresultdetailEntity resultdetail = plycomresultdetailEntityService.findPlycomresult(companyid, pldtailid, year);
            //生成流程
            String message = flowProcessEntityService.createflowprocessbyapplyidintask(resultdetail);
            messageUtil.setResultObj(resultdetail.getPlycomresultdetailid());
            if ("success".equals(message)) {
                messageUtil.setResult(true);
                messageUtil.setErrorMsg("申报成功");
            } else {
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("申报成功,流程生成失败");
            }
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("已经申报过");
            messageUtil.setResultObj(plycomresultdetail.getPlycomresultdetailid());
        }
        return messageUtil;

    }

    //提交
    @RequestMapping(value = "/savefilesinasset", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public MessageUtil savefilesinasset(String searchId, String issubmitoffline) {
        MessageUtil messageUtil = new MessageUtil();
        if (StringUtils.isNotBlank(searchId)) {
            PlycomresultdetailEntity plycomresultdetailentity = plycomresultdetailEntityService.find(searchId);
            if (plycomresultdetailentity != null) {
                //查询政策流程表
                FlowProcessEntity flowprocessentity = flowProcessEntityService.queryListByapplyidandfid(searchId, "5");
                if (flowprocessentity == null) {
                    messageUtil.setResult(false);
                    messageUtil.setErrorMsg("未找到相关流程");
                    return messageUtil;
                }
                flowprocessentity.setProcessstate("2");
                flowprocessentity.setUpdatetime(new Date());
                plycomresultdetailentity.setCashflag("1");//提交待审批
                plycomresultdetailentity.setIssubmitoffline(issubmitoffline);
                plycomresultdetailEntityService.update(plycomresultdetailentity);

                List<String> submitFileList = new ArrayList<>();
                //所需材料
                List<PlyfileEntity> plyfileEntityList = plyfileEntityService.findPlyfileEntityByPldtailid(plycomresultdetailentity.getPldtailid());
                if (plyfileEntityList != null && plyfileEntityList.size() > 0) {
                    for (PlyfileEntity entity : plyfileEntityList) {
                        JSONArray ids = new JSONArray();
                        //上传附件
                        List<Uploadfile> uploadfiles = uploadfileService.findUploadfilesbyfujianfileid(entity.getPlyfileid(), plycomresultdetailentity.getPlycomresultdetailid());
                        if (uploadfiles != null && uploadfiles.size() > 0) {
                            entity.setUploadfiles(uploadfiles);
                            for (Uploadfile uploadfile : uploadfiles) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("assetid", uploadfile.getId());
                                ids.add(jsonObject);
                            }
                            System.out.println(ids);
                            //String applyid, String auditid,String fid, String plyfileid, String assettype
                            List<String> fileList = flowProcessAssetEntityService.FlowProcessUploadPublicFile(ids, flowprocessentity.getApplyid(), flowprocessentity.getAuditid(),
                                    flowprocessentity.getFid(), entity.getPlyfileid(), uploadfiles.get(0).getFileext());
                            submitFileList.addAll(fileList);
                        }

                    }
                }
                //部门审批推送至钉钉
                plycomresultdetailEntityService.checkToDingDing(plycomresultdetailentity, submitFileList);
                messageUtil.setResult(true);
                messageUtil.setErrorMsg("保存成功");
            } else {
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("兑现不存在");
            }

        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("兑现不存在，检查请求参数");
        }

        return messageUtil;
    }

    //暂存
    @RequestMapping(value = "/saveuploadfiles", method = RequestMethod.GET)
    @ResponseBody
    public MessageUtil saveuploadfiles(String fujianlistid, String plycomresultid, String upfileids) {
        MessageUtil messageUtil = new MessageUtil();
        String[] ids = null;
        if (StringUtils.isNotBlank(upfileids)) {
            ids = upfileids.split(",");
        }
        if (upfileids != null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                if (StringUtils.isNotBlank(ids[i])) {
                    Uploadfile uploadfile = uploadfileService.find(ids[i]);
                    if (uploadfile != null) {
                        uploadfile.setFujianfileid(fujianlistid);
                        uploadfile.setPlycomresultid(plycomresultid);
                        uploadfileService.update(uploadfile);
                    }
                }
            }
        }
        List<Uploadfile> fujianlist = uploadfileService.findUploadfilesbyfujianfileid(fujianlistid, plycomresultid);
        if (fujianlist != null && fujianlist.size() > 0) {
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("保存成功");
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("保存失败");
        }
        return messageUtil;

    }

    //删除附件
    @RequestMapping(value = "/deletefujian/{id}", method = RequestMethod.POST)
    @ResponseBody
    public MessageUtil deletefujian(@PathVariable String id) {
        MessageUtil messageUtil = new MessageUtil();
        if (StringUtils.isNotBlank(id)) {
            uploadfileService.delete(id);
        }
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("删除成功");
        return messageUtil;

    }

}
