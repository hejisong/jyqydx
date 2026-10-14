package com.ywjc.glsyjs.bigdata.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.datadd.entity.Policyuser;
import com.ywjc.datadd.service.PolicyuserService;
import com.ywjc.glsyjs.bigdata.utils.BigDataUtil;
import com.ywjc.glsyjs.companyinfo.entity.Businessscopetag;
import com.ywjc.glsyjs.companyinfo.service.BusinessscopetagService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.entity.Companypatent;
import com.ywjc.glsyjs.interfaces.company.entity.Corpbusinessscope;
import com.ywjc.glsyjs.interfaces.company.entity.Iihightechenterprises;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.service.CompanypatentService;
import com.ywjc.glsyjs.interfaces.company.service.CorpbusinessscopeService;
import com.ywjc.glsyjs.interfaces.company.service.IihightechenterprisesService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
import com.ywjc.glsyjs.interfaces.oauth.entity.OauthEntity;
import com.ywjc.glsyjs.interfaces.oauth.service.OauthEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetagrelation;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagService;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagrelationService;
import com.ywjc.glsyjs.interfaces.tag.service.TagService;
import com.ywjc.glsyjs.interfaces.utils.GetPostUtil;
import com.ywjc.glsyjs.interfaces.utils.YshApiUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller("bigdataController")
@RequestMapping("/bigdata")
public class BigdataController extends BaseController {
    private static Logger log = Logger.getLogger(BigdataController.class);


    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;


    @Resource(name = "policyuserServiceImpl")
    private PolicyuserService policyuserService;

    @Resource(name = "corpbusinessscopeServiceImpl")
    private CorpbusinessscopeService corpbusinessscopeService;

    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;


    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;

    @Resource(name = "oauthEntityServiceImpl")
    private OauthEntityService oauthEntityService;


    @Resource(name = "businessscopetagServiceImpl")
    private BusinessscopetagService businessscopetagService;

    @Resource(name = "iihightechenterprisesServiceImpl")
    private IihightechenterprisesService iihightechenterprisesService;

    @Resource(name = "tagServiceImpl")
    private TagService tagService;

    @Resource(name = "companypatentServiceImpl")
    private CompanypatentService companypatentService;

    @Resource(name = "accuratetagServiceImpl")
    private AccuratetagService accuratetagService;

    @Resource(name = "accuratetagrelationServiceImpl")
    private AccuratetagrelationService accuratetagrelationService;


    @RequestMapping(value="/initcorpinfo",method = RequestMethod.GET)
    public void initcorpinfo(){

        //更新企业
        List<Iihightechenterprises> allcorps=iihightechenterprisesService.findAll();
        for (Iihightechenterprises icorp:allcorps) {
            CompanyEntity companyEntity=companyEntityService.find(icorp.getCompanyid());
            if(companyEntity==null){
             System.out.println(icorp.getCompanyid()+"-"+icorp.getEnterprisename());
                companyEntity=companyEntityService.queryCompanyBycorpname(icorp.getEnterprisename());
                if(companyEntity!=null){
                    accuratetagrelationService.updateCompanyid(icorp.getCompanyid(),companyEntity.getCompanyid());
                    icorp.setCompanyid(companyEntity.getCompanyid());
                    iihightechenterprisesService.update(icorp);

                }else{
                    companyEntity=new CompanyEntity();
                    companyEntity.setCompanyid(icorp.getCompanyid());
                    companyEntity.setEnterprisename(icorp.getEnterprisename());
                    companyEntityService.save(companyEntity);
                }
            }
        }

        List<Companypatent> companypatents=companypatentService.findAll();
        for (Companypatent companypatent:companypatents) {
            CompanyEntity companyEntity=companyEntityService.find(companypatent.getCompanyid());
            if(companyEntity==null){
                System.out.println(companypatent.getCompanyid()+"-"+companypatent.getEnterprisename());
                companyEntity=companyEntityService.queryCompanyBycorpname(companypatent.getEnterprisename());
                if(companyEntity!=null){
                    accuratetagrelationService.updateCompanyid(companypatent.getCompanyid(),companyEntity.getCompanyid());
                    companypatent.setCompanyid(companyEntity.getCompanyid());
                    companypatentService.update(companypatent);

                }else{
                    companyEntity=new CompanyEntity();
                    companyEntity.setCompanyid(companypatent.getCompanyid());
                    companyEntity.setEnterprisename(companypatent.getEnterprisename());
                    companyEntityService.save(companyEntity);
                }
            }
        }


    }








