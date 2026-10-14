package com.ywjc.glsyjs.Policyservices.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.Policyservices.entity.Policymaterial;
import com.ywjc.glsyjs.Policyservices.entity.Policymateriallist;
import com.ywjc.glsyjs.Policyservices.service.PolicymateriallistService;
import com.ywjc.glsyjs.Policyservices.utils.ExportExcel;
import com.ywjc.glsyjs.Policyservices.utils.KeyMatchUtil;
import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.common.validate.ValidateBinding;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.service.UsercompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
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
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import com.ywjc.glsyjs.sys.service.PolicyFileEntityService;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/policyfilemgr")
public class PolicyFileController {
    private static Logger log = Logger.getLogger(PolicyFileController.class);

    @Resource(name = "policyfileServiceImpl")
    private PolicyfileService policyfileService;

    @Resource(name = "loginlogServiceImpl")
    private LoginlogService loginlogService;

    @Resource(name = "policyFileEntityServiceImpl")
    private PolicyFileEntityService policyfileEntityService;

    @Resource(name = "policydetailServiceImpl")
    private PolicydetailService policydetailService;

    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;

    @Resource(name = "usercompanyEntityServiceImpl")
    private UsercompanyEntityService usercompanyEntityService;

    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;

    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;

    @Resource(name = "plyfileEntityServiceImpl")
    private PlyfileEntityService plyfileEntityService;

    @Resource(name = "uploadfileServiceImpl")
    private UploadfileService uploadfileService;

    @Resource(name = "organinfoServiceImpl")
    private OrganinfoService organinfoService;

