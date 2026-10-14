package com.ywjc.glsyjs.interfaces.company.entity;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.persistence.*;

import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;


import freemarker.template.TemplateException;

@Entity
@Table(name = "corpbusinessscope")
public class Corpbusinessscope extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "id")	
    private String id;
	@Column(name = "companyid")	
    private String companyid;
	@Column(name = "enterprisename")	
    private String enterprisename;
	@Column(name = "businessscope")	
    private String businessscope;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}

	public String getBusinessscope() {
		return businessscope;
	}

	public void setBusinessscope(String businessscope) {
		this.businessscope = businessscope;
	}
}