    //高新技术企业精准化标签
    @RequestMapping(value="/iihcorptag",method = RequestMethod.GET)
    public String iihcorptag(){
        Accuratetag tag=accuratetagService.findTagByTagname("高新技术企业");
        if(tag==null){
            tag = new Accuratetag();
            tag.setTagname("高新技术企业");
            tag.setCreatetime(new Date());
            tag.setState("1");
            accuratetagService.save(tag);
        }
        tag=accuratetagService.findTagByTagname("高新技术企业");
        List<Iihightechenterprises> allcorps=iihightechenterprisesService.findAll();
        for (Iihightechenterprises icorp:allcorps) {

            Accuratetagrelation tagRelation=accuratetagrelationService.findByCorp(icorp.getCompanyid(),tag.getTagid(),icorp.getRecognitionyear());
            if(tagRelation==null){
                tagRelation=new Accuratetagrelation();
                tagRelation.setCid(icorp.getCompanyid());
                tagRelation.setTagid(tag.getTagid());
                tagRelation.setCreatetime(new Date());
                tagRelation.setTagyear(icorp.getRecognitionyear());
                tagRelation.setTagname("高新技术企业");
                accuratetagrelationService.save(tagRelation);
            }
        }

        return "bigdata/result";


    }


    //专利企业精准化标签
    @RequestMapping(value="/cpatenttag",method = RequestMethod.GET)
    public String cpatenttag(){


        List<Companypatent> companypatents=companypatentService.findAll();
        for (Companypatent companypatent:companypatents) {
             String tagname="企业授权发明专利";
            if(companypatent.getPatenttype().contains("外观设计")){
                tagname="授权外观专利";
            }else if(companypatent.getPatenttype().contains("实用新型")){
                tagname="授权实用新型专利";
            }
            Accuratetag tag=accuratetagService.findTagByTagname(tagname);
            if(tag==null){
                tag = new Accuratetag();
                tag.setTagname(tagname);
                tag.setCreatetime(new Date());
                tag.setState("1");
                accuratetagService.save(tag);
            }
            tag=accuratetagService.findTagByTagname(tagname);
            Accuratetagrelation tagRelation=accuratetagrelationService.findByCorp(companypatent.getCompanyid(),tag.getTagid(),companypatent.getPatentyear());
            if(tagRelation==null){
                tagRelation=new Accuratetagrelation();
                tagRelation.setCid(companypatent.getCompanyid());
                tagRelation.setTagid(tag.getTagid());
                tagRelation.setCreatetime(new Date());
                tagRelation.setTagyear(companypatent.getPatentyear());
                tagRelation.setTagname(tagname);
                accuratetagrelationService.save(tagRelation);
            }
        }

        return "bigdata/result";


    }



