package com.ywjc.glsyjs.businesshare.repositories;


import com.ywjc.glsyjs.businesshare.entity.Orderagency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface OrderagencyRepository extends PagingAndSortingRepository<Orderagency, String> {

    @Query("select o from Orderagency o where o.ordermanid =?1 order by o.ordertime desc ")
    public List<Orderagency> findByordermanid(String ordermanid);

    @Query("select o from Orderagency o where o.agencymanid =?1 and  o.orderman like ?2 and o.ordercompany like ?3 order by o.ordertime desc ")
    public Page<Orderagency> findByagencymanid(String agencymanid,String orderman,String ordercompany,  Pageable pageable);

    @Query("select o from Orderagency o where o.ordercompanyid =?1 order by o.ordertime desc ")
    public List<Orderagency> findByordercompanyid(String ordercompanyid);

    @Query("select o from Orderagency o where    o.orderman like ?1 and o.ordercompany like ?2 order by o.ordertime desc ")
    public Page<Orderagency> findOrderagencys(String orderman,String ordercompany, Pageable pageable);


    @Query("select o from Orderagency o where   o.ordercompanyid =?1 and  o.orderman like ?2  order by o.ordertime desc ")
    public Page<Orderagency> findPageByordercompanyid(String ordercompanyid,String orderman, Pageable pageable);
}
