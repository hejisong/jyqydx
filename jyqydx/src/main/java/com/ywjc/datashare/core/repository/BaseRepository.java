package com.ywjc.datashare.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liu_k
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
	
	/**
	 * 
	 * @param sql SQL文（考虑数据库执行效率，建议去掉order by）
	 * @param values
	 * @return
	 */
	long sqlCount(String sql, List<Object> values);

	List sqlQueryForList(String sql, List<Object> values);
	
	List sqlQueryForList(String sql, List<Object> values, Integer firstResult, Integer maxResults);

	List sqlQueryForList(String sql, List<Object> values, Class resultClass);
	
	List sqlQueryForList(String sql, List<Object> values, Class resultClass, Integer firstResult, Integer maxResults);

	Page sqlQueryForPage(String sql, String orderBy, List<Object> values, Pageable pageable);
	
	Page sqlQueryForPage(String sql, String orderBy, List<Object> values, Pageable pageable, Class resultClass);

	T queryForOne(T t);

	List<T> queryForList(T t);

	List<T> queryForList(T t, Sort sort);

	Page<T> queryForPage(T t, Pageable pageable);
}
