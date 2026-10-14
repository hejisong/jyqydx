package com.ywjc.glsyjs.interfaces.company.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.Policyservices.entity.Intellectmatch;
import com.ywjc.glsyjs.bigdata.entity.Businessipinfotrademark;
import com.ywjc.glsyjs.bigdata.entity.Gxcwbqytjj;
import com.ywjc.glsyjs.bigdata.service.BusinessipinfotrademarkService;
import com.ywjc.glsyjs.bigdata.service.GxcwbqytjjService;
import com.ywjc.glsyjs.bigdata.utils.BigDataUtil;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.entity.Companypatent;
import com.ywjc.glsyjs.interfaces.company.repositories.CompanyEntityRepository;
import com.ywjc.glsyjs.interfaces.company.repositories.CompanypatentRepository;
import com.ywjc.glsyjs.interfaces.company.repositories.CompanyEntityRepository;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.service.CompanypatentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("companyEntityServiceImpl")
public class CompanyEntityServiceImpl extends BaseServiceImpl<CompanyEntity, String> implements
        CompanyEntityService {

    @Resource(name = "companyEntityRepository")
    private CompanyEntityRepository companyEntityRepository;

    @Resource(name = "companypatentRepository")
    private CompanypatentRepository companypatentRepository;


    @Resource(name = "gxcwbqytjjServiceImpl")
    private GxcwbqytjjService gxcwbqytjjService;

    @Resource(name = "businessipinfotrademarkServiceImpl")
    private BusinessipinfotrademarkService businessipinfotrademarkService;

    @Resource(name = "companypatentServiceImpl")
    private CompanypatentService companypatentService;

    @Resource(name = "queryServiceImpl")
    private QueryService queryService;


    @Resource(name = "companyEntityRepository")
    public void setReposity(PagingAndSortingRepository<CompanyEntity, String> companyEntityRepository) {
        super.setReposity(companyEntityRepository);
    }

    @Override
    public void updateCompanyPhoneno(String phone, String companyid) {
        companyEntityRepository.updateCompanyPhoneno(phone, companyid);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyEntity queryCompanyByid(String companyid) {
        return companyEntityRepository.queryCompanyByid(companyid);
    }
    @Override
    public CompanyEntity queryCompanyBycorpname(String corpname){
        return companyEntityRepository.queryCompanyByname(corpname);
    }
    @Override
    public CompanyEntity queryCompany(String corpname,String uscreditcode){
        return companyEntityRepository.queryCompany(corpname,uscreditcode);
    }
    @Override
    public CompanyEntity queryCompanyOne(String corpname,String uscreditcode){
        return companyEntityRepository.queryCompanyOne(corpname,uscreditcode);
    }


    @Override
    @Transactional(readOnly = true)
    public List<CompanyEntity> queryCompanyList(String enterprisename) {
        return companyEntityRepository.queryCompanyList(enterprisename);
    }

    @Override
    public List<CompanyEntity> queryAll(){
        return companyEntityRepository.queryAll();
    }

    @Override
    public Page<CompanyEntity> queryCompanyPage(String enterprisename,String uscreditcode, Pageable pageable){
        enterprisename= StringUtils.isNotBlank(enterprisename)?"%"+enterprisename+"%":"%%";
        if(StringUtils.isNotBlank(uscreditcode)){
            uscreditcode= StringUtils.isNotBlank(uscreditcode)?"%"+uscreditcode+"%":"%%";
            return companyEntityRepository.queryCompanyPage(enterprisename,uscreditcode,pageable);
        }

        return companyEntityRepository.queryCompanyPageAll(enterprisename,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyEntity> queryCompanyListBySearchName(String searchName) {
        return companyEntityRepository.queryCompanyListBySearchName(searchName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyEntity> queryCompanyListByPhone(String phone) {
        return companyEntityRepository.queryCompanyListByPhone(phone);
    }

    @Override
    public List<Object> querytypesenterprises(){
        return companyEntityRepository.querytypesenterprises();
    }

    @Override
    public List<Object> queryCompanys(Intellectmatch match ){
        String corptagid=match.getCorptagid();
        String corptype=match.getCorptype();
        String status =match.getStatus();
        String minrecapital=match.getMinrecapital();
        String maxrecapital=match.getMaxrecapital();
        Date startdate=match.getStartdate();
        Date enddate=match.getEnddate();
        List<Object> values = new ArrayList<Object>();
        String sql = "select DISTINCT c.companyid,c.enterprisename from company c left join accuratetagrelation r on c.companyid=r.cid where 1=1  ";

        if (StringUtils.isNotBlank(corptagid)) {
            String tagid="";
            String[] corptagids = corptagid.split(",");
            for (String tid:corptagids) {
                tagid+="'"+tid+"',";

            }
            if(StringUtils.isNotBlank(tagid)){
                tagid=tagid.substring(0,tagid.length()-1);

            }
            sql = sql + " and r.tagid   in ("+tagid+") ";
        }
       //企业类型
        if (StringUtils.isNotBlank(corptype)) {
            String type="";
            String[] corptypes = corptype.split(",");
            sql = sql +" and ( 1=1 ";
            for (String t:corptypes) {
                sql = sql + " or c.typesenterprises  like ? ";
                values.add("%"+t+"%");
            }
            sql = sql +" ) ";

        }

        if (StringUtils.isNotBlank(status)) {
            String state="";
            String[] statuss = status.split(",");
            for (String s:statuss) {
                state+="'"+s+"',";

            }
            if(StringUtils.isNotBlank(state)){
                state=state.substring(0,state.length()-1);

            }
            sql = sql + " and c.operationstatus  in ("+state+")  ";

        }

        if (StringUtils.isNotBlank(minrecapital)) {
            sql = sql + " and c.recapitaln  >= ? ";
            values.add(minrecapital);
        }

        if (StringUtils.isNotBlank(maxrecapital)) {
            sql = sql + " and c.recapitaln  <= ? ";
            values.add(maxrecapital);
        }

        if(startdate!=null){
            sql = sql + " and c.dateincorporation  >= ? ";
            values.add(startdate);
        }

        if(enddate!=null){
            sql = sql + " and c.dateincorporation  <= ? ";
            values.add(enddate);
        }

        List<Object> info = queryService.queryForObject(sql,values);
        return info;
    }

    @Override
    public List<Object> queryCompanysAuto(Intellectmatch match){
        String corptagid=match.getCorptagid();
        String corptype=match.getCorptype();
        String status =match.getStatus();
        String minrecapital=match.getMinrecapital();
        String maxrecapital=match.getMaxrecapital();
        Date startdate=match.getStartdate();
        Date enddate=match.getEnddate();
        List<Object> values = new ArrayList<Object>();
        String sql = "select DISTINCT c.companyid from company c left join accuratetagrelation r on c.companyid=r.cid where 1=1  ";

        if (StringUtils.isNotBlank(corptagid)) {
            String tagid="";
            String[] corptagids = corptagid.split(",");
            for (String tid:corptagids) {
                tagid+="'"+tid+"',";

            }
            if(StringUtils.isNotBlank(tagid)){
                tagid=tagid.substring(0,tagid.length()-1);

            }
            sql = sql + " and r.tagid   in ("+tagid+") ";
        }
        //企业类型
        if (StringUtils.isNotBlank(corptype)) {
            String type="";
            String[] corptypes = corptype.split(",");
            sql = sql +" and ( 1=1 ";
            for (String t:corptypes) {
                sql = sql + " or c.typesenterprises  like ? ";
                values.add("%"+t+"%");
            }
            sql = sql +" ) ";

        }

        if (StringUtils.isNotBlank(status)) {
            String state="";
            String[] statuss = status.split(",");
            for (String s:statuss) {
                state+="'"+s+"',";

            }
            if(StringUtils.isNotBlank(state)){
                state=state.substring(0,state.length()-1);

            }
            sql = sql + " and c.operationstatus  in ("+state+")  ";

        }

        if (StringUtils.isNotBlank(minrecapital)) {
            sql = sql + " and c.recapitaln  >= ? ";
            values.add(minrecapital);
        }

        if (StringUtils.isNotBlank(maxrecapital)) {
            sql = sql + " and c.recapitaln  <= ? ";
            values.add(maxrecapital);
        }

        if(startdate!=null){
            sql = sql + " and c.dateincorporation  >= ? ";
            values.add(startdate);
        }

        if(enddate!=null){
            sql = sql + " and c.dateincorporation  <= ? ";
            values.add(enddate);
        }

        List<Object> info = queryService.queryForObject(sql,values);
        return info;
    }

    @Override
    public List<CompanyEntity> queryCompanys(List<Object> companyids){
        return companyEntityRepository.queryCompanys(companyids);
    }

    @Override
    public  void updateInfoByCorpname(String corpname){
        CompanyEntity company=companyEntityRepository.queryCompanyByname(corpname);
        if(company!=null){

        }else{
            company=new CompanyEntity();
            company.setCompanyid(KeyUtils.getKey());

        }
        initInfo(company);

    }

    @Override
    public  void updateInfo(CompanyEntity company){
        initInfo(company);

    }

    private void initInfo(CompanyEntity company){
//        dataSERIOUSTAX(company);
//        dataTRADEMARK(company);
//        dataPATENT(company);
//        dataINSTITUTION(company);
//        dataEQUITY(company);
//        dataBUSINESS(company);
        dataGXCWB_QY_TJJ(company);
        dataBUSINESS_IPINFO_TRADEMARK(company);
//        dataBUSINESS_IPINFO_PATENT(company);
//        dataBUSINESS_STOCKERHOLDER(company);
//        dataBUSINESS_INSTITUTION(company);


        company.setState("1");
        update(company);
    }

    //BUSINESS_RISK_SERIOUSTAX
    private void dataSERIOUSTAX(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataSERIOUSTAX(company.getEnterprisename());

        JSONArray datas = result.getJSONArray("data");
        //注册地址
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);
            String companyregisaddress=data.getString("ADDRESS");
            if(StringUtils.isNotBlank(companyregisaddress)&&!"null".equals(companyregisaddress)){
                company.setCompanyregisaddress(companyregisaddress);
            }
            if(StringUtils.isNotBlank(company.getCompanyregisaddress())&&"null".equals(company.getCompanyregisaddress())){
                company.setCompanyregisaddress("");
            }
        }


    }

    //MCF_BUSINESS_IPINFO_TRADEMARK
    private void dataTRADEMARK(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataTRADEMARK(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        //商标名称
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);
            String name=data.getString("NAME");
        }


    }


    /**
     * MCF_BUSINESS_IPINFO_PATENT  专利信息
     * TYPE_NAME	类型
     * PATENT_NAME	名称
     * REQUEST_NUM	专利申请号
     * REQUEST_DATE	申请日期
     */
    private void dataPATENT(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataPATENT(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        if(datas!=null&&datas.size()>0){
            for (int i=0;i<datas.size();i++) {
                JSONObject data = datas.getJSONObject(i);
                String patenttype=data.getString("TYPE_NAME");
                String patentname=data.getString("PATENT_NAME");
                String applicationnum=data.getString("REQUEST_NUM");
                String applicationtime=data.getString("REQUEST_DATE");
                Date applicationtime_d=null;
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
                if(StringUtils.isNotBlank(applicationtime)){
                    try {
                        applicationtime_d=sdf.parse(applicationtime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if(StringUtils.isNotBlank(applicationnum)){
                    List<Companypatent> companypatents=companypatentRepository.findByapplicationnum(applicationnum);
                    if(companypatents!=null&&companypatents.size()>0){

                    }else{
                        Companypatent companypatent=new Companypatent();
                        companypatent.setCompanypatentid(KeyUtils.getKey());
                        companypatent.setCompanyid(company.getCompanyid());
                        companypatent.setEnterprisename(company.getEnterprisename());
                        companypatent.setEnterprisenum(company.getUscreditcode());
                        if(StringUtils.isNotBlank(patenttype)&&!"null".equals(patenttype)){
                            companypatent.setPatenttype(patenttype);
                        }
                        if(StringUtils.isNotBlank(patentname)&&!"null".equals(patentname)){
                            companypatent.setPatentname(patentname);
                        }
                        if(StringUtils.isNotBlank(applicationnum)&&!"null".equals(applicationnum)){
                            companypatent.setApplicationnum(applicationnum);
                        }
                        if(applicationtime_d!=null){
                            companypatent.setApplicationtime(applicationtime_d);
                        }




                        companypatentRepository.save(companypatent);
                    }
                }

            }


        }


    }

    /**
     * MCF_BUSINESS_INSTITUTION
     *   * {"code":200,"flag":true,"data":[{"STATUS":"存续（在营、开业、在册）","REGIST_CAPI":"1000万元人民币",
     *      * "SCOPE2":"房屋建筑工程、园林绿化工程、安防工程、公路交通工程的施工；道路普通货物运输（危险品除外）。",
     *      * "CREDIT_NO":"91321112MA1WDUYB7J","OPER_NAME":"汤光华","START_DATE":"2018-04-19"}],"message":"请求成功！"}
     */
    private void dataINSTITUTION(CompanyEntity company ){
        JSONObject  result= BigDataUtil.getDataINSTITUTION(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);
            //状态
            String operationstatus=data.getString("STATUS");
            //注册资本
            String recapital=data.getString("REGIST_CAPI");
            //营业范围
            String naturebusiness=data.getString("SCOPE2");
            //统一社会信用代码
            String uscreditcode=data.getString("CREDIT_NO");
            //法定代表人
            String lrepresentative=data.getString("OPER_NAME");
            //成立日期
            Date dateincorporation=null;
            if(data.getDate("START_DATE")!=null&&"!null".equals(data.getDate("START_DATE"))){
                dateincorporation=data.getDate("START_DATE");
            }
            if(StringUtils.isNotBlank(operationstatus)){
                company.setOperationstatus(operationstatus);
            }
            if(StringUtils.isNotBlank(recapital)&&!"null".equals(recapital)){
                company.setRecapital(recapital);
                String r=recapital;
                r=r.substring(0,r.indexOf("万"));
                r=r.trim();
                try {
                    Double b=Double.valueOf(r);
                    company.setRecapitaln(b);
                }catch (NumberFormatException e){

                }

            }
            if(StringUtils.isNotBlank(naturebusiness)&&!"null".equals(naturebusiness)){
                company.setNaturebusiness(naturebusiness);
            }
            if(StringUtils.isNotBlank(uscreditcode)&&!"null".equals(uscreditcode)){
                company.setUscreditcode(uscreditcode);
            }
            if(StringUtils.isNotBlank(lrepresentative)&&!"null".equals(lrepresentative)){
                company.setLrepresentative(lrepresentative);
            }

            if(dateincorporation!=null&&!"null".equals(dateincorporation)){
                company.setDateincorporation(dateincorporation);
            }
            //
            if(StringUtils.isNotBlank(company.getOperationstatus())&&"null".equals(company.getOperationstatus())){
                company.setOperationstatus("");
            }

            if(StringUtils.isNotBlank(company.getRecapital())&&"null".equals(company.getRecapital())){
                company.setRecapital("");
            }

            if(StringUtils.isNotBlank(company.getNaturebusiness())&&"null".equals(company.getNaturebusiness())){
                company.setNaturebusiness("");
            }

            if(StringUtils.isNotBlank(company.getUscreditcode())&&"null".equals(company.getUscreditcode())){
                company.setUscreditcode("");
            }

            if(StringUtils.isNotBlank(company.getLrepresentative())&&"null".equals(company.getLrepresentative())){
                company.setLrepresentative("");
            }

            if(company.getDateincorporation()!=null&&"null".equals(company.getDateincorporation())){
                company.setDateincorporation(null);
            }
        }


    }

    //MCF_BUSINESS_EQUITY
    private void dataEQUITY(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataEQUITY(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);
            //登记机关
            String registrationauthority=data.getString("BELONG_ORG");
            //组织机构代码
            String organcode=data.getString("ORG_NO");
            if(StringUtils.isNotBlank(registrationauthority)&&!"null".equals(registrationauthority)){
                company.setRegistrationauthority(registrationauthority);
            }
            if(StringUtils.isNotBlank(organcode)&&!"null".equals(organcode)){
                company.setOrgancode(organcode);
            }
            if(StringUtils.isNotBlank(company.getRegistrationauthority())&&"null".equals(company.getRegistrationauthority())){
                company.setRegistrationauthority("");
            }
            if(StringUtils.isNotBlank(company.getOrgancode())&&"null".equals(company.getOrgancode())){
                company.setOrgancode("");
            }
        }

    }

    //MCF_BUSINESS
    private void dataBUSINESS(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataBUSINESS(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);
            //企业类型
            String typesenterprises=data.getString("TYPE");
            if(StringUtils.isNotBlank(typesenterprises)&&!"null".equals(typesenterprises)){
                company.setTypesenterprises(typesenterprises);
            }

            if(StringUtils.isNotBlank(company.getTypesenterprises())&&"null".equals(company.getTypesenterprises())){
                company.setTypesenterprises("");
            }
        }

    }


    /**
     * 南京建邺“秒触发”政策服务系统-GXCWB_QY_TJJ-0803
     * 请求参数：USCC_2（统一社会信用代码），token
     * 返回参数：
     * 	BUSINESS_INCOME	营业收入
     * 	TOTAL_LIABILITIES	负债合计
     */

    private void dataGXCWB_QY_TJJ(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataGXCWB_QY_TJJ(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);
            //营业收入
            Double sale_income=null;
            if(data.getDouble("BUSINESS_INCOME")!=null&&!"null".equals(data.getDouble("BUSINESS_INCOME"))){
                sale_income=data.getDouble("BUSINESS_INCOME");
            }
            //负债合计
            Double debit_amount=data.getDouble("TOTAL_LIABILITIES");
            if(data.getDouble("TOTAL_LIABILITIES")!=null&&!"null".equals(data.getDouble("TOTAL_LIABILITIES"))){
                debit_amount=data.getDouble("TOTAL_LIABILITIES");
            }
            //年份
           /* String year=data.getString("YEAR");
            if(year!=null&&!"null".equals(year)){
                Gxcwbqytjj info=gxcwbqytjjService.findByYear(company.getCompanyid(),year);
                if(info!=null){
                    info.setSale_income(sale_income);
                    info.setDebit_amount(debit_amount);
                    gxcwbqytjjService.update(info);
                }else{
                    info=new Gxcwbqytjj();
                    info.setId(KeyUtils.getKey());
                    info.setCompanyid(company.getCompanyid());
                    info.setCompanyname(company.getEnterprisename());
                    info.setSale_income(sale_income);
                    info.setDebit_amount(debit_amount);
                    gxcwbqytjjService.save(info);
                }
            }*/

            Gxcwbqytjj info=gxcwbqytjjService.findBycompanyid(company.getCompanyid());
            if(info!=null){
                info.setSale_income(sale_income);
                info.setDebit_amount(debit_amount);
                gxcwbqytjjService.update(info);
            }else{
                info=new Gxcwbqytjj();
                info.setId(KeyUtils.getKey());
                info.setCompanyid(company.getCompanyid());
                info.setCompanyname(company.getEnterprisename());
                info.setSale_income(sale_income);
                info.setDebit_amount(debit_amount);
                info.setYear("2019");
                gxcwbqytjjService.save(info);
            }


        }

    }


    /**
     * 南京建邺“秒触发”政策服务系统-BUSINESS_IPINFO_TRADEMARK-0803
     * 请求参数：PNAME，token
     * 返回参数：
     * 	ID	商标ID
     * 	NAME	商标名称
     * 	COMPANY	企业名称
     * 	APPLY_DATE	申请日期
     * 	PERIOD	专用权期限
     * 	AGENT	代理人名称
     */
    private void dataBUSINESS_IPINFO_TRADEMARK(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataBUSINESS_IPINFO_TRADEMARK(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);
            //商标ID
            String trademarkid=data.getString("ID");
            //商标名称
            String trademarkname=data.getString("NAME");
            //申请日期
            Date apply_date=null;
            if(data.getString("APPLY_DATE")!=null&&!"null".equals(data.getString("APPLY_DATE"))&&!"-".equals(data.getString("APPLY_DATE"))){
                apply_date=data.getDate("APPLY_DATE");
            }
            //专用权期限
            String period=data.getString("PERIOD");
            //代理人名称
            String agent=data.getString("AGENT");

            Businessipinfotrademark info=businessipinfotrademarkService.findBytrademarkid(trademarkid);
            if(info!=null){
                info.setTrademarkname(trademarkname);
                info.setApply_date(apply_date);
                info.setPeriod(period);
                info.setAgent(agent);
                businessipinfotrademarkService.update(info);
            }else{
                info=new Businessipinfotrademark();
                info.setId(KeyUtils.getKey());
                info.setCompanyid(company.getCompanyid());
                info.setCompanyname(company.getEnterprisename());
                info.setTrademarkid(trademarkid);
                info.setTrademarkname(trademarkname);
                info.setApply_date(apply_date);
                info.setPeriod(period);
                info.setAgent(agent);
                businessipinfotrademarkService.save(info);
            }


        }

    }



    /**
     * 南京建邺“秒触发”政策服务系统-BUSINESS_IPINFO_PATENT-0803
     * 请求参数：NAME，token
     * 返回参数：
     * 	NAME	专利名称
     *  PEQUEST_DATE	申请日期
     * 	ID	专利ID
     * 	REQUEST_NUM	专利申请号
     * 	OUTHOR_DATE	授权公布日
     * 	OUTHOR_NMM	授权号
     */
    private void dataBUSINESS_IPINFO_PATENT(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataBUSINESS_IPINFO_PATENT(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);

            //专利名称
            String patentname=data.getString("NAME");
            //申请日期
            Date applicationtime=null;
            if(data.getDouble("PEQUEST_DATE")!=null&&!"null".equals(data.getDouble("PEQUEST_DATE"))){
                applicationtime=data.getDate("PEQUEST_DATE");
            }

            //专利ID
            String patentid=data.getString("ID");
            //专利申请号
            String applicationnum=data.getString("REQUEST_NUM");
            //授权公布日
            Date dateauthorization=null;
            if(data.getDouble("OUTHOR_DATE")!=null&&!"null".equals(data.getDouble("OUTHOR_DATE"))){
                dateauthorization=data.getDate("OUTHOR_DATE");
            }
            //授权号
            String outhor_nmm=data.getString("OUTHOR_NMM");

            Companypatent info=companypatentService.findBypubannomentno(outhor_nmm);

            if(info!=null){
                info.setPatentname(patentname);
                info.setApplicationtime(applicationtime);
                info.setApplicationnum(applicationnum);
                info.setDateauthorization(dateauthorization);
                info.setOuthor_nmm(outhor_nmm);
                companypatentService.update(info);
            }else{
                info=new Companypatent();
                info.setCompanypatentid(KeyUtils.getKey());
                info.setCompanyid(company.getCompanyid());
                info.setPatentname(patentname);
                info.setPatentid(patentid);
                info.setApplicationtime(applicationtime);
                info.setApplicationnum(applicationnum);
                info.setDateauthorization(dateauthorization);
                info.setOuthor_nmm(outhor_nmm);
                companypatentService.save(info);
            }


        }

    }




    /**
     * 南京建邺“秒触发”政策服务系统-BUSINESS_STOCKERHOLDER0803
     * 请求参数：NAME，token
     * 返回参数：
     * NAME	股东名称
     * AMOUNT	投资金额
     * PERCENT	占股比
     */
    private void dataBUSINESS_STOCKERHOLDER(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataBUSINESS_STOCKERHOLDER(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);

            //股东名称
            String name=data.getString("NAME");

            //投资金额
            String amount=data.getString("AMOUNT");
            //占股比
            String percent=data.getString("PERCENT");


        }

    }


    /**
     * 南京建邺“秒触发”政策服务系统-BUSINESS_INSTITUTION-0803
     * 请求参数：NAME，token
     * 返回参数：
     * NAME	企业名称
     * REGIST_CAPI	注册资本
     * HISTORY_NAMES	历史名称
     * ADDRESS	地址
     * REG_NO	企业注册号
     * SCOPE2	经营范围
     * TERM_START	营业开始日期
     * TERM_END	营业结束日期
     * BELONG_ORG	所属工商局
     * OPER_NAME	企业法定代表人
     * START_DATE	成立日期
     * END_DATE	注销日期
     * CHECK_DATE	核准日期
     * STATUS	经营状态
     * ORD_NO	组织结构号
     * CREDIT_NO	统一社会信用代码
     * PROVINCE	省份编写
     * CITY	城市编码
     */
    private void dataBUSINESS_INSTITUTION(CompanyEntity company){
        JSONObject  result= BigDataUtil.getDataBUSINESS_INSTITUTION(company.getEnterprisename());
        JSONArray datas = result.getJSONArray("data");
        if(datas!=null&&datas.size()>0){
            JSONObject data = datas.getJSONObject(0);

            //企业名称
            String name=data.getString("NAME");
            //注册资本
            String  REGIST_CAPI=data.getString("REGIST_CAPI");

            // 	历史名称
            String HISTORY_NAMES=data.getString("HISTORY_NAMES");
           // 	地址
            String ADDRESS=data.getString("ADDRESS");
            // 	企业注册号
            String REG_NO=data.getString("REG_NO");
            // 	经营范围
            String SCOPE2=data.getString("SCOPE2");
            // 	营业开始日期
            Date TERM_START=null;
            if(data.getDouble("TERM_START")!=null&&!"null".equals(data.getDouble("TERM_START"))){
                TERM_START=data.getDate("TERM_START");
            }
            // 	营业结束日期
            Date TERM_END=null;
            if(data.getDouble("TERM_END")!=null&&!"null".equals(data.getDouble("TERM_END"))){
                TERM_END=data.getDate("TERM_END");
            }
            // 	所属工商局
            String BELONG_ORG=data.getString("BELONG_ORG");
            // 	企业法定代表人
            String OPER_NAME=data.getString("OPER_NAME");
            // 	成立日期
            Date START_DATE=null;
            if(data.getDouble("START_DATE")!=null&&!"null".equals(data.getDouble("START_DATE"))){
                START_DATE=data.getDate("START_DATE");
            }
            // 	注销日期
            Date END_DATE=null;
            if(data.getDouble("END_DATE")!=null&&!"null".equals(data.getDouble("END_DATE"))){
                END_DATE=data.getDate("END_DATE");
            }
            // 	核准日期
            Date CHECK_DATE=null;
            if(data.getDouble("CHECK_DATE")!=null&&!"null".equals(data.getDouble("CHECK_DATE"))){
                CHECK_DATE=data.getDate("CHECK_DATE");
            }
           // 	经营状态
            String STATUS=data.getString("STATUS");
            // 	组织结构号
            String ORD_NO=data.getString("ORD_NO");
            // 	统一社会信用代码
            String CREDIT_NO=data.getString("CREDIT_NO");
            // 	省份编写
            String PROVINCE=data.getString("PROVINCE");
            // 	城市编码
            String CITY=data.getString("CITY");

            if(StringUtils.isNotBlank(REGIST_CAPI)&&!"null".equals(REGIST_CAPI)){
                company.setRecapital(REGIST_CAPI);
                if(REGIST_CAPI.indexOf("万")!=-1){
                    REGIST_CAPI=REGIST_CAPI.substring(0,REGIST_CAPI.indexOf("万"));
                    REGIST_CAPI=REGIST_CAPI.trim();
                    Double b=Double.valueOf(REGIST_CAPI);
                    company.setRecapitaln(b);

                }
            }
            if(StringUtils.isNotBlank(HISTORY_NAMES)&&!"null".equals(HISTORY_NAMES)){
                company.setNameusedbefore(HISTORY_NAMES);
            }

            if(StringUtils.isNotBlank(ADDRESS)&&!"null".equals(ADDRESS)){
                company.setCompanyregisaddress(ADDRESS);
            }

            if(StringUtils.isNotBlank(REG_NO)&&!"null".equals(REG_NO)){
                company.setBusinessregisnum(REG_NO);
            }

            if(StringUtils.isNotBlank(SCOPE2)&&!"null".equals(SCOPE2)){
                company.setNaturebusiness(SCOPE2);
            }

            if(StringUtils.isNotBlank(BELONG_ORG)&&!"null".equals(BELONG_ORG)){
                company.setRegistrationauthority(BELONG_ORG);
            }

            if(StringUtils.isNotBlank(OPER_NAME)&&!"null".equals(OPER_NAME)){
                company.setLrepresentative(OPER_NAME);
            }


            if(TERM_START!=null) {
                company.setTerm_startdate(TERM_START);
            }
            if (TERM_END != null) {
                company.setTerm_enddate(TERM_END);
            }
            if (START_DATE != null) {
                company.setDateincorporation(START_DATE);
            }
            if (END_DATE != null) {
                company.setEnd_date(END_DATE);
            }
            if (CHECK_DATE != null) {
                company.setColumcheck_date(CHECK_DATE);
            }

            if (StringUtils.isNotBlank(STATUS)&&!"null".equals(STATUS)) {
                company.setOperationstatus(STATUS);
            }

            if (StringUtils.isNotBlank(ORD_NO)&&!"null".equals(ORD_NO)) {
                company.setOrgancode(ORD_NO);
            }

            if (StringUtils.isNotBlank(CREDIT_NO)&&!"null".equals(CREDIT_NO)) {
                company.setUscreditcode(CREDIT_NO);
            }

            if (StringUtils.isNotBlank(PROVINCE)&&!"null".equals(PROVINCE)) {
                company.setProvince(PROVINCE);
            }

            if (StringUtils.isNotBlank(CITY)&&!"null".equals(CITY)) {
                company.setCity(CITY);
            }

            }
    }


}
