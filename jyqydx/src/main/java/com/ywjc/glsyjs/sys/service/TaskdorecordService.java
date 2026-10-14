package com.ywjc.glsyjs.sys.service;


import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.Taskdorecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskdorecordService extends BaseService<Taskdorecord, String> {

    public Page<Taskdorecord> findByTasktype(String tasktype, Pageable pageable);

}
