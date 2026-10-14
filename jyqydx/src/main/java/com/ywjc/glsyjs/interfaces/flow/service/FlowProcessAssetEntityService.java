package com.ywjc.glsyjs.interfaces.flow.service;

import com.alibaba.fastjson.JSONArray;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessAssetEntity;

import java.util.List;

public interface FlowProcessAssetEntityService extends BaseService<FlowProcessAssetEntity, String> {

    //流程上传文件公共方法
    public  List<String>  FlowProcessUploadPublicFile(JSONArray dateArray, String applyId, String auditid,
                                            String fid, String plyfileid, String assettype);

}
