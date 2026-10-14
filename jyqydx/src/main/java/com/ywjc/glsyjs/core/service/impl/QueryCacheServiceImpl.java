package com.ywjc.glsyjs.core.service.impl;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ywjc.glsyjs.sys.entity.DaoCache;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ywjc.glsyjs.core.entity.QueryPo;
import com.ywjc.glsyjs.core.service.QueryService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Service("queryCacheServiceImpl")
public class QueryCacheServiceImpl implements QueryService {
	
	@Resource(name = "queryServiceImpl")
	private QueryService queryService;
	
	private static Logger log = Logger.getLogger(QueryCacheServiceImpl.class);  
	@PersistenceContext(unitName = "glsyjs")
	protected EntityManager entityManager;

	@Override
	@Transactional
	public List<Object> queryForObject(String sql, List<Object> values) {
		QueryPo query =new QueryPo(sql, values);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String key = gson.toJson(query);
		DaoCache daocache=new DaoCache();
		 if (daocache.rslistOjbect==null){
			 daocache.rslistOjbect = CacheBuilder.newBuilder()
	                             .maximumSize(10000)
	                             .expireAfterAccess(1, TimeUnit.DAYS)
	                             .build(
	                                 new CacheLoader<String, List<Object>>() {
	                                    public List<Object> load(String key){
	                                    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	                                    	QueryPo query = gson.fromJson(key, QueryPo.class);                                    	
	                                         return queryService.queryForObject(query.getSql(),query.getValues());
	                                     }
	                            });  
	        }
	        List<Object> rs =null;
	        try {
	             rs = daocache.rslistOjbect.get(key);
	        } catch (ExecutionException e) {
	        	log.error(e.getMessage());
	        	log.info(e);
	        }  
	        return rs;
	}

	@Override
	@Transactional
	public List<Object[]> query(String sql, List<Object> values) {
		QueryPo query =new QueryPo(sql, values);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String key = gson.toJson(query);
		DaoCache daocache=new DaoCache();
        if (daocache.rsFindOjbect==null){
            daocache.rsFindOjbect = CacheBuilder.newBuilder()
                             .maximumSize(10000)
                             .expireAfterAccess(1, TimeUnit.DAYS)
                             .build(
                                 new CacheLoader<String, List<Object[]>>() {
                                    public List<Object[]> load(String key) {
                                    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                                    	QueryPo query = gson.fromJson(key, QueryPo.class);                                    	
                                         return queryService.query(query.getSql(),query.getValues());
                                     }
                            });  
        }
        List<Object[]> rs =null;
        try {
             rs = daocache.rsFindOjbect.get(key);
        } catch (ExecutionException e) {
        	log.error(e.getMessage());
        	log.info(e);
        }  
        return rs;
	}

	@Override
	@Transactional
	public Object queryObject(String sql) {
		return queryService.queryObject(sql);
	}

	@Override
	@Transactional
	public List<Object> queryObjectBySql(String sql) {
		DaoCache daocache=new DaoCache();
		 if (daocache.rslistOjbect==null){
	            daocache.rslistOjbect = CacheBuilder.newBuilder()
	                             .maximumSize(10000)
	                             .expireAfterAccess(1, TimeUnit.DAYS)
	                             .build(
	                                 new CacheLoader<String, List<Object>>() {
	                                    public List<Object> load(String key) {
	                                         return queryService.queryObjectBySql(key);
	                                     }
	                            });  
	        }
	        List<Object> rs =null;
	        try {
	             rs = daocache.rslistOjbect.get(sql);
	        } catch (ExecutionException e) {
	        	log.error(e.getMessage());
	        	log.info(e);
	        }
	        return rs;
	}

	@Override
	@Transactional
	public Integer executeSql(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		Integer rst=query.executeUpdate();
		entityManager.close();
		return rst;
	}

	@Override
	@Transactional
	public void batchInsert(List<Object> objs) {
		for (int i = 0; i < objs.size(); i++) {
			entityManager.persist(objs.get(i));
			entityManager.close();
		}
	}
	

	@Override
	@Transactional
	public List<Object[]> queryObjectsBySql(String sql) {
		DaoCache daocache=new DaoCache();
		if (daocache.rsFindOjbect==null){
            daocache.rsFindOjbect = CacheBuilder.newBuilder()
                             .maximumSize(10000)
                             .expireAfterAccess(1, TimeUnit.DAYS)
                             .build(
                                 new CacheLoader<String, List<Object[]>>() {
                                    public List<Object[]> load(String key){
                                         return queryService.queryObjectsBySql(key);
                                     }
                            });  
        }
        List<Object[]> rs =null;
        try {
             rs = daocache.rsFindOjbect.get(sql);
        } catch (ExecutionException e) {
        	log.error(e.getMessage());
        	log.info(e);
        }  
        return rs;
	}

	@Override
	@Transactional
	public Page<Object> queryForPage(String sql, String order,List<Object> values,Class<?> cla,Pageable pageable) {
		return queryService.queryForPage(sql, order, values, cla, pageable);
	}
	
	@Override
	@Transactional
	public Page<Object> queryObjectForPage(String columnsql,String sql,String order, List<Object> values,Pageable pageable) {
		return queryService.queryObjectForPage(columnsql, sql, order, values, pageable);
	}
	
	@Override
	@Transactional
	public Object queryObject(String columnsql, String sql,List<Object> values) {
		Query query =  entityManager.createNativeQuery("select " + columnsql + " " + sql);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		Object rst=query.getSingleResult();
		entityManager.close();
		return rst;
	}
	
	@Override
	@Transactional
	public List<Object> queryObjectBySql(String columnsql,String sql,List<Object> values) {
		return queryService.queryObjectBySql(columnsql, sql, values);
	}

	@Override
	public Page<Object> queryForPage3(String string, String order, List<Object> values, Class<?> cla,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeBatchSql(List<String> params) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
