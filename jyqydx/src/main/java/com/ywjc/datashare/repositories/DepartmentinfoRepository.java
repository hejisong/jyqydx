package com.ywjc.datashare.repositories;


import com.ywjc.datashare.entity.Departmentinfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface DepartmentinfoRepository extends PagingAndSortingRepository<Departmentinfo, String> {
    @Query(value = "select * from ctwrite.t_department  ", nativeQuery = true)
    public List<Departmentinfo> findListAll();

    @Query(value = "select * from ctwrite.t_department where deptid=?1 ", nativeQuery = true)
    public Departmentinfo findByid(String id);
}
