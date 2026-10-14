package com.ywjc.glsyjs.sys.controller;

import com.alibaba.fastjson.JSONArray;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ywjc.glsyjs.Policyservices.entity.Payinfo;
import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import com.ywjc.glsyjs.Policyservices.service.PayinfoService;
import com.ywjc.glsyjs.Policyservices.service.PolicydeclareService;
import com.ywjc.glsyjs.Policyservices.utils.KeyMatchUtil;
import com.ywjc.glsyjs.account.entity.*;
import com.ywjc.glsyjs.account.service.*;
import com.ywjc.glsyjs.bigdata.entity.Gxcwbqytjj;
import com.ywjc.glsyjs.bigdata.service.GxcwbqytjjService;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.filter.Token;
import com.ywjc.glsyjs.core.util.*;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.service.UsercompanyEntityService;
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
import com.ywjc.glsyjs.interfaces.policyfile.entity.Policyfile;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicyfileService;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.service.TagService;
import com.ywjc.glsyjs.interfaces.utils.CommonUtil;
import com.ywjc.glsyjs.interfaces.utils.LpApiNewUtil;
import com.ywjc.glsyjs.mail.filter.HttpClientUtil;
import com.ywjc.glsyjs.mail.twocode.MatrixToImageWriter;
import com.ywjc.glsyjs.shiro.security.LoginRealm;
import com.ywjc.glsyjs.shiro.security.PasswordHelper;
import com.ywjc.glsyjs.shiro.security.RoleUsernamePasswordToken;
import com.ywjc.glsyjs.sys.entity.*;
import com.ywjc.glsyjs.sys.service.*;
import com.ywjc.glsyjs.sys.util.DateUtil;
import com.ywjc.glsyjs.sys.util.Dimension2;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class IndexController {
    private static Logger log = Logger.getLogger(IndexController.class);

    @Resource(name = "authorizedrecordServiceImpl")
    private AuthorizedrecordService authorizedrecordService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "menuServiceImpl")
    private MenuService menuService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @Resource(name = "indexServiceImpl")
    private IndexService indexService;

    @Resource(name = "organinfoServiceImpl")
    private OrganinfoService organinfoService;
    @Resource(name = "loginlogServiceImpl")
    private LoginlogService loginlogService;

    @Resource(name = "loginrecordServiceImpl")
    private LoginrecordService loginrecordService;

    @Resource(name = "ewmloginServiceImpl")
    private EwmloginService ewmloginService;

    @Resource(name = "passwordHelper")
    private PasswordHelper passwordHelper;

    @Resource(name = "usercompanyEntityServiceImpl")
    private UsercompanyEntityService usercompanyEntityService;

    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;

    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;

    @Resource(name = "plyfileEntityServiceImpl")
    private PlyfileEntityService plyfileEntityService;

    @Resource(name = "policyFileEntityServiceImpl")
    private  PolicyFileEntityService policyfileEntityService;

    @Resource(name = "uploadfileServiceImpl")
    private UploadfileService uploadfileService;

    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;

    @Resource(name = "tagServiceImpl")
    private TagService tagService;


    @Resource(name = "policydeclareServiceImpl")
    private PolicydeclareService policydeclareService;

    @Resource(name = "policydetailServiceImpl")
    private PolicydetailService policydetailService;

    @Resource(name = "flowauditinfoServiceImpl")
    private FlowauditinfoService flowauditinfoService;

    @Resource(name = "payinfoServiceImpl")
    private PayinfoService payinfoService;

    @Resource(name = "policyfileServiceImpl")
    private PolicyfileService policyfileService;


    @Resource(name = "gxcwbqytjjServiceImpl")
    private GxcwbqytjjService gxcwbqytjjService;


    /**
     * 主页
     *
     * @param model
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session, HttpServletRequest req) throws IOException {
        refreshCodenew(model,session,req);
        model.addAttribute("infotype",Constantval.INDEX_ONE);
        List<PolicyFileEntity> policyFiles=policyfileEntityService.findPolicyFiles();
        model.addAttribute("policyFiles",policyFiles);
        return "/index/login2";
//        return "/policy/newpolicy";
       // return "/index/login";
    }


    @RequestMapping(value = "/changecorp/{companyid}", method = RequestMethod.GET)
    public String changecorp(@PathVariable String companyid, ModelMap model, HttpSession session, HttpServletRequest req) throws IOException {
        String username = (String) session.getAttribute("username");
        String linknum = (String) session.getAttribute("linknum");
        String linktel = (String) session.getAttribute("linktel");
        UsercompanyEntity usercompanyEntity = usercompanyEntityService.findCompanyByDecryptInfo(username, linknum, linktel,companyid);
        session.setAttribute("isReletedCompanyFlag", true);
        session.setAttribute("logincompany",usercompanyEntity);


        return "redirect:/";

    }

    //查看查看需补充的材料
    @RequestMapping(value = "/znppxq", method = RequestMethod.GET)
    public String znppxq(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        znppxqinfo( searchId, model,  session,  req);
        //查询政策流程表
        FlowProcessEntity flowprocessentity=flowProcessEntityService.queryListByapplyidandfid(searchId,"5");
        if(flowprocessentity!=null){
            if("2".equals(flowprocessentity.getProcessstate())){
                return "/index/znppxqshow";
            }
        }
        return "/index/znppxq";
    }


    //查看查看需补充的材料
    @RequestMapping(value = "/znppxqshow", method = RequestMethod.GET)
    public String znppxqshow(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        znppxqinfo( searchId, model,  session,  req);
        return "/index/znppxqshow";
    }


    private void znppxqinfo(String searchId,ModelMap model, HttpSession session, HttpServletRequest req){
        refreshCodenew(model, session, req);
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(searchId);
            if(plycomresultdetailentity!=null){
                model.addAttribute("plycomresultdetailentity",plycomresultdetailentity);
                PolicydetailEntity policydetail=policydetailService.find(plycomresultdetailentity.getPldtailid());
                model.addAttribute("policydetail",policydetail);
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
                }else{
                    PlyfileEntity plyfileEntity=new PlyfileEntity();
                    plyfileEntity.setPolicycategoryname(plycomresultdetailentity.getPolicycategoryname());
                    plyfileEntity.setPolicycategorynumber(plycomresultdetailentity.getPolicycategorynum());
                    plyfileEntity.setPolicycategorysubname(plycomresultdetailentity.getPolicysubclassname());
                    plyfileEntity.setPolicysubclassno(plycomresultdetailentity.getPolicysubclassno());
                    plyfileEntity.setPolicyconditions(plycomresultdetailentity.getPolicyconditions());
                    plyfileEntity.setPldtailid(plycomresultdetailentity.getPldtailid());
                    plyfileEntity.setMaterialname("附件信息");
                    plyfileEntity.setStatus("1");
                    plyfileEntity.setSn("1");
                    plyfileEntityService.save(plyfileEntity);
                    plyfileEntityList = plyfileEntityService.findPlyfileEntityByPldtailid(plycomresultdetailentity.getPldtailid());
                    model.addAttribute("plyfileEntityList",plyfileEntityList);
                }
            }
        }
        model.addAttribute("infotype",Constantval.INDEX_TWO);
    }






    //查看查看需补充的材料
    @RequestMapping(value = "/upinfo", method = RequestMethod.GET)
    public String upinfo(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        model.addAttribute("searchId",searchId);
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(searchId);
            if(plycomresultdetailentity!=null){
                model.addAttribute("plycomresultdetailentity",plycomresultdetailentity);
                //政策明细
                PolicydetailEntity policydetail=policydetailService.find(plycomresultdetailentity.getPldtailid());
                model.addAttribute("policydetail",policydetail);
                CompanyEntity companyEntity=companyEntityService.queryCompanyByid(plycomresultdetailentity.getCompanyid());
                model.addAttribute("companyEntity",companyEntity);


                List<FlowProcessEntity> flowprocesslist=flowProcessEntityService.queryListByapplyid(searchId);
                if(flowprocesslist!=null&&flowprocesslist.size()>0){
                    model.addAttribute("flowprocesslist",flowprocesslist);
                    for (FlowProcessEntity f :flowprocesslist) {
                        if("1".equals(f.getFid())||"6".equals(f.getFid())){
                            model.addAttribute("flowprocess",f);
                            //审核信息
                            List<Flowauditinfo> flowauditinfos= flowauditinfoService.findByauditid(f.getAuditid());
                            model.addAttribute("flowauditinfos",flowauditinfos);
                        }else if("2".equals(f.getFid())||"7".equals(f.getFid())){
                            model.addAttribute("uflowprocess",f);
                        }

                    }
                }
            }
        }
        model.addAttribute("infotype",Constantval.INDEX_THREE);
        return "/index/upinfo";
    }

    @RequestMapping(value="/saveupinfo",method = RequestMethod.POST)
    public String saveupinfo(@Valid String applyid,String uploadfileid,String uploadfilename,String state,
                       HttpSession session, ModelMap model, HttpServletRequest request){

        if("1".equals(state)){//提交
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(applyid);
            if(plycomresultdetailentity!=null) {
                plycomresultdetailentity.setCashflag("6");//已上传凭据，待支付
                plycomresultdetailEntityService.update(plycomresultdetailentity);
            }
            //保存附件
            FlowProcessEntity flowprocessentity=flowProcessEntityService.queryListByapplyidandfid(applyid,"2","7");
            flowprocessentity.setUploadfileid(uploadfileid);
            flowprocessentity.setUploadfilename(uploadfilename);
            flowprocessentity.setProcessstate("2");
            flowprocessentity.setUpdatetime(new Date());
            flowProcessEntityService.update(flowprocessentity);

            FlowProcessEntity flowprocessnext=flowProcessEntityService.queryListByapplyidandfid(applyid,"3","8");
            flowprocessnext.setProcessstate("1");
            flowProcessEntityService.update(flowprocessnext);
            return "redirect:/wddx?&searchName=&companyid="+plycomresultdetailentity.getCompanyid();
        }else{
            //保存附件
            FlowProcessEntity flowprocessentity=flowProcessEntityService.queryListByapplyidandfid(applyid,"2","7");
            flowprocessentity.setUploadfileid(uploadfileid);
            flowprocessentity.setUploadfilename(uploadfilename);
            flowProcessEntityService.update(flowprocessentity);
            return "redirect://upinfo?searchId="+applyid;
        }


    }




    //删除我的申报
    @RequestMapping(value = "/deleteznppxq", method = RequestMethod.GET)
    public String deleteznppxq(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        String companyid=null;
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(searchId);
            if(plycomresultdetailentity!=null){
                companyid=plycomresultdetailentity.getCompanyid();
                plycomresultdetailEntityService.delete(plycomresultdetailentity);
            }
        }
        model.addAttribute("infotype",Constantval.INDEX_TWO);
        return "redirect:/zcfhxpg?&searchName=&companyid="+companyid+"&enjoypolicycategory=2";
    }



    /**
     * //我的政策
     * 根据企业名称和政策类型查询兑现评估列表
     * (享受政策类别（1 免申即享 2 少量补录即享 3 满足条件即享 4 预告）)
     * @param searchName
     * @param companyid
     * @param enjoypolicycategory
     * @param model
     * @param session
     * @param req
     * @return
     */
    @RequestMapping(value = "/zcfhxpg", method = RequestMethod.GET)
    public String zcfhxpg(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE_WEB) Pageable pageable,
                          @RequestParam(value = "searchName") String searchName,
                          @RequestParam(value = "companyid") String companyid,
                          @RequestParam(value = "enjoypolicycategory") String enjoypolicycategory,ModelMap model, HttpSession session, HttpServletRequest req) {
        model.addAttribute("search_companyid",companyid);
        model.addAttribute("enjoypolicycategory",enjoypolicycategory);
        refreshCodenew(model, session, req);
        if(StringUtils.isNotBlank(searchName)&&StringUtils.isBlank(companyid)){
            String searchName1=StringUtils.isBlank(searchName) ? "%%" : "%"+searchName.trim()+"%";
            List<CompanyEntity> companyEntityList1 = companyEntityService.queryCompanyListBySearchName(searchName1);
            if(companyEntityList1!=null&&companyEntityList1.size()==1){
                companyid=companyEntityList1.get(0).getCompanyid();
            }
        }
    /*    if(StringUtils.isBlank(companyid)){
            companyid="a3ca45bd0d99cc558aabcef03cfabbad";
        }*/
        if(StringUtils.isBlank(companyid)){
            companyid="/interface/flow/process";
        }


//        if(StringUtils.isNotBlank(companyid)){
            String type=enjoypolicycategory;
            Page<PlycomresultdetailEntity> page=plycomresultdetailEntityService.findPage(companyid,type,pageable);
            model.addAttribute("page",page);
            model.addAttribute("pageable",pageable);
            refreshtypecount(companyid,model, session, req);
            List<PolicyFileEntity>  policyfilelists=policyfileEntityService.findPolicyFileEntitiesByall();
            model.addAttribute("policyfilelists",policyfilelists);

//        }
        model.addAttribute("infotype",Constantval.INDEX_TWO);
        return "/index/zcfhxpg";

    }


    //兑现评估
    @RequestMapping(value = "/dxpg", method = RequestMethod.GET)
    public String dxpg(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE_WEB) Pageable pageable,
                          @RequestParam(value = "searchName") String searchName,
                          @RequestParam(value = "companyid") String companyid,
                          @RequestParam(value = "enjoypolicycategory") String enjoypolicycategory,ModelMap model, HttpSession session, HttpServletRequest req) {
        model.addAttribute("search_companyid",companyid);
        model.addAttribute("search_companyname",searchName);
        model.addAttribute("enjoypolicycategory",enjoypolicycategory);
        model.addAttribute("ispg",Constantval.INDEX_FOUR);
        if(StringUtils.isBlank(companyid)){
            companyid="/interface/flow/process";
        }


//        if(StringUtils.isNotBlank(companyid)){
        String type=enjoypolicycategory;
        Page<PlycomresultdetailEntity> page=plycomresultdetailEntityService.findPage(companyid,type,pageable);
        model.addAttribute("page",page);
        model.addAttribute("pageable",pageable);
        refreshtypecount(companyid,model, session, req);
        List<PolicyFileEntity>  policyfilelists=policyfileEntityService.findPolicyFileEntitiesByall();
        model.addAttribute("policyfilelists",policyfilelists);

//        }
        model.addAttribute("infotype",Constantval.INDEX_ONE);
        return "/index/dxpg";

    }

    /**
     * //兑现提醒--4）	兑现提醒的内容调整已经审批通过的即将付款和已付款到统一支付平台的政策匹配信息
     * 根据企业名称和政策类型查询兑现评估列表
     * (享受政策类别（1 免申即享 2 少量补录即享 3 满足条件即享 4 预告）)
     * @param searchName
     * @param companyid
     * @param enjoypolicycategory
     * @param model
     * @param session
     * @param req
     * @return
     */
    @RequestMapping(value = "/dxwarn", method = RequestMethod.GET, produces = {"text/javascript;charset=UTF-8"})
    public String dxwarn(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE_WEB) Pageable pageable,
                          @RequestParam(value = "searchName") String searchName,
                          @RequestParam(value = "companyid") String companyid,
                          @RequestParam(value = "enjoypolicycategory") String enjoypolicycategory,ModelMap model, HttpSession session, HttpServletRequest req) {
        model.addAttribute("search_companyid",companyid);
        model.addAttribute("search_companyid",companyid);
        model.addAttribute("enjoypolicycategory",enjoypolicycategory);
        refreshCodenew(model, session, req);
        if(StringUtils.isNotBlank(searchName)&&StringUtils.isBlank(companyid)){
            String searchName1=StringUtils.isBlank(searchName) ? "%%" : "%"+searchName.trim()+"%";
            List<CompanyEntity> companyEntityList1 = companyEntityService.queryCompanyListBySearchName(searchName1);
            if(companyEntityList1!=null&&companyEntityList1.size()==1){
                companyid=companyEntityList1.get(0).getCompanyid();
            }
        }
     /*   if(StringUtils.isBlank(companyid)){
            companyid="a3ca45bd0d99cc558aabcef03cfabbad";
        }*/

//        if(StringUtils.isNotBlank(companyid)){
            String type=enjoypolicycategory;
            Page<PlycomresultdetailEntity> page=plycomresultdetailEntityService.findPageForWarn(companyid,type,pageable);
            model.addAttribute("page",page);
            model.addAttribute("pageable",pageable);
            refreshtypecountwarn(companyid,model, session, req);
            List<PolicyFileEntity>  policyfilelists=policyfileEntityService.findPolicyFileEntitiesByall();
            model.addAttribute("policyfilelists",policyfilelists);

//        }
        model.addAttribute("infotype",Constantval.INDEX_FOUR);
        return "/index/zcfhxpgwarn";

    }

    //登录界面重定向到评估列表
    @RequestMapping(value = "/logininzccxpglist", method = RequestMethod.GET, produces = {"text/javascript;charset=UTF-8"})
    public String logininzccxpglist(@RequestParam(value = "searchName") String searchName,@RequestParam(value = "companyid") String companyid,ModelMap model, HttpSession session, HttpServletRequest req) throws UnsupportedEncodingException {
        refreshCodenew(model, session, req);
        return "redirect:/zcfhxpg?searchName="+ URLEncoder.encode(searchName, "UTF-8")+"&companyid="+companyid+"&enjoypolicycategory=1";
    }
    @RequestMapping(value = "/zcfhxpg1", method = RequestMethod.GET)
    public String zcfhxpg1(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        return "/index/zcfhxpg1";
    }

    @RequestMapping(value = "/zcfhxpg2", method = RequestMethod.GET)
    public String zcfhxpg2(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        return "/index/zcfhxpg2";
    }
    @RequestMapping(value = "/znwd", method = RequestMethod.GET)
    public String znwd(ModelMap model, HttpSession session, HttpServletRequest req) {
        model.addAttribute("infotype",Constantval.INDEX_SIX);
        refreshCodenew(model, session, req);
        return "/index/znwd";
    }
    @RequestMapping(value = "/yqlzc", method = RequestMethod.GET)
    public String yqlzc(ModelMap model, HttpSession session, HttpServletRequest req) {
        model.addAttribute("infotype",Constantval.INDEX_SEVEN);
        refreshCodenew(model, session, req);
        return "/index/yqlzc";
    }
    //一起来专窗
    @RequestMapping(value = "/yqlzcinfo", method = RequestMethod.GET)
    public String yqlzcinfo(ModelMap model, HttpSession session, HttpServletRequest req) {
        model.addAttribute("infotype",Constantval.INDEX_SEVEN);
        refreshCodenew(model, session, req);
        //查找一起来专窗角色人员
        List<User> users=userService.findByRolecode("agencystaff");
        model.addAttribute("users",users);
        return "/index/yqlinfo";
    }

    //一起来专窗
    @RequestMapping(value = "/yqlzcinfo/order", method = RequestMethod.GET)
    public String order(String companyid,ModelMap model, HttpSession session, HttpServletRequest req) {
        model.addAttribute("infotype",Constantval.INDEX_SEVEN);
        refreshCodenew(model, session, req);
        //查找一起来专窗角色人员
        List<User> users=userService.findByRolecode("agencystaff");
        model.addAttribute("users",users);
        model.addAttribute("companyid",companyid);
        CompanyEntity companyEntity=companyEntityService.find(companyid);
        model.addAttribute("companyEntity",companyEntity);
        // 生成token秘钥
        String pwdsalt = KeyUtils.getKey();
        session.setAttribute("pwdsalt", pwdsalt);
        return "/index/yqlorder";
    }
    //查看兑现明细
    @RequestMapping(value = "/dxjd", method = RequestMethod.GET)
    public String dxjd(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(searchId);
            if(plycomresultdetailentity!=null){
                model.addAttribute("plycomresultdetailentity",plycomresultdetailentity);
            }
        }
        return "/index/dxjd";
    }
    @RequestMapping(value = "/dxjdwzsbl", method = RequestMethod.GET)
    public String dxjdwzsbl(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(searchId);
            if(plycomresultdetailentity!=null){
                model.addAttribute("plycomresultdetailentity",plycomresultdetailentity);
//                searchId="d59efee87c1411ebb138b06ebf39a80b";
                List<FlowProcessEntity> flowprocesslist=flowProcessEntityService.queryListByapplyid(searchId);
                if(flowprocesslist!=null&&flowprocesslist.size()>0){
                    model.addAttribute("flowprocesslist",flowprocesslist);
                }
            }
        }
        return "/index/dxjdwzsbl";
    }

    @RequestMapping(value = "/dxdetail", method = RequestMethod.GET)
    public String dxdetail(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plydetail=plycomresultdetailEntityService.find(searchId);
            if(plydetail!=null){
                model.addAttribute("plycomresultdetailentity",plydetail);
                PolicydetailEntity policydetail=policydetailService.find(plydetail.getPldtailid());
                model.addAttribute("policydetail",policydetail);
                //付款信息
                Payinfo payinfo=payinfoService.findbyResultid(plydetail.getPlycomresultdetailid());
                model.addAttribute("payinfo", payinfo);
//                searchId="d59efee87c1411ebb138b06ebf39a80b";
                List<FlowProcessEntity> flowprocesslist=flowProcessEntityService.queryListByapplyid(searchId);
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
                model.addAttribute("flowprocess",flowprocess);*/
                //审核信息
                /*if(flowprocess!=null){
                    List<Flowauditinfo> flowauditinfos= flowauditinfoService.findByauditid(flowprocess.getAuditid());
                    model.addAttribute("flowauditinfos",flowauditinfos);
                }*/

            }
        }
        model.addAttribute("infotype",Constantval.INDEX_THREE);
        return "/index/dxdetail";
    }
    @RequestMapping(value = "/zcfwgl", method = RequestMethod.GET)
    public String zcfwgl(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCode(model, session, req);
        return "/index/zcfwgl";
    }
    @RequestMapping(value = "/spxq1", method = RequestMethod.GET)
    public String spxq1(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCode(model, session, req);
        return "/index/spxq1";
    }
    @RequestMapping(value = "/spxq", method = RequestMethod.GET)
    public String spxq(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCode(model, session, req);
        return "/index/spxq";
    }
    @RequestMapping(value = "/dxjdwzs", method = RequestMethod.GET)
    public String dxjdwzs(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        return "/index/dxjdwzs";
    }
    @RequestMapping(value = "/zclct", method = RequestMethod.GET)
    public String zclct(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCode(model, session, req);
        return "/index/zclct";
    }
    //企业授权
    /*@RequestMapping(value = "/qysq", method = RequestMethod.GET)
    public String qysq(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        if(StringUtils.isNotBlank(searchId)){
            List<AuthorizedrecordEntity> authorizedrecordlist=authorizedrecordService.findlistBycompanyid(searchId);
            if(authorizedrecordlist!=null&&authorizedrecordlist.size()>0){
                model.addAttribute("authorizedrecordlist",authorizedrecordlist);
                for(AuthorizedrecordEntity author:authorizedrecordlist){
                    if("1".equals(author.getState())){
                        model.addAttribute("authorname",author.getAuthorizedperson());
                    }
                }
            }
        }
        return "/index/qysq";
    }*/
    @RequestMapping(value = "/qysq", method = RequestMethod.GET)
    public String qysq(@RequestParam(value = "searchId") String searchId, ModelMap model, HttpSession session, HttpServletRequest req, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        model.addAttribute("infotype",Constantval.INDEX_FIVE);
        refreshCodenew(model, session, req);
        AuthorizedrecordEntity au=authorizedrecordService.findlistBycompanyidLast(searchId);
        CompanyEntity companyEntity=companyEntityService.find(searchId);
        if(companyEntity!=null){
            model.addAttribute("company",companyEntity);
        }
        if(au!=null){
            model.addAttribute("authorname",au.getAuthorizedperson());
            model.addAttribute("auid",au.getAuthorizedid());
        }

        Page<AuthorizedrecordEntity> page=null;
        if(StringUtils.isNotBlank(searchId)){
//            List<AuthorizedrecordEntity> authorizedrecordlist=authorizedrecordService.findlistBycompanyid(searchId);
            page=authorizedrecordService.findpageListBycompanyid(searchId,pageable);


        }
        model.addAttribute("searchId", searchId);
        model.addAttribute("page", page);
        model.addAttribute("pageable", pageable);
        // 生成token秘钥
        String pwdsalt = KeyUtils.getKey();
        session.setAttribute("pwdsalt", pwdsalt);

//        session.setAttribute("username","黄康");
//        session.setAttribute("linktel","17366256643");
//        session.setAttribute("linknum","320922199607126316");


        return "/index/qysq";
    }



    //查看详情
    @RequestMapping(value = "/dxtxkxs", method = RequestMethod.GET)
    public String dxtxkxs(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        model.addAttribute("infotype",Constantval.INDEX_TWO);
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(searchId);
            if(plycomresultdetailentity!=null){
                model.addAttribute("plycomresultdetailentity",plycomresultdetailentity);
                PolicydetailEntity policydetail=policydetailService.find(plycomresultdetailentity.getPldtailid());
                model.addAttribute("policydetail",policydetail);
                List<PlyfileEntity> plyfileEntityList = plyfileEntityService.findPlyfileEntityByPldtailid(plycomresultdetailentity.getPldtailid());
                if(plyfileEntityList!=null&&plyfileEntityList.size()>0){
                    model.addAttribute("plyfileEntityList",plyfileEntityList);
                }
            }
        }
        return "/index/dxtxkxs";
    }
    @RequestMapping(value = "/zcfhxpg3", method = RequestMethod.GET)
    public String zcfhxpg3(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCode(model, session, req);
        return "/index/zcfhxpg3";
    }
    @RequestMapping(value = "/newpolicy", method = RequestMethod.GET)
    public String newpolicy( @PageableDefault(page = 0, size = Constantval.PAGE_SIZE_TANCHU) Pageable pageable,
                             @RequestParam(required = false) String startdate,
                             @RequestParam(required = false) String enddate,
                             @RequestParam(required = false) String tagid,
                             @RequestParam(required = false) String key,
                             @RequestParam(required = false) String level,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        model.addAttribute("infotype",Constantval.INDEX_EIGHT);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("tagid",tagid);
        model.addAttribute("key",key);
        if(StringUtils.isNotBlank(tagid)){
            String[] tagidstrs=tagid.split(",");
            model.addAttribute("tagidstrs",tagidstrs);
        }



        Date start_date=null;
        Date end_date=null;
       /* if(startdate==null&&enddate==null){
            Long start= DateUtil.getYearBeforeThree();
            Long end= DateUtil.getYearEndTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(start);
            start_date=calendar.getTime();
            calendar.setTimeInMillis(end);
            end_date=calendar.getTime();
        }*/
        if(StringUtils.isNotBlank(startdate)){
            try {
                start_date=df.parse(startdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }if(StringUtils.isNotBlank(enddate)){
            try {
                end_date=df.parse(enddate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
//        List<PolicyFileEntity>  policyFiles=policyfileEntityService.findPolicyFilesByCondition(start_date,end_date,level,content);
        Page<Object> page=policyfileEntityService.findPolicyFiles(start_date,end_date,level,tagid,key,pageable);
//        List<Object> policyFiles=policyfileEntityService.findPolicyFiles(start_date,end_date,level,content);

            if(page!=null&&page.getContent().size()>0){
                for (Object file:page.getContent()) {
                    Object[] o=(Object[])file;
                    if(StringUtils.isNotBlank(key)){
                        o[1]= KeyMatchUtil.getMatchedString(o[1].toString(),key);
                    }
                    //查找政策标签
                    List<Object> tags=tagService.findtag((String)o[0]);

                   dealTag(tags,o);


                }

        }

        model.addAttribute("page",page);
        model.addAttribute("pageable",pageable);
        if(start_date==null){
        }else{
            model.addAttribute("startdate",start_date);
        }

        if(end_date==null){
        }else{
            model.addAttribute("enddate",end_date);
        }


        model.addAttribute("level",level);


        //判断是否绑定企业
        List<TagEntity> tags=null;
        String companyid=isCompanylogin(session);
//        companyid="e77684d4a6105f9412c9fd1f2a8ae0db";
        if(companyid!=null){
            tags=tagService.findByCompanyid(companyid);
        }else{
            tags=tagService.findAll();
        }
        if(tags!=null&&tags.size()>0){
        }else{
            tags=tagService.findAll();
        }

        model.put("tags", dealTags(tags));
        return "/policy/newpolicy";
    }

    private  void  dealTag( List<Object> tags, Object[] o){
        int heght=140;
        List<Object> policytags=new ArrayList<>();
        int tagcount1=0;
        int tagcount2=0;
        int tagcount3=0;
        int tagcount4=0;
        int tagcount5=0;
        List<Object> tags1=new ArrayList<>();
        List<Object> tags2=new ArrayList<>();
        List<Object> tags3=new ArrayList<>();
        List<Object> tags4=new ArrayList<>();
        List<Object> tags5=new ArrayList<>();
        for (int i=0;i<tags.size();i++){
            Object[] obj=(Object[])tags.get(i);
            String tagname=(String)obj[1];
            if((tagcount1+tagname.length())<=32){
                tags1.add(tags.get(i));
                tagcount1+=tagname.length();
            }else if((tagcount2+tagname.length())<=32){
                tags2.add(tags.get(i));
                tagcount2+=tagname.length();
            }else if((tagcount3+tagname.length())<=32){
                tags3.add(tags.get(i));
                tagcount3+=tagname.length();
            }else if((tagcount4+tagname.length())<=32){
                tags4.add(tags.get(i));
                tagcount4+=tagname.length();
            }else if((tagcount5+tagname.length())<=32){
                tags5.add(tags.get(i));
                tagcount5+=tagname.length();
            }

        }
        if(tagcount1!=0){
            policytags.add(tags1);
        }
        if(tagcount2!=0){
            heght=180;
            policytags.add(tags2);
        }
        if(tagcount3!=0){
            heght=220;
            policytags.add(tags3);
        }
        if(tagcount4!=0){
            heght=260;
            policytags.add(tags4);
        }
        if(tagcount5!=0){
            heght=300;
            policytags.add(tags5);
        }
        o[7]=policytags;
        o[8]=heght;

    }



    private   List<Object>  dealTags( List<TagEntity> tags){
        int heght=140;
        List<Object> policytags=new ArrayList<>();
        int tagcount1=0;
        int tagcount2=0;
        int tagcount3=0;
        int tagcount4=0;
        List<TagEntity> tags1=new ArrayList<>();
        List<TagEntity> tags2=new ArrayList<>();
        List<TagEntity> tags3=new ArrayList<>();
        List<TagEntity> tags4=new ArrayList<>();
        for (int i=0;i<tags.size();i++){
            TagEntity tag=tags.get(i);
            String tagname=tag.getTagname();
            if((tagcount1+tagname.length())<=54){
                tags1.add(tags.get(i));
                tagcount1+=tagname.length();
            }else if((tagcount2+tagname.length())<=59){
                tags2.add(tags.get(i));
                tagcount2+=tagname.length();
            }else if((tagcount3+tagname.length())<=59){
                tags3.add(tags.get(i));
                tagcount3+=tagname.length();
            }else if((tagcount4+tagname.length())<=59){
                tags4.add(tags.get(i));
                tagcount4+=tagname.length();
            }

        }
        if(tagcount1!=0){
            policytags.add(tags1);
        }
        if(tagcount2!=0){
            heght=180;
            policytags.add(tags2);
        }
        if(tagcount3!=0){
            heght=220;
            policytags.add(tags3);
        }
        if(tagcount4!=0){
            heght=260;
            policytags.add(tags4);
        }
       return policytags;


    }



    @RequestMapping(value = "/policydeclare", method = RequestMethod.GET)
    public String policydeclare( @PageableDefault(page = 0, size = Constantval.PAGE_SIZE_TANCHU) Pageable pageable,
                             @RequestParam(required = false) String startdate,
                             @RequestParam(required = false) String enddate,
                             @RequestParam(required = false) String declarename,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        model.addAttribute("infotype",Constantval.INDEX_NINE);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("declarename",declarename);
        Date start_date=null;
        Date end_date=null;
        if(startdate==null&&enddate==null){
            Long start= DateUtil.getYearStartTime();
            Long end= DateUtil.getYearEndTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(start);
            start_date=calendar.getTime();
            calendar.setTimeInMillis(end);
            end_date=calendar.getTime();
        }
        if(StringUtils.isNotBlank(startdate)){
            try {
                start_date=df.parse(startdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }if(StringUtils.isNotBlank(enddate)){
            try {
                end_date=df.parse(enddate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        declarename= StringUtils.isNotBlank(declarename)?"%"+declarename+"%":"%%";
        Page<Policydeclare> page = policydeclareService.queryPageinfo(declarename,start_date,end_date,"2",pageable);

        model.addAttribute("page",page);
        model.addAttribute("pageable",pageable);
        model.addAttribute("startdate",start_date);
        model.addAttribute("enddate",end_date);
        List<Organinfo> organinfos=organinfoService.findOrgs();
        model.addAttribute("organinfos", organinfos);


        return "/policy/policydeclare";
    }

    private String  isCompanylogin(HttpSession session){
        //判断是否绑定企业
        String username = (String) session.getAttribute("username");
        String linknum = (String) session.getAttribute("linknum");
        String linktel = (String) session.getAttribute("linktel");
        List<TagEntity> tags=null;
        String companyid=null;
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(linknum) && StringUtils.isNotBlank(linktel)) {
//                判断是否绑定企业
            List<UsercompanyEntity> usercompanyEntityList = usercompanyEntityService.findCompanyByDecryptInfo(username, linknum, linktel);
            if (CollectionUtils.isEmpty(usercompanyEntityList)) {
            } else {
                companyid=usercompanyEntityList.get(0).getCompanyid();
            }
        }
        return  companyid;
    }


    @RequestMapping(value = "/newpolicylist/{filetype}", method = RequestMethod.GET)
    public String newpolicylist(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE_TANCHU) Pageable pageable,
                                @PathVariable String filetype,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        model.addAttribute("infotype",Constantval.INDEX_ONE);

//        List<Object>  policyFiles=policyfileEntityService.findPolicyFilesByFiletype(filetype);
        Page<Object>  page=policyfileEntityService.findPolicyFilesByFiletype(filetype,pageable);
        model.addAttribute("page",page);
        model.addAttribute("pageable",pageable);
        model.addAttribute("filetype",filetype);
        List<Object> filetypes=policyfileEntityService.findFiletypeSum();
        for (Object file_type:filetypes) {
            Object[] obj=( Object[])file_type;
            model.addAttribute("filetype"+obj[0],obj[1]);
        }
        Date start_date=null;
        Date end_date=null;
        Long start= DateUtil.getYearStartTime();
        Long end= DateUtil.getYearEndTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(start);
        start_date=calendar.getTime();
        calendar.setTimeInMillis(end);
        end_date=calendar.getTime();
        model.addAttribute("startdate",start_date);
        model.addAttribute("enddate",end_date);
        //判断是否绑定企业
        List<TagEntity> tags=null;
        String companyid=isCompanylogin(session);
        if(companyid!=null){

        }else{
            tags=tagService.findAll();
        }
        model.put("tags", tags);
        return "/policy/newpolicy";
    }




    @RequestMapping(value = "/kxszc", method = RequestMethod.GET)
    public String kxszc(@RequestParam(value = "searchName") String searchName,@RequestParam(value = "companyid") String companyid,@RequestParam(value = "enjoypolicycategory") String enjoypolicycategory,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        if(StringUtils.isNotBlank(searchName)&&StringUtils.isBlank(companyid)){
            String searchName1=StringUtils.isBlank(searchName) ? "%%" : "%"+searchName.trim()+"%";
            List<CompanyEntity> companyEntityList1 = companyEntityService.queryCompanyListBySearchName(searchName1);
            if(companyEntityList1!=null&&companyEntityList1.size()==1){
                companyid=companyEntityList1.get(0).getCompanyid();
            }
        }
        if(StringUtils.isNotBlank(companyid)){

            List<PlycomresultdetailEntity> plycomresultdetailentitys=plycomresultdetailEntityService.findPlycomresultByCompanyidandtype(companyid,enjoypolicycategory);
            model.addAttribute("policylists",plycomresultdetailentitys);
            refreshtypecount(companyid,model, session, req);

            model.addAttribute("search_companyid",companyid);
            model.addAttribute("search_companyname",searchName);
        }
        return "/index/kxszc";
    }
    /**
     * 我的兑现
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/wddx", method = RequestMethod.GET)
    public String wddx(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE_WEB) Pageable pageable,
                       @RequestParam(value = "searchName") String searchName,
                       @RequestParam(value = "companyid") String companyid,ModelMap model, HttpSession session, HttpServletRequest req) {
        model.addAttribute("search_companyid",companyid);
        model.addAttribute("search_companyname",searchName);
        model.addAttribute("infotype",Constantval.INDEX_THREE);//1首页 2政策查询 3我的兑现 4兑现提醒 5企业授权 6智能问答 7一企来专窗
//        refreshCodenew(model, session, req);
 /*       if(StringUtils.isNotBlank(searchName)&&StringUtils.isBlank(companyid)){
            String searchName1=StringUtils.isBlank(searchName) ? "%%" : "%"+searchName.trim()+"%";
            List<CompanyEntity> companyEntityList1 = companyEntityService.queryCompanyListBySearchName(searchName1);
            if(companyEntityList1!=null&&companyEntityList1.size()==1){
                companyid=companyEntityList1.get(0).getCompanyid();
            }
        }*/

        if(StringUtils.isBlank(companyid)){
            companyid="a3ca45bd0d99cc558aabcef03cfabbad";
        }
        if(StringUtils.isNotBlank(companyid)){
            Page<PlycomresultdetailEntity> page=plycomresultdetailEntityService.findwddxPage(companyid,pageable);
            model.addAttribute("page",page);
            model.addAttribute("pageable",pageable);
            model.addAttribute("infocount",page.getTotalElements());
            refreshtypecount(companyid,model, session, req);
        }


        return "/index/wddx";
    }
    @RequestMapping(value = "/dxtx", method = RequestMethod.GET)
    public String dxtx(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        return "/index/dxtx";
    }
    //查看当前差距
    @RequestMapping(value = "/dxtxxq", method = RequestMethod.GET)
    public String dxtxxq(@RequestParam(value = "searchId") String searchId,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        model.addAttribute("infotype",Constantval.INDEX_TWO);//1首页 2我的政策 3我的兑现 4兑现提醒 5企业授权 6智能问答 7一企来专窗 8政策查询 9政策申报
        if(StringUtils.isNotBlank(searchId)){
            PlycomresultdetailEntity plycomresultdetailentity=plycomresultdetailEntityService.find(searchId);
            if(plycomresultdetailentity!=null){
                Calendar date = Calendar.getInstance();
                String year = String.valueOf(date.get(Calendar.YEAR)-1);
                Gxcwbqytjj gxcwbqytjj=gxcwbqytjjService.findByYear(plycomresultdetailentity.getCompanyid(),year);
                model.addAttribute("gxcwbqytjj",gxcwbqytjj);
                model.addAttribute("plycomresultdetailentity",plycomresultdetailentity);
                PolicydetailEntity policydetail=policydetailService.find(plycomresultdetailentity.getPldtailid());
                model.addAttribute("policydetail",policydetail);

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
            }
        }
        return "/index/dxtxxq";
    }
    /**
     * 后台主页
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/indexlogin", method = RequestMethod.GET)
    public String indexlogin(ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCode(model, session, req);
        return "/index/login";
    }



    /**
     * 后台主页
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session) {
        //企业数量
        List<CompanyEntity> companys=companyEntityService.queryAll();
        model.addAttribute("corptotal",companys.size());
        //已兑现政策数
        String  plycomresultcount=plycomresultdetailEntityService.counttotal();
        model.addAttribute("plycomresultcount",plycomresultcount);
        //未兑付资金（万）
        String notpaytotal=plycomresultdetailEntityService.notpaytotal();
        if(notpaytotal==null){
            model.addAttribute("notpaytotal",0);
        }else{
            Double notpaytotald=Double.valueOf(notpaytotal)/10000;
            model.addAttribute("notpaytotal",notpaytotald);
        }

        //已兑付资金（万）
        String paytotal=plycomresultdetailEntityService.paytotal();
        if(paytotal==null){
            model.addAttribute("paytotal",0);
        }else{
            Double paytotald=Double.valueOf(paytotal)/10000;
            model.addAttribute("paytotal",paytotald);
        }

        //类型列表
        List<Policyfile> policyfilesall=policyfileService.totalAll();
        List<Policyfile> policyfiles=policyfileService.totalAllTwo();
        //政策文件

        model.addAttribute("filetotal",policyfilesall.size());
        for (Policyfile policyfile :policyfiles) {
            counttypecount(policyfile);
            List<PolicydetailEntity> details=policydetailService.findPolicyFiles(policyfile.getPlfidid());
            policyfile.setDetailnum(details.size());
        }
        for (Policyfile policyfile :policyfilesall) {
            counttypecount(policyfile);
            List<PolicydetailEntity> details=policydetailService.findPolicyFiles(policyfile.getPlfidid());
            policyfile.setDetailnum(details.size());
        }
        model.addAttribute("policyfiles",policyfiles);
        model.addAttribute("policyfilesall",policyfilesall);
        //企业兑现资金排名
        List<Object> paycorps=plycomresultdetailEntityService.paycorps();
        model.addAttribute("paycorps",paycorps);
        List<Object> money_xdata=new ArrayList<>();
        List<Object> money_ydata=new ArrayList<>();
        for (Object obj:paycorps) {
            Object[] o=( Object[])obj;
            Double money=0d;
            if(o[2]!=null){
                 money=(Double)o[2]/10000;
            }

            o[1]=o[1].toString().substring(0,6);
            money_ydata.add("'"+o[1]+"'");
            money_xdata.add(money);
        }
        model.addAttribute("money_xdata",money_xdata);
        model.addAttribute("money_ydata",money_ydata);
       //企业兑现排名
        List<Object> policycorpscount=plycomresultdetailEntityService.policycorpscount();
        model.addAttribute("policycorpscount",policycorpscount);
        List<Object> corp_xdata=new ArrayList<>();
        List<Object> corp_ydata=new ArrayList<>();
        for (Object obj:policycorpscount) {
            Object[] o=( Object[])obj;
            o[1]=o[1].toString().substring(0,6);
            corp_xdata.add("'"+o[1]+"'");
            corp_ydata.add(o[2]);
        }
        model.addAttribute("corp_xdata",corp_xdata);
        model.addAttribute("corp_ydata",corp_ydata);
        //兑现过程
        List<Object> policyTypecount=plycomresultdetailEntityService.policyTypecount();
        Double cash_ydata1=0d;
        Double cash_ydata2=0d;
        Double cash_ydata3=0d;
        Double cash_ydata4=0d;
        for (Object obj:policyTypecount) {
            Object[] o=( Object[])obj;
            Character ctype=(Character)o[0];
            String type=String.valueOf(ctype);
            Double money=0d;
            if(o[1]!=null){
                money=(Double)o[1]/10000;
            }

            if("1".equals(type)){
                cash_ydata1=money;
            }
            if("2".equals(type)){
                cash_ydata2=money;
            }
            if("3".equals(type)){
                cash_ydata3=money;
            }
            if("4".equals(type)){
                cash_ydata4=money;
            }

        }

        model.addAttribute("cash_ydata1",cash_ydata1);
        model.addAttribute("cash_ydata2",cash_ydata2);
        model.addAttribute("cash_ydata3",cash_ydata3);
        model.addAttribute("cash_ydata4",cash_ydata4);
        //兑现过程-企业数量
        List<Object> policycashflagcount=plycomresultdetailEntityService.policycashflagcount();
        Integer cashcorp_ydata1=0;
        Integer cashcorp_ydata2=0;
        Integer cashcorp_ydata3=0;
        Integer cashcorp_ydata4=0;
        for (Object obj:policycashflagcount) {
            Object[] o=( Object[])obj;
            Character ctype=(Character)o[0];
            String type=String.valueOf(ctype);
            BigInteger bcorpcount=(BigInteger) o[1];
            Integer corpcount=bcorpcount.intValue();
            if("5".equals(type)){
                cashcorp_ydata1=corpcount;
            }
            if("2".equals(type)||"7".equals(type)||"6".equals(type)){
                cashcorp_ydata2=cashcorp_ydata2+corpcount;
            }
            if("1".equals(type)){
                cashcorp_ydata3=corpcount;
            }
            if("0".equals(type)){
                cashcorp_ydata4=corpcount;
            }
        }
        model.addAttribute("cashcorp_ydata1",cashcorp_ydata1);
        model.addAttribute("cashcorp_ydata2",cashcorp_ydata2);
        model.addAttribute("cashcorp_ydata3",cashcorp_ydata3);
        model.addAttribute("cashcorp_ydata4",cashcorp_ydata4);
        model.addAttribute("policyTypecount",policyTypecount);
        model.addAttribute("policycashflagcount",policycashflagcount);
        //审批统计
        Calendar calendar = Calendar.getInstance();
        String year1 = calendar.get(Calendar.YEAR)+"";//获取年份
        calendar.add(Calendar .YEAR, -1);
        String year2 = calendar.get(Calendar.YEAR)+"";//获取年份
        List<Object> policyOrgcount1=plycomresultdetailEntityService.policyOrgcount(year1);
        List<Object> policyOrgcount2=plycomresultdetailEntityService.policyOrgcount(year2);
        Integer checkdata_y1=0;
        Integer checkdata_y2=0;
        Integer checkdata_y3=0;
        Integer checkdata_y4=0;
        Integer checkdata_y5=0;
        Integer checkdata_y11=0;
        Integer checkdata_y22=0;
        Integer checkdata_y33=0;
        Integer checkdata_y44=0;
        Integer checkdata_y55=0;
        for (Object obj:policyOrgcount1) {
            Object[] o=( Object[])obj;
            String type=(String)o[0];
            BigInteger bcorpcount=(BigInteger) o[1];
            Integer corpcount=bcorpcount.intValue();
            if("科技局".equals(type)){
                checkdata_y1=corpcount;
            }
            if("发改委".equals(type)){
                checkdata_y2=cashcorp_ydata2+corpcount;
            }
            if("人社局".equals(type)){
                checkdata_y3=corpcount;
            }
            if("金融监管局".equals(type)){
                checkdata_y4=corpcount;
            }
            if("市场监管局".equals(type)){
                checkdata_y5=corpcount;
            }
        }

        for (Object obj:policyOrgcount2) {
            Object[] o=( Object[])obj;
            String type=(String)o[0];
            BigInteger bcorpcount=(BigInteger) o[1];
            Integer corpcount=bcorpcount.intValue();
            if("科技局".equals(type)){
                checkdata_y11=corpcount;
            }
            if("发改委".equals(type)){
                checkdata_y22=cashcorp_ydata2+corpcount;
            }
            if("人社局".equals(type)){
                checkdata_y33=corpcount;
            }
            if("金融监管局".equals(type)){
                checkdata_y44=corpcount;
            }
            if("市场监管局".equals(type)){
                checkdata_y55=corpcount;
            }
        }

        model.addAttribute("checkdata_y1",checkdata_y1);
        model.addAttribute("checkdata_y2",checkdata_y2);
        model.addAttribute("checkdata_y3",checkdata_y3);
        model.addAttribute("checkdata_y4",checkdata_y4);
        model.addAttribute("checkdata_y5",checkdata_y5);
        model.addAttribute("checkdata_y11",checkdata_y11);
        model.addAttribute("checkdata_y22",checkdata_y22);
        model.addAttribute("checkdata_y33",checkdata_y33);
        model.addAttribute("checkdata_y44",checkdata_y44);
        model.addAttribute("checkdata_y55",checkdata_y55);

        //资金兑付统计
        List<Object> payFileum=plycomresultdetailEntityService.payFileum();
        List<Object> policy_data=new ArrayList<>();
        int i=0;
        for (Object obj:payFileum) {
            i++;
            Object[] o=( Object[])obj;
            if(o[0]!=null){
                String filename=(String)o[0];
                Double money=0d;
                if(o[1]!=null){
                    money=(Double)o[1]/10000;
                }
                int count = new Double(money).intValue();
                String rs="{name:'"+filename+"',value: "+money+"}";
                if(filename!=null&&filename.length()>20){
                    model.addAttribute("policy_xdata"+i,filename.substring(0,20));
                }else{
                    model.addAttribute("policy_xdata"+i,filename);
                }

                model.addAttribute("policy_ydata"+i,money);
                policy_data.add(rs);
            }


        }
        model.addAttribute("payFileum",payFileum.size());
        model.addAttribute("policy_data",policy_data);
        List<Object> payOrgsum=plycomresultdetailEntityService.payOrgsum();
        Double org_xdata1=0d;
        Double org_xdata2=0d;
        Double org_xdata3=0d;
        Double org_xdata4=0d;
        Double org_xdata5=0d;

        for (Object obj:payOrgsum) {
            Object[] o=( Object[])obj;
            String org=(String)o[0];
            Double money=0d;
            if(o[1]!=null){
                money=(Double)o[1]/10000;
            }
            if("科技局".equals(org)){
                org_xdata1=money;
            }
            if("发改委".equals(org)){
                org_xdata2=money;
            }
            if("人社局".equals(org)){
                org_xdata3=money;
            }
            if("金融监管局".equals(org)){
                org_xdata4=money;
            }
            if("市场监管局".equals(org)){
                org_xdata5=money;
            }
        }
        model.addAttribute("org_xdata1",org_xdata1);
        model.addAttribute("org_xdata2",org_xdata2);
        model.addAttribute("org_xdata3",org_xdata3);
        model.addAttribute("org_xdata4",org_xdata4);
        model.addAttribute("org_xdata5",org_xdata5);


       return "/index/dashboard";
    }


    private Object[] counttypecount(Policyfile policyfile) {
        Object[] data = new Object[2];
        List<Object[]> countlists= policydetailService.findtypecountall(policyfile.getPlfidid());

        String withoutnum="",replenishnum="",improving="",prednum="";
        for(int i=0;i<countlists.size();i++){
            if(countlists.get(i)[0]!=null){
                String type=countlists.get(i)[0].toString();
                String count=countlists.get(i)[1].toString();
                if(type.contains("免申即享")){
                    withoutnum=count;
                }else if(type.contains("补充材料")){
                    replenishnum=count;
                }else if(type.contains("企业资质能力提升")){
                    improving=count;
                }else if(type.contains("可享受政策预告")){
                    prednum=count;
                }
            }
        }
        withoutnum=StringUtils.isNotBlank(withoutnum)?withoutnum:"0";
        replenishnum=StringUtils.isNotBlank(replenishnum)?replenishnum:"0";
        improving= StringUtils.isNotBlank(improving)?improving:"0";
        prednum=StringUtils.isNotBlank(prednum)?prednum:"0";
        policyfile.setWithoutnum(withoutnum);
        policyfile.setReplenishnum(replenishnum);
        policyfile.setImproving(improving);
        policyfile.setPrednum(prednum);
        return data;
    }




    @Token(remove = true)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(HttpServletRequest request, HttpSession session, @RequestParam(required = true) String code,
                          @RequestParam(required = true) String loginname, @RequestParam(required = true) String passwordDes,
                          @RequestParam(required = true) String industry, @RequestParam(required = true) String token,
                          @RequestParam(required = false, defaultValue = "false") boolean rememberMe, ModelMap model) {
        request.getSession(true);
        // 验证验证码
        String codedata = (String) session.getAttribute("vcode");
        if (StringUtils.isNotBlank(code)) {
            code = code.toLowerCase();
        }
        if (StringUtils.isNotBlank(codedata)) {
            codedata = codedata.toLowerCase();
        }
        if (StringUtils.isBlank(codedata)) {
            return "nullCode";
        }
        if (!codedata.equals(code)) {
            return "errorCode";
        }
        Subject user = SecurityUtils.getSubject();
        if (user.isAuthenticated() || user.isRemembered()) {
            user.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            return "errorSession";
        }
        // Des解密
        String pwdsalt = (String) session.getAttribute("pwdsalt");
        String password = DESUtil.entry(passwordDes, pwdsalt);
        RoleUsernamePasswordToken token2 = new RoleUsernamePasswordToken(loginname, password, rememberMe);
        try {
            user.login(token2);
            Loginrecord loginrecord = new Loginrecord();
            loginrecord.setId(KeyUtils.getKey());
            loginrecord.setLoginname(loginname);
            loginrecord.setLogintime(new Date());
            if (user.isAuthenticated()) {
                Principal principal = (Principal) user.getPrincipal();
                if (principal == null || StringUtils.isBlank(principal.getId())) {
                    return "error";
                }
                // 用户、角色、菜单信息加载
                // 删除原文件里的验证码图片
                String pathurl = request.getSession().getServletContext().getRealPath("");
                if (session.getAttribute("filename") != null) {
                    delFile(pathurl + "\\image", session.getAttribute("filename").toString());
                }
                session.removeAttribute("vcode");
                session.removeAttribute("filename");
                intUserinfoAndUserroleinfoAndmenuinfo( principal, session);
                loginlogService.saveloginlog("/index",session, request,Constantval.LOGIN_ON);
                String loginReferer = request.getHeader("Referer");
                session.setAttribute(Constantval.SESSION_LOGIN_REFERER, loginReferer);
                loginrecordService.save(loginrecord);
                return "ok";
            }
            loginrecordService.save(loginrecord);
            return "ok";
        } catch (LockedAccountException e) {
            token2.clear();
            if (user.isAuthenticated() || user.isRemembered()) {
                user.logout();
            }
            log.error(e.getMessage());
            log.info(e);
            return "locked";
        }catch (ExcessiveAttemptsException e) {
            token2.clear();
            if (user.isAuthenticated() || user.isRemembered()) {
                user.logout();
            }
            log.error(e.getMessage());
            log.info(e);
            return "errorNum";
        } catch (Exception e) {
            token2.clear();
            if (user.isAuthenticated() || user.isRemembered()) {
                user.logout();
            }
            log.error(e.getMessage());
            log.info(e);
            return "errorMsg";
        }
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "/changepwd", method = RequestMethod.GET)
    public String changepasswordPage(HttpServletRequest request, HttpSession session) {
        return "common/pwd";
    }


    @RequestMapping(value = "/savechangepwd", method = RequestMethod.POST)
    @ResponseBody
    public String changepassword(String oldpassage, String passage, String conpassage, HttpServletRequest request,
                                 HttpSession session) {
        Principal principal = (Principal) session.getAttribute("userinfo");
        /*String reg = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#%]).{10,16}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(passage);
        if (matcher.matches() == false) {
            return "noSafty";
        }*/
        if (StringUtils.isBlank(oldpassage)) {
            return "noOldpwd";
        }
        if (StringUtils.isBlank(passage)) {
            return "noNewpwd";
        }
        if (StringUtils.isBlank(conpassage)) {
            return "noConpwd";
        }
        if (!passage.equals(conpassage)) {
            return "notequal";
        }
        String id = principal.getUser().getId();
        User userinfo = userService.find(id);
        String salt = userinfo.getSalt();
        String oldpasswordtemp = passwordHelper.getPassword(salt, oldpassage);
        if (userinfo.getPassword().equals(oldpasswordtemp)) {
            String newpasswordtemp = passwordHelper.getPassword(salt, passage);
            if (oldpasswordtemp.equals(newpasswordtemp)) {
                return "equalpwd";
            }
            salt = KeyUtils.getKey();
            String passwordtemp = passwordHelper.getPassword(salt, passage);
//			User user = userService.find(id);
            if (null != userinfo) {
                userinfo.setPassword(passwordtemp);
                userinfo.setSalt(salt);
                userinfo.setIsnewpwd("Y");
                userService.update(userinfo);
            }
            RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            LoginRealm userRealm = (LoginRealm) securityManager.getRealms().iterator().next();
            userRealm.clearAllCache();
            return "OK";
        } else {
            return "erroroldpwd";
        }
    }


    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpSession session) {
//        loginlogService.saveloginlog("/logout",session, request,Constantval.LOGIN_OFF);
        Principal principal = (Principal) session.getAttribute("userinfo");
        if(principal!=null){
            User userinfo=userService.find(principal.getUser().getId());
            userinfo.setIsonline(Constantval.LOGIN_OFF);
            userService.update(userinfo);
        }
        String index = "redirect:/";
        if (principal != null) {
            User user = principal.getUser();
            index+="indexlogin";

        }
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
        }
        return index;
    }






    /**
     * @param principal
     * @param session
     */
    private void intUserinfoAndUserroleinfoAndmenuinfo( Principal principal, HttpSession session) {
        // 用户基本信息加载
		User userinfo = userService.find(principal.getId());
		// User userinfo = userService.findByIdNotDecrypt(principal.getId());
		if(userinfo!=null){
            userinfo.setIsonline(Constantval.LOGIN_ON);
            userService.update(userinfo);

            principal.setUser(userinfo);
            // 角色信息加载
            List<Role> roles = null;
            roles = roleService.findUserroles(userinfo.getId());
            List<Menu> menus = null;
            // 角色菜单信息加载
            if (!roles.isEmpty()) {
                menus = menuService.findUserMenus(getRoleids(roles));
                principal.setMenus(menuService.parseUserMenus(menus));
            }
            roles = null;
            session.setAttribute("userinfo", principal);
        }

    }




    /**
     * @param session
     */
    private void changeUserinfoAndUserroleinfoAndmenuinfo(HttpSession session, String industry) {
        Principal principal = (Principal) session.getAttribute("userinfo");
        if (!industry.equals(principal.getCurrent_industry())) {
            // 用户基本信息加载
            User userinfo = principal.getUser();
            // 角色信息加载
            principal.setCurrent_industry(industry);
            List<Role> roles = roleService.findUserroles(userinfo.getId());
            List<Menu> menus = null;
            // 角色菜单信息加载
            if (!roles.isEmpty()) {
                menus = menuService.findUserMenus(getRoleids(roles));
                principal.setMenus(menuService.parseUserMenus(menus));
            }
            roles = null;
            session.setAttribute("userinfo", principal);
        }
    }

    /**
     * @param roles
     * @return
     */
    private List<String> getRoleids(List<Role> roles) {
        List<String> ids = new ArrayList<String>();
        for (Role r : roles) {
            ids.add(r.getId());
        }
        return ids;
    }

    /**
     * 刷新验证码
     *
     * @throws IOException
     */

    @RequestMapping(value = "/reflushcode/{industry}", method = RequestMethod.POST)
    @ResponseBody
    public Object[] reflushCode(@PathVariable String industry, ModelMap model, HttpSession session,
                                HttpServletRequest req) {
        return refreshCode(model, session, req);
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @return
     */
    private void delFile(String path, String name) {
        File file = new File(path + "\\" + name + ".png");
        if (file != null && file.exists()) {
            file.delete();
        }
    }
    private Object[] refreshtypecount(String companyid,ModelMap model, HttpSession session, HttpServletRequest req) {
        Object[] data = new Object[2];
        List<Object[]> countlists= plycomresultdetailEntityService.findtypecount(companyid);
        String withoutnum="",replenishnum="",improving="",prednum="";
        for(int i=0;i<countlists.size();i++){
            if("1".equals(countlists.get(i)[1].toString())){
                withoutnum=countlists.get(i)[2].toString();
            }else if("2".equals(countlists.get(i)[1].toString())){
                replenishnum=countlists.get(i)[2].toString();
            }else if("3".equals(countlists.get(i)[1].toString())){
                improving=countlists.get(i)[2].toString();
            }else{
                prednum=countlists.get(i)[2].toString();
            }
        }
        List<Object[]> countlistsMSJX= plycomresultdetailEntityService.findtypecountMSJX(companyid);
        for(int i=0;i<countlistsMSJX.size();i++){
            if("1".equals(countlistsMSJX.get(i)[1].toString())){
                withoutnum=countlistsMSJX.get(i)[2].toString();
            }
        }

        model.addAttribute("withoutnum",StringUtils.isNotBlank(withoutnum)?withoutnum:"0");
        model.addAttribute("replenishnum",StringUtils.isNotBlank(replenishnum)?replenishnum:"0");
        model.addAttribute("improving",StringUtils.isNotBlank(improving)?improving:"0");
        model.addAttribute("prednum",StringUtils.isNotBlank(prednum)?prednum:"0");
        return data;
    }

    private Object[] refreshtypecountwarn(String companyid,ModelMap model, HttpSession session, HttpServletRequest req) {
        Object[] data = new Object[2];
        List<Object[]> countlists= plycomresultdetailEntityService.findtypecountwarn(companyid);
        String withoutnum="",replenishnum="",improving="",prednum="";
        for(int i=0;i<countlists.size();i++){
            if("1".equals(countlists.get(i)[1].toString())){
                withoutnum=countlists.get(i)[2].toString();
            }else if("2".equals(countlists.get(i)[1].toString())){
                replenishnum=countlists.get(i)[2].toString();
            }else if("3".equals(countlists.get(i)[1].toString())){
                improving=countlists.get(i)[2].toString();
            }else{
                prednum=countlists.get(i)[2].toString();
            }
        }
        model.addAttribute("withoutnum",StringUtils.isNotBlank(withoutnum)?withoutnum:"0");
        model.addAttribute("replenishnum",StringUtils.isNotBlank(replenishnum)?replenishnum:"0");
        model.addAttribute("improving",StringUtils.isNotBlank(improving)?improving:"0");
        model.addAttribute("prednum",StringUtils.isNotBlank(prednum)?prednum:"0");
        return data;
    }

    /**
     * 判断是否绑定企业
     * @param model
     * @param session
     * @param req
     * @return
     */
    private Object[] refreshCodenew(ModelMap model, HttpSession session, HttpServletRequest req) {
        Object[] data = new Object[2];
        //TODO
        //添加登录信息（测试使用用后删除）
//        session.setAttribute("username", "黄康");
//        session.setAttribute("linknum", "320922199607126316");
//        session.setAttribute("linktel", "17366256643");
//        session.setAttribute("username", "赵洋");
//        session.setAttribute("linknum", "411281199303173513");
//        session.setAttribute("linktel", "15621365228");
        //

    /*    String username = (String) session.getAttribute("username");
        String linknum = (String) session.getAttribute("linknum");
        String linktel = (String) session.getAttribute("linktel");
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(linknum) && StringUtils.isNotBlank(linktel)) {
//                判断是否绑定企业
            List<UsercompanyEntity> usercompanyEntityList = usercompanyEntityService.findCompanyByDecryptInfo(username, linknum, linktel);
            if (CollectionUtils.isEmpty(usercompanyEntityList)) {
                model.put("isReletedCompanyFlag", false);
            } else {
                model.put("isReletedCompanyFlag", true);
                model.put("companyList", usercompanyEntityList);
                model.put("search_companyid",usercompanyEntityList.get(0).getCompanyid());
                model.put("search_companyname",usercompanyEntityList.get(0).getEnterprisename());
            }
        }*/
        return data;
    }
    private Object[] refreshCode(ModelMap model, HttpSession session, HttpServletRequest req) {
        Object[] data = new Object[2];
        CreateImageCode vCode = new CreateImageCode(65, 20, 4);
        String name = KeyUtils.getKey();
        String pathurl = req.getSession().getServletContext().getRealPath("");
        FileOutputStream fos = null;
        // 删除原文件里的所有验证码图片
        if (session.getAttribute("filename") != null) {
            try {
                delFile(pathurl + "\\image", session.getAttribute("filename").toString());
            } catch (Exception e) {
            } finally {
            }
        }
        try {
            fos = new FileOutputStream(new File(pathurl + "\\image\\" + name + ".png"));
            vCode.write(fos);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            log.info(e);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.info(e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
                log.info(e);
            }
        }
        // 生成token秘钥
        String pwdsalt = KeyUtils.getKey();
        session.setAttribute("pwdsalt", pwdsalt);
        session.setAttribute("vcode", vCode.getCode());
        session.setAttribute("filename", name);
        model.addAttribute("name", name);
        data[0] = name;
        data[1] = pwdsalt;
        return data;
    }

    /**
     * 跳传二维码登录界面
     * @param model
     * @param session
     * @param req
     * @return
     */
    @RequestMapping(value = "/indexewmlogin", method = RequestMethod.GET)
    public String indexewmlogin(ModelMap model, HttpSession session, HttpServletRequest req) {
        String pwdsalt = KeyUtils.getKey();
        session.setAttribute("pwdsalt", pwdsalt);
       return "/index/ewmlogn";
        //return "/index/bangdingcompany";
    }

    /**
     * 返回二维码登录成功界面
     * @param qid
     * @param model
     * @param session
     * @param
     * @return
     */
    @RequestMapping(value = "/indexewmlogin2/{qid}", method = RequestMethod.GET)
    public String indexewmlogin2(String qid, ModelMap model, HttpSession session, HttpServletRequest request) {
        Ewmlogin ewmlogin = ewmloginService.findByQid(qid);
        if (ewmlogin != null) {
            String loginname = StringUtils.isBlank(ewmlogin.getLoginname()) ? "" : ewmlogin.getLoginname().trim();
            String username = StringUtils.isBlank(ewmlogin.getUsername()) ? "" : ewmlogin.getUsername().trim();
            String linknum = StringUtils.isBlank(ewmlogin.getLinknum()) ? "" : ewmlogin.getLinknum().trim();
            String linktel = StringUtils.isBlank(ewmlogin.getLinktel()) ? "" : ewmlogin.getLinktel().trim();
            //添加登录信息
            session.setAttribute("username", username);
            session.setAttribute("linknum", linknum);
            session.setAttribute("linktel", linktel);
            //添加登录信息
//            String message = loginnew(loginname, "Jtyxjcyyjdd@2019", request, session, model);
//            if ("ok".equals(message)) {
//                判断是否绑定企业
                List<UsercompanyEntity> usercompanyEntityList = usercompanyEntityService.findCompanyByDecryptInfo(username, linknum, linktel);
                if (CollectionUtils.isEmpty(usercompanyEntityList)) {
                    session.setAttribute("isReletedCompanyFlag",false);
                } else {
                    List<UsercompanyEntity> usercompanyList =new ArrayList<>();
                    for (UsercompanyEntity usercompany:usercompanyEntityList) {
                        boolean ishas=false;
                        for (UsercompanyEntity ucompany:usercompanyList) {
                            if(ucompany.getCompanyid().equals(usercompany.getCompanyid())&&ucompany.getEnterprisename().equals(usercompany.getEnterprisename())){
                                ishas=true;
                            }


                        }
                        if(ishas==false){
                            usercompanyList.add(usercompany);
                        }

                    }

                    session.setAttribute("logincompany",usercompanyList.get(0));
                    session.setAttribute("companyList",usercompanyList);
                    session.setAttribute("isReletedCompanyFlag",true);
                }
//            }
        }
        return "/index/login2";
    }

    /**
     * 绑定企业返回登录页面（传递绑定企业信息）
     * @param qid
     * @param model
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/companyinresultlogin", method = RequestMethod.GET)
    public String companyinresultlogin(String qid, ModelMap model, HttpSession session, HttpServletRequest request) {
        refreshCodenew(model,session,request);
        String username = (String) session.getAttribute("username");
        String linknum = (String) session.getAttribute("linknum");
        String linktel = (String) session.getAttribute("linktel");
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(linknum) && StringUtils.isNotBlank(linktel)) {
//                判断是否绑定企业
            List<UsercompanyEntity> usercompanyEntityList = usercompanyEntityService.findCompanyByDecryptInfo(username, linknum, linktel);
            if (CollectionUtils.isEmpty(usercompanyEntityList)) {
                model.put("isReletedCompanyFlag", false);
            } else {
                List<UsercompanyEntity> usercompanyList =new ArrayList<>();
                for (UsercompanyEntity usercompany:usercompanyEntityList) {
                    boolean ishas=false;
                    for (UsercompanyEntity ucompany:usercompanyList) {
                        if(ucompany.getCompanyid().equals(usercompany.getCompanyid())&&ucompany.getEnterprisename().equals(usercompany.getEnterprisename())){
                            ishas=true;
                        }


                    }
                    if(ishas==false){
                        usercompanyList.add(usercompany);
                    }

                }
                model.put("isReletedCompanyFlag", true);
                model.put("companyList", usercompanyList);
            }
        }

        model.addAttribute("infotype",Constantval.INDEX_ONE);
        List<PolicyFileEntity> policyFiles=policyfileEntityService.findPolicyFiles();
        model.addAttribute("policyFiles",policyFiles);
        return "/index/login2";
    }

    /**
     * 退出登录（清除session信息）
     * @param qid
     * @param model
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/Exitlogin", method = RequestMethod.GET)
    public String Exitlogin(String qid, ModelMap model, HttpSession session, HttpServletRequest request) {
        String username = (String) session.getAttribute("username");
        String linknum = (String) session.getAttribute("linknum");
        String linktel = (String) session.getAttribute("linktel");
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(linknum) && StringUtils.isNotBlank(linktel)) {
            session.removeAttribute("username");
            session.removeAttribute("linknum");
            session.removeAttribute("linktel");
        }

        session.removeAttribute("logincompany");
        session.removeAttribute("companyList");
        session.removeAttribute("isReletedCompanyFlag");
        session.removeAttribute("nocorpFlag");
    /*    List<UsercompanyEntity> usercompanyEntityList = usercompanyEntityService.findCompanyByDecryptInfo("葛蓉", "320123199110013423", "18913352807");

        List<UsercompanyEntity> usercompanyList =new ArrayList<>();
        for (UsercompanyEntity usercompany:usercompanyEntityList) {
            boolean ishas=false;
            for (UsercompanyEntity ucompany:usercompanyList) {
                if(ucompany.getCompanyid().equals(usercompany.getCompanyid())&&ucompany.getEnterprisename().equals(usercompany.getEnterprisename())){
                    ishas=true;
                }


            }
            if(ishas==false){
                usercompanyList.add(usercompany);
            }

        }
        model.put("isReletedCompanyFlag", true);
        model.put("companyList", usercompanyList);
        session.setAttribute("logincompany",usercompanyList.get(0));
        session.setAttribute("companyList",usercompanyList);
        session.setAttribute("isReletedCompanyFlag",true);*/

        return "redirect:/";
    }

    /**
     * 扫描二维码登录
     *
     * @param model
     * @param session
     * @param
     * @return
     */
    @RequestMapping(value = "/ewmlogin", method = RequestMethod.POST)
    @ResponseBody
    public Object[] ewmlogin(String qid, ModelMap model, HttpSession session,
                             HttpServletRequest request) {
        log.info("查询二维码是否使用的qid：" + qid);
        Object[] data = new Object[4];
        //查询二维码是否正常使用等
        Ewmlogin ewmlogin = ewmloginService.findByQid(qid);
        if (ewmlogin != null) {
            //1 未使用，2 登录成功
            if ("2".equals(ewmlogin.getEwmstate())) {
                    String loginname = StringUtils.isBlank(ewmlogin.getLoginname()) ? "" : ewmlogin.getLoginname().trim();
                    String username = StringUtils.isBlank(ewmlogin.getUsername()) ? "" : ewmlogin.getUsername().trim();
                    String linknum = StringUtils.isBlank(ewmlogin.getLinknum()) ? "" : ewmlogin.getLinknum().trim();
                    String linktel = StringUtils.isBlank(ewmlogin.getLinktel()) ? "" : ewmlogin.getLinktel().trim();
                    //添加登录信息
                    session.setAttribute("username", username);
                    session.setAttribute("linknum", linknum);
                    session.setAttribute("linktel", linktel);
//                    String message = loginnew(loginname, "Jtyxjcyyjdd@2019", request, session, model);
//                    String message="ok";
//                    if ("ok".equals(message)) {
//                      判断是否绑定企业
                        List<UsercompanyEntity> usercompanyEntityList = usercompanyEntityService.findCompanyByDecryptInfo(username, linknum, linktel);
                        if (CollectionUtils.isEmpty(usercompanyEntityList)) {
                            data[1] = "no";
                            model.put("isReletedCompanyFlag", false);
                            //根据人员信息查询社保企业
                            com.alibaba.fastjson.JSONObject jsonObject = LpApiNewUtil.legalPersonOrStaffInfo(username,linknum);
                            if(jsonObject!=null){
                                JSONArray ja = jsonObject.getJSONObject("data").getJSONArray("data");
                                if(ja!=null){
                                    //公司名称
                                    String companyName = jsonObject.getJSONObject("data").getJSONArray("data").getJSONObject(0).getString("attrib_02");
                                    //统一社会信用码
                                    String uscreditcode = jsonObject.getJSONObject("data").getJSONArray("data").getJSONObject(0).getString("attrib_01");
                                    //1: 法人  2：员工  3：无所在企业
                                    String tag = jsonObject.getJSONObject("data").getString("tag");
                                    CompanyEntity companyEntity=null;
                                    if(!"3".equals(tag)){
                                        companyEntity=companyEntityService.queryCompany(companyName,uscreditcode);
                                        if(companyEntity==null){
                                            companyEntity = new CompanyEntity();
                                            // 若公司不存在，新增公司
                                            companyEntity.setEnterprisename(companyName);
                                            companyEntity.setUscreditcode(uscreditcode);
                                            if("1".equals(tag)){
                                                companyEntity.setLrepresentative(username);
                                                companyEntity.setPhoneno(linktel);
                                            }
                                            companyEntityService.save(companyEntity);
                                        }
                                    }
                                    data[2]=tag;
                                    data[3]=companyEntity;

                                }else{
                                    //无企业的状态
                                    data[1] = "ok";
                                    data[0] = "101";
                                    session.setAttribute("nocorpFlag",true);


                                }


                            }

                        } else {
                            List<UsercompanyEntity> usercompanyList =new ArrayList<>();
                            for (UsercompanyEntity usercompany:usercompanyEntityList) {
                                if(StringUtils.isBlank(usercompany.getLinktel())){
                                    usercompany.setLinktel(linktel);
                                    usercompanyEntityService.update(usercompany);
                                    User user=userService.find(usercompany.getUserid());
                                    if(user!=null&&StringUtils.isBlank(user.getLinktel())){
                                        user.setLinktel(linktel);
                                        userService.update(user);
                                    }

                                }
                                boolean ishas=false;
                                for (UsercompanyEntity ucompany:usercompanyList) {
                                    if(ucompany.getCompanyid().equals(usercompany.getCompanyid())&&ucompany.getEnterprisename().equals(usercompany.getEnterprisename())){
                                        ishas=true;
                                    }


                                }
                                if(ishas==false){
                                    usercompanyList.add(usercompany);
                                }

                            }


                            data[1] = "ok";
                            model.put("isReletedCompanyFlag", true);
                            model.put("companyList", usercompanyList);
                            session.setAttribute("logincompany",usercompanyList.get(0));
                            session.setAttribute("companyList",usercompanyList);
                            session.setAttribute("isReletedCompanyFlag",true);
                            //授权
//                            CompanyEntity companyEntity=companyEntityService.find(usercompanyList.get(0).getCompanyid());
//                            qysq(usercompanyList.get(0).getUserid(),username,companyEntity);

                            //是否授权
              /*              List<AuthorizedrecordEntity> aus=authorizedrecordService.findsqList(usercompanyList.get(0).getCompanyid(),usercompanyList.get(0).getUserid());
                            if(aus!=null&&aus.size()>0){
                                model.put("isau", true);
                                session.setAttribute("isau",true);
                            }else{
                                model.put("isau", false);
                                session.setAttribute("isau",false);
                            }*/
                        }
                        data[0] = "101";

//                    }else{
//                        data[0] = "102";
//                        data[1] = "登录失败";
//                    }
            } else {
                data[0] = "102";
                data[1] = "二维码未使用";
            }
        } else {
            data[0] = "103";
            data[1] = "未查询到二维码";
        }
        return data;

    }
    /**
     * 创建二维码图片
     * @param model
     * @param session
     * @param req
     * @return
     */
    @RequestMapping(value = " /createewm", method = RequestMethod.POST)
    @ResponseBody
    public Object[] createewm(ModelMap model, HttpSession session,
                              HttpServletRequest req) {
        Object[] data = new Object[3];
        System.out.println("创建二维码图片！");
        //生成二维码
        String date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date());
        Properties prop = new Properties();
        String url = "";
        try {
            prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream("centit.properties"));
            url = prop.getProperty("createerweimaurl");
            if (url == null) {
                url = "";
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            log.info(e);
        }
        log.info("----获取数据的url：" + url);
        System.out.println(date + "----获取数据的url：" + url);
        if (url != null) {
            log.info(date + "开始访问生成二维码接口");
            System.out.println(date + "开始访问生成二维码接口");
            String Datajson = HttpClientUtil.sendPost(url, "{\"qrType\":\"3S31\",\"custom\":\"测试接口\"}");
            log.info(date + "生成二维码接口返回参数" + Datajson);
            System.out.println(date + "生成二维码接口返回参数" + Datajson);
            if (Datajson != null && !"".equals(Datajson)) {
                JSONObject jsonObject = JSONObject.fromObject(Datajson);
                log.info(date + "jsonObject：" + jsonObject);
                // 获取到key为shoppingCartItemList的值
                String retCode = jsonObject.getString("retCode");
                String retMessage = jsonObject.getString("retMessage");
                String qrCode = jsonObject.getString("qrCode");

                if (qrCode != null && !"".equals(qrCode)) {
                    log.info("接口返回的qrCode" + qrCode);
                    try {
                        String qid = "";
                        if (qrCode.indexOf("qid") != -1) {
                            String str1 = qrCode.substring(0, qrCode.indexOf("qid="));
                            String str2 = qrCode.substring(str1.length() + 4, qrCode.length());
                            qid = str2.substring(0, str2.indexOf("&v"));
                            log.info("截取到二维码中的qid：" + qid);
                            System.out.println("截取到二维码中的qid：" + qid);
                        }
//                        String content = "https://www.baidu.com";
                        // String path = "D:/createewm";// 二维码保存的路径
                        String filedir = req.getSession().getServletContext().getRealPath("/ewmimages");
                        log.info("二维码保存路径filedir：" + filedir);
                        System.out.println("二维码保存路径filedir：" + filedir);
                        mkdirs(filedir);
                        String codeName = UUID.randomUUID().toString();// 二维码的图片名
                        String imageType = "jpg";// 图片类型
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
                        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                        BitMatrix bitMatrix = multiFormatWriter.encode(qrCode, BarcodeFormat.QR_CODE, 400, 400, hints);
                        File file1 = new File(filedir, codeName + "." + imageType);
                        MatrixToImageWriter.writeToFile(bitMatrix, imageType, file1);
                        if (StringUtils.isNotBlank(codeName)) {
                            Ewmlogin ewmlogin = new Ewmlogin();
                            String id = UUID.randomUUID().toString();
                            id = id.replace("-", "");
                            ewmlogin.setEwmid(id);
                            ewmlogin.setEwmname(codeName);
                            ewmlogin.setEwmstate("1");//1 未使用，2 登录成功
                            ewmlogin.setEwmmessage("未使用");
                            ewmlogin.setCreatetime(new Date());
                            ewmlogin.setQid(qid);
                            ewmloginService.save(ewmlogin);
                        }
                        //获取二维码图片路径
                        String ewmurl = "/" + codeName + "." + imageType;
                        System.out.println("生成的二维码图片路径ewmurl：" + ewmurl);
                        data[0] = "0";
                        data[1] = ewmurl;
                        data[2] = qid;
                    } catch (WriterException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                        log.info(e);
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                        log.info(e);
                    }
                } else {
                    data[0] = "1";
                    data[1] = retMessage;
                    data[2] = "";
                }

            } else {
                log.info("生成二维码失败");
                data[0] = "1";
                data[1] = "生成二维码失败";
                data[2] = "";
            }

        } else {
            log.info("未获取到二维码地址");
            data[0] = "1";
            data[1] = "未获取到二维码地址";
            data[2] = "";
        }
        return data;
    }

    /**
     * ajax接收token回调地址
     * @param token
     * @return
     */
    @RequestMapping(value = "/ewmlogn", method = RequestMethod.GET, produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public String ewmlogn(String token) {
        String message = ewmqueryToken(token);
//        User userinfo=userService.findByLoginname("zhao_y");
//        String message=ewmTokenvaluebyuser("ad2Gylkt8cT++Ky5vU2ygjW3Oe96PVXDoxbxPWK0dN3HdhppbXX+MwSPTq7Prg74j2aPLC/K6n/KyGCi42dfg8wV6dFHDtMtoUD/PLeAJ+wPrEfx2KzZ13p1AHbcqVdRHkUyf2cbN3GCm1qLW9kmPmcVU3M9tkVf3yx/oakk5tgWI9FGaeJJU20ciqOEOLulLjhcQlDNvc6TZJAQA8/YbEcNHUhVj0N9XjQq4UtQw3vsL4JN8I+P0QGX5iP+HZYmR0EdfjZo9/LUc6a0qNYrq8n6SAzLW7f75sKghwB1HcEvNFQJIrJel6cVsikYZIVu");
//        String message=token;
        System.out.println("解析token方法返回信息：" + message);
        //显示登录成功(返回status101，message自己定如登录成功、登录失败)或者登录失败(返回status:102，message自己定)
        if ("true".equals(message)) {
            return "{\"status\":\"101\",\"message\":\"登录成功!\"}";
        } else {
            return "{\"status\":\"102\",\"message\":\"" + message + "\"}";
        }
    }

    /**
     * 根据token查询用户信息并解码
     * @param token
     * @return
     */
    private String ewmqueryToken(String token) {
        System.out.println("根据token值调用接口查询用户信息-------开始");
        if (StringUtils.isBlank(token) || "null".equals(token)) {
            log.info("token值不能为空");
            return "token值不能为空";
        }
        String date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date());
        Properties prop = new Properties();
        String url = "";
        try {
            prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream("centit.properties"));
            url = prop.getProperty("querytokenurl");
            if (url == null) {
                url = "";
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            log.info(e);
        }
        log.info("----获取数据的url：" + url);
        System.out.println("----获取数据的url：" + url);
        //TODO
        if (url != null) {
            url += "?token=" + token;
            log.info(date + "开始访问获取用户信息接口");
            String Datajson = HttpClientUtil.httpGet(url);
            log.info(date + "获取用户信息接口返回参数" + Datajson);
            System.out.println(date + "获取用户信息接口返回参数" + Datajson);
            if (Datajson != null && !"".equals(Datajson)) {
                JSONObject jsonObject = JSONObject.fromObject(Datajson);
                // 获取到key为shoppingCartItemList的值
                String retCode = jsonObject.getString("retCode");
                String retMessage = jsonObject.getString("retMessage");
                String tokenValue = jsonObject.getString("tokenValue");
                log.info("查询用户接口返回的tokenvalue：" + tokenValue);
                if (tokenValue != null && !"".equals(tokenValue)) {
                    String message = ewmTokenvaluebyuser(tokenValue);
                    if (!"登陆成功".equals(message)) {
                        return message;
                    }
                }
            }
        } else {
            log.info("querytokenurl未配置");
        }
        return "true";
    }

    /**
     * 解密tokenvalue
     *
     * @param tokenValue
     * @return
     */
    private String ewmTokenvaluebyuser(String tokenValue) {
        String date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date());
        System.out.println("开始解密tokenvalue值");
        if (StringUtils.isBlank(tokenValue) || "null".equals(tokenValue)) {
            log.info("tokenValue值不能为空");
            return "tokenValue值不能为空";
        }
        //解密tokenvalue
//      String re1 = Dimension2.decryptWithPublickey2("ad2Gylkt8cT++Ky5vU2yggSiWBRJ9jNunspwVuRMfXAMK9mcngedG+0xuMs1QCRTOgV3BJbABEKNmVxw/YTKMKoZlIsSpfBeSgczfEZ9mkhUxl+Zo8ExXop2fk5Eznlkz0rClwbec3TSPXZQxHBhVYxgD3f8qM4T5Lg2P/nMcfXudg+HLfddXBOVLOmYb4ksdgvlYNxgt0POTY8uRvsBnIDOHYuY2KecxA6KnNPEQW+XL32o2L8lTkV3DMN9nZixFlRMER/aaQeu7TldYyLm6FtBbWE5016NJMi0Kfs8nn5RS4DtsvjBPFTs6//bvXWoyIwRkoUCGoAcTj/EyvnzwRoH2rUV246NBGPg1rvsg38=");
        String re1 = Dimension2.decryptWithPublickey2(tokenValue);
        log.info(date + "tokenValue解密后的的参数：" + re1);
        System.out.println(date + "tokenValue解密后的的参数：" + re1);
        if (StringUtils.isNotBlank(re1) && !"null".equals(re1)) {
            JSONObject jsonre1 = JSONObject.fromObject(re1);
            if (jsonre1 != null && jsonre1.size() > 0) {
                // 获取到key为shoppingCartItemList的值
                String active = jsonre1.getString("active");
                String custom = jsonre1.getString("custom");
                String passive = jsonre1.getString("passive");
                String qid = jsonre1.getString("qid");
                String qrType = jsonre1.getString("qrType");
                String sceneID = jsonre1.getString("sceneID");

                String username = "";
                String loginname = "";
                String linknum = "";
                String linktel = "";
                if (StringUtils.isNotBlank(active) && !"null".equals(active)) {
                    log.info(date + "用户信息active：" + active);
                    JSONObject actives = JSONObject.fromObject(active);
                    username = actives.getString("name");
                    linknum = actives.getString("idNum");
                    linktel = actives.getString("phoneNum");

                    //查询用户信息添加用户信息
                    String name1 = StringUtils.isBlank(username) ? "" : username.trim();
                    List<User> userinfos = userService.findusersinnameorlinknum(name1, linknum, linktel);
                    if (userinfos != null && userinfos.size() > 0) {

                        loginname = userinfos.get(0).getLoginname();
                        //存在用户信息返回登录成功
                        log.info("用户存在正常登陆");
                        System.out.println(date + "用户存在正常登陆");
                    } else {
                        //不存在用户信息注册用户
                        log.info("不存在用户信息注册用户");
                        System.out.println(date + "不存在用户信息注册用户");
                        User userinfo = new User();
                        String id = UUID.randomUUID().toString();
                        id = id.replace("-", "");
                        userinfo.setId(id);
                        userinfo.setUsertype("5");
                        userinfo.setUsername(username);
                        userinfo.setLinknum(linknum);
                        userinfo.setLinktel(linktel);
                        userinfo.setCreateDate(new Date());
                        String salt = KeyUtils.getKey();
                        userinfo.setSalt(salt);
                        userinfo.setPassword(passwordHelper.getPassword(salt, "Jtyxjcyyjdd@2019"));
                        userinfo.setIsvaild(Constantval.CONSTANT_ISVALID);
                        userinfo.setModifyDate(new Date());
                        //用户登录名改用手机号
                        userinfo.setLoginname(linktel);
                        loginname = userinfo.getLoginname();
                        userService.save(userinfo);
                    }
                    if (StringUtils.isNotBlank(qid)) {
                        log.info(date + "用户接口传回的qid：" + qid);
                        System.out.println(date + "用户接口传回的qid：" + qid);
                        Ewmlogin ewmlogin = ewmloginService.findByQid(qid);
                        if (ewmlogin != null) {
                            ewmlogin.setEwmstate("2");
                            ewmlogin.setEwmmessage("登录成功");
                            ewmlogin.setUsername(username);
                            ewmlogin.setLoginname(loginname);
                            ewmlogin.setLinknum(linknum);
                            ewmlogin.setLinktel(linktel);
                            ewmloginService.update(ewmlogin);
                        }
                    }
                }
            }
        }
        return "登陆成功";
    }
//    private String ewmTokenvaluebyuser1(String tokenValue) {
//        //判断是否存在有认证企业
//
//    }

    /**
     * //当文件夹不存在时自动创建多层目录
     *
     * @param destPath
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }
    /**
     *
     * @param request
     * @param session
     * @param loginname
     * @param passwordDes
     * @param model
     * @return
     */
    private String loginnew(String loginname, String passwordDes, HttpServletRequest request, HttpSession session, ModelMap model) {
        request.getSession(true);
        //二维码登录不需
        /*// 验证验证码
        String codedata = (String) session.getAttribute("vcode");
        if (StringUtils.isNotBlank(code)) {
            code = code.toLowerCase();
        }
        if (StringUtils.isNotBlank(codedata)) {
            codedata = codedata.toLowerCase();
        }
        if (StringUtils.isBlank(codedata)) {
            return "nullCode";
        }
        if (!codedata.equals(code)) {
            return "errorCode";
        }*/
        Subject user = SecurityUtils.getSubject();
        if (user.isAuthenticated() || user.isRemembered()) {
            user.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            return "errorSession";
        }
        // Des解密
        String pwdsalt = (String) session.getAttribute("pwdsalt");
        String password = DESUtil.entry(passwordDes, pwdsalt);
        RoleUsernamePasswordToken token2 = new RoleUsernamePasswordToken(loginname, password, false);
        try {
            user.login(token2);
            Loginrecord loginrecord = new Loginrecord();
            loginrecord.setId(KeyUtils.getKey());
            loginrecord.setLoginname(loginname);
            loginrecord.setLogintime(new Date());
            if (user.isAuthenticated()) {
                Principal principal = (Principal) user.getPrincipal();
                if (principal == null || StringUtils.isBlank(principal.getId())) {
                    return "error";
                }
                /*// 用户、角色、菜单信息加载
                // 删除原文件里的验证码图片
                String pathurl = request.getSession().getServletContext().getRealPath("");
                if (session.getAttribute("filename") != null) {
                    delFile(pathurl + "\\image", session.getAttribute("filename").toString());
                }
                session.removeAttribute("vcode");
                session.removeAttribute("filename");*/
                intUserinfoAndUserroleinfoAndmenuinfo( principal, session);
                loginlogService.saveloginlog("/index", session, request, Constantval.LOGIN_ON);
                String loginReferer = request.getHeader("Referer");
                session.setAttribute(Constantval.SESSION_LOGIN_REFERER, loginReferer);
                loginrecordService.save(loginrecord);
                return "ok";
            }
            loginrecordService.save(loginrecord);
            return "ok";
        } catch (ExcessiveAttemptsException e) {
            token2.clear();
            if (user.isAuthenticated() || user.isRemembered()) {
                user.logout();
            }
            log.error(e.getMessage());
            log.info(e);
            return "errorNum";
        } catch (Exception e) {
            token2.clear();
            if (user.isAuthenticated() || user.isRemembered()) {
                user.logout();
            }
            log.error(e.getMessage());
            log.info(e);
            return "errorMsg";
        }
    }

    /**
     * 根据企业名称查询企业信息（包含法人手机号）
     *
     * @param enterprisename
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchCompanyandPhone", method = RequestMethod.GET)
    public Object[] searchCompanyandPhone(@RequestParam(value = "enterprisename") String enterprisename) {
        Object[] data = new Object[2];
        enterprisename = StringUtils.isBlank(enterprisename) ? "%%" : "%" + enterprisename.trim() + "%";
        List<Object[]> usercompanyEntityList = usercompanyEntityService.findCompanyByCompanyname(enterprisename);
        if (CollectionUtils.isNotEmpty(usercompanyEntityList)) {
            data[0] = "1";
            data[1] = usercompanyEntityList;
        } else {
            data[0] = "2";
            data[1] = "未获取到企业信息";
        }
        return data;
    }


    @RequestMapping(value = "/searchNewpolicyinfo", method = RequestMethod.GET)
    public String searchNewpolicyinfo(@RequestParam(value = "policyname") String policyname,ModelMap model, HttpSession session, HttpServletRequest req) {
        refreshCodenew(model, session, req);
        model.addAttribute("infotype",Constantval.INDEX_TWO);
        return "/policy/"+policyname;

    }

    @ResponseBody
    @RequestMapping(value = "/searchewmloginbyqid", method = RequestMethod.GET)
    public Object[] searchewmloginbyqid(@RequestParam(value = "qid") String qid) {
        Object[] data = new Object[3];
        Ewmlogin ewmlogin = ewmloginService.findByQid(qid);
        if (ewmlogin!=null) {
            data[0] = "1";
            data[1] = ewmlogin;
        } else {
            data[0] = "2";
            data[1] = "未获取到二维码信息";
        }
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/searchcompanybyname", method = RequestMethod.GET)
    public Object[] searchcompanybyname(@RequestParam(value = "searchName") String searchName) {
        Object[] data = new Object[2];
        if(StringUtils.isNotBlank(searchName)){
            searchName = CommonUtil.generateFuzzyString(searchName);
            List<CompanyEntity> companyEntityList = companyEntityService.queryCompanyListBySearchName(searchName);
            if(companyEntityList!=null&&companyEntityList.size()>0){
                if(companyEntityList.size()>1){
                    data[0] = "1";
                    data[1] = companyEntityList;
                }else{
                    data[0] = "2";
                    data[1] = companyEntityList.get(0);
                }

            }else{
                data[0] = "3";
                data[1] = "未获取到企业信息";
            }

        }else{
            data[0] = "3";
            data[1] = "请输入企业名称或统一社会信用代码";
        }
        return data;
    }
//暂存保存文件关联id
    @RequestMapping(value = "/saveuploadfiles", method = RequestMethod.POST)
    @ResponseBody
    public Object[] saveuploadfiles(String fujianlistid,String plycomresultid,String upfileids, ModelMap model, HttpSession session,
                             HttpServletRequest request) {
        Object[] data = new Object[2];
        String[] ids=null;
        if(StringUtils.isNotBlank(upfileids)){
            ids = upfileids.split(",");
        }
        if(upfileids!=null&&ids.length>0){
            for(int i=0;i<ids.length;i++){
                if(StringUtils.isNotBlank(ids[i])){
                    Uploadfile uploadfile=uploadfileService.find(ids[i]);
                    if(uploadfile!=null){
                        uploadfile.setFujianfileid(fujianlistid);
                        uploadfile.setPlycomresultid(plycomresultid);
                        uploadfileService.update(uploadfile);
                    }
                }
            }
        }
        List<Uploadfile> fujianlist=uploadfileService.findUploadfilesbyfujianfileid(fujianlistid,plycomresultid);
        if (fujianlist != null&&fujianlist.size()>0){
            data[0] = "1";
            data[1] = "保存成功";
        }else{
            data[0] = "0";
            data[1] = "保存失败";
        }
        return data;

    }

    //删除附件
    @RequestMapping(value = "/deletefujian", method = RequestMethod.POST)
    @ResponseBody
    public Object[] deletefujian(String id, ModelMap model, HttpSession session,
                                     HttpServletRequest request) {
        Object[] data = new Object[2];
        if(StringUtils.isNotBlank(id)){
//          Uploadfile uploadfile=uploadfileService.find(id);
            uploadfileService.delete(id);
        }
        data[0] = "0";
        data[1] = "删除成功";
        return data;

    }

    @RequestMapping(value = "/jylogin", method = RequestMethod.GET)
    public String dojyLogin(HttpServletRequest request, HttpSession session, ModelMap model) {
        request.getSession(true);
        // 获取单点登录的用户名
        AttributePrincipal principal1=null;
        try {
             principal1 = (AttributePrincipal) request.getUserPrincipal();
        }catch (Exception e) {

        }

        String username="";
        if (principal1!=null)
            username= principal1.getName();
        String loginname=username;
//        loginname="shaoaj";
        User userinfo=userService.findByLoginname(loginname);
        Principal principal=new Principal();
        principal.setId(userinfo.getId());
        principal.setUsername(username);
        // 用户、角色、菜单信息加载
        intUserinfoAndUserroleinfoAndmenuinfo(principal, session);
        Loginrecord loginrecord = new Loginrecord();
        loginrecord.setId(KeyUtils.getKey());
        loginrecord.setLoginname(loginname);
        loginrecord.setLogintime(new Date());
        loginrecordService.save(loginrecord);
        return "redirect:/index";
//        return "/index/dashboard";

    }




    private void qysq(String userid,String username, CompanyEntity companyEntity ){
        List<AuthorizedrecordEntity> author=authorizedrecordService.findsqList(companyEntity.getCompanyid(),userid);
        if(author!=null&&author.size()>0){

        }else{
            AuthorizedrecordEntity authorizedrecordEntity=new AuthorizedrecordEntity();
            String id = UUID.randomUUID().toString();
            id = id.replace("-", "");
            authorizedrecordEntity.setAuthorizedid(id);
            authorizedrecordEntity.setCompanyid(companyEntity.getCompanyid());
            authorizedrecordEntity.setCompanyname(companyEntity.getEnterprisename());
            authorizedrecordEntity.setLegalperson(companyEntity.getLrepresentative());
            authorizedrecordEntity.setAuthorizedpersonid(userid);
            authorizedrecordEntity.setAuthorizedperson(username);
            authorizedrecordEntity.setAuthorizedtime(new Date());
            authorizedrecordEntity.setState("0");
            authorizedrecordService.save(authorizedrecordEntity);
        }


    }



    /**
     * 查询当前操作人是否有权限操作
     *
     */
    @ResponseBody
    @RequestMapping(value = "/isauthor", method = RequestMethod.POST)
    public String isauthor(String usercompanyid,HttpSession session ) {
        String isauthor="2";
        UsercompanyEntity logincompany=usercompanyEntityService.find(usercompanyid);
        if(logincompany!=null){
            isauthor=logincompany.getIslegalperson();
        }

        return isauthor;

    }


}


