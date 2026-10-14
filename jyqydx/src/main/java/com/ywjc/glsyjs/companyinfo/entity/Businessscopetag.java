package com.ywjc.glsyjs.companyinfo.entity;


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
@Table(name = "businessscopetag")
public class Businessscopetag extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
	@Column(name = "businessscope")	
    private String businessscope;
	@Column(name = "tag")	
    private String tag;

	@Transient
	private String[] tags;

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessscope() {
		return businessscope;
	}

	public void setBusinessscope(String businessscope) {
		this.businessscope = businessscope;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
