package com.ywjc.glsyjs.mail.filter;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

public class Casclienturl {
	private String ewmurl;

	public Casclienturl() {
		super();
	}

	public Casclienturl(String configFile) throws ConfigurationException {
		super();
		Configuration config = new PropertiesConfiguration(configFile);
		this.ewmurl=config.getString("createerweimaurl");
	}
	public String getEwmurl() {
		return ewmurl;
	}

	public void setEwmurl(String ewmurl) {
		this.ewmurl = ewmurl;
	}

	public GenericKeyedObjectPoolConfig getTrackerClientPoolConfig() {
		GenericKeyedObjectPoolConfig poolConfig = new GenericKeyedObjectPoolConfig();

		return poolConfig;
	}

	public GenericKeyedObjectPoolConfig getStorageClientPoolConfig() {
		GenericKeyedObjectPoolConfig poolConfig = new GenericKeyedObjectPoolConfig();
		return poolConfig;
	}

}
