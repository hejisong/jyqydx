package com.ywjc.datashare.core.repository.impl;

import com.ywjc.datashare.core.repository.BaseRepository;
import com.ywjc.datashare.core.repository.support.MatchType;
import com.ywjc.datashare.core.repository.support.QueryBetween;
import com.ywjc.datashare.core.repository.support.QueryField;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.mapping.Map;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaBuilder.In;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	private final Logger logger = Logger.getLogger(this.getClass());

	private final EntityManager entityManager;

	// 父类没有不带参数的构造方法，这里手动构造父类
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public long sqlCount(String sql, List<Object> values) {
		Query query = entityManager.createNativeQuery("select count(1) from ( " + sql + ")");
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		long total = ((BigDecimal) query.getSingleResult()).longValue();
		return total;
	}

	@Override
	public List sqlQueryForList(String sql, List<Object> values) {
		return sqlQueryForList(sql, values, Map.class);
	}

	@Override
	public List sqlQueryForList(String sql, List<Object> values, Class resultClass) {
		return sqlQueryForList(sql, values, resultClass, null, null);
	}

	@Override
	public List sqlQueryForList(String sql, List<Object> values, Integer firstResult, Integer maxResults) {
		return sqlQueryForList(sql, values, Map.class, firstResult, maxResults);
	}

	@Override
	public List sqlQueryForList(String sql, List<Object> values, Class resultClass, Integer firstResult,
			Integer maxResults) {
		Query query;
		if(Map.class.equals(resultClass)){
			query = entityManager.createNativeQuery(sql);
			query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		} else {
			query = entityManager.createNativeQuery(sql, resultClass);
		}
		
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		if (firstResult != null) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		List resultList = query.getResultList();
		return resultList;
	}

	@Override
	public Page sqlQueryForPage(String sql, String orderBy, List<Object> values, Pageable pageable) {
		return sqlQueryForPage(sql, orderBy, values, pageable, Map.class);
	}

	@Override
	public Page sqlQueryForPage(String sql, String orderBy, List<Object> values, Pageable pageable, Class resultClass) {
		long total = sqlCount(sql, values);
		List list = sqlQueryForList(sql + orderBy, values, resultClass, pageable.getOffset(), pageable.getPageSize());
		return new PageImpl<Object>(list, pageable, total);
	}

	@Override
	public T queryForOne(final T t) {
		return super.findOne(new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return getPredocate(root, query, cb, t);
			}
		});
	}

	@Override
	public List<T> queryForList(final T queryParam) {
		return queryForList(queryParam, null);
	}

	@Override
	public List<T> queryForList(T t, Sort sort) {
		return super.findAll(new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return getPredocate(root, query, cb, t);
			}
		}, sort);
	}

	public Page<T> queryForPage(T t, Pageable pageable) {
		return super.findAll(new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return getPredocate(root, query, cb, t);
			}
		}, pageable);
	}

	protected Predicate getPredocate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, T t) {
		List<Predicate> predicates = new ArrayList<>();
		// 获取查询对象的所有属性
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {

			if (Modifier.isStatic(field.getModifiers())) {
				// 静态变量跳过
				continue;
			}

			field.setAccessible(true);

			String fieldName = null;
			MatchType queryType = null;
			Object fieldValue = null;
			Predicate predicate = null;
			// 获取属性的 自定义注解类型
			QueryField annotaion = field.getAnnotation(QueryField.class);
			// 如果没有注解 则跳过
			if (annotaion != null) {
				// 如果注解中 name为空 则用字段名称作为属性名
				if (!StringUtils.isEmpty(annotaion.fieldName())) {
					fieldName = annotaion.fieldName();
				} else {
					fieldName = field.getName();
				}
				queryType = annotaion.matchType();
			} else {
				fieldName = field.getName();
				queryType = MatchType.EQUAL;
			}

			try {
				fieldValue = field.get(t);
			} catch (IllegalArgumentException e) {
				System.err.println("IllegalArgumentException Occured");
			} catch (IllegalAccessException e) {
				System.err.println("IllegalAccessException Occured");
			}
			// 判断字段类型是否为空
			if (fieldValue == null || fieldValue.equals("")) {
				continue;
			}

			switch (queryType) {
			case EQUAL:
				Path<Object> equal = getRootByQueryFiled(fieldName, root);
				if(null!=cb) {
					predicate = cb.equal(equal, fieldValue);
				}
				
				predicates.add(predicate);
				break;
			case BEWTEEN:
				Path<Comparable> between = getRootByQueryFiledComparable(fieldName, root);
				QueryBetween queryBetween = null;
				if (fieldValue instanceof QueryBetween)
					queryBetween = (QueryBetween) fieldValue;
				else
					continue;
				if(cb!=null&&queryBetween!=null) {
					predicate = cb.between(between, queryBetween.after, queryBetween.before);
					predicates.add(predicate);
				}
				break;
			case LESS_THAN:
				Path<Comparable> lessThan = getRootByQueryFiledComparable(fieldName, root);
				if (fieldValue instanceof QueryBetween)
					queryBetween = (QueryBetween) fieldValue;
				else
					continue;
				if(null!=cb) {
					predicate = cb.lessThan(lessThan, queryBetween.after);
				}
				
				predicates.add(predicate);
				break;
			case LESS_THAN_EQUAL:
				Path<Comparable> lessThanOrEqualTo = getRootByQueryFiledComparable(fieldName, root);
				if (fieldValue instanceof QueryBetween)
					queryBetween = (QueryBetween) fieldValue;
				else
					continue;
				if(null!=cb) {
					predicate = cb.lessThanOrEqualTo(lessThanOrEqualTo, queryBetween.after);
				}
				
				predicates.add(predicate);
				break;
			case GREATEROR_THAN:
				Path<Comparable> greaterThan = getRootByQueryFiledComparable(fieldName, root);
				if (fieldValue instanceof QueryBetween)
					queryBetween = (QueryBetween) fieldValue;
				else
					continue;
				if(null!=cb) {
					predicate = cb.greaterThan(greaterThan, queryBetween.after);
				}
				
				predicates.add(predicate);
				break;
			case GREATEROR_THAN_EQUAL:
				Path<Comparable> greaterThanOrEqualTo = getRootByQueryFiledComparable(fieldName, root);
				if (fieldValue instanceof QueryBetween)
					queryBetween = (QueryBetween) fieldValue;
				else
					continue;
				if(null!=cb) {
					predicate = cb.lessThanOrEqualTo(greaterThanOrEqualTo, queryBetween.after);
				}
				
				predicates.add(predicate);
				break;
			case NOT_EQUAL:
				Path<Object> notEqual = getRootByQueryFiled(fieldName, root);
				if(null!=cb) {
					predicate = cb.notEqual(notEqual, fieldValue);
					predicates.add(predicate);
				}
				
				break;
			case IS_NULL:
				Path<Object> isNull = getRootByQueryFiled(fieldName, root);
				if(null!=cb) {
					predicate = cb.isNull(isNull);
					predicates.add(predicate);
				}
				
				break;
			case IS_NOT_NULL:
				Path<Object> isNotNull = getRootByQueryFiled(fieldName, root);
				if(null!=cb) {
					predicate = cb.isNotNull(isNotNull);
					predicates.add(predicate);
				}
				
				break;
			case LEFT_LIKE:
				Path<String> leftLike = getRootByQueryFiledString(fieldName, root);
				if(null!=cb) {
					predicate = cb.like(leftLike, "%" + fieldValue.toString());
					predicates.add(predicate);
				}
				
				break;
			case RIGHT_LIKE:
				Path<String> rightLike = getRootByQueryFiledString(fieldName, root);
				if(null!=cb) {
					predicate = cb.like(rightLike, fieldValue.toString() + "%");
					predicates.add(predicate);
				}
				
				break;
			case LIKE:
				Path<String> fullLike = getRootByQueryFiledString(fieldName, root);
				if(null!=cb) {
					predicate = cb.like(fullLike, "%" + fieldValue.toString() + "%");
					predicates.add(predicate);
				}
				
				break;
			case DEFAULT_LIKE:
				Path<String> like = getRootByQueryFiledString(fieldName, root);
				if(null!=cb) {
					predicate = cb.like(like, fieldValue.toString());
					predicates.add(predicate);
				}
				
				break;
			case NOT_LIKE:
				Path<String> notLike = getRootByQueryFiledString(fieldName, root);
				if(null!=cb) {
					predicate = cb.like(notLike, fieldValue.toString());
					predicates.add(predicate);
				}
				
				break;
			case IN:
				Path<Object> in = getRootByQueryFiled(fieldName, root);
				List inList = null;
				if (fieldValue instanceof List) {
					inList = (List) fieldValue;
				}
				if(null!=cb) {
					In ins = cb.in(in);
					for (Object object : inList) {
						ins.value(object);
					}
					predicates.add(ins);
				}
				
				
				
				
				break;
			default:
				break;

			}

		}
		// 如果 为空 代表 没有任何有效的条件
		if (predicates.size() == 0) {
			if(null!=cb) {
				return cb.and();
			}
			
		}
		Object[] list = predicates.toArray();
		Predicate[] arr = new Predicate[predicates.size()];
		Predicate[] result = predicates.toArray(arr);
		if(null!=cb) {
			return cb.and(result);
		}else {
			return null;
		}
		
	}

	private Path<Object> getRootByQueryFiled(String queryFiled, Root<T> root) {
		if (queryFiled.indexOf(".") < 0) {
			return root.get(queryFiled);
		} else {
			return getRootByQueryFiled(queryFiled.substring(queryFiled.indexOf(".") + 1, queryFiled.length()),
					root.get(queryFiled.substring(0, queryFiled.indexOf("."))));
		}
	}

	private Path<Object> getRootByQueryFiled(String queryFiled, Path<Object> path) {
		if (queryFiled.indexOf(".") < 0) {
			return path.get(queryFiled);
		} else {
			return getRootByQueryFiled(queryFiled.substring(queryFiled.indexOf(".") + 1, queryFiled.length()),
					path.get(queryFiled.substring(0, queryFiled.indexOf("."))));
		}
	}

	private Path<String> getRootByQueryFiledString(String queryFiled, Root<T> root) {
		if (queryFiled.indexOf(".") < 0) {
			return root.get(queryFiled);
		} else {
			return getRootByQueryFiledString(queryFiled.substring(queryFiled.indexOf(".") + 1, queryFiled.length()),
					root.get(queryFiled.substring(0, queryFiled.indexOf("."))));
		}
	}

	private Path<String> getRootByQueryFiledString(String queryFiled, Path<Object> path) {
		if (queryFiled.indexOf(".") < 0) {
			return path.get(queryFiled);
		} else {
			return getRootByQueryFiledString(queryFiled.substring(queryFiled.indexOf(".") + 1, queryFiled.length()),
					path.get(queryFiled.substring(0, queryFiled.indexOf("."))));
		}
	}

	private Path<Comparable> getRootByQueryFiledComparable(String queryFiled, Root<T> root) {
		if (queryFiled.indexOf(".") < 0) {
			return root.get(queryFiled);
		} else {
			return getRootByQueryFiledComparable(queryFiled.substring(queryFiled.indexOf(".") + 1, queryFiled.length()),
					root.get(queryFiled.substring(0, queryFiled.indexOf("."))));
		}
	}

	private Path<Comparable> getRootByQueryFiledComparable(String queryFiled, Path<Object> path) {
		if (queryFiled.indexOf(".") < 0) {
			return path.get(queryFiled);
		} else {
			return getRootByQueryFiledComparable(queryFiled.substring(queryFiled.indexOf(".") + 1, queryFiled.length()),
					path.get(queryFiled.substring(0, queryFiled.indexOf("."))));
		}
	}

}
