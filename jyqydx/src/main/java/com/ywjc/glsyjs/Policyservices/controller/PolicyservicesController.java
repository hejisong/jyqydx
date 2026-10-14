package com.ywjc.glsyjs.Policyservices.controller;

import com.alibaba.fastjson.JSONArray;
import com.ywjc.glsyjs.Policyservices.entity.Payinfo;
import com.ywjc.glsyjs.Policyservices.service.PayinfoService;
import com.ywjc.glsyjs.Policyservices.utils.Export;
import com.ywjc.glsyjs.Policyservices.utils.ExportExcel;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.FileService;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.SMS_Manage.SmsMsgUtil;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessAssetEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.Flowauditinfo;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessAssetEntityService;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
import com.ywjc.glsyjs.interfaces.flow.service.FlowauditinfoService;
import com.ywjc.glsyjs.interfaces.oauth.entity.OauthEntity;
import com.ywjc.glsyjs.interfaces.oauth.service.OauthEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlyfileEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlyfileEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.utils.*;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/policyservices")
public class PolicyservicesController {
    private static Logger log = Logger.getLogger(PolicyservicesController.class);


    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;


    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;

    @Resource(name = "oauthEntityServiceImpl")
    private OauthEntityService oauthEntityService;

    @Resource(name = "uploadfileServiceImpl")
    private UploadfileService uploadfileService;

    @Resource(name = "fileServiceImpl")
    private FileService fileService;

    @Resource(name = "plyfileEntityServiceImpl")
    private PlyfileEntityService plyfileEntityService;

    @Resource(name = "flowProcessAssetEntityServiceImpl")
    private FlowProcessAssetEntityService flowProcessAssetEntityService;

    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;

    @Resource(name = "policydetailServiceImpl")
    private PolicydetailService policydetailService;

    @Resource(name = "flowauditinfoServiceImpl")
    private FlowauditinfoService flowauditinfoService;

    @Resource(name = "payinfoServiceImpl")
    private PayinfoService payinfoService;

