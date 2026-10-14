package com.ywjc.glsyjs.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.system.UserInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.cloudpivot.vo.Attachment;
import com.ywjc.glsyjs.interfaces.cloudpivot.vo.SubmitData;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.entity.Companypatent;
import com.ywjc.glsyjs.interfaces.company.entity.Iihightechenterprises;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.service.CompanypatentService;
import com.ywjc.glsyjs.interfaces.company.service.IihightechenterprisesService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicyfileService;
import com.ywjc.glsyjs.interfaces.utils.FlowUtil;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.entity.Taskdorecord;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import com.ywjc.glsyjs.sys.service.PolicyFileEntityService;
import com.ywjc.glsyjs.sys.service.TaskdorecordService;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

@Component("policycompanytask")
public class PolicyComPanytask<Policydetail> {
    private static Logger log = Logger.getLogger(PolicyComPanytask.class);
    private static final LinkedBlockingQueue<String> companyidQueue= new LinkedBlockingQueue<>();

    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;
    //policydetail
    @Resource(name = "policydetailServiceImpl")
    private PolicydetailService policydetailService;

    @Resource(name = "policyFileEntityServiceImpl")
    private PolicyFileEntityService policyFileEntityService;

    @Resource(name = "iihightechenterprisesServiceImpl")
    private IihightechenterprisesService iihightechenterprisesService;

    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;

    @Resource(name = "companypatentServiceImpl")
    private CompanypatentService companypatentService;

    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;

