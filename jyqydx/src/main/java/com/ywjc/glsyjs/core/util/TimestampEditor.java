/*
 */
package com.ywjc.glsyjs.core.util;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.time.DateUtils;

/**
 * 日期类型转换
 * 
 */
public class TimestampEditor extends PropertyEditorSupport {

	/** 默认日期格式 */
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** 是否将空转换为null */
	private boolean emptyAsNull;

	/** 日期格式 */
	private String dateFormat = DEFAULT_DATE_FORMAT;

	/**
	 * @param emptyAsNull
	 *            是否将空转换为null
	 */
	public TimestampEditor(boolean emptyAsNull) {
		this.emptyAsNull = emptyAsNull;
	}

	/**
	 * @param emptyAsNull
	 *            是否将空转换为null
	 * @param dateFormat
	 *            日期格式
	 */
	public TimestampEditor(boolean emptyAsNull, String dateFormat) {
		this.emptyAsNull = emptyAsNull;
		this.dateFormat = dateFormat;
	}

	/**
	 * 获取日期
	 * 
	 * @return 日期
	 */
	@Override
	public String getAsText() {
		Timestamp value = (Timestamp) getValue();
		return value != null ? new SimpleDateFormat(dateFormat).format(value)
				: "";
	}

	/**
	 * 设置日期
	 * 
	 * @param text
	 *            字符串
	 */
	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String value = text.trim();
			if (emptyAsNull && "".equals(value)) {
				setValue(null);
			} else {
				try {
					setValue(new Timestamp(DateUtils.parseDate(value,
							CoreAttributes.DATE_PATTERNS).getTime()));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		}
	}

}