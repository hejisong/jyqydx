package com.ywjc.glsyjs.shiro.security;

import com.ywjc.glsyjs.account.entity.Permission;
import com.ywjc.glsyjs.account.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ChainDefinitionSectionMetaSource implements
		FactoryBean<Ini.Section> {

	@Autowired
	private PermissionService permissionService;

	/**
	 * Default filter chain definitions
	 */
	private String filterChainDefinitions;

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws BeansException {
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);

		Ini.Section section = ini
				.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}

		for (Permission resource : permissionService.findAll()) {
			if (StringUtils.isNotEmpty(resource.getValue())
					&& StringUtils.isNotEmpty(resource.getPermission())) {
				section.put(resource.getValue(), resource.getPermission());
			}
		}

		return section;
	}

	@Override
	public Class<?> getObjectType() {
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
