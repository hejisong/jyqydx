package com.ywjc.glsyjs.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.log4j.Logger;

public class ObjectToObject {
	private static Logger log = Logger.getLogger(ObjectToObject.class);

	public static List<Object> getObject(Class<?> c, List<Object[]> objs) {
		List<String> columns = initColumnNames(c);
		List<Object> objects = null;
		objects = zuzhuanData(c, columns, objs);
		return objects;

	}

	public static String Columnsql(Class<?> c) {
		List<String> columnNames = initColumnNames(c);
		return initColumnsql(columnNames);
	}

	private static List<String> initColumnNames(Class<?> c) {
		Field[] fields = c.getDeclaredFields();
		List<String> columnlist = new ArrayList<String>();
		Column a = null;
		for (Field f : fields) {
			a = f.getAnnotation(Column.class);
			if (a != null) {
				columnlist.add(a.name());
			}
		}
		return columnlist;
	}

	private static String initColumnsql(List<String> columns) {
		StringBuilder sb = new StringBuilder();
		for (String col : columns) {
			sb.append(col);
			sb.append(",");
		}
		String sqlcolumn = sb.toString();
		return sqlcolumn.substring(0, sqlcolumn.length() - 1);
	}

	private static List<Object> zuzhuanData(Class<?> c, List<String> columns, List<Object[]> objs) {
		List<Object> list = new ArrayList<Object>();
		Map<String, Class<?>> colTypeMap = getColumnClass(c, columns);
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Object o = null;
		Method m = null;
		for (Object[] obj : objs) {
			try {
				o = c.newInstance();
				for (int i = 0; i < columns.size(); i++) {
					m = c.getDeclaredMethod(parseColumn(columns.get(i)), colTypeMap.get(columns.get(i)));
					if (m != null) {
						if (obj[i] instanceof Timestamp) {
							m.invoke(o, sdf.parse(sdf.format((Timestamp) obj[i])));
						} else if (obj[i] instanceof BigInteger) {
							m.invoke(o, ((BigInteger) obj[i]).longValue());
						} else if (obj[i] instanceof Integer) {
							m.invoke(o, ((Integer) obj[i]).longValue());
						} else if (obj[i] instanceof BigDecimal) {
							if (colTypeMap.get(columns.get(i)).equals(Long.class)) {
								m.invoke(o, ((BigDecimal) obj[i]).longValue());
							} else {
								m.invoke(o, ((BigDecimal) obj[i]).doubleValue());
							}
						} else if (obj[i] instanceof Double) {
							m.invoke(o, (Double) obj[i]);
						} else if (obj[i] instanceof Date) {
							m.invoke(o, (Date) obj[i]);
						} else if (obj[i] instanceof String) {
							m.invoke(o, (String) obj[i]);
						} else if (obj[i] instanceof Character) {
							m.invoke(o, String.valueOf(obj[i]));
						}
					}
				}
			} catch (InstantiationException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (SecurityException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (InvocationTargetException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (ParseException e) {
				log.error(e.getMessage());
				log.info(e);
			}
			list.add(o);
		}
		return list;
	}

	private static String parseColumn(String col) {
		StringBuilder sb = new StringBuilder("set");
		sb.append(col.substring(0, 1).toUpperCase());
		sb.append(col.substring(1, col.length()));
		return sb.toString();
	}

	private static Map<String, Class<?>> getColumnClass(Class<?> c, List<String> columns) {
		Field f = null;
		Class<?> cl = null;
		Map<String, Class<?>> types = new HashMap<String, Class<?>>();
		for (String column : columns) {
			try {
				f = c.getDeclaredField(column);
			} catch (NoSuchFieldException e) {
				log.error(e.getMessage());
				log.info(e);
			} catch (SecurityException e) {
				log.error(e.getMessage());
				log.info(e);
			}
			if (f != null) {
				cl = f.getType();
				types.put(column, cl);
			}

		}
		return types;
	}
}