    @Resource(name = "tagServiceImpl")
    private TagService tagService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
                       ModelMap model,HttpSession session,
                       @RequestParam(required = false) String policyname,
                       @RequestParam(required = false) String content,
                       @RequestParam(required = false) String fileorg,
                       @RequestParam(required = false) String filelevel,
                       @RequestParam(required = false) String state,
                       @RequestParam(required = false) String infotype,
                       @RequestParam(required = false) Date startdate,
                       @RequestParam(required = false) Date enddate,
                       HttpServletRequest request) {
        loginlogService.saveloginlog("/policyfilemgr/list",session, request,Constantval.LOGIN_VISIT);
        model.addAttribute("policyname", policyname);
        model.addAttribute("content", content);
        model.addAttribute("fileorg", fileorg);
        model.addAttribute("state", state);
        model.addAttribute("filelevel", filelevel);
        model.addAttribute("startdate", startdate);
        model.addAttribute("enddate", enddate);

        if("1".equals(infotype)){
            policyname= (String)session.getAttribute("policyname");
            content=(String) session.getAttribute("content");
            filelevel=(String)session.getAttribute("filelevel");

        }else{
            session.setAttribute("policyname",policyname);
            session.setAttribute("content",content);
            session.setAttribute("filelevel",filelevel);
        }



        policyname=StringUtils.isNotBlank(policyname)?"%"+policyname+"%":"%%";
        content=StringUtils.isNotBlank(content)?"%"+content+"%":"%%";
        filelevel=StringUtils.isNotBlank(filelevel)?"%"+filelevel+"%":"%%";
        state=StringUtils.isNotBlank(state)?"%"+state+"%":"%%";
        Page<Policyfile> page=policyfileService.queryPageinfo(policyname,content,filelevel,state,pageable);
        model.addAttribute("page", page);
        model.addAttribute("pageable", pageable);
        return "/policyfilemgr/list";
    }

    /**
     * 新增区县机构信息
     */
    @RequestMapping(value="/new")
    public String addOrganinfo(ModelMap model,HttpSession session,HttpServletRequest request)
    {
        Principal principal = (Principal) session.getAttribute("userinfo");
        loginlogService.saveloginlog("/policyfilemgr/new",session, request,Constantval.LOGIN_VISIT);
        List<Organinfo> organs=organinfoService.findOrgnames();
        model.addAttribute("organs", organs);
        return "/policyfilemgr/new";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(@Valid Policyfile info,BindingResult bindingResult, HttpSession session,
                         ModelMap model, HttpServletRequest request){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String releasetime=info.getReleasetimestr();
        String invalidtime=info.getInvalidtimestr();
        if(StringUtils.isNotBlank(releasetime)){
            Date rdate= null;
            try {
                rdate = sdf.parse(releasetime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            info.setReleasetime(rdate);
        }

        if(StringUtils.isNotBlank(invalidtime)){
            Date idate= null;
            try {
                idate = sdf.parse(invalidtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            info.setInvalidtime(idate);
        }

        if(StringUtils.isBlank(info.getPlfidid())){
            info.setPlfidid(KeyUtils.getKey());
            info.setState("1");
            info.setDispatchtime(new Date());
            String maxnum=policyfileService.findMaxNum();
            Integer imaxnum=Integer.parseInt(maxnum)+1;
            info.setSerialnum(String.valueOf(imaxnum));
            policyfileService.save(info);
        }else{
            Policyfile infoold=policyfileService.find(info.getPlfidid());
            infoold.setPolicyname(info.getPolicyname().trim());
            infoold.setFwunits(info.getFwunits());
            infoold.setFilelevel(info.getFilelevel());
            infoold.setFiletype(info.getFiletype());
            infoold.setReleasetime(info.getReleasetime());
            infoold.setFwtitanic(info.getFwtitanic());
            infoold.setFilecontent(info.getFilecontent());
            infoold.setInvalidtime(info.getInvalidtime());
            infoold.setUploadfileid(info.getUploadfileid());
            infoold.setUploadfilename(info.getUploadfilename());
            infoold.setOporgid(info.getOporgid());
            infoold.setOporgname(info.getOporgname());
            policyfileService.update(infoold);
//            System.out.println(info.getFilecontent());
        }

        return Constantval.CONSTANT_SUCCESS;
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String editinfo(@PathVariable String id, ModelMap model, HttpServletRequest request, HttpSession session){
        Principal principal = (Principal) session.getAttribute("userinfo");
        loginlogService.saveloginlog("/policyfilemgr/edit",session, request,Constantval.LOGIN_VISIT);
        Policyfile policyfile=policyfileService.find(id);
        if(StringUtils.isNotBlank(policyfile.getUploadfileid())){
            policyfile.setUploadfileList(getUpLoadfile(policyfile.getUploadfileid(),policyfile.getUploadfilename()));
        }
        model.addAttribute("policyfile", policyfile);
        List<Organinfo> organs=organinfoService.findOrgnames();
        model.addAttribute("organs", organs);
        return "/policyfilemgr/new";
    }

    @RequestMapping(value="/show/{id}",method = RequestMethod.GET)
    public String show(@PathVariable String id, ModelMap model, HttpServletRequest request, HttpSession session){
        Principal principal = (Principal) session.getAttribute("userinfo");
        loginlogService.saveloginlog("/policyfilemgr/show",session, request,Constantval.LOGIN_VISIT);
        Policyfile policyfile=policyfileService.find(id);
        if(StringUtils.isNotBlank(policyfile.getUploadfileid())){
            policyfile.setUploadfileList(getUpLoadfile(policyfile.getUploadfileid(),policyfile.getUploadfilename()));
        }
        model.addAttribute("policyfile", policyfile);
        return "/policyfilemgr/show";
    }

    //政策拆解明细
    @RequestMapping(value="/policydetail/{plfidid}",method = RequestMethod.GET)
    public String policydetail(@PathVariable String plfidid,String isdelete,@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
                               ModelMap model, HttpServletRequest request, HttpSession session){
        Policyfile policyfile=policyfileService.find(plfidid);
        Page<PolicydetailEntity> page=policydetailService.findPolicyFilesPage(plfidid,isdelete,pageable);
        model.addAttribute("page", page);
        model.addAttribute("pageable", pageable);
        model.addAttribute("isdelete", isdelete);
        model.addAttribute("policyfile", policyfile);
        return "/policyfilemgr/detaillist";
    }


    @RequestMapping(value="/newdetail/{plfidid}",method = RequestMethod.GET)
    public String newdetail(@PathVariable String plfidid,@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
                               ModelMap model, HttpServletRequest request, HttpSession session){
        PolicydetailEntity policydetail=new PolicydetailEntity();
        policydetail.setPlfidid(plfidid);
        model.addAttribute("policydetail", policydetail);
        return "/policyfilemgr/detail";
    }

    //确认明细
    @RequestMapping(value="/confirm/{pldtailid}/{state}",method = RequestMethod.GET)
    public String confirm(@PathVariable String pldtailid, @PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
        loginlogService.saveloginlog("/policyfilemgr/confirm",session, request,Constantval.LOGIN_VISIT);
        PolicydetailEntity policydetail=policydetailService.find(pldtailid);
        policydetail.setState(state);
        policydetailService.update(policydetail);
        return "redirect:/policyfilemgr/policydetail/"+policydetail.getPlfidid();
    }





    @RequestMapping(value="/confirmmore",method = RequestMethod.POST)
    @ResponseBody
    public String confirmmore( String pldtailids,  String state){
        String[] pids=pldtailids.split(",");
        for (String pid:pids) {
            PolicydetailEntity policydetail=policydetailService.find(pid);
            policydetail.setState(state);
            policydetailService.update(policydetail);
        }


        return Constantval.CONSTANT_SUCCESS;
    }

    //补充材料
    @RequestMapping(value="/material/{pldtailid}",method = RequestMethod.GET)
    public String material(@PathVariable String pldtailid, ModelMap model, HttpServletRequest request, HttpSession session){
        PolicydetailEntity policydetail=policydetailService.find(pldtailid);
        //所需材料
        List<PlyfileEntity> plyfiles = plyfileEntityService.findPlyfileEntityByPldtailid(pldtailid);
        model.addAttribute("policydetail", policydetail);
        model.addAttribute("plyfiles", plyfiles);
        return "/policyfilemgr/detail";
    }


    /**
     * 保存申报材料
     */
    @RequestMapping(value="/detailsave",method = RequestMethod.POST)
    public String detailsave(@Valid PolicydetailEntity info, BindingResult bindingResult, HttpSession session,
                               ModelMap model, HttpServletRequest request) {
        loginlogService.saveloginlog("/policyfilemgr/detailsave",session, request,Constantval.LOGIN_VISIT);

        Principal principal = (Principal) session.getAttribute("userinfo");
        User user = principal.getUser();
        if(StringUtils.isNotBlank(info.getPldtailid())) {
            PolicydetailEntity oldpdetail = policydetailService.find(info.getPldtailid());
            oldpdetail.setNplcategory(info.getNplcategory());
            oldpdetail.setPlsubclassname(info.getPlsubclassname());
            oldpdetail.setPlconditions(info.getPlconditions());
            oldpdetail.setRdescription(info.getRdescription());
            oldpdetail.setDeclareconditions(info.getDeclareconditions());
            oldpdetail.setRamount(info.getRamount());
            oldpdetail.setThpath(info.getThpath());
            oldpdetail.setFeaunit(info.getFeaunit());
            oldpdetail.setPevaluationdepartment(info.getPevaluationdepartment());
            oldpdetail.setMinamount(info.getMinamount());
            oldpdetail.setMaxamount(info.getMaxamount());
            oldpdetail.setSerialnum(info.getSerialnum());
            policydetailService.update(oldpdetail);

        }else{
            info.setPldtailid(KeyUtils.getKey());
            info.setState("0");
            String serialnum=policydetailService.findMax(info.getPlfidid());
            if(StringUtils.isBlank(serialnum)){
                serialnum="0";
            }
            info.setSerialnum( Integer.parseInt(serialnum)+1);
            policydetailService.save(info);
        }
        List<Policymateriallist> materiallists = info.getMateriallists();
        if (materiallists != null && materiallists.size() > 0) {
            plyfileEntityService.updatePlyfile(materiallists, info);
        }

        return "redirect://policyfilemgr/policydetail/"+info.getPlfidid();
    }


   //querytype=1查看原文 querytype=2查看奖励明细
    @RequestMapping(value="/webshow/{id}/{querytype}",method = RequestMethod.GET)
    public String webshow(@PathVariable String id,@PathVariable String querytype,String tagid,String key,String companyid, ModelMap model, HttpServletRequest request, HttpSession session){
        Policyfile policyfile=policyfileService.find(id);
        model.addAttribute("querytype", querytype);
        model.addAttribute("key", key);
        if(StringUtils.isNotBlank(policyfile.getUploadfileid())){
            policyfile.setUploadfileList(getUpLoadfile(policyfile.getUploadfileid(),policyfile.getUploadfilename()));
        }

        List<PolicydetailEntity> details=null;
        if(StringUtils.isNotBlank(tagid)){
            if(tagid.indexOf(",")==-1){
                tagid=tagid+"," ;
            }
            String[] tagidstrs=tagid.split(",");
            model.addAttribute("tagidstrs",tagidstrs);
            details=policydetailService.findPolicyDetailTag(policyfile.getPlfidid(),tagidstrs);
        }else{
            details=policydetailService.findPolicyFiles(policyfile.getPlfidid());
        }

        String content="";
        if(StringUtils.isNotBlank(key)){
            String filecontent=policyfile.getFilecontent();
            content= KeyMatchUtil.getMatchedString(filecontent,key);
        }else{
            content=policyfile.getFilecontent();
        }

        if(details!=null&&details.size()>0){
            for (PolicydetailEntity detail:details) {
                String s1=KeyMatchUtil.getMatchedString(detail.getNplcategory(),key);
                String s2=KeyMatchUtil.getMatchedString(detail.getPlsubclassname(),key);
                String s3=KeyMatchUtil.getMatchedString(detail.getPlconditions(),key);
                String s4=KeyMatchUtil.getMatchedString(detail.getRdescription(),key);
                detail.setNplcategorystr(s1);
                detail.setPlsubclassnamestr(s2);
                detail.setPlconditionsstr(s3);
                detail.setRdescriptionstr(s4);
                if(StringUtils.isNotBlank(companyid)){
                    //申报补充材料的政策
                    Calendar date = Calendar.getInstance();
                    String year = String.valueOf(date.get(Calendar.YEAR)-1);
                    if(StringUtils.isNotBlank(detail.getNplcategory())){
                        if(detail.getNplcategory().contains("专利")){

                        }else{
                            PlycomresultdetailEntity plycomresultdetail=plycomresultdetailEntityService.findPlycomresult(companyid,detail.getPldtailid(),year);
                            if(plycomresultdetail!=null){
                                detail.setSbstate("1");
                            }
                        }
                    }


                }
            }
        }
        model.addAttribute("content", content);
        model.addAttribute("policyfile", policyfile);
        model.addAttribute("tagid", tagid);

        model.addAttribute("details", details);
        model.addAttribute("infotype",Constantval.INDEX_EIGHT);
        //查找政策标签
        List<Object> tags=tagService.findtag(id);
        model.addAttribute("tags", tags);
        if("1".equals(querytype)){
            return "/policyfilemgr/webshow";
        }
        return "/policyfilemgr/webshowdetail";
    }




    private List<Uploadfile> getUpLoadfile(String uploadfileid, String uploadfilename){
        List<Uploadfile> files=new ArrayList<Uploadfile>();
        if(org.apache.commons.lang.StringUtils.isNotBlank(uploadfileid)&& org.apache.commons.lang.StringUtils.isNotBlank(uploadfilename)){
            String[] uploadfileids=uploadfileid.split(",");
            String[] uploadfilenames=uploadfilename.split(",");
            for (int i=0;i<uploadfileids.length;i++) {
                Uploadfile uploadfile=new Uploadfile();
                uploadfile.setId(uploadfileids[i]);
                uploadfile.setFilename(uploadfilenames[i]);
                files.add(uploadfile);
            }
        }
        return files;
    }




    @RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
    public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
        Principal principal = (Principal) session.getAttribute("userinfo");
        loginlogService.saveloginlog("/policyfilemgr/updatestate",session, request,Constantval.LOGIN_VISIT);
        Policyfile policyfile=policyfileService.find(id);
        policyfile.setState(state);
        policyfileService.update(policyfile);
        return "redirect:/policyfilemgr/list";
    }

    //查找政策明细
    @RequestMapping(value = "/querydetail", method = RequestMethod.POST)
    @ResponseBody
    public Page<PolicydetailEntity> queryfile(HttpSession session,String plfidid,String nplcategory,String key,String companyid, @PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
                                            ModelMap model, HttpServletRequest request) {
        nplcategory= StringUtils.isNotBlank(nplcategory)?"%"+nplcategory+"%":"%%";
        Page<PolicydetailEntity> page=policydetailService.findPolicyFile(plfidid,nplcategory,pageable);
        if(StringUtils.isNotBlank(key)){
            if(page.getContent()!=null&&page.getContent().size()>0){
                for (PolicydetailEntity detail:page.getContent()) {
                    String zcdl=detail.getNplcategory();
                    String s1=KeyMatchUtil.getMatchedString(detail.getNplcategory(),key);
                    String s2=KeyMatchUtil.getMatchedString(detail.getPlsubclassname(),key);
                    String s3=KeyMatchUtil.getMatchedString(detail.getPlconditions(),key);
                    String s4=KeyMatchUtil.getMatchedString(detail.getRdescription(),key);
                    detail.setNplcategorystr(s1);
                    detail.setPlsubclassnamestr(s2);
                    detail.setPlconditionsstr(s3);
                    detail.setRdescriptionstr(s4);
                }
            }
        }

        if(page.getContent()!=null&&page.getContent().size()>0){
            for (PolicydetailEntity detail:page.getContent()) {
            String nply=detail.getNplcategory();
            if(StringUtils.isNotBlank(companyid)){
                //申报补充材料的政策
                Calendar date = Calendar.getInstance();
                String year = String.valueOf(date.get(Calendar.YEAR)-1);
                if(StringUtils.isNotBlank(nply)){
                    if(nply.contains("专利")){
                        detail.setSbstate("2");
                    }else{
                        PlycomresultdetailEntity plycomresultdetail=plycomresultdetailEntityService.findPlycomresult(companyid,detail.getPldtailid(),year);
                        if(plycomresultdetail!=null){
                            detail.setSbstate("1");
                        }
                    }
                }


            }


        }
        }

        return page;
    }


    @RequestMapping(value="/queryByKey",method = RequestMethod.GET)
    public String queryByKey(String content, ModelMap model, HttpServletRequest request, HttpSession session){
        List<Object> policyFiles=policyfileEntityService.findPolicyFiles(null,null,null,content);
        if(StringUtils.isNotBlank(content)){
            if(policyFiles!=null&&policyFiles.size()>0){
                for (Object file:policyFiles) {
                    Object[] o=(Object[])file;
                    o[1]= KeyMatchUtil.getMatchedString(o[1].toString(),content);
                }
            }
        }
        model.addAttribute("policyFiles", policyFiles);
        model.addAttribute("content", content);
        //政策查询
        List<Object> details =policydetailService.findPolicydetails(content);
        if(details!=null&&details.size()>0){
            for (Object detail:details) {
                Object[] o=(Object[])detail;
                o[2]= KeyMatchUtil.getMatchedString(o[2].toString(),content);
                o[3]= KeyMatchUtil.getMatchedString(o[3].toString(),content);
                o[4]= KeyMatchUtil.getMatchedString(o[4].toString(),content);
            }
        }
        model.addAttribute("details", details);
        model.addAttribute("infotype",Constantval.INDEX_EIGHT);
        return "/policyfilemgr/weblist";
    }


    @RequestMapping(value="/queryByTag/{tagid}",method = RequestMethod.GET)
    public String queryByTag(@PathVariable String tagid, ModelMap model, HttpServletRequest request, HttpSession session){
        //政策文件
        List<PolicyFileEntity> policyFiles=policyfileEntityService.findPolicyFilesByTag(tagid);
        model.addAttribute("policyFiles", policyFiles);
        //政策细则
        List<PolicydetailEntity> details =policydetailService.findPolicyFilesByTag(tagid);

        model.addAttribute("details", details);
        model.addAttribute("infotype",Constantval.INDEX_EIGHT);
        return "/policyfilemgr/weblisttag";
    }



    //企业申报
    @RequestMapping(value="/apply/{pldtailid}/{companyid}",method = RequestMethod.GET)
    public String apply(@PathVariable String pldtailid,@PathVariable String companyid, ModelMap model, HttpServletRequest request, HttpSession session){
        PolicydetailEntity policy=policydetailService.find(pldtailid);
        //申报补充材料的政策
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR)-1);
        String pid="";
            PlycomresultdetailEntity plycomresultdetail=new PlycomresultdetailEntity();
            plycomresultdetail.setPlycomresultdetailid(KeyUtils.getKey());
            plycomresultdetail.setCompanyid(companyid);

            Policyfile file=policyfileService.find(policy.getPlfidid());
            if(file!=null){
                plycomresultdetail.setPolicyname(file.getPolicyname());
            }
            CompanyEntity corp=companyEntityService.queryCompanyByid(companyid);
            if(corp!=null){
                plycomresultdetail.setUscreditcode(corp.getUscreditcode());
                plycomresultdetail.setEnterprisenum(corp.getNumber());
                plycomresultdetail.setEnterprisename(corp.getEnterprisename());
            }
            plycomresultdetail.setPldtailid(pldtailid);
            plycomresultdetail.setPlfidid(policy.getPlfidid());
            plycomresultdetail.setPolicycategorynum(policy.getPlcategoryno());
            plycomresultdetail.setPolicycategoryname(policy.getNplcategory());
            plycomresultdetail.setPolicysubclassno(policy.getPlsubclassnum());
            plycomresultdetail.setPolicysubclassname(policy.getPlsubclassname());

            plycomresultdetail.setPolicyconditions(policy.getPlconditions());
            if(policy.getRamount()!=null){
                plycomresultdetail.setAmount(policy.getRamount()+"");
            }

            plycomresultdetail.setRdescription(policy.getRdescription());
            plycomresultdetail.setParticularyear(year);
            plycomresultdetail.setCashflag("0");
            plycomresultdetail.setEnjoypolicycategory("2");
            plycomresultdetail.setInputtime(new Date());
            plycomresultdetail.setUpdatetime(new Date());
            System.out.println(plycomresultdetail.getPlycomresultdetailid());
            plycomresultdetailEntityService.save(plycomresultdetail);
            System.out.println(plycomresultdetail.getPlycomresultdetailid());
            //生成流程
            PlycomresultdetailEntity resultdetail=plycomresultdetailEntityService.findPlycomresult(companyid,pldtailid,year);
            String message=flowProcessEntityService.createflowprocessbyapplyidintask(resultdetail);
            System.out.println(message);
            pid=resultdetail.getPlycomresultdetailid();
//        return "redirect:/zcfhxpg?&searchName=&companyid="+companyid+"&enjoypolicycategory=2";
        return "redirect:/znppxq?searchId="+ pid;
    }


    //政策提交附件同步至流程附件表
    @RequestMapping(value = "/applycheck", method = RequestMethod.POST)
    @ResponseBody
    public String applycheck(String pldtailid,String companyid, ModelMap model, HttpSession session,
                                     HttpServletRequest request) {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        companyid=StringUtils.isNotBlank(companyid)?companyid.trim():companyid;
        PlycomresultdetailEntity plycomresultdetail=plycomresultdetailEntityService.findPlycomresult(companyid,pldtailid,year);
        if(plycomresultdetail!=null){
            return Constantval.CONSTANT_FAIL;
        }
           return Constantval.CONSTANT_SUCCESS;

    }


     //政策明细标签管理
    @RequestMapping(value="/tagmgr/{pldtailid}",method = RequestMethod.GET)
    public String tagmgr(@PathVariable String pldtailid, ModelMap model, HttpServletRequest request, HttpSession session){
        PolicydetailEntity policydetail=policydetailService.find(pldtailid);
        Policyfile policyfile=policyfileService.find(policydetail.getPlfidid());
        List<TagEntity> ptags=tagService.findByPid(pldtailid);
        List<TagEntity> tags=tagService.findAll();
        model.addAttribute("ptags", ptags);
        model.addAttribute("tags", tags);
        model.addAttribute("policydetail", policydetail);
        model.addAttribute("policyfile", policyfile);
        return "/policyfilemgr/tagmgr";
    }





    // 导出政策文件数据
    @RequestMapping(value = "/infoExport", method = RequestMethod.GET)
    public void infoExport(  String policyname, String content, String filelevel,  String state,HttpServletResponse response, HSSFWorkbook workbook) {
        policyname=StringUtils.isNotBlank(policyname)?"%"+policyname+"%":"%%";
        content=StringUtils.isNotBlank(content)?"%"+content+"%":"%%";
        filelevel=StringUtils.isNotBlank(filelevel)?"%"+filelevel+"%":"%%";
        List<Object[]> infos=policyfileService.queryPolicyfileAll(policyname,content,filelevel,state);

        String titlt="政策文件信息";
        String[] rowName={"政策名称","发文单位","文号","生效日期","失效日期","政策级别","政策状态"};
        ExportExcel expExc = new ExportExcel();
        workbook = expExc.exportfile(titlt,rowName,infos,"file");
        if(workbook!=null){
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String(("政策文件信息.xls").getBytes("gb2312"), "ISO8859-1") + "\"");
                OutputStream out = response.getOutputStream();
                workbook.write(out);
                out.close();

            } catch (IOException e) {
                System.err.println("IOException Occured");
            }
        }



    }



    // 导出政策文件数据
    @RequestMapping(value = "/detailinfoExport", method = RequestMethod.GET)
    public void detailinfoExport( String policyname, String content, String filelevel,String state,HttpServletResponse response, HSSFWorkbook workbook) {

        policyname=StringUtils.isNotBlank(policyname)?"%"+policyname+"%":"%%";
        content=StringUtils.isNotBlank(content)?"%"+content+"%":"%%";
        filelevel=StringUtils.isNotBlank(filelevel)?"%"+filelevel+"%":"%%";
        List<Object[]> infos=policyfileService.queryPolicyfileAll(policyname,content,filelevel,state);
        for (Object[] info:infos) {
            List<Object[]>  details=policydetailService.findDetails(info[7].toString());
            info[6]=details;
        }


        String titlt="政策文件及明细信息";
        String[] rowName={"政策名称","发文单位","文号","生效日期","失效日期","政策级别","政策大类","政策小类","政策条件","奖励描述","奖励金额","兑付路径","审批单位","参与评定部门","政策明细类型","申报条件"};
        String[] rowNameinfo={"政策名称","发文单位","文号","生效日期","失效日期","政策级别"};
        String[] rowNamedetail={"政策大类","政策小类","政策条件","奖励描述","奖励金额","兑付路径","审批单位","参与评定部门","政策明细类型","申报条件"};
        ExportExcel expExc = new ExportExcel();
        workbook = expExc.exportfiledetail(titlt,rowName,rowNameinfo,rowNamedetail,infos);
        if(workbook!=null){
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String(("政策文件及明细信息.xls").getBytes("gb2312"), "ISO8859-1") + "\"");
                OutputStream out = response.getOutputStream();
                workbook.write(out);
                out.close();

            } catch (IOException e) {
                System.err.println("IOException Occured");
            }
        }



    }




    // 导出政策文件数据
    @RequestMapping(value = "/detailExport/{plfidid}", method = RequestMethod.GET)
    public void detailExport(@PathVariable String plfidid,HttpServletResponse response, HSSFWorkbook workbook) {
        PolicyFileEntity policyFile=policyfileEntityService.find(plfidid);
        List<Object[]>  details=null;
        if(policyFile!=null){
             details=policydetailService.findDetails(plfidid);
        }
        String titlt="政策明细信息";
        String[] rowName={"政策大类","政策小类","政策条件","奖励描述","奖励金额","兑付路径","审批单位","参与评定部门","政策明细类型","申报条件"};
        ExportExcel expExc = new ExportExcel();
        workbook = expExc.exportdetail(titlt,rowName,policyFile,details);
        if(workbook!=null){
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String(("政策明细信息.xls").getBytes("gb2312"), "ISO8859-1") + "\"");
                OutputStream out = response.getOutputStream();
                workbook.write(out);
                out.close();

            } catch (IOException e) {
                System.err.println("IOException Occured");
            }
        }



    }


    /**
     * 政策明细批量导入
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public Object[] importResult(String uploadfileid,String plfidid,  HttpSession session) {

        Object[] returnmessage = new Object[2];
        ValidateBinding bind = null;
        int num = 0;
        Uploadfile file = uploadfileService.find(uploadfileid);
        if (file == null) {
            returnmessage[0] = Constantval.CONSTANT_FAIL;
            return returnmessage;
        }
        String filename = ConfigUtil.getConfigVal("file_save_path") + "\\doc\\upload_" + file.getId() + "."
                + file.getFileext();
        // 创建对Excel工作簿文件的引用
        Workbook excelwork = null;
        FileInputStream fileio = null;
        if (file.getFileext().toLowerCase().equals("xls")) {
            try {
                fileio = new FileInputStream(filename);
                excelwork = new HSSFWorkbook(fileio);

                Object[] r = policydetailService.importInfoExcel( excelwork, plfidid);
                bind = (ValidateBinding) r[1];
                num = (int) r[0];
                if (bind.hasErrors()) {
                    returnmessage[0] = Constantval.CONSTANT_FAIL;
                    returnmessage[1] = bind.getErrorHtml();
                    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
                    Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
                    Matcher m_html = p_html.matcher(bind.getErrorHtml());
                    returnmessage[1] = m_html.replaceAll(""); // 过滤html标签
                    return returnmessage;
                } else {
                    returnmessage[0] = Constantval.CONSTANT_SUCCESS;
                    returnmessage[1] = Integer.toString(num);
                    return returnmessage;
                }

            } catch (FileNotFoundException e) {
                log.error(e.getMessage());
                log.info(e);
                returnmessage[0] = Constantval.CONSTANT_FAIL;
                return returnmessage;
            } catch (IOException e) {
                log.error(e.getMessage());
                log.info(e);
                returnmessage[0] = Constantval.CONSTANT_FAIL;
                return returnmessage;
            } finally {
                try {
                    if (fileio != null) {
                        fileio.close();
                    }
                } catch (IOException e) {
                    log.error(e.getMessage());
                    log.info(e);
                }
            }
        } else if (file.getFileext().toLowerCase().equals("xlsx")) {
            try {
                fileio = new FileInputStream(filename);
                excelwork = new XSSFWorkbook(fileio);
                Object[] r = policydetailService.importInfoExcel( excelwork, plfidid);
                bind = (ValidateBinding) r[1];
                num = (int) r[0];
                if (bind.hasErrors()) {
                    returnmessage[0] = Constantval.CONSTANT_FAIL;
                    returnmessage[1] = bind.getErrorHtml();
                    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
                    Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
                    Matcher m_html = p_html.matcher(bind.getErrorHtml());
                    returnmessage[1] = m_html.replaceAll(""); // 过滤html标签
                    return returnmessage;
                } else {
                    returnmessage[0] = Constantval.CONSTANT_SUCCESS;
                    returnmessage[1] = Integer.toString(num);
                    return returnmessage;
                }
            } catch (FileNotFoundException e) {
                log.error(e.getMessage());
                log.info(e);
                returnmessage[0] = Constantval.CONSTANT_FAIL;
                return returnmessage;
            } catch (IOException e) {
                log.error(e.getMessage());
                log.info(e);
                returnmessage[0] = Constantval.CONSTANT_FAIL;
                return returnmessage;
            } finally {
                try {
                    if (fileio != null) {
                        fileio.close();
                    }
                } catch (IOException e) {
                    log.error(e.getMessage());
                    log.info(e);
                }
            }
        } else {
            returnmessage[0] = Constantval.CONSTANT_FAIL;
            return returnmessage;
        }
    }
    

}
