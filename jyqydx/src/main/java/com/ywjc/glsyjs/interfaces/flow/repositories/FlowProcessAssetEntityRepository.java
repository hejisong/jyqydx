package com.ywjc.glsyjs.interfaces.flow.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessAssetEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FlowProcessAssetEntityRepository extends BaseRepository<FlowProcessAssetEntity, String> {



    @Query(value="delete from om_flow_process_asset where assetid = ?1 ",nativeQuery=true)
    @Modifying
    public void deleteOldAssetid(String assetid);

    @Query(value = "select f.assetid from om_flow_process_asset f where f.plyfileid = ?1 and f.auditid = ?2",nativeQuery=true)
    public List<String> queryOldAssetidList(String plyfileid,String auditid);

}
