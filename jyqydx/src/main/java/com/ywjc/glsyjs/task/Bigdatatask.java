package com.ywjc.glsyjs.task;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.datashare.entity.Departmentinfo;
import com.ywjc.datashare.entity.Personinfo;
import com.ywjc.datashare.service.DepartmentinfoService;
import com.ywjc.datashare.service.PersoninfoService;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.companyinfo.entity.Businessscopetag;
import com.ywjc.glsyjs.companyinfo.service.BusinessscopetagService;
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
import com.ywjc.glsyjs.interfaces.oauth.entity.OauthEntity;
import com.ywjc.glsyjs.interfaces.oauth.service.OauthEntityService;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetagrelation;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagService;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagrelationService;
import com.ywjc.glsyjs.interfaces.tag.service.TagService;
import com.ywjc.glsyjs.interfaces.utils.GetPostUtil;
import com.ywjc.glsyjs.interfaces.utils.YshApiUtil;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.entity.Taskdorecord;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import com.ywjc.glsyjs.sys.service.TaskdorecordService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component("bigdatatask")
public class Bigdatatask {

	private static Logger log = Logger.getLogger(Bigdatatask.class);


	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;

	@Resource(name = "corpbusinessscopeServiceImpl")
	private CorpbusinessscopeService corpbusinessscopeService;

	@Resource(name = "businessscopetagServiceImpl")
	private BusinessscopetagService businessscopetagService;

	@Resource(name = "tagServiceImpl")
	private TagService tagService;

	@Resource(name = "oauthEntityServiceImpl")
	private OauthEntityService oauthEntityService;


	@Resource(name = "taskdorecordServiceImpl")
	private TaskdorecordService taskdorecordService;

	@Resource(name = "accuratetagServiceImpl")
	private AccuratetagService accuratetagService;

	@Resource(name = "accuratetagrelationServiceImpl")
	private AccuratetagrelationService accuratetagrelationService;


	@Resource(name = "iihightechenterprisesServiceImpl")
	private IihightechenterprisesService iihightechenterprisesService;

	@Resource(name = "companypatentServiceImpl")
	private CompanypatentService companypatentService;

	//政务大数据
	public void bigdatashare() {
		Date begintime=new Date();
		List<CompanyEntity> corps=companyEntityService.findAll();
		for (CompanyEntity corp :corps) {
			companyEntityService.updateInfo(corp);
		}
		Date endtime=new Date();
		Taskdorecord taskdorecord=new Taskdorecord();
		taskdorecord.setId(KeyUtils.getKey());
		taskdorecord.setTasktype("2");
		taskdorecord.setTaskclass("Bigdatatask");
		taskdorecord.setTasmethod("bigdatashare");
		taskdorecord.setDatacount(corps.size()+"");
		taskdorecord.setUpdatedatacount(corps.size()+"");
		taskdorecord.setBegintime(begintime);
		taskdorecord.setEndtime(endtime);
		taskdorecordService.save(taskdorecord);
	}




