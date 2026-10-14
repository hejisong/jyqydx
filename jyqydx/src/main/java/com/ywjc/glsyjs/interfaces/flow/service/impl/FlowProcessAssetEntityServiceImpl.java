package com.ywjc.glsyjs.interfaces.flow.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessAssetEntity;
import com.ywjc.glsyjs.interfaces.flow.repositories.FlowEntityRepository;
import com.ywjc.glsyjs.interfaces.flow.repositories.FlowProcessAssetEntityRepository;
import com.ywjc.glsyjs.interfaces.flow.service.FlowEntityService;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessAssetEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@Service("flowProcessAssetEntityServiceImpl")
public class FlowProcessAssetEntityServiceImpl extends BaseServiceImpl<FlowProcessAssetEntity, String> implements
        FlowProcessAssetEntityService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "flowProcessAssetEntityRepository")
    private FlowProcessAssetEntityRepository repository;

    @Resource(name = "uploadfileServiceImpl")
    private UploadfileService uploadfileService;



    @Resource(name = "flowProcessAssetEntityRepository")
    public void setReposity(PagingAndSortingRepository<FlowProcessAssetEntity, String> repository) {
        super.setReposity(repository);
    }

    @Override
    public  List<String>  FlowProcessUploadPublicFile(JSONArray assetArray, String applyid, String auditid,
                                            String fid, String plyfileid, String assettype) {
        List<String> oldAssetIdList = repository.queryOldAssetidList(plyfileid, applyid);

        List<String> existFileList = new ArrayList<>();
        List<String> existFileListCopy = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(oldAssetIdList)) {
            for (String assetId : oldAssetIdList) {
                existFileList.add(assetId);
                existFileListCopy.add(assetId);
            }
        }
        List<String> submitFileList = new ArrayList<>();
        if (assetArray != null && !assetArray.isEmpty()) {
            for (int j = 0; j < assetArray.size(); j++) {
                JSONObject jsonObject = assetArray.getJSONObject(j);
                String assetId = jsonObject.getString("assetid");
                if (StringUtils.isNotBlank(assetId)) {
                    submitFileList.add(assetId);
                }
            }
        }

        // 删除的附件集合
        existFileList.removeAll(submitFileList);
        // 保存的附件集合
        submitFileList.removeAll(existFileListCopy);

        if (CollectionUtils.isNotEmpty(existFileList)) {
            for (String oldAssetid : existFileList) {
                // 删除之前上传的文件
                Uploadfile uploadfile =  uploadfileService.find(oldAssetid);
                deleteFileName(uploadfile.getFilename());
                // 删除文件之后再删除附件表记录
                uploadfileService.delete(oldAssetid);
                //删除中间表信息
                repository.deleteOldAssetid(oldAssetid);
            }
        }

        //新增关联关系
        for (String submitFileAssetid : submitFileList) {
            FlowProcessAssetEntity flowProcessAssetEntity = new FlowProcessAssetEntity();
            flowProcessAssetEntity.setId(UUID.randomUUID().toString().replace("-", ""));
            flowProcessAssetEntity.setAssetid(submitFileAssetid);
            flowProcessAssetEntity.setPlyfileid(plyfileid);
            flowProcessAssetEntity.setFid(fid);
            flowProcessAssetEntity.setAuditid(auditid);
            flowProcessAssetEntity.setApplyid(applyid);
            flowProcessAssetEntity.setAssettype(assettype);
            repository.save(flowProcessAssetEntity);
        }

        return submitFileList;

    }

    /**
     * 删除文件，单个文件
     *
     * @param fileName 待删除的文件名
     * @return 文件删除成功返回true, 否则返回false
     */
    public boolean deleteFileName(String fileName) {
        File file = new File(ConfigUtil.getConfigVal("file_save_path") + "/doc/upload_" + fileName);
        if (!file.exists()) {
            logger.error("删除文件失败：" + fileName + "文件不存在");
            return false;
        } else {
            return deleteFile(file);
        }
    }

    /**
     * 删除单个文件
     *
     * @param file 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    private boolean deleteFile(File file) {
        if (file.isFile() && file.exists()) {
            file.delete();
            logger.error("删除单个文件" + file.getName() + "成功！");
            return true;
        } else {
            logger.error("删除单个文件" + file.getName() + "失败！");
            return false;
        }
    }
}
