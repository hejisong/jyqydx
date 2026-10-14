package com.ywjc.glsyjs.sys.repositories;


import com.ywjc.glsyjs.sys.entity.Taskdorecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TaskdorecordRepository extends PagingAndSortingRepository<Taskdorecord, String> {

    @Query("select t from Taskdorecord t where t.tasktype =?1 order by t.begintime desc ")
    public Page<Taskdorecord> findByTasktype(String tasktype, Pageable pageable);
	
}