	//企业经营范围处理
	public void corpbusinessscope() {
		Date begintime=new Date();
		List<CompanyEntity> corps=companyEntityService.findAll();
		int i=0;
		for (CompanyEntity corp :corps) {
			String naturebusiness=corp.getNaturebusiness();
			if(StringUtils.isNotBlank(naturebusiness)){
				String[] naturebusinesss=naturebusiness.split("；");
				if(naturebusinesss!=null&&naturebusinesss.length>0){
					for (String business:naturebusinesss) {
						Corpbusinessscope scorp=corpbusinessscopeService.findByScorp(corp.getCompanyid(),business);
						if(scorp==null){
							i++;
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

		Date endtime=new Date();
		Taskdorecord taskdorecord=new Taskdorecord();
		taskdorecord.setId(KeyUtils.getKey());
		taskdorecord.setTasktype("3");
		taskdorecord.setTaskclass("Bigdatatask");
		taskdorecord.setTasmethod("bigdatashare");
		taskdorecord.setDatacount(corps.size()+"");
		taskdorecord.setUpdatedatacount(i+"");
		taskdorecord.setBegintime(begintime);
		taskdorecord.setEndtime(endtime);
		taskdorecordService.save(taskdorecord);
	}

	//企业根据经营范围自动打标签
	public void corpbusinessscopeTag() {
		Date begintime=new Date();
		List<CompanyEntity> corps=companyEntityService.findAll();
		int i=0;
		for (CompanyEntity corp :corps) {
			boolean isupdate=false;
			//经营范围
			List<Corpbusinessscope> corpbusinessscopes=corpbusinessscopeService.findByScorpBycompanyid(corp.getCompanyid());
			if(corpbusinessscopes!=null&&corpbusinessscopes.size()>0){
				//原有标签
				List<String> oldtag=tagService.findTagidByCid(corp.getCompanyid());
				for (Corpbusinessscope corpbusinessscope:corpbusinessscopes) {
					Businessscopetag businessscopetag=businessscopetagService.findByscope(corpbusinessscope.getBusinessscope());
					if(businessscopetag!=null&&StringUtils.isNotBlank(businessscopetag.getTag())){
						String[] tags=businessscopetag.getTag().split(",");
						if(tags!=null&&tags.length>0){
							for (String tagid:tags) {
								if(oldtag.contains(tagid)){
									oldtag.remove(tagid);
								}
								boolean isadd=tagService.saveTagRelation(corp.getCompanyid(),tagid);
								if(isadd){
									isupdate=true;
								}
							}

						}

					}

				}
				if(oldtag!=null&&oldtag.size()>0){
					isupdate=true;
					for (String oldtagid :oldtag) {
						tagService.deleteByCid(oldtagid,corp.getCompanyid());
					}

				}
			}
			if(isupdate){
				i++;
			}
		}
		Date endtime=new Date();
		Taskdorecord taskdorecord=new Taskdorecord();
		taskdorecord.setId(KeyUtils.getKey());
		taskdorecord.setTasktype("4");
		taskdorecord.setTaskclass("Bigdatatask");
		taskdorecord.setTasmethod("bigdatashare");
		taskdorecord.setDatacount(corps.size()+"");
		taskdorecord.setUpdatedatacount(i+"");
		taskdorecord.setBegintime(begintime);
		taskdorecord.setEndtime(endtime);
		taskdorecordService.save(taskdorecord);
	}


	//高新技术企业自动打标签
	public void iihcorptag () {
		Date begintime=new Date();
		int i=0;
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
			initicorp(icorp);
			Accuratetagrelation tagRelation=accuratetagrelationService.findByCorp(icorp.getCompanyid(),tag.getTagid(),icorp.getRecognitionyear());
			if(tagRelation==null){
				tagRelation=new Accuratetagrelation();
				tagRelation.setCid(icorp.getCompanyid());
				tagRelation.setTagid(tag.getTagid());
				tagRelation.setCreatetime(new Date());
				tagRelation.setTagyear(icorp.getRecognitionyear());
				tagRelation.setTagname("高新技术企业");
				accuratetagrelationService.save(tagRelation);
				i++;
			}
		}

		Date endtime=new Date();
		Taskdorecord taskdorecord=new Taskdorecord();
		taskdorecord.setId(KeyUtils.getKey());
		taskdorecord.setTasktype("5");
		taskdorecord.setTaskclass("Bigdatatask");
		taskdorecord.setTasmethod("iihcorptag");
		taskdorecord.setDatacount(allcorps.size()+"");
		taskdorecord.setUpdatedatacount(i+"");
		taskdorecord.setBegintime(begintime);
		taskdorecord.setEndtime(endtime);
		taskdorecordService.save(taskdorecord);
	}


	private void initicorp(Iihightechenterprises icorp){
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

	private void initpcorp(Companypatent companypatent){
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




	//专利信息自动打标签

	public void cpatenttag () {
		Date begintime=new Date();
		int i=0;
		List<Companypatent> companypatents=companypatentService.findAll();
		for (Companypatent companypatent:companypatents) {
			initpcorp(companypatent);
			String tagname = "企业授权发明专利";
			if (companypatent.getPatenttype().contains("外观设计")) {
				tagname = "授权外观专利";
			} else if (companypatent.getPatenttype().contains("实用新型")) {
				tagname = "授权实用新型专利";
			}
			Accuratetag tag = accuratetagService.findTagByTagname(tagname);
			if (tag == null) {
				tag = new Accuratetag();
				tag.setTagname(tagname);
				tag.setCreatetime(new Date());
				tag.setState("1");
				accuratetagService.save(tag);
			}
			tag = accuratetagService.findTagByTagname(tagname);
			Accuratetagrelation tagRelation = accuratetagrelationService.findByCorp(companypatent.getCompanyid(), tag.getTagid(), companypatent.getPatentyear());
			if (tagRelation == null) {
				tagRelation = new Accuratetagrelation();
				tagRelation.setCid(companypatent.getCompanyid());
				tagRelation.setTagid(tag.getTagid());
				tagRelation.setCreatetime(new Date());
				tagRelation.setTagyear(companypatent.getPatentyear());
				tagRelation.setTagname(tagname);
				accuratetagrelationService.save(tagRelation);
				i++;
			}
		}

		Date endtime=new Date();
		Taskdorecord taskdorecord=new Taskdorecord();
		taskdorecord.setId(KeyUtils.getKey());
		taskdorecord.setTasktype("6");
		taskdorecord.setTaskclass("Bigdatatask");
		taskdorecord.setTasmethod("cpatenttag");
		taskdorecord.setDatacount(companypatents.size()+"");
		taskdorecord.setUpdatedatacount(i+"");
		taskdorecord.setBegintime(begintime);
		taskdorecord.setEndtime(endtime);
		taskdorecordService.save(taskdorecord);
	}





	//企业收款账户查询
	public void corpAccount() {
		Date begintime=new Date();
		List<CompanyEntity> corps=companyEntityService.findAll();
		int i=0;
		for (CompanyEntity corp :corps) {
			//收款信息
			if(StringUtils.isNotBlank(corp.getUscreditcode())){
				boolean flag=getaccountinfo(corp);
				if(flag){
					i++;
				}
			}


		}
		Date endtime=new Date();
		Taskdorecord taskdorecord=new Taskdorecord();
		taskdorecord.setId(KeyUtils.getKey());
		taskdorecord.setTasktype("5");
		taskdorecord.setTaskclass("Bigdatatask");
		taskdorecord.setTasmethod("bigdatashare");
		taskdorecord.setDatacount(corps.size()+"");
		taskdorecord.setUpdatedatacount(i+"");
		taskdorecord.setBegintime(begintime);
		taskdorecord.setEndtime(endtime);
		taskdorecordService.save(taskdorecord);
	}



	//赋予账号信息（返回页面）
	private boolean getaccountinfo(CompanyEntity corp ){
		//获取token信息
		boolean isupdate=false;
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
			isupdate=true;
		}
		return isupdate;
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






}