    /**
     * 政策兑现审核（查询）
     *
     */
    @RequestMapping(value = "/policyservices", method = RequestMethod.GET)
    public String policyservices(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable, HttpSession session, HttpServletRequest request,
                             ModelMap model, @RequestParam(required = false) String enterprisename, @RequestParam(required = false) String begintime, @RequestParam(required = false) String endtime,
                             @RequestParam(required = false) String cashflag) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(cashflag==null){
            cashflag="1";
        }
        Page<Object> page=plycomresultdetailEntityService.findinfoPage(enterprisename,begintime,endtime,cashflag,pageable);
        for (Object obj:page.getContent()) {
           Object[] oo=( Object[])obj;
            FlowProcessEntity flowprocess=flowProcessEntityService.queryListByapplyidandfid((String)oo[1],"6");
            oo[12]=flowprocess;

            if(StringUtils.isBlank((String)oo[2])){
                CompanyEntity companyEntity=companyEntityService.queryCompanyByid((String)oo[11]);
                oo[2]=companyEntity.getUscreditcode();
            }
        }
        model.addAttribute("enterprisename", enterprisename);
        model.addAttribute("begintime", begintime);
        model.addAttribute("endtime", endtime);
        model.addAttribute("cashflag", cashflag);
        model.addAttribute("page", page);
        model.addAttribute("pageable", pageable);
        return "/policyservices/list";
    }

    /**
     *政策兑现支付（查询）
     *
     */
    @RequestMapping(value = "/policypayments", method = RequestMethod.GET)
    public String policypayments(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable, HttpSession session, HttpServletRequest request,
                             ModelMap model, @RequestParam(required = false) String enterprisename, @RequestParam(required = false) String begintime, @RequestParam(required = false) String endtime,
                             @RequestParam(required = false) String cashflag) {


        if(cashflag==null){
            cashflag="6";
        }
        Page<Object> page=plycomresultdetailEntityService.findpaymentlistPage(enterprisename,begintime,endtime,cashflag,pageable);
        model.addAttribute("enterprisename", enterprisename);
        model.addAttribute("begintime", begintime);
        model.addAttribute("endtime", endtime);
        model.addAttribute("cashflag", cashflag);
        model.addAttribute("page", page);
        model.addAttribute("pageable", pageable);
        return "/policyservices/paymentlist";
    }

    @RequestMapping(value="/payall",method = RequestMethod.POST)
    @ResponseBody
    public String payall(String ids) {
        String[] idlist=ids.split(",");
        for (String id:idlist) {
            PlycomresultdetailEntity entity=plycomresultdetailEntityService.find(id);
            if(entity!=null&&StringUtils.isNotBlank(entity.getCashflag())&&!"1".equals(entity.getCashflag())){
                //TODO
                List<YshApiPayEntity> yshApiPayEntityList = new ArrayList<>();
                YshApiPayEntity yshApiPayEntity = new YshApiPayEntity();
                yshApiPayEntity.setInPaymentAccount("AccSub20210413191307074");
                yshApiPayEntity.setOutPaymentAccount("AccSub20210413193254031");
                yshApiPayEntity.setAmount("0.05");
                yshApiPayEntity.setRemark("交易接口测试");
                yshApiPayEntityList.add(yshApiPayEntity);

                String token = getYshApiAccessToken();
                JSONObject resultObject1 = YshApiUtil.yshApiPay(yshApiPayEntityList,token);
                JSONArray result_infos = resultObject1.getJSONArray("result_info");
                String transId="";
                if(result_infos!=null&&result_infos.size()>0){
                    for (int j = 0; j < result_infos.size(); j++) {
                        JSONObject jsonObject2 = result_infos.getJSONObject(j);
                        String transState=jsonObject2.getString("transState");
                        if("1".equals(transState)){
                            transId=jsonObject2.getString("transId");
                        }
                    }
                }
                //TODO transId后面是否加上AUTO
                JSONObject resultObject = YshApiUtil.getReceiptUrl(token, transId);
                System.out.println(resultObject);
                JSONObject result_info = resultObject.getJSONObject("result_info");
                String receiptUrl = result_info.getString("receiptUrl");
                System.out.println(receiptUrl);
                if(StringUtils.isNotBlank(receiptUrl)&&StringUtils.isNotBlank(id)){
                    saveUploadfileandasset(id,receiptUrl);
                }

            }
        }
        return "SUCCESS";
    }

    /**
     * 支付详情页
     * @param mid
     * @param model
     * @param session
     * @param request
     * @return
     */

    @RequestMapping(value = "/payinfo", method = RequestMethod.GET)
    public String payinfo(String mid,ModelMap model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("id", mid);
        if(StringUtils.isNotBlank(mid)){
            //获取政策信息
            PlycomresultdetailEntity plydetail=plycomresultdetailEntityService.find(mid);
            model.addAttribute("plydetail", plydetail);
            PolicydetailEntity policydetail=policydetailService.find(plydetail.getPldtailid());
            model.addAttribute("policydetail",policydetail);
            CompanyEntity companyEntity=companyEntityService.find(plydetail.getCompanyid());
            model.addAttribute("company",companyEntity);
            //兑现流程信息om_flow_process
            //TODO
//            mid="d59efee87c1411ebb138b06ebf39a80b";
            List<FlowProcessEntity> flowprocesslist=flowProcessEntityService.queryListByapplyid(mid);
            String payaccountdz= ConfigUtil.getConfigVal("payaccountdz");
            String payaccount= ConfigUtil.getConfigVal("payaccount");
            String payname= ConfigUtil.getConfigVal("payname");
            model.addAttribute("payaccountdz",payaccountdz);
            model.addAttribute("payaccount",payaccount);
            model.addAttribute("payname",payname);
            //所需材料
            List<PlyfileEntity> plyfileEntityList = plyfileEntityService.findPlyfileEntityByPldtailid(plydetail.getPldtailid());
            if(plyfileEntityList!=null&&plyfileEntityList.size()>0){
                for(PlyfileEntity entity:plyfileEntityList){
                    //上传附件
                    List<Uploadfile> uploadfiles=uploadfileService.findUploadfilesbyfujianfileid(entity.getPlyfileid(),plydetail.getPlycomresultdetailid());
                    if(uploadfiles!=null&&uploadfiles.size()>0){
                        entity.setUploadfiles(uploadfiles);
                    }
                }
                model.addAttribute("plyfileEntityList",plyfileEntityList);
            }


            if(flowprocesslist!=null&&flowprocesslist.size()>0){
                if(flowprocesslist!=null&&flowprocesslist.size()>0){
                    model.addAttribute("flowprocesslist",flowprocesslist);
                    for (FlowProcessEntity f:flowprocesslist) {
                        if("2".equals(f.getFid())||"7".equals(f.getFid())){
                            //凭据上传
                            if(StringUtils.isNotBlank(f.getUploadfileid())){
                                List<Uploadfile> uploadfiles=new ArrayList<>();
                                String[] fileids=f.getUploadfileid().split(",");
                                String[] filenames=f.getUploadfilename().split(",");
                                for(int i=0;i<fileids.length;i++){
                                    Uploadfile uploadfile=new Uploadfile();
                                    uploadfile.setId(fileids[i]);
                                    uploadfile.setFilename(filenames[i]);
                                    uploadfiles.add(uploadfile);
                                }
                                model.addAttribute("uploadfiles",uploadfiles);
                            }
                        }
                        if("1".equals(f.getFid())||"6".equals(f.getFid())){
                            FlowProcessEntity flowprocess=f;
                            model.addAttribute("flowprocess",flowprocess);
                            //审核信息
                            List<Flowauditinfo> flowauditinfos= flowauditinfoService.findByauditid(flowprocess.getAuditid());
                            model.addAttribute("flowauditinfos",flowauditinfos);
                        }
                    }
                }
                for (FlowProcessEntity f:flowprocesslist) {
                    if("2".equals(f.getFid())||"7".equals(f.getFid())){
                        //凭据上传
                        if(StringUtils.isNotBlank(f.getUploadfileid())){
                            List<Uploadfile> uploadfiles=new ArrayList<>();
                            String[] fileids=f.getUploadfileid().split(",");
                            String[] filenames=f.getUploadfilename().split(",");
                            for(int i=0;i<fileids.length;i++){
                                Uploadfile uploadfile=new Uploadfile();
                                uploadfile.setId(fileids[i]);
                                uploadfile.setFilename(filenames[i]);
                                uploadfiles.add(uploadfile);
                            }
                            model.addAttribute("uploadfiles",uploadfiles);
                        }
                    }
                    if("1".equals(f.getFid())||"6".equals(f.getFid())){
                        FlowProcessEntity flowprocess=f;
                        model.addAttribute("flowprocess",flowprocess);
                        //审核信息
                        List<Flowauditinfo> flowauditinfos= flowauditinfoService.findByauditid(flowprocess.getAuditid());
                        model.addAttribute("flowauditinfos",flowauditinfos);
                    }
                }
            }
        }

        return "/policyservices/payinfo";
    }
    //赋予账号信息（返回页面）
    private void getaccountinfo(String token,String creditcode,ModelMap model,String type){
        JSONObject jsonObject=YshApiUtil.getAccountInfoListBycreditCode(token,creditcode);

        JSONObject result_info = jsonObject.getJSONObject("result_info");
        if(result_info!=null){
            JSONObject accountSubInfoListCompany = result_info.getJSONObject("accountSubInfoListCompany");
            JSONArray creditCodelist1=accountSubInfoListCompany.getJSONArray(creditcode);
            String ownerName="";
            String stzhAccountId="";
            String balance="";
            String createTime="";
            for (int j = 0; j < creditCodelist1.size(); j++) {
                JSONObject jsonObject2 = creditCodelist1.getJSONObject(j);
                ownerName=jsonObject2.getString("ownerName");
                stzhAccountId=jsonObject2.getString("stzhAccountId");
                createTime=jsonObject2.getString("createTime");
                balance=jsonObject2.getString("balance");
            }
            //TODO 支付详情页收付款账号信息
            if("1".equals(type)){
                model.addAttribute("inPaymentownerName", ownerName);
                model.addAttribute("inPaymentAccount", stzhAccountId);
                model.addAttribute("inPaymenttime", createTime);
                model.addAttribute("inPaymentamount", balance);
            }else{
                model.addAttribute("outPaymentownerName", ownerName);
                model.addAttribute("outPaymentAccount", stzhAccountId);
                model.addAttribute("outPaymenttime", createTime);
                model.addAttribute("outPaymentamount", balance);
            }
        }
    }
    @RequestMapping(value = "/payamount", method = RequestMethod.POST)
    @ResponseBody
    public Object[] payamount(String qid, String paymoney,String remark,String outpayname,String payaccount, ModelMap model, HttpSession session,
                             HttpServletRequest request) {
        Object[] data = new Object[3];
        PlycomresultdetailEntity plycomresultdetail=plycomresultdetailEntityService.find(qid);
        if(plycomresultdetail!=null){
            CompanyEntity companyEntity=companyEntityService.find(plycomresultdetail.getCompanyid());
            //通知企业将发放资金
            sendMessage("0","config.content1",qid,companyEntity.getAccount());
            //查询二维码是否正常使用等
            List<YshApiPayEntity> yshApiPayEntityList = new ArrayList<>();
            YshApiPayEntity yshApiPayEntity = new YshApiPayEntity();
            String payaccountdz= ConfigUtil.getConfigVal("payaccountdz");
            yshApiPayEntity.setInPaymentAccount(companyEntity.getDzaccount());
            yshApiPayEntity.setOutPaymentAccount(payaccountdz);
            yshApiPayEntity.setAmount(paymoney);
            yshApiPayEntity.setRemark(remark);
            yshApiPayEntityList.add(yshApiPayEntity);
            //获取token信息
            String token = getYshApiAccessToken();
            JSONObject resultObject1 = YshApiUtil.yshApiPay(yshApiPayEntityList,token);
            System.out.println(resultObject1);

            if(resultObject1!=null&& resultObject1.getJSONArray("result_info")!=null){
                //获取收款账号
                String stzhAccountId=companyEntity.getAccount();
                //通知企业资金已到账
                sendMessage("0","config.content2",qid,stzhAccountId);
                JSONArray result_infos = resultObject1.getJSONArray("result_info");
                String transId="";
                if(result_infos!=null&&result_infos.size()>0){
                    for (int j = 0; j < result_infos.size(); j++) {
                        JSONObject jsonObject2 = result_infos.getJSONObject(j);
                        String transState=jsonObject2.getString("transState");
                        if("1".equals(transState)){
                            transId=jsonObject2.getString("transId");
                        }
                    }
                }else{

                    String error=resultObject1.getString("err_msg");
                    data[0] = error;
                    data[1] = "error";
                    return data;
                }
                String receiptUrl =null;
                //交易查询
                if(StringUtils.isNotBlank(transId)){
                    plycomresultdetail.setCashflag("5");
                    plycomresultdetail.setUpdatetime(new Date());
                    plycomresultdetailEntityService.update(plycomresultdetail);
                    FlowProcessEntity flowProcess=flowProcessEntityService.queryListByapplyidandfid(qid,"8","3");
                    if(flowProcess!=null){
                        flowProcess.setProcessstate("2");
                        flowProcess.setUpdatetime(new Date());
                        flowProcessEntityService.update(flowProcess);
                    }

                    JSONObject resultpayinfo=null;
                    try {
                        resultpayinfo = YshApiUtil.queryTransINfo(token,transId);
                    }catch (Exception e) {
                        data[0] = "接口连接失败,请稍后重试！";
                        data[1] = "error";
                        return data;
                    }

                    JSONObject rpayinfo = resultpayinfo.getJSONObject("result_info");
                    String inpaymentaccount = rpayinfo.getString("inPaymentAccount");
                    String outpaymentaccount = rpayinfo.getString("outPaymentAccount");
                    String amount = rpayinfo.getString("amount");
                    String transstate = rpayinfo.getString("transState");
                    String transid = rpayinfo.getString("transId");
                    Date createtime = rpayinfo.getDate("createTime");
                    Date updatetime = rpayinfo.getDate("updateTime");
                    Payinfo payinfo=new Payinfo();
                    payinfo.setInfoid(KeyUtils.getKey());
                    payinfo.setCompanyid(companyEntity.getCompanyid());
                    payinfo.setPlyresultid(plycomresultdetail.getPlycomresultdetailid());
                    payinfo.setInpaymentaccount(inpaymentaccount);
                    payinfo.setOutpaymentaccount(outpaymentaccount);
                    payinfo.setAmount(amount);
                    payinfo.setTransid(transid);
                    payinfo.setTransstate(transstate);
                    payinfo.setCreatetime(createtime);
                    payinfo.setUpdatetime(updatetime);
                    payinfo.setRemark(remark);
                    payinfo.setOutpayname(outpayname);
                    payinfo.setOutpayaccount(payaccount);
                    payinfo.setInpayaccount(companyEntity.getAccount());
                    payinfo.setInpayname(companyEntity.getEnterprisename());
                    JSONObject resultObject=null;
                    try {
                        resultObject = YshApiUtil.getReceiptUrl(token, transId);
                    }catch (Exception e) {
                        data[0] = "接口连接失败,请稍后重试！";
                        data[1] = "error";
                        return data;
                    }

                    System.out.println(resultObject);
                    JSONObject result_info = resultObject.getJSONObject("result_info");
                    receiptUrl = result_info.getString("receiptUrl");
                    payinfo.setFileid(savePayfile(receiptUrl));
                    payinfo.setFilename(receiptUrl);
                    payinfoService.save(payinfo);
                }
                data[0] = "1";
                data[1] = "ok";
                data[2] =receiptUrl;
            }else{
                data[0] = "接口连接失败,请稍后重试！";
                data[1] = "error";
            }

        }

        return data;

    }


    //根据回单上传支付凭据
    private String  savePayfile(String receiptUrl){

            String  assetid = UUID.randomUUID().toString().replace("-", "");
            Uploadfile obj = new Uploadfile();
            obj.setId(assetid);
            obj.setFilecontent(null);
            obj.setFileext("pdf");
            obj.setFilename(assetid+".pdf");
            obj.setIson(Uploadfile.CONSTANT_ISON_ZERO);
            obj.setUpdatedate(new Date());
            obj.setFilesize(null);
            uploadfileService.save(obj);
            String fileurl =ConfigUtil.getConfigVal("file_save_path") + "/doc";
            try {
                Export.downloadFile(receiptUrl,fileurl);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                log.error(e.getMessage());
            }
           return  assetid;
    }




    //获取账号信息
    private String getaccountinfo(String token,String creditcode){
        JSONObject jsonObject=YshApiUtil.getAccountInfoListBycreditCode(token,creditcode);
        JSONObject result_info = jsonObject.getJSONObject("result_info");
        JSONObject accountSubInfoListCompany = result_info.getJSONObject("accountSubInfoListCompany");
        JSONArray creditCodelist1=accountSubInfoListCompany.getJSONArray(creditcode);
        String stzhAccountId="";
        for (int j = 0; j < creditCodelist1.size(); j++) {
            JSONObject jsonObject2 = creditCodelist1.getJSONObject(j);
            String accountSubName=jsonObject2.getString("accountSubName");
            stzhAccountId=jsonObject2.getString("stzhAccountId");
        }
        return stzhAccountId;
    }
    //支付发送短信

    /**
     *
     * @param type 发送类型：0短信单发；1短信群发
     * @param contentkey 短信内容key值 config.content1 config.content2
     * @param applyid 企业政策id
     * @param inPaymentAccount 收款账号
     * @return
     */
    private String sendMessage(String type,String contentkey,String applyid,String inPaymentAccount){
        PlycomresultdetailEntity plydetail=plycomresultdetailEntityService.find(applyid);
        CompanyEntity company=companyEntityService.find(plydetail.getCompanyid());
        String phone=company.getPhoneno();
        String ENTERPRISENAME=plydetail.getEnterprisename();
        String POLICYNAME=plydetail.getPolicyname();
        String PLCONDITIONS=plydetail.getPolicyconditions();
        String RDESCRIPTION=plydetail.getRdescription()+"，兑现金额"+plydetail.getAmount()+"元";
        String XXXXXX=inPaymentAccount;
        //调用短信平台接口，发送短信
        String messageContent = PropertyUtil.readProperty("centit.properties", contentkey);
        messageContent=messageContent.replace("ENTERPRISENAME", ENTERPRISENAME);
        messageContent=messageContent.replace("POLICYNAME", POLICYNAME);
        messageContent=messageContent.replace("PLCONDITIONS", PLCONDITIONS);
        messageContent=messageContent.replace("RDESCRIPTION", RDESCRIPTION);
        if("config.content2".equals(contentkey)){
            messageContent=messageContent.replace("XXXXXX", XXXXXX);
        }

        String sender = PropertyUtil.readProperty("centit.properties", "config.sender");
        String singn = PropertyUtil.readProperty("centit.properties", "config.singn");
        try {
//            String retMsg = SmsMsgUtil.smsMsgSend("0", messageContent, sender, phone, singn);
              String retMsg =SendSMSUtil.sendMessnew(phone, messageContent);
            if (CommonUtil.matchString(retMsg, "OK")) {  //校验返回retMsg中枢否有“成功”二字
                System.out.println(retMsg);
            }
        } catch (Exception e) {
            log.error("发送手机短信异常,异常原因：" + e.getCause() + ",提示信息：" + e.getMessage() + "!");
        }
        return "";
    }

    //根据回单上传支付凭据
    private void saveUploadfileandasset(String qid,String receiptUrl){
        FlowProcessEntity flowprocessentity=flowProcessEntityService.queryListByapplyidandflowname(qid,"凭据上传");
        if(flowprocessentity!=null){
            String  id = UUID.randomUUID().toString().replace("-", "");
            String  assetid = UUID.randomUUID().toString().replace("-", "");
            Uploadfile obj = new Uploadfile();
            obj.setId(assetid);
            obj.setFilecontent(null);
            obj.setFileext("pdf");
            obj.setFilename(assetid+".pdf");
            obj.setIson(Uploadfile.CONSTANT_ISON_ZERO);
            obj.setUpdatedate(new Date());
            obj.setFilesize(null);
            uploadfileService.save(obj);
            FlowProcessAssetEntity assetentity=new FlowProcessAssetEntity();
            assetentity.setId(id);
            assetentity.setAssetid(assetid);
            assetentity.setApplyid(qid);
            assetentity.setAuditid(flowprocessentity.getAuditid());
            assetentity.setFid(flowprocessentity.getFid());
            assetentity.setAssettype("pdf");
            flowProcessAssetEntityService.save(assetentity);
            try {
                Export.downloadFile(receiptUrl,"D:/doc/"+assetid+".pdf");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                log.error(e.getMessage());
            }
        }
    }


    public String getYshApiAccessToken() {
        String merchantId = "MCH2021032913535600685";
        String merchantSecret = "3aa5e2344f905191f68dbdfa6a4c6b1f";
        String grant_type = "client_credentials";
        String scope = "search";
        OauthEntity oauthEntity = new OauthEntity();
        oauthEntity.setClient_id(merchantId);
        oauthEntity.setClient_secret(merchantSecret);
        oauthEntity.setGrant_type(grant_type);
        oauthEntity.setScope(scope);
        OauthEntity temp = oauthEntityService.queryOne(oauthEntity);
        String access_token = temp.getAccess_token();
        String expires_in = temp.getExpires_in();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            if (org.apache.commons.lang.StringUtils.isBlank(access_token) || org.apache.commons.lang.StringUtils.isBlank(expires_in) || date.getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expires_in).getTime()) {
                //获取支付平台token
                String url = "http://121.196.184.146:8087/yshApi/subsidyGateway/getToken";
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("merchantId", merchantId);
                jsonObject.put("merchantSecret", merchantSecret);
                JSONObject accesstokenInfo = GetPostUtil.httpPost(url, jsonObject);

                JSONObject result_info = accesstokenInfo.getJSONObject("result_info");
                access_token = result_info.getString("token");
                int expires = result_info.getInteger("expires");
                date.setTime(date.getTime() + expires * 1000);
                expires_in = df.format(date);
                temp.setAccess_token(access_token);
                temp.setExpires_in(expires_in);
                oauthEntityService.updateOmoauth(temp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return access_token;
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
                             ModelMap model,
                             @RequestParam(required = false) String searchId) {
        model.addAttribute("searchId", searchId);
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(searchId);
            if(plycomresultdetailentity!=null){
                model.addAttribute("plycomresultdetailentity",plycomresultdetailentity);
                //政策明细
                PolicydetailEntity policydetail=policydetailService.find(plycomresultdetailentity.getPldtailid());
                model.addAttribute("policydetail",policydetail);
                CompanyEntity companyEntity=companyEntityService.queryCompanyByid(plycomresultdetailentity.getCompanyid());
                model.addAttribute("companyEntity",companyEntity);
                //所需材料
                List<PlyfileEntity> plyfileEntityList = plyfileEntityService.findPlyfileEntityByPldtailid(plycomresultdetailentity.getPldtailid());
                if(plyfileEntityList!=null&&plyfileEntityList.size()>0){
                    for(PlyfileEntity entity:plyfileEntityList){
                        //上传附件
                        List<Uploadfile> uploadfiles=uploadfileService.findUploadfilesbyfujianfileid(entity.getPlyfileid(),plycomresultdetailentity.getPlycomresultdetailid());
                        if(uploadfiles!=null&&uploadfiles.size()>0){
                            entity.setUploadfiles(uploadfiles);
                        }
                    }
                    model.addAttribute("plyfileEntityList",plyfileEntityList);
                }
                FlowProcessEntity flowprocess=flowProcessEntityService.queryListByapplyidandfid(searchId,"1");
                if(flowprocess==null){
                    flowprocess=flowProcessEntityService.queryListByapplyidandfid(searchId,"6");
                }
                model.addAttribute("flowprocess",flowprocess);
                //审核信息
                if(flowprocess!=null){
                    List<Flowauditinfo> flowauditinfos= flowauditinfoService.findByauditid(flowprocess.getAuditid());
                    model.addAttribute("flowauditinfos",flowauditinfos);
                }
                List<FlowProcessEntity> flowprocesslist=flowProcessEntityService.queryListByapplyid(searchId);
                if(flowprocesslist!=null&&flowprocesslist.size()>0){
                    model.addAttribute("flowprocesslist",flowprocesslist);
                }
            }
        }
        System.out.println("");
        return "/policyservices/check";
    }
    @RequestMapping(value = "/check1", method = RequestMethod.GET)
    public String check1(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
                        ModelMap model,
                        @RequestParam(required = false) String objid,
                        @RequestParam(required = false) String objname) {

        return "/policyservices/check1";
    }

    private Date[] initTime(String timetype, Date begintime, Date endtime) {
        Date[] times = new Date[2];
        if (StringUtils.isBlank(timetype)) {
            timetype = "jygy";
        }
        if (!"form".equals(timetype)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            Calendar cal = Calendar.getInstance();
            endtime = new Date();
            cal.setTime(endtime);
            // 近一周
            if ("jyz".equals(timetype)) {
                cal.add(Calendar.DAY_OF_MONTH, -7);
                begintime = cal.getTime();
                // 近一个月
            } else if ("jygy".equals(timetype)) {
                cal.add(Calendar.DAY_OF_MONTH, -30);
                begintime = cal.getTime();
                // 本月
            } else if ("by".equals(timetype)) {
                cal.set(Calendar.DAY_OF_MONTH, 1);
                begintime = cal.getTime();
                // 今年
            } else if ("jn".equals(timetype)) {
                String strdate = cal.get(Calendar.YEAR) + "-01-01 00:00:00";
                try {
                    begintime = sdf.parse(strdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        times[0] = begintime;
        times[1] = endtime;
        return times;
    }
    //政策提交附件同步至流程附件表
    @RequestMapping(value = "/savefilesinasset", method = RequestMethod.POST)
    @ResponseBody
    public Object[] savefilesinasset(String searchId,String issubmitoffline, ModelMap model, HttpSession session,
                                    HttpServletRequest request) {
        Object[] data = new Object[2];
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(searchId);
            if(plycomresultdetailentity!=null){
                plycomresultdetailentity.setCashflag("1");//提交待审批
                plycomresultdetailEntityService.update(plycomresultdetailentity);
                //查询政策流程表
                FlowProcessEntity flowprocessentity=flowProcessEntityService.queryListByapplyidandfid(searchId,"5");
                plycomresultdetailentity.setIssubmitoffline(issubmitoffline);
                flowprocessentity.setProcessstate("2");
                flowprocessentity.setUpdatetime(new Date());
                plycomresultdetailEntityService.update(plycomresultdetailentity);
                if(flowprocessentity!=null){
                    List<String> submitFileList=new ArrayList<>();
                    //所需材料
                    List<PlyfileEntity> plyfileEntityList = plyfileEntityService.findPlyfileEntityByPldtailid(plycomresultdetailentity.getPldtailid());
                    if(plyfileEntityList!=null&&plyfileEntityList.size()>0){
                        for(PlyfileEntity entity:plyfileEntityList){
                            JSONArray ids=new JSONArray();
                            //上传附件
                            List<Uploadfile> uploadfiles=uploadfileService.findUploadfilesbyfujianfileid(entity.getPlyfileid(),plycomresultdetailentity.getPlycomresultdetailid());
                            if(uploadfiles!=null&&uploadfiles.size()>0){
                                entity.setUploadfiles(uploadfiles);
                                for(int i=0;i<uploadfiles.size();i++){
                                    JSONObject jsonObject=new JSONObject();
                                    jsonObject.put("assetid",uploadfiles.get(i).getId());
                                    ids.add(jsonObject);
                                }
                                System.out.println(ids);
                                //String applyid, String auditid,String fid, String plyfileid, String assettype
                                List<String> fileList= flowProcessAssetEntityService.FlowProcessUploadPublicFile(ids,flowprocessentity.getApplyid(),flowprocessentity.getAuditid(),
                                        flowprocessentity.getFid(), entity.getPlyfileid(), uploadfiles.get(0).getFileext());
                                submitFileList.addAll(fileList);
                            }

                        }
                    }
                    //部门审批推送至钉钉
                    plycomresultdetailEntityService.checkToDingDing(plycomresultdetailentity,submitFileList);
                }
            }
        }


        data[0] = "0";
        data[1] = "保存成功";
        return data;

    }





    /**
     *政策兑现归档（查询）
     *
     */
    @RequestMapping(value = "/policyplaceonfile", method = RequestMethod.GET)
    public String policyplaceonfile(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable, HttpSession session, HttpServletRequest request,
                                 ModelMap model, @RequestParam(required = false) String enterprisename, @RequestParam(required = false) String begintime, @RequestParam(required = false) String endtime,
                                 @RequestParam(required = false) String cashflag) {


        Page<Object> page=plycomresultdetailEntityService.findinfoPage(enterprisename,begintime,endtime,cashflag,pageable);
        model.addAttribute("enterprisename", enterprisename);
        model.addAttribute("begintime", begintime);
        model.addAttribute("endtime", endtime);
        model.addAttribute("cashflag", cashflag);
        model.addAttribute("page", page);
        model.addAttribute("pageable", pageable);
        return "/policyservices/placeonfilelist";
    }


    /**
     *政策兑现归档详情（查询）
     *
     */
    @RequestMapping(value = "/pplaceonfiledetail/{plycomresultdetailid}", method = RequestMethod.GET)
    public String pplaceonfiledetail(@PathVariable String plycomresultdetailid, HttpSession session, HttpServletRequest request,
                                     ModelMap model, @RequestParam(required = false) String enterprisename, @RequestParam(required = false) String begintime, @RequestParam(required = false) String endtime,
                                     @RequestParam(required = false) String cashflag) {


        //获取政策信息
        PlycomresultdetailEntity plydetail=plycomresultdetailEntityService.find(plycomresultdetailid);
        model.addAttribute("plydetail", plydetail);
        PolicydetailEntity policydetail=policydetailService.find(plydetail.getPldtailid());
        model.addAttribute("policydetail",policydetail);
        //兑现流程信息om_flow_process
        //TODO
        List<FlowProcessEntity> flowprocesslist=flowProcessEntityService.queryListByapplyid(plycomresultdetailid);

        if(flowprocesslist!=null&&flowprocesslist.size()>0) {

            for (FlowProcessEntity f : flowprocesslist) {
                if("2".equals(f.getFid())||"7".equals(f.getFid())){
                    //凭据上传
                    if(StringUtils.isNotBlank(f.getUploadfileid())){
                        List<Uploadfile> uploadfiles=new ArrayList<>();
                        String[] fileids=f.getUploadfileid().split(",");
                        String[] filenames=f.getUploadfilename().split(",");
                        for(int i=0;i<fileids.length;i++){
                            Uploadfile uploadfile=new Uploadfile();
                            uploadfile.setId(fileids[i]);
                            uploadfile.setFilename(filenames[i]);
                            uploadfiles.add(uploadfile);
                        }
                        model.addAttribute("uploadfiles",uploadfiles);
                    }
                }

                if("1".equals(f.getFid())||"6".equals(f.getFid())){
                    FlowProcessEntity flowprocess=f;
                    model.addAttribute("flowprocess",flowprocess);
                    //审核信息
                    List<Flowauditinfo> flowauditinfos= flowauditinfoService.findByauditid(flowprocess.getAuditid());
                    model.addAttribute("flowauditinfos",flowauditinfos);
                }
            }
        }


        model.addAttribute("flowprocesslist", flowprocesslist);
        //获取token信息
        String token = getYshApiAccessToken();
        //付款信息
        Payinfo payinfo=payinfoService.findbyResultid(plydetail.getPlycomresultdetailid());
        model.addAttribute("payinfo", payinfo);
        //所需材料
        List<PlyfileEntity> plyfileEntityList = plyfileEntityService.findPlyfileEntityByPldtailid(plydetail.getPldtailid());
        if(plyfileEntityList!=null&&plyfileEntityList.size()>0){
            for(PlyfileEntity entity:plyfileEntityList){
                //上传附件
                List<Uploadfile> uploadfiles=uploadfileService.findUploadfilesbyfujianfileid(entity.getPlyfileid(),plydetail.getPlycomresultdetailid());
                if(uploadfiles!=null&&uploadfiles.size()>0){
                    entity.setUploadfiles(uploadfiles);
                }
            }
            model.addAttribute("plyfileEntityList",plyfileEntityList);
        }
       /* FlowProcessEntity flowprocess=flowProcessEntityService.queryListByapplyidandfid(plydetail.getPlycomresultdetailid(),"1");
        if(flowprocess==null){
            flowprocess=flowProcessEntityService.queryListByapplyidandfid(plydetail.getPlycomresultdetailid(),"6");
        }
        model.addAttribute("flowprocess",flowprocess);
        //审核信息
        if(flowprocess!=null){
            List<Flowauditinfo> flowauditinfos= flowauditinfoService.findByauditid(flowprocess.getAuditid());
            model.addAttribute("flowauditinfos",flowauditinfos);
        }*/

        return "/policyservices/placeonfile";
    }

    // 导出审核数据
    @RequestMapping(value = "/infoExport", method = RequestMethod.GET)
    public void infoExport(HttpServletResponse response, HSSFWorkbook workbook,@RequestParam(required = false) String enterprisename, @RequestParam(required = false) String begintime, @RequestParam(required = false) String endtime,
                           @RequestParam(required = false) String cashflag) {

//        List<Object[]> infos=plycomresultdetailEntityService.findinfoAll();
        List<Object[]> infos=plycomresultdetailEntityService.findinfoAll(enterprisename,begintime,endtime,cashflag);
        for (Object[] info:infos) {
            if(info[8]!=null&&StringUtils.isNotBlank(info[8].toString())){
                info[2]=info[2]+"("+info[8]+")";
            }
        }
        String titlt="政策兑现审核信息";
        String[] rowName={"企业名称","统一社会信用代码","政策类型","政策条件","政策奖励","申请金额(元)","匹配日期","状态"};
        ExportExcel expExc = new ExportExcel();
        workbook = expExc.export(titlt,rowName,infos,"check");
        if(workbook!=null){
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String(("政策兑现审核信息.xls").getBytes("gb2312"), "ISO8859-1") + "\"");
                OutputStream out = response.getOutputStream();
                workbook.write(out);
                out.close();

            } catch (IOException e) {
                System.err.println("IOException Occured");
            }
        }



    }




    // 导出支付数据
    @RequestMapping(value = "/payinfoExport", method = RequestMethod.GET)
    public void payinfoExport(HttpServletResponse response, HSSFWorkbook workbook,@RequestParam(required = false) String enterprisename, @RequestParam(required = false) String begintime, @RequestParam(required = false) String endtime,
                              @RequestParam(required = false) String cashflag) {

        List<Object[]> infos=plycomresultdetailEntityService.findpayinfoAll(enterprisename,begintime,endtime,cashflag);
        for (Object[] info:infos) {
            if(info[9]!=null&&StringUtils.isNotBlank(info[9].toString())){
                info[3]=info[3]+"("+info[9]+")";
            }

        }
        String titlt="政策兑现支付信息";
        String[] rowName={"企业名称","统一社会信用代码","企业收款账号","政策类型","政策条件","政策奖励","实际兑现金额(元)","匹配日期","状态"};
        ExportExcel expExc = new ExportExcel();
        workbook = expExc.export(titlt,rowName,infos,"pay");
        if(workbook!=null){
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String(("政策兑现支付信息.xls").getBytes("gb2312"), "ISO8859-1") + "\"");
                OutputStream out = response.getOutputStream();
                workbook.write(out);
                out.close();

            } catch (IOException e) {
                System.err.println("IOException Occured");
            }
        }


    }


    // 导出支付数据
    @RequestMapping(value = "/onfileinfoExport", method = RequestMethod.GET)
    public void onfileinfoExport(HttpServletResponse response, HSSFWorkbook workbook,@RequestParam(required = false) String enterprisename, @RequestParam(required = false) String begintime, @RequestParam(required = false) String endtime,
                                 @RequestParam(required = false) String cashflag) {
        List<Object[]> infos=plycomresultdetailEntityService.findonfileinfoAll(enterprisename,begintime,endtime,cashflag);
        for (Object[] info:infos) {
            if(info[9]!=null&&StringUtils.isNotBlank(info[9].toString())){
                info[3]=info[3]+"("+info[9]+")";
            }

        }
        String titlt="政策兑现信息";
        String[] rowName={"企业名称","统一社会信用代码","企业收款账号","政策类型","政策条件","政策奖励","兑现金额(元)","匹配日期","状态"};
        ExportExcel expExc = new ExportExcel();
        workbook = expExc.export(titlt,rowName,infos,"pay");
        if(workbook!=null){
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String(("政策兑现信息.xls").getBytes("gb2312"), "ISO8859-1") + "\"");
                OutputStream out = response.getOutputStream();
                workbook.write(out);
                out.close();

            } catch (IOException e) {
                System.err.println("IOException Occured");
            }
        }


    }

}