    @RequestMapping(value="/updatecorp",method = RequestMethod.GET)
    public void initcorp(){
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR)-1);
        //更新企业
        List<Iihightechenterprises> allcorps=iihightechenterprisesService.findNoCompanyid();
        List<Companypatent> pcompanys=companypatentService.findNoCompanyid();
        initCompany(allcorps);
        initPCompany(pcompanys);
        List<Iihightechenterprises> infos=iihightechenterprisesService.findByYear(year);
        initCompany(infos);
        List<Companypatent> companypatents=companypatentService.findByYear(year);
        initPCompany(companypatents);

    }


    private void initPCompany( List<Companypatent> corps){
        for (Companypatent pcorp:corps) {
            if(StringUtils.isNotBlank(pcorp.getCompanyid())){
                CompanyEntity corp=companyEntityService.queryCompanyByid(pcorp.getCompanyid());
                if(corp==null){
                    corp=companyEntityService.queryCompanyBycorpname(pcorp.getEnterprisename());
                    if(corp==null){
                        corp=new CompanyEntity();
                        corp.setCompanyid(pcorp.getCompanyid());
                        corp.setEnterprisename(pcorp.getEnterprisename());
                        companyEntityService.save(corp);
                        System.out.println(pcorp.getCompanyid()+"--"+pcorp.getEnterprisename());
                    }else{
                        pcorp.setCompanyid(corp.getCompanyid());
                        companypatentService.update(pcorp);
                    }

                }
            }else{
                CompanyEntity corp=new CompanyEntity();
                corp.setCompanyid(KeyUtils.getKey());
                corp.setEnterprisename(pcorp.getEnterprisename());
                corp.setUscreditcode(pcorp.getCredit_no());
                companyEntityService.save(corp);
                pcorp.setCompanyid(corp.getCompanyid());
                companypatentService.update(pcorp);
            }
        }
    }


    private void initCompany( List<Iihightechenterprises> corps){
        for (Iihightechenterprises icorp:corps) {
            if(StringUtils.isNotBlank(icorp.getCompanyid())){
                CompanyEntity corp=companyEntityService.queryCompanyByid(icorp.getCompanyid());
                if(corp==null){
                    corp=companyEntityService.queryCompanyBycorpname(icorp.getEnterprisename());
                    if(corp==null){
                        corp=new CompanyEntity();
                        corp.setCompanyid(icorp.getCompanyid());
                        corp.setEnterprisename(icorp.getEnterprisename());
                        companyEntityService.save(corp);
                        System.out.println(icorp.getCompanyid()+"--"+icorp.getEnterprisename());
                    }else{
                        icorp.setCompanyid(corp.getCompanyid());
                        iihightechenterprisesService.update(icorp);
                    }


                }
            }else{
                CompanyEntity corp=new CompanyEntity();
                corp.setCompanyid(KeyUtils.getKey());
                corp.setEnterprisename(icorp.getEnterprisename());
                companyEntityService.save(corp);
                icorp.setCompanyid(corp.getCompanyid());
                iihightechenterprisesService.update(icorp);
            }
        }
    }


    @RequestMapping(value="/inittime",method = RequestMethod.GET)
    public String inittime(HttpSession session, ModelMap model, HttpServletRequest request) {
        List<PlycomresultdetailEntity> infos = plycomresultdetailEntityService.findAll();
        for (PlycomresultdetailEntity info : infos) {
            FlowProcessEntity flowprocess = flowProcessEntityService.queryListByapplyidandflowname(info.getPlycomresultdetailid(), "政策匹配");
            if (flowprocess != null) {
                info.setInputtime(flowprocess.getCreatetime());
                plycomresultdetailEntityService.update(info);
            }
        }
        return "bigdata/result";
    }



    @RequestMapping(value="/data/{infotype}/{corpname}",method = RequestMethod.GET)
    public String list(@PathVariable String infotype,@PathVariable String corpname, HttpSession session,
                       ModelMap model, HttpServletRequest request) {
        JSONObject result =null;
        if("1".equals(infotype)){
            result= BigDataUtil.getDataSERIOUSTAX(corpname);
        }else if("2".equals(infotype)){
            result= BigDataUtil.getDataTRADEMARK(corpname);
        }else if("3".equals(infotype)){
            result= BigDataUtil.getDataPATENT(corpname);
        }else if("4".equals(infotype)){
            result= BigDataUtil.getDataINSTITUTION(corpname);
        }else if("5".equals(infotype)){
            result= BigDataUtil.getDataEQUITY(corpname);
        }else if("6".equals(infotype)){
            result= BigDataUtil.getDataBUSINESS(corpname);
        }else if("7".equals(infotype)){
            result= BigDataUtil.getDataGXCWB_QY_TJJ(corpname);
        }else if("8".equals(infotype)){
            result= BigDataUtil.getDataBUSINESS_IPINFO_TRADEMARK(corpname);
        }else if("9".equals(infotype)){
            result= BigDataUtil.getDataBUSINESS_IPINFO_PATENT(corpname);
        }else if("10".equals(infotype)){
            result= BigDataUtil.getDataBUSINESS_STOCKERHOLDER(corpname);
        }else if("11".equals(infotype)){
            result= BigDataUtil.getDataBUSINESS_INSTITUTION(corpname);
        }
        log.error(result);
        log.info(result);
        model.addAttribute("result", result);

        return "bigdata/result";
    }


    @RequestMapping(value="/initcorps",method = RequestMethod.GET)
    public String initcorps(HttpSession session, ModelMap model, HttpServletRequest request) {
        List<CompanyEntity> corps=companyEntityService.findAll();
        for (CompanyEntity corp :corps) {
            companyEntityService.updateInfo(corp);
        }

        return "bigdata/result";
    }

    @RequestMapping(value="/initcorpone/{corpname}",method = RequestMethod.GET)
    public String initcorpone(@PathVariable String corpname,HttpSession session, ModelMap model, HttpServletRequest request) {
        CompanyEntity corp=companyEntityService.queryCompanyBycorpname(corpname);
        companyEntityService.updateInfo(corp);
        return "bigdata/result";
    }

    @RequestMapping(value="/initrecapital",method = RequestMethod.GET)
    public String initrecapital(HttpSession session, ModelMap model, HttpServletRequest request) {
        List<CompanyEntity> companys=companyEntityService.findAll();
        for (CompanyEntity company:companys) {
            if(StringUtils.isNotBlank(company.getRecapital())){
                String r=company.getRecapital();
                if(r.indexOf("万")!=-1){
                    r=r.substring(0,r.indexOf("万"));
                    r=r.trim();
                    Double b=Double.valueOf(r);
                    company.setRecapitaln(b);
                    companyEntityService.update(company);
                }



            }
        }
        return "bigdata/result";
    }



    @RequestMapping(value="/initCorptag",method = RequestMethod.GET)
    public String initCorptag(HttpSession session, ModelMap model, HttpServletRequest request) {
        List<CompanyEntity> corps=companyEntityService.findAll();
        for (CompanyEntity corp :corps) {
            //经营范围
            List<Corpbusinessscope> corpbusinessscopes=corpbusinessscopeService.findByScorpBycompanyid(corp.getCompanyid());
            if(corpbusinessscopes!=null&&corpbusinessscopes.size()>0){
                for (Corpbusinessscope corpbusinessscope:corpbusinessscopes) {
                    Businessscopetag businessscopetag=businessscopetagService.findByscope(corpbusinessscope.getBusinessscope());
                    if(businessscopetag!=null&&StringUtils.isNotBlank(businessscopetag.getTag())){
                        String[] tags=businessscopetag.getTag().split(",");
                        if(tags!=null&&tags.length>0){
                            for (String tagid:tags) {
                                tagService.saveTagRelation(corp.getCompanyid(),tagid);
                            }

                        }

                    }

                }
            }
        }
        return "bigdata/result";
    }


    @RequestMapping(value="/initscope",method = RequestMethod.GET)
    public String Corpbusinessscope(HttpSession session, ModelMap model, HttpServletRequest request) {

        List<CompanyEntity> corps=companyEntityService.findAll();
        for (CompanyEntity corp :corps) {
            String naturebusiness=corp.getNaturebusiness();
            if(StringUtils.isNotBlank(naturebusiness)){
                String[] naturebusinesss=naturebusiness.split("；");
                if(naturebusinesss!=null&&naturebusinesss.length>0){
                    for (String business:naturebusinesss) {
                        Corpbusinessscope scorp=corpbusinessscopeService.findByScorp(corp.getCompanyid(),business);
                        if(scorp==null){
                            scorp=new Corpbusinessscope();
                            scorp.setId(KeyUtils.getKey());
                            scorp.setCompanyid(corp.getCompanyid());
                            scorp.setEnterprisename(corp.getEnterprisename());
                            scorp.setBusinessscope(business);
                            corpbusinessscopeService.save(scorp);
                        }
                    }
                }


            }
        }
        return "bigdata/result";
    }



    @RequestMapping(value="/checkdd/{username}/{mobile}",method = RequestMethod.GET)
    public String checkdd(@PathVariable String username,@PathVariable String mobile,HttpSession session, ModelMap model, HttpServletRequest request) {
        Policyuser policyuser=policyuserService.findUser(username,mobile);
        if(policyuser==null){
            model.addAttribute("result", "noddper");
        }

        return "bigdata/result";
    }


    @RequestMapping(value="/initaccount",method = RequestMethod.GET)
    public String initaccount(HttpSession session, ModelMap model, HttpServletRequest request) {
        List<CompanyEntity> corps=companyEntityService.findAll();
        for (CompanyEntity corp :corps) {
            //收款信息
            if(StringUtils.isNotBlank(corp.getUscreditcode())){
                getaccountinfo(corp);
            }


        }

        return "bigdata/result";
    }


    @RequestMapping(value="/initaccount1",method = RequestMethod.GET)
    public String initaccount1(HttpSession session, ModelMap model, HttpServletRequest request) {
        CompanyEntity corp1=companyEntityService.find("4028079a79a285850179a28611360000");
        CompanyEntity corp2=companyEntityService.find("a3ca45bd0d99cc558aabcef03cfabbad");
        CompanyEntity corp3=companyEntityService.find("402809957aae3bc6017aae5cbc960448");
        getaccountinfo(corp1);
        getaccountinfo(corp2);
        getaccountinfo(corp3);
        return "bigdata/result";
    }



    //赋予账号信息（返回页面）
    private void getaccountinfo(CompanyEntity corp ){
        //获取token信息
        String token = getYshApiAccessToken();
        String creditcode=corp.getUscreditcode();
        JSONObject jsonObject= YshApiUtil.getAccountInfoListBycreditCode(token,creditcode);
        JSONObject result_info = jsonObject.getJSONObject("result_info");
        String stzhAccountId="";
        String accountSubId="";
        if(result_info!=null){
            JSONObject accountSubInfoListCompany = result_info.getJSONObject("accountSubInfoListCompany");
            JSONArray creditCodelist1=accountSubInfoListCompany.getJSONArray(creditcode);

            for (int j = 0; j < creditCodelist1.size(); j++) {
                JSONObject jsonObject2 = creditCodelist1.getJSONObject(j);
                stzhAccountId=jsonObject2.getString("stzhAccountId");
                accountSubId=jsonObject2.getString("accountSubId");
            }


        }
        if(StringUtils.isNotBlank(accountSubId)){
            corp.setAccount(stzhAccountId);
            corp.setDzaccount(accountSubId);
            companyEntityService.update(corp);
        }
    }


    public String getYshApiAccessToken() {
        String merchantId = ConfigUtil.getConfigVal("merchantId");
        String merchantSecret = ConfigUtil.getConfigVal("merchantSecret");
        String grant_type =  ConfigUtil.getConfigVal("grant_type");
        String scope =ConfigUtil.getConfigVal("scope");
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
                String url =ConfigUtil.getConfigVal("tokenurl");
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


    @RequestMapping(value="/corps",method = RequestMethod.GET)
    public String list(HttpSession session, ModelMap model, HttpServletRequest request) {
        String filename = "D:\\corps.xlsx";
        // 创建对Excel工作簿文件的引用
        Workbook excelwork = null;
        FileInputStream fileio = null;

        try {
            fileio = new FileInputStream(filename);
            excelwork = new XSSFWorkbook(fileio);

            importEvaresultExcel(excelwork);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            log.info(e);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.info(e);
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
        return "bigdata/result";

    }




    private void importEvaresultExcel( Workbook workbook) {
        String corpname=null;
        String creditcode=null;
        String regaddr=null;

        int num=-1;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 循环sheet
            //获取第一张Sheet表
            Sheet sheet =workbook.getSheetAt(i);

            //获取Sheet表中所包含的总行数
            int numberrow=sheet.getPhysicalNumberOfRows();
            num=0;
            for(int j=1;j<numberrow;j++){
                Row row = sheet.getRow(j); // 获得工作薄的第j行
                if(row!=null){
                    for(int k=0;k<7;k++){// 获得工作薄的第j行的第k列的值
                        Cell cell = row.getCell(k);
                        if(cell!=null){
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            if(k==0){//评价年份
                                corpname=publicExcel(cell).trim();
                            }else if(k==1){
                                creditcode=publicExcel(cell).trim();
                            }else if(k==2){
                                regaddr=publicExcel(cell).trim();
                            }
                        }
                    }
                    //保存到数据库
                    CompanyEntity corp=companyEntityService.queryCompanyBycorpname(corpname);
                    if(corp==null){
                        System.out.println(creditcode);
                        corp=new CompanyEntity();
                        corp.setEnterprisename(corpname);
                        corp.setUscreditcode(creditcode);
                        corp.setCompanyregisaddress(regaddr);
                        companyEntityService.save(corp);
                        System.out.println("保存企业："+corpname);

                    }
                    num=num+1;


                }
            }
        }


    }



    @RequestMapping(value="/hicorps",method = RequestMethod.GET)
    public String hicorps(HttpSession session, ModelMap model, HttpServletRequest request) {
        String filename = "D:\\hicorp.xlsx";
        // 创建对Excel工作簿文件的引用
        Workbook excelwork = null;
        FileInputStream fileio = null;

        try {
            fileio = new FileInputStream(filename);
            excelwork = new XSSFWorkbook(fileio);

            importHicorpExcel(excelwork);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            log.info(e);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.info(e);
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
        return "bigdata/result";

    }




    private void importHicorpExcel( Workbook workbook) {
        String corpname=null;
        String year=null;
        String crditno=null;
        String org=null;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 循环sheet
            //获取第一张Sheet表
            Sheet sheet =workbook.getSheetAt(i);

            //获取Sheet表中所包含的总行数
            int numberrow=sheet.getPhysicalNumberOfRows();
            for(int j=1;j<numberrow;j++){
                Row row = sheet.getRow(j); // 获得工作薄的第j行
                if(row!=null){
                    for(int k=0;k<7;k++){// 获得工作薄的第j行的第k列的值
                        Cell cell = row.getCell(k);
                        if(cell!=null){
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            if(k==1){//评价年份
                                corpname=publicExcel(cell).trim();
                            }else if(k==2){
                                year=publicExcel(cell).trim();
                            }else if(k==3){
                                crditno=publicExcel(cell).trim();
                            }else if(k==4){
                                org=publicExcel(cell).trim();
                            }
                        }
                    }
                    //保存到数据库
                    CompanyEntity corp=companyEntityService.queryCompanyBycorpname(corpname.trim());
                    Iihightechenterprises info=iihightechenterprisesService.findBycertificateno(crditno);
                    if(info==null){
                        info=new Iihightechenterprises();
                        info.setIiheeid(KeyUtils.getKey());
                        if(corp!=null){
                            info.setCompanyid(corp.getCompanyid());
                        }
                        info.setEnterprisename(corpname);
                        info.setRecognitionyear(year);
                        int iyear=Integer.parseInt(year)+3;
                        info.setValidityterm(String.valueOf(iyear));
                        info.setCertificateno(crditno);
                        info.setApprovalauthority(org);
                        iihightechenterprisesService.save(info);
                        System.out.println(corpname);
                    }






                }
            }
        }


    }


    private String publicExcel( Cell cell){
        String value = null;
        if(cell!=null){
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                    value =Double.toString(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    ;
                    break;
                default:
            }
        }

        return value;
    }


}