    @Resource(name = "organinfoServiceImpl")
    private OrganinfoService organinfoService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "taskdorecordServiceImpl")
    private TaskdorecordService taskdorecordService;


    public static void addPlycomQueue(String companyid){
        companyidQueue.add(companyid);
    }
    public void Plycom(){
        boolean flag = true;
        while(flag) {
            String companyid=companyidQueue.poll();
            CompanyEntity company=companyEntityService.find(companyid);

            //鼓励企业研发投入
//        对获得企业院士工作站研发平台认定的
//        获得市以上工程技术研究中心
//        对获得国家、省级重点实验室平台认定的
        }

    }

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
        //更新专利年份
        updateYear();
    }


    public void addPlycomResult(){
        Date begintime=new Date();
        int i=0;

        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR)-1);
        List<Iihightechenterprises> infos=iihightechenterprisesService.findByYear(year);
        if(infos!=null&&infos.size()>0){
            for (Iihightechenterprises info:infos) {

                List<Iihightechenterprises> corps=iihightechenterprisesService.findBycompanyid(info.getCompanyid());
                if(corps.size()==1){
                    //对首次获得高新技术企业（含技术先进型服务企业）
                    i=saveResult(info.getCompanyid(),"b2baff2ed8d55fab03fb4f1b22a23c18",year,i);
                    System.out.println("首次"+info.getEnterprisename());
                }else if(corps.size()==2){
                    //高新技术企业有效期满当年仍获得认定的
                    Iihightechenterprises ii=corps.get(1);
                    String year1=ii.getRecognitionyear();
                    int iyear1=Integer.parseInt(year1)+3;
                    int iyear=Integer.parseInt(year);
                    if(iyear1==iyear){
                       i= saveResult(info.getCompanyid(),"b333823ec4bcde64fc30b88ee7f1dabe",year,i);
                        System.out.println("再次"+info.getEnterprisename());
                    }


                }

            }
        }

        //企业授权发明专利	每件补助4000元
        List<Companypatent> companypatents=companypatentService.findByYear(year);
        if(companypatents!=null&&companypatents.size()>0){
            for (Companypatent companypatent:companypatents) {
                i=saveResultCompanypatent(companypatent.getCompanyid(),"121323fff3f1c8dbabaedadcabb9a3c1",year,companypatent,i);
                System.out.println("专利"+companypatent.getEnterprisename());
            }
        }



        Date endtime=new Date();
        Taskdorecord taskdorecord=new Taskdorecord();
        taskdorecord.setId(KeyUtils.getKey());
        taskdorecord.setTasktype("2");
        taskdorecord.setTaskclass("policycompanytask");
        taskdorecord.setTasmethod("addPlycomResult");
        taskdorecord.setUpdatedatacount(i+"");
        taskdorecord.setBegintime(begintime);
        taskdorecord.setEndtime(endtime);
        taskdorecordService.save(taskdorecord);


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
                      corp=new CompanyEntity();
                      corp.setCompanyid(icorp.getCompanyid());
                      corp.setEnterprisename(icorp.getEnterprisename());
                      companyEntityService.save(corp);

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
                    corp=new CompanyEntity();
                    corp.setCompanyid(pcorp.getCompanyid());
                    corp.setEnterprisename(pcorp.getEnterprisename());
                    corp.setUscreditcode(pcorp.getCredit_no());
                    companyEntityService.save(corp);

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

    private int saveResult(String companyid,String pldtailid,String particularyear, int i){

        PlycomresultdetailEntity plycomresultdetail=plycomresultdetailEntityService.findPlycomresult(companyid,pldtailid,particularyear);
        if(plycomresultdetail==null){
            plycomresultdetail=new PlycomresultdetailEntity();
            plycomresultdetail.setPlycomresultdetailid(KeyUtils.getKey());
            plycomresultdetail.setCompanyid(companyid);
            PolicydetailEntity policy=policydetailService.find(pldtailid);
            PolicyFileEntity file=policyFileEntityService.find(policy.getPlfidid());
            CompanyEntity corp=companyEntityService.queryCompanyByid(companyid);
            plycomresultdetail.setUscreditcode(corp.getUscreditcode());
            plycomresultdetail.setPldtailid(pldtailid);
            plycomresultdetail.setPlfidid(policy.getPlfidid());
            if(file!=null){
                plycomresultdetail.setPolicyname(file.getPolicyname());
            }

            plycomresultdetail.setPolicycategorynum(policy.getPlcategoryno());
            plycomresultdetail.setPolicycategoryname(policy.getNplcategory());
            plycomresultdetail.setPolicysubclassno(policy.getPlsubclassnum());
            plycomresultdetail.setPolicysubclassname(policy.getPlsubclassname());
            plycomresultdetail.setEnterprisenum(corp.getNumber());
            plycomresultdetail.setNumber(corp.getNumber());
            plycomresultdetail.setEnterprisename(corp.getEnterprisename());
            plycomresultdetail.setPolicyconditions(policy.getPlconditions());
            plycomresultdetail.setAmount(policy.getRamount()+"");
            plycomresultdetail.setRdescription(policy.getRdescription());
            plycomresultdetail.setParticularyear(particularyear);
            plycomresultdetail.setEnjoypolicycategory("1");
            plycomresultdetail.setCashflag("0");
            plycomresultdetail.setInputtime(new Date());
            plycomresultdetail.setUpdatetime(new Date());
            System.out.println(plycomresultdetail.getPlycomresultdetailid());
            plycomresultdetailEntityService.save(plycomresultdetail);
            i++;
            //生成审核流程
            PlycomresultdetailEntity resultdetail=plycomresultdetailEntityService.findPlycomresult(companyid,pldtailid,particularyear);
            String message=flowProcessEntityService.createflowprocessbyapplyidintask(resultdetail);
//            System.out.println(message);
        }
        return i;
    }


    private int saveResultCompanypatent(String companyid,String pldtailid,String particularyear,Companypatent companypatent,int i){
        PlycomresultdetailEntity plycomresultdetail=plycomresultdetailEntityService.findPlycomresultByCompany(companyid,companypatent.getCompanypatentid());
           if(plycomresultdetail==null){
               plycomresultdetail=new PlycomresultdetailEntity();
               plycomresultdetail.setPlycomresultdetailid(KeyUtils.getKey());
               plycomresultdetail.setCompanyid(companyid);
               PolicydetailEntity policy=policydetailService.find(pldtailid);
               PolicyFileEntity file=policyFileEntityService.find(policy.getPlfidid());
               CompanyEntity corp=companyEntityService.queryCompanyByid(companyid);
               plycomresultdetail.setUscreditcode(corp.getUscreditcode());
               plycomresultdetail.setPldtailid(pldtailid);
               plycomresultdetail.setPlfidid(policy.getPlfidid());
               if(file!=null){
                   plycomresultdetail.setPolicyname(file.getPolicyname());
               }

               plycomresultdetail.setPolicycategorynum(policy.getPlcategoryno());
               plycomresultdetail.setPolicycategoryname(policy.getNplcategory());
               plycomresultdetail.setPolicysubclassno(policy.getPlsubclassnum());
               plycomresultdetail.setPolicysubclassname(policy.getPlsubclassname());
               plycomresultdetail.setEnterprisenum(corp.getNumber());
               plycomresultdetail.setNumber(corp.getNumber());
               plycomresultdetail.setEnterprisename(corp.getEnterprisename());
               plycomresultdetail.setPolicyconditions(policy.getPlconditions());
               plycomresultdetail.setAmount(policy.getRamount()+"");
               plycomresultdetail.setRdescription(policy.getRdescription());
               plycomresultdetail.setParticularyear(particularyear);
               plycomresultdetail.setEnjoypolicycategory("1");
               plycomresultdetail.setCashflag("0");
               plycomresultdetail.setCompanypatentid(companypatent.getCompanypatentid());
               plycomresultdetail.setPatentname(companypatent.getPatentname());
               plycomresultdetail.setInputtime(new Date());
               plycomresultdetail.setUpdatetime(new Date());
               plycomresultdetailEntityService.save(plycomresultdetail);
               i++;
               //生成审核流程
               PlycomresultdetailEntity resultdetail=plycomresultdetailEntityService.findPlycomresultByCompany(companyid,companypatent.getCompanypatentid());
               String message=flowProcessEntityService.createflowprocessbyapplyidintask(resultdetail);
//               System.out.println(message);
           }
           return i;

    }


    private void updateYear(){
        List<Companypatent> companypatents=companypatentService.findNoYear();
        if(companypatents!=null&&companypatents.size()>0){
            for (Companypatent companypatent:companypatents) {
                String pubannomentday=companypatent.getPubannomentday();
                if(StringUtils.isNotBlank(pubannomentday)&&pubannomentday.length()>0){
                    pubannomentday=pubannomentday.substring(0,4);
                    companypatent.setPatentyear(pubannomentday);
                    companypatentService.update(companypatent);
                }

            }
        }
    }


    public void addPlycomResultToDingding(){
        //查找所有已生成流程未推送的对象
        List<PlycomresultdetailEntity> infos=plycomresultdetailEntityService.findlistByIsflow("1");
        for (PlycomresultdetailEntity info:infos) {
           List<FlowProcessEntity> flowinfos=flowProcessEntityService.queryListByapplyid(info.getPlycomresultdetailid());
           if(flowinfos!=null&&flowinfos.size()>0){
               for (FlowProcessEntity flowinfo:flowinfos) {


               }
           }
        }

    }




}
