package com.ywjc.glsyjs.interfaces.flow.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "om_flow_process_asset")
public class FlowProcessAssetEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    //附件表id
    @Column(name = "assetid")
    private String assetid;

    //关联上传文件id
    @Column(name = "plyfileid")
    private String plyfileid;

    //流程节点code
    @Column(name = "fid")
    private String fid;

    //流程id
    @Column(name = "auditid")
    private String auditid;

    //申请id
    @Column(name = "applyid")
    private String applyid;

    //附件类型
    @Column(name = "assettype")
    private String assettype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetid() {
        return assetid;
    }

    public void setAssetid(String assetid) {
        this.assetid = assetid;
    }

    public String getPlyfileid() {
        return plyfileid;
    }

    public void setPlyfileid(String plyfileid) {
        this.plyfileid = plyfileid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid;
    }

    public String getApplyid() {
        return applyid;
    }

    public void setApplyid(String applyid) {
        this.applyid = applyid;
    }

    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype;
    }
}
