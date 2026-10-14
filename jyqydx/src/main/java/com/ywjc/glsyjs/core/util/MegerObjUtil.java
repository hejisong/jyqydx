package com.ywjc.glsyjs.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class MegerObjUtil {
	private static Logger log = Logger.getLogger(MegerObjUtil.class);

	/**
	 * 
	 * @param nobj 新值
	 * @param oobj 原值
	 * @param info 要比较的字段
	 * @param c    对象类型
	 * @return
	 * @throws Exception
	 */
	public static String merger(Object nobj, Object oobj, List<String[]> info, Class c) {
		Object oldObj = c.cast(oobj);
		Object newObj = c.cast(nobj);
		StringBuffer sb = new StringBuffer();
		// sb.append("<table><tr><td>序号</td><td>名称</td><td>原值</td><td>新值</td></tr>");
		Object nVal = null;
		Object oVal = null;
		Method m = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = null;
		String entityName = c.getName().substring(c.getName().lastIndexOf(".") + 1, c.getName().length());
		String column = "";
		for (String[] en : info) {
			name = en[0];
			name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
			if (Character.isLowerCase(en[0].charAt(0))) {
				column = en[0];
			} else {
				column = (new StringBuilder()).append(Character.toLowerCase(en[0].charAt(0))).append(en[0].substring(1))
						.toString();
			}
			try {
				m = c.getMethod(name);
				nVal = m.invoke(newObj);
				oVal = m.invoke(oldObj);
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (SecurityException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (InvocationTargetException e) {
				log.error(e.getMessage());
				log.info(e);
			}

			if (nVal != null && nVal instanceof String) {
				if ("".equals(String.valueOf(nVal))) {
					nVal = null;
				}
			}
			if (oVal != null && oVal instanceof String) {
				if ("".equals(String.valueOf(oVal))) {
					oVal = null;
				}
			}
			if (oVal == null && nVal != null) {
				if (!(nVal instanceof Date)) {
					sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>").append("")
							.append("</td><td  name='" + entityName + "'  column='" + column + "'>")
							.append(String.valueOf(nVal)).append("</td></tr>");
				} else {
					sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>").append("")
							.append("</td><td  name='" + entityName + "'  column='" + column + "'>")
							.append(sdf.format((Date) nVal)).append("</td></tr>");
				}
			} else if (oVal != null && nVal == null) {
				if (!(nVal instanceof Date)) {
					sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
							.append(String.valueOf(oVal))
							.append("</td><td  name='" + entityName + "'  column='" + column + "'>").append("")
							.append("</td></tr>");
				} else {
					sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
							.append(sdf.format((Date) oVal))
							.append("</td><td  name='" + entityName + "'  column='" + column + "'>").append("")
							.append("</td></tr>");
				}
			} else {
				if (oVal instanceof String) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
								.append(String.valueOf(oVal))
								.append("</td><td  name='" + entityName + "'  column='" + column + "'>")
								.append(String.valueOf(nVal)).append("</td></tr>");
					}
				} else if (oVal instanceof Long) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
//					if((Long)oVal != (Long)nVal){
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
								.append((Long) oVal)
								.append("</td><td  name='" + entityName + "'  column='" + column + "'>")
								.append(String.valueOf((Long) nVal)).append("</td></tr>");
					}
				} else if (oVal instanceof Double) {
					if ((Double) oVal - (Double) nVal > 0.0000001) {
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
								.append((Double) oVal)
								.append("</td><td  name='" + entityName + "'  column='" + column + "'>")
								.append(String.valueOf((Double) nVal)).append("</td></tr>");
					}
				}else if(oVal instanceof Date){
					if(null!=oVal&&null!=nVal) {
						Long ov=((Date)oVal).getTime();
						Long nv=((Date)nVal).getTime();
						if(null!=ov&&null!=nv) {
							if(ov.longValue() != nv.longValue()){
								sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>").append(sdf.format((Date)oVal)).append("</td><td  name='"+entityName+"'  column='"+column+"'>").append(sdf.format((Date)nVal)).append("</td></tr>");
							}
						}

					}
				}
			}
			/*
			 * if(oVal==null&&nVal!=null){ if(!(nVal instanceof Date)){
			 * sb.append(en[1]).append("（原值：").append("").append("->").append("新值：").append(
			 * String.valueOf(nVal)).append("）\n"); }else{
			 * sb.append(en[1]).append("（原值：").append("").append("->").append("新值：").append(
			 * sdf.format((Date)nVal)).append("）\n"); } }else if(oVal!=null&&nVal==null){
			 * if(!(nVal instanceof Date)){
			 * sb.append(en[1]).append("（原值：").append(String.valueOf(oVal)).append("->").
			 * append("新值：").append("").append("）\n"); }else{
			 * sb.append(en[1]).append("（原值：").append(sdf.format((Date)oVal)).append("->").
			 * append("新值：").append("").append("）\n"); } }else{ if(oVal instanceof String){
			 * if(!String.valueOf(oVal).equals(String.valueOf(nVal))){
			 * sb.append(en[1]).append("（原值：").append(String.valueOf(oVal)).append("->").
			 * append("新值：").append(String.valueOf(nVal)).append("）\n"); } }else if(oVal
			 * instanceof Long){ if(!String.valueOf(oVal).equals(String.valueOf(nVal))){ //
			 * if((Long)oVal != (Long)nVal){
			 * sb.append(en[1]).append("（原值：").append((Long)oVal).append("->").append("新值：")
			 * .append(String.valueOf((Long)nVal)).append("）\n"); } }else if(oVal instanceof
			 * Double){ if((Double)oVal-(Double)nVal>0.0000001){
			 * sb.append(en[1]).append("（原值：").append((Double)oVal).append("->").append(
			 * "新值：").append(String.valueOf((Double)nVal)).append("）\n"); } }else if(oVal
			 * instanceof Date){ if(((Date)oVal).getTime() != ((Date)nVal).getTime()){
			 * sb.append(en[1]).append("（原值：").append(sdf.format((Date)oVal)).append("->").
			 * append("新值：").append(sdf.format((Date)nVal)).append("）\n"); } } }
			 */
		}
		// sb.append("</table>");
		return sb.toString();
	}

	/**
	 * 拼资质,人员变更内容
	 * 
	 * @param nobj
	 * @param oobj
	 * @param info
	 * @param c
	 * @return
	 */
	public static String merger1(Object nobj, Object oobj, List<String[]> info, Class c) {
		Object oldObj = c.cast(oobj);
		Object newObj = c.cast(nobj);
		StringBuffer sb = new StringBuffer();
		Object nVal = null;
		Object oVal = null;
		Method m = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String name = null;
		int i = 0;
		String entityName = c.getName().substring(c.getName().lastIndexOf(".") + 1, c.getName().length());
		for (String[] en : info) {
			name = en[0];
			name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());

			try {
				m = c.getMethod(name);
				nVal = m.invoke(newObj);
				oVal = m.invoke(oldObj);
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (SecurityException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (InvocationTargetException e) {
				log.error(e.getMessage());
				log.info(e);
			}

			if (nVal != null && nVal instanceof String) {
				if ("".equals(String.valueOf(nVal))) {
					nVal = null;
				}
			}
			if (oVal != null && oVal instanceof String) {
				if ("".equals(String.valueOf(oVal))) {
					oVal = null;
				}
			}
			if (oVal == null && nVal != null) {
				if (!(nVal instanceof Date)) {
					if (i == 0) {
						sb.append(en[1]).append("</td><td>").append("")
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(String.valueOf(nVal)).append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>").append("")
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(String.valueOf(nVal)).append("</td></tr>");
					}
					i++;
				} else {
					if (i == 0) {
						sb.append(en[1]).append("</td><td>").append("")
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(sdf.format((Date) nVal)).append("</td ></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>").append("")
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(sdf.format((Date) nVal)).append("</td></tr>");
					}
					i++;
				}
			} else if (oVal != null && nVal == null) {
				if (!(nVal instanceof Date)) {
					if (i == 0) {
						sb.append(en[1]).append("</td><td>").append(String.valueOf(oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
								.append(String.valueOf(oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					}
					i++;
				} else {
					if (i == 0) {
						sb.append(en[1]).append("</td><td>").append(sdf.format((Date) oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
								.append(sdf.format((Date) oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					}
					i++;
				}
			} else {
				if (oVal instanceof String) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
						if (i == 0) {
							sb.append(en[1]).append("</td><td>").append(String.valueOf(oVal))
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf(nVal)).append("</td></tr>");
						} else {
							sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
									.append(String.valueOf(oVal))
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf(nVal)).append("</td></tr>");
						}
						i++;
					}
				} else if (oVal instanceof Long) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
//					if((Long)oVal != (Long)nVal){
						if (i == 0) {
							sb.append(en[1]).append("</td><td>").append((Long) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Long) nVal)).append("</td></tr>");
						} else {
							sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
									.append((Long) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Long) nVal)).append("</td></tr>");
						}
						i++;
					}
				} else if (oVal instanceof Double) {
					if ((Double) oVal - (Double) nVal > 0.0000001) {
						if (i == 0) {
							sb.append(en[1]).append("</td><td>").append((Double) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Double) nVal)).append("</td></tr>");
						} else {
							sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
									.append((Double) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Double) nVal)).append("</td></tr>");
						}
						i++;
					}
				}else if(oVal instanceof Date){
					if(null!=oVal&&null!=nVal) {
						Long ov=((Date)oVal).getTime();
						Long nv=((Date)nVal).getTime();
						if(null!=ov&&null!=nv) {
							if(ov.longValue() != nv.longValue()){
								if(i==0){
									sb.append(en[1]).append("</td><td>").append(sdf.format((Date)oVal)).append("</td><td  name='"+entityName+"'  column='"+en[0]+"'>").append(sdf.format((Date)nVal)).append("</td></tr>");
								}else{
									sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>").append(sdf.format((Date)oVal)).append("</td><td  name='"+entityName+"'  column='"+en[0]+"'>").append(sdf.format((Date)nVal)).append("</td></tr>");
								}
								i++;
							}
						}
					}
				}
			}
		}
		return sb.toString();
	}

	public static String merger2(Object nobj, Object oobj, List<String[]> info, Class c, String str) {
		Object oldObj = c.cast(oobj);
		Object newObj = c.cast(nobj);
		StringBuffer sb = new StringBuffer();
		Object nVal = null;
		Object oVal = null;
		Method m = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String name = null;
		int i = 0;
		String entityName = c.getName().substring(c.getName().lastIndexOf(".") + 1, c.getName().length());
		for (String[] en : info) {
			name = en[0];
			name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());

			try {
				m = c.getMethod(name);
				nVal = m.invoke(newObj);
				oVal = m.invoke(oldObj);
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (SecurityException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (InvocationTargetException e) {
				log.error(e.getMessage());
				log.info(e);
			}

			if (nVal != null && nVal instanceof String) {
				if ("".equals(String.valueOf(nVal))) {
					nVal = null;
				}
			}
			if (oVal != null && oVal instanceof String) {
				if ("".equals(String.valueOf(oVal))) {
					oVal = null;
				}
			}
			if (oVal == null && nVal != null) {
				if (!(nVal instanceof Date)) {
					if (i == 0) {
						sb.append(str).append(en[1]).append("</td><td>").append("")
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(String.valueOf(nVal)).append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
								.append("").append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(String.valueOf(nVal)).append("</td></tr>");
					}
					i++;
				} else {
					if (i == 0) {
						sb.append(str).append(en[1]).append("</td><td>").append("")
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(sdf.format((Date) nVal)).append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
								.append("").append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(sdf.format((Date) nVal)).append("</td></tr>");
					}
					i++;
				}
			} else if (oVal != null && nVal == null) {
				if (!(nVal instanceof Date)) {
					if (i == 0) {
						sb.append(str).append(en[1]).append("</td><td>").append(String.valueOf(oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
								.append(String.valueOf(oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					}
					i++;
				} else {
					if (i == 0) {
						sb.append(str).append(en[1]).append("</td><td>").append(sdf.format((Date) oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
								.append(sdf.format((Date) oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					}
					i++;
				}
			} else {
				if (oVal instanceof String) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
						if (i == 0) {
							sb.append(str).append(en[1]).append("</td><td>").append(String.valueOf(oVal))
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf(nVal)).append("</td></tr>");
						} else {
							sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
									.append(String.valueOf(oVal))
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf(nVal)).append("</td></tr>");
						}
						i++;
					}
				} else if (oVal instanceof Long) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
//					if((Long)oVal != (Long)nVal){
						if (i == 0) {
							sb.append(str).append(en[1]).append("</td><td>").append((Long) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Long) nVal)).append("</td></tr>");
						} else {
							sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
									.append((Long) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Long) nVal)).append("</td></tr>");
						}
						i++;
					}
				} else if (oVal instanceof Double) {
					if ((Double) oVal - (Double) nVal > 0.0000001) {
						if (i == 0) {
							sb.append(str).append(en[1]).append("</td><td>").append((Double) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Double) nVal)).append("</td></tr>");
						} else {
							sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
									.append((Double) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Double) nVal)).append("</td></tr>");
						}
						i++;
					}
				}else if(oVal instanceof Date){
					if(null!=oVal&&null!=nVal){
						Long oV=((Date)oVal).getTime();
						Long nV=((Date)nVal).getTime();
						if(oV.longValue() != nV.longValue()){
							if(i==0){
								sb.append(str).append(en[1]).append("</td><td>").append(sdf.format((Date)oVal)).append("</td><td  name='"+entityName+"'  column='"+en[0]+"'>").append(sdf.format((Date)nVal)).append("</td></tr>");
							}else{
								sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>").append(sdf.format((Date)oVal)).append("</td><td  name='"+entityName+"'  column='"+en[0]+"'>").append(sdf.format((Date)nVal)).append("</td></tr>");
							}
							i++;
						}
					}
				}
			}
		}
		return sb.toString();
	}

	public static String merger3(Object nobj, Object oobj, List<String[]> info, Class c) {
		String resultStr = "";
		Object oldObj = c.cast(oobj);
		Object newObj = c.cast(nobj);
		StringBuffer sbold = new StringBuffer();
		StringBuffer sbnew = new StringBuffer();
		Object nVal = null;
		Object oVal = null;
		Method m = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String name = null;
		int i = 0;
		String entityName = c.getName().substring(c.getName().lastIndexOf(".") + 1, c.getName().length());
		for (String[] en : info) {
			name = en[0];
			name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());

			try {
				m = c.getMethod(name);
				nVal = m.invoke(newObj);
				oVal = m.invoke(oldObj);
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (SecurityException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (InvocationTargetException e) {
				log.error(e.getMessage());
				log.info(e);
			}

			if (nVal != null && nVal instanceof String) {
				if ("".equals(String.valueOf(nVal))) {
					nVal = null;
				}
			}
			if (oVal != null && oVal instanceof String) {
				if ("".equals(String.valueOf(oVal))) {
					oVal = null;
				}
			}
			if (oVal == null && nVal != null) {
				if (!(nVal instanceof Date)) {
					sbnew.append(String.valueOf(nVal)).append("-");
				} else {
					sbnew.append(sdf.format((Date) nVal)).append("-");
				}
			} else if (oVal != null && nVal == null) {
				if (!(nVal instanceof Date)) {
					sbold.append(String.valueOf(oVal)).append("-");
				} else {
					sbold.append(sdf.format((Date) oVal)).append("-");
				}
			} else {
				if (oVal instanceof String) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
						i = 1;
					}
					sbold.append(String.valueOf(oVal)).append("-");
					sbnew.append(String.valueOf(nVal)).append("-");
				} else if (oVal instanceof Long) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
						i = 1;
					}
					sbold.append((Long) oVal).append("-");
					sbnew.append(String.valueOf((Long) nVal)).append("-");
				} else if (oVal instanceof Double) {
					if ((Double) oVal - (Double) nVal > 0.0000001) {
						i = 1;
					}
					sbold.append((Double)oVal).append("-");
					sbnew.append(String.valueOf((Double)nVal)).append("-");	
				}else if(oVal instanceof Date){
					if(null!=oVal&&null!=nVal){
						Long oV=((Date)oVal).getTime();
						Long nV=((Date)nVal).getTime();
						if(oV.longValue()==nV.longValue()){
							i=1;
						}
						sbold.append(sdf.format((Date)oVal)).append("-");
						sbnew.append(sdf.format((Date)nVal)).append("-");
					}
				}
			}
		}

		if (i == 0) {
			return resultStr;
		} else if (i > 0 && i < info.size() - 1) {
			if (sbold != null && sbold.length() > 1) {
				sbold.deleteCharAt(sbold.length() - 1);
			}
			if (sbnew != null && sbold.length() > 1) {
				sbnew.deleteCharAt(sbnew.length() - 1);
			}
			resultStr = sbold.toString() + "</td><td>" + sbnew.toString();
		} else if (i == info.size() - 1) {
			if (sbold != null && sbold.length() > 1) {
				sbold.deleteCharAt(sbold.length() - 1);
			}
			if (sbnew != null && sbold.length() > 1) {
				sbnew.deleteCharAt(sbnew.length() - 1);
			}
			resultStr = sbold.toString() + "</td><td>" + sbnew.toString();
		}

		/*
		 * name='"+entityName+"' column='"+en[0]+"
		 */

		return resultStr;
	}

	public static String mergersegment(Object nobj, Object oobj, List<String[]> info, Class c) {
		Object oldObj = c.cast(oobj);
		Object newObj = c.cast(nobj);
		StringBuffer sb = new StringBuffer();
		Object nVal = null;
		Object oVal = null;
		Method m = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = null;
		String entityName = c.getName().substring(c.getName().lastIndexOf(".") + 1, c.getName().length());
		for (String[] en : info) {
			name = en[0];
			name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());

			try {
				m = c.getMethod(name);
				nVal = m.invoke(newObj);
				oVal = m.invoke(oldObj);
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (SecurityException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (InvocationTargetException e) {
				log.error(e.getMessage());
				log.info(e);
			}

			if (nVal != null && nVal instanceof String) {
				if ("".equals(String.valueOf(nVal))) {
					nVal = null;
				}
				if ("isepc".equals(en[0]) && "1".equals(nVal)) {
					nVal = "是";
				}
				if ("isepc".equals(en[0]) && "0".equals(nVal)) {
					nVal = "否";
				}
			}
			if (oVal != null && oVal instanceof String) {
				if ("".equals(String.valueOf(oVal))) {
					oVal = null;
				}
				if ("isepc".equals(en[0]) && "1".equals(oVal)) {
					oVal = "是";
				}
				if ("isepc".equals(en[0]) && "0".equals(oVal)) {
					oVal = "否";
				}
			}
			if (oVal == null && nVal != null) {
				if (!(nVal instanceof Date)) {
					sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>").append("")
							.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
							.append(String.valueOf(nVal)).append("</td></tr>");
				} else {
					sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>").append("")
							.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
							.append(sdf.format((Date) nVal)).append("</td></tr>");
				}
			} else if (oVal != null && nVal == null) {
				if (!(nVal instanceof Date)) {
					sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
							.append(String.valueOf(oVal))
							.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
							.append("</td></tr>");
				} else {
					sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
							.append(sdf.format((Date) oVal))
							.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
							.append("</td></tr>");
				}
			} else {
				if (oVal instanceof String) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
								.append(String.valueOf(oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(String.valueOf(nVal)).append("</td></tr>");
					}
				} else if (oVal instanceof Long) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
//					if((Long)oVal != (Long)nVal){
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
								.append((Long) oVal)
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(String.valueOf((Long) nVal)).append("</td></tr>");
					}
				} else if (oVal instanceof Double) {
					if ((Double) oVal - (Double) nVal > 0.0000001) {
						sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>")
								.append((Double) oVal)
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(String.valueOf((Double) nVal)).append("</td></tr>");
					}
				}else if(oVal instanceof Date){
					if(null!=oVal&&nVal!=null) {
						Long ov=((Date)oVal).getTime();
						Long nv=((Date)nVal).getTime();
						if(null!=ov&&null!=nv) {
							if(ov.longValue() != nv.longValue()){
								sb.append("<tr style='font-size:13px;'><td>").append(en[1]).append("</td><td>").append(sdf.format((Date)oVal)).append("</td><td  name='"+entityName+"'  column='"+en[0]+"'>").append(sdf.format((Date)nVal)).append("</td></tr>");
							}
						}
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param from         要复制的
	 * @param to           复制给
	 * @param excludsArray 排除属性列表
	 */
	public static void copyPropertiesExclude(Object from, Object to, String[] excludsArray) {

		List<String> excludesList = null;

		if (excludsArray != null && excludsArray.length > 0) {

			excludesList = Arrays.asList(excludsArray); // 构造列表对象
		}
		if (from == null) {
			return;
		}
		Method[] fromMethods = from.getClass().getDeclaredMethods();
		Method[] toMethods = to.getClass().getDeclaredMethods();
		Method fromMethod = null;
		Method toMethod = null;
		String fromMethodName = null;
		String toMethodName = null;

		for (int i = 0; i < fromMethods.length; i++) {

			fromMethod = fromMethods[i];
			fromMethodName = fromMethod.getName();

			if (!fromMethodName.contains("get"))
				continue;
			// 排除列表检测
			if (excludesList != null && excludesList.contains(fromMethodName.substring(3).toLowerCase())) {

				continue;
			}
			toMethodName = "set" + fromMethodName.substring(3);
			toMethod = findMethodByName(toMethods, toMethodName);

			if (toMethod == null)
				continue;
			Object value=null;
			
			try {
				value = fromMethod.invoke(from, new Object[0]);
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException Occured");
			} catch (IllegalArgumentException e) {
				System.err.println("IllegalArgumentException Occured");
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException Occured");
			}

			if (value == null)
				continue;
			// 集合类判空处理
			if (value instanceof Collection) {

				Collection<?> newValue = (Collection<?>) value;

				if (newValue.size() <= 0)
					continue;
			}

			try {
				toMethod.invoke(to, new Object[] { value });
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException Occured");
			} catch (IllegalArgumentException e) {
				System.err.println("IllegalArgumentException Occured");
			} catch (InvocationTargetException e) {
				System.err.println("InvocationTargetException Occured");
			}
		}
	}

	public static Method findMethodByName(Method[] methods, String name) {

		for (int j = 0; j < methods.length; j++) {

			if (methods[j].getName().equals(name)) {

				return methods[j];
			}

		}
		return null;
	}




	public static String mergerlzzxx(Object nobj, Object oobj, List<String[]> info, Class c, String str) {
		Object oldObj = c.cast(oobj);
		Object newObj = c.cast(nobj);
		StringBuffer sb = new StringBuffer();
		Object nVal = null;
		Object oVal = null;
		Method m = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = null;
		int i = 0;
		String entityName = c.getName().substring(c.getName().lastIndexOf(".") + 1, c.getName().length());
		for (String[] en : info) {
			name = en[0];
			name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());

			try {
				m = c.getMethod(name);
				nVal = m.invoke(newObj);
				oVal = m.invoke(oldObj);
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (SecurityException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (InvocationTargetException e) {
				log.error(e.getMessage());
				log.info(e);
			}

			if (nVal != null && nVal instanceof String) {
				if ("".equals(String.valueOf(nVal))) {
					nVal = null;
				}
			}
			if (oVal != null && oVal instanceof String) {
				if ("".equals(String.valueOf(oVal))) {
					oVal = null;
				}
			}
			if (oVal == null && nVal != null) {
				if (!(nVal instanceof Date)) {
					if (i == 0) {
						sb.append(str).append(en[1]).append("</td><td>").append("")
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(String.valueOf(nVal)).append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
								.append("").append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(String.valueOf(nVal)).append("</td></tr>");
					}
					i++;
				} else {
					if (i == 0) {
						sb.append(str).append(en[1]).append("</td><td>").append("")
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(sdf.format((Date) nVal)).append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
								.append("").append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
								.append(sdf.format((Date) nVal)).append("</td></tr>");
					}
					i++;
				}
			} else if (oVal != null && nVal == null) {
				if (!(nVal instanceof Date)) {
					if (i == 0) {
						sb.append(str).append(en[1]).append("</td><td>").append(String.valueOf(oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
								.append(String.valueOf(oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					}
					i++;
				} else {
					if (i == 0) {
						sb.append(str).append(en[1]).append("</td><td>").append(sdf.format((Date) oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					} else {
						sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
								.append(sdf.format((Date) oVal))
								.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>").append("")
								.append("</td></tr>");
					}
					i++;
				}
			} else {
				if (oVal instanceof String) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
						if (i == 0) {
							sb.append(str).append(en[1]).append("</td><td>").append(String.valueOf(oVal))
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf(nVal)).append("</td></tr>");
						} else {
							sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
									.append(String.valueOf(oVal))
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf(nVal)).append("</td></tr>");
						}
						i++;
					}
				} else if (oVal instanceof Long) {
					if (!String.valueOf(oVal).equals(String.valueOf(nVal))) {
//					if((Long)oVal != (Long)nVal){
						if (i == 0) {
							sb.append(str).append(en[1]).append("</td><td>").append((Long) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Long) nVal)).append("</td></tr>");
						} else {
							sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
									.append((Long) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Long) nVal)).append("</td></tr>");
						}
						i++;
					}
				} else if (oVal instanceof Double) {
					if ((Double) oVal - (Double) nVal > 0.0000001) {
						if (i == 0) {
							sb.append(str).append(en[1]).append("</td><td>").append((Double) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Double) nVal)).append("</td></tr>");
						} else {
							sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>")
									.append((Double) oVal)
									.append("</td><td  name='" + entityName + "'  column='" + en[0] + "'>")
									.append(String.valueOf((Double) nVal)).append("</td></tr>");
						}
						i++;
					}
				}else if(oVal instanceof Date){
					if(null!=oVal&&null!=nVal){
						Long oV=((Date)oVal).getTime();
						Long nV=((Date)nVal).getTime();
						if(oV.longValue() != nV.longValue()){
							if(i==0){
								sb.append(str).append(en[1]).append("</td><td>").append(sdf.format((Date)oVal)).append("</td><td  name='"+entityName+"'  column='"+en[0]+"'>").append(sdf.format((Date)nVal)).append("</td></tr>");
							}else{
								sb.append("<tr style='font-size:13px;'><td>").append(str).append(en[1]).append("</td><td>").append(sdf.format((Date)oVal)).append("</td><td  name='"+entityName+"'  column='"+en[0]+"'>").append(sdf.format((Date)nVal)).append("</td></tr>");
							}
							i++;
						}
					}
				}
			}
		}
		return sb.toString();
	}
}