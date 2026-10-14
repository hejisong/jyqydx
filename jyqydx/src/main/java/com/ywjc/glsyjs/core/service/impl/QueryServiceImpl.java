package com.ywjc.glsyjs.core.service.impl;

import com.ywjc.glsyjs.core.service.QueryService;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("queryServiceImpl")
public class QueryServiceImpl implements QueryService {
	private static Logger log = Logger.getLogger(QueryServiceImpl.class);
	@PersistenceContext(unitName = "glsyjs")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> queryForObject(String sql, List<Object> values) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		List<Object> rst = query.getResultList();
//		entityManager.close();
		return rst;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object[]> query(String sql, List<Object> values) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		List<Object[]> result = query.getResultList();
//		entityManager.close();
		return result;
	}

	@Override
	@Transactional
	public Object queryObject(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		Object rst = query.getSingleResult();
//		entityManager.close();
		return rst;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> queryObjectBySql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		List<Object> rst = query.getResultList();
//		entityManager.close();
		return rst;
	}

	@Override
	@Transactional
	public Integer executeSql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		Integer rst = query.executeUpdate();
//		entityManager.close();
		return rst;
	}

	@Override
	@Transactional
	public void batchInsert(List<Object> objs) {
		for (int i = 0; i < objs.size(); i++) {
			entityManager.persist(objs.get(i));
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> queryObjectsBySql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> rst = query.getResultList();
//		entityManager.close();
		return rst;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public Page<Object> queryForPage(String sql, String order, List<Object> values, Class<?> cla, Pageable pageable) {
		Query query = entityManager.createNativeQuery("select count(*) " + sql);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		List<String> columns = initColumnNames(cla);
		String columnsql = initColumnsql(columns);
		Integer total =Integer.valueOf(query.getSingleResult().toString());
		query = entityManager.createNativeQuery("select " + columnsql + " " + sql + " " + order);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		List<Object[]> content = total > pageable.getOffset() ? query.getResultList()
				: Collections.<Object[]>emptyList();
			content.removeAll(Collections.singleton(null));
		List<Object> contentval = null;
		contentval = zuzhuanData(cla, columns, content);
//		entityManager.close();
		return new PageImpl<Object>(contentval, pageable, total);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public Page<Object> queryObjectForPage(String columnsql, String sql, String order, List<Object> values,
			Pageable pageable) {
		Query query = entityManager.createNativeQuery("select count(*) " + sql);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		int total = ((BigInteger) query.getSingleResult()).intValue();
		query = entityManager.createNativeQuery("select " + columnsql + " " + sql + " " + order);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		List<Object> content = total > pageable.getOffset() ? query.getResultList() : Collections.<Object>emptyList();
//		entityManager.close();
		return new PageImpl<Object>(content, pageable, total);
	}

	@Override
	@Transactional
	public Object queryObject(String columnsql, String sql, List<Object> values) {
		Query query = entityManager.createNativeQuery("select " + columnsql + " " + sql);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		Object rst = query.getSingleResult();
//		entityManager.close();
		return rst;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> queryObjectBySql(String columnsql, String sql, List<Object> values) {
		Query query = entityManager.createNativeQuery("select " + columnsql + " " + sql);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		List<Object> rst = query.getResultList();
//		entityManager.close();
		return rst;
	}

	private List<String> initColumnNames(Class<?> c) {
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

	private String initColumnsql(List<String> columns) {
		StringBuilder sb = new StringBuilder();
		for (String col : columns) {
			sb.append("tbl.");
			sb.append(col);
			sb.append(",");
		}
		String sqlcolumn = sb.toString();
		return sqlcolumn.substring(0, sqlcolumn.length() - 1);
	}

	private List<Object> zuzhuanData(Class<?> c, List<String> columns, List<Object[]> objs) {
		List<Object> list = new ArrayList<Object>();
		Map<String, Class<?>> colTypeMap;
		try {
			colTypeMap = getColumnClass(c, columns);
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Object o = null;
			Method m = null;
			for (Object[] obj : objs) {
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
				list.add(o);
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

		return list;
	}

	private String parseColumn(String col) {
		StringBuilder sb = new StringBuilder("set");
		sb.append(col.substring(0, 1).toUpperCase());
		sb.append(col.substring(1, col.length()));
		return sb.toString();
	}

	/**
	 * 此方法先只适用于本系统，具体映射要根据不同数据库确定
	 * 
	 * @param c
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private Map<String, Class<?>> getColumnClass(Class<?> c, List<String> columns) {
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

	@Override
	@SuppressWarnings("unchecked")
	public Page<Object> queryForPage3(String sql, String order, List<Object> values, Class<?> cla, Pageable pageable) {
		Query query = entityManager.createNativeQuery("select count(*) " + "from (" + sql + ") ");
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		List<String> columns = initColumnNames(cla);
		String columnsql = initColumnsql1(columns);
		Integer total = ((BigDecimal) query.getSingleResult()).intValue();

		query = entityManager.createNativeQuery("select " + columnsql + " from (" + sql + " " + order + " )");
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		List<Object[]> content = total > pageable.getOffset() ? query.getResultList()
				: Collections.<Object[]>emptyList();
		List<Object> contentval = null;
		contentval = zuzhuanData(cla, columns, content);

		return new PageImpl<Object>(contentval, pageable, total);
	}

	private String initColumnsql1(List<String> columns) {
		StringBuilder sb = new StringBuilder();
		for (String col : columns) {
			sb.append(col);
			sb.append(",");
		}
		String sqlcolumn = sb.toString();
		return sqlcolumn.substring(0, sqlcolumn.length() - 1);
	}

	@Override
	@Transactional
	public Integer executeBatchSql(List<String> params) {
		Query query = null;
		Integer num = 0;
		for (String sql : params) {
			query = entityManager.createNativeQuery(sql);
			num = num + query.executeUpdate();
		}
		return num;
	}
}
