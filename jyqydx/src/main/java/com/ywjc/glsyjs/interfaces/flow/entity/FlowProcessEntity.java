package com.ywjc.glsyjs.interfaces.flow.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "om_flow_process")
public class FlowProcessEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "auditid")
    private String auditid;

    //申请id
    @Column(name = "applyid")
    private String applyid;

    //流程定义id
    @Column(name = "fid")
    private String fid;

    //流程名
    @Column(name = "flowname")
    private String flowname;

    //审核部门id
    @Column(name = "deptid")
    private String deptid;

    //部门名称
    @Column(name = "deptname")
    private String deptname;

    //审核人id
    @Column(name = "auditperid")
    private String auditperid;

    //审核人姓名
    @Column(name = "auditpername")
    private String auditpername;

    //审核状态 1 同意 2 不同意 3 退回
    @Column(name = "state")
    private String state;

    //审批金额
    @Column(name = "sjspje")
    private BigDecimal SJSPJE;

    //审核意见
    @Column(name = "auditidea")
    private String auditidea;

    //审核时间
    @Column(name = "audittime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date audittime;

    //节点状态 0：待处理  1处理中  2:处理完成
    @Column(name = "processstate")
    private String processstate;

    //创建人id
    @Column(name = "createperid")
    private String createperid;

    //创建人名称
    @Column(name = "createpername")
    private String createpername;

    //创建时间
    @Column(name = "createtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

    //更新人id
    @Column(name = "updateperid")
    private String updateperid;

    //更新人名称
    @Column(name = "updatepername")
    private String updatepername;

    //更新时间
    @Column(name = "updatetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    //凭证上传附件id
    @Column(name = "uploadfileid")
    private String uploadfileid;

    @Column(name = "uploadfilename")
    private String uploadfilename;

    //流程状态  1 进行中 2 结束
    @Column(name = "flowstate")
    private String flowstate;

    @Transient
    private String nextnode;
    @Transient
    private List<Flowauditinfo> flowauditinfos;

    public List<Flowauditinfo> getFlowauditinfos() {
        return flowauditinfos;
    }

    public void setFlowauditinfos(List<Flowauditinfo> flowauditinfos) {
        this.flowauditinfos = flowauditinfos;
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

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getAuditperid() {
        return auditperid;
    }

    public void setAuditperid(String auditperid) {
        this.auditperid = auditperid;
    }

    public String getAuditpername() {
        return auditpername;
    }

    public void setAuditpername(String auditpername) {
        this.auditpername = auditpername;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuditidea() {
        return auditidea;
    }

    public void setAuditidea(String auditidea) {
        this.auditidea = auditidea;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getProcessstate() {
        return processstate;
    }

    public void setProcessstate(String processstate) {
        this.processstate = processstate;
    }

    public String getCreateperid() {
        return createperid;
    }

    public void setCreateperid(String createperid) {
        this.createperid = createperid;
    }

    public String getCreatepername() {
        return createpername;
    }

    public void setCreatepername(String createpername) {
        this.createpername = createpername;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateperid() {
        return updateperid;
    }

    public void setUpdateperid(String updateperid) {
        this.updateperid = updateperid;
    }

    public String getUpdatepername() {
        return updatepername;
    }

    public void setUpdatepername(String updatepername) {
        this.updatepername = updatepername;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public BigDecimal getSJSPJE() {
        return SJSPJE;
    }

    public void setSJSPJE(BigDecimal SJSPJE) {
        this.SJSPJE = SJSPJE;
    }

    public String getFlowstate() {
        return flowstate;
    }

    public void setFlowstate(String flowstate) {
        this.flowstate = flowstate;
    }

    public String getNextnode() {
        return nextnode;
    }

    public void setNextnode(String nextnode) {
        this.nextnode = nextnode;
    }

    public String getUploadfileid() {
        return uploadfileid;
    }

    public void setUploadfileid(String uploadfileid) {
        this.uploadfileid = uploadfileid;
    }

    public String getUploadfilename() {
        return uploadfilename;
    }

    public void setUploadfilename(String uploadfilename) {
        this.uploadfilename = uploadfilename;
    }

    @Override
    public String toString() {
        return "FlowProcessEntity{" +
                "auditid='" + auditid + '\'' +
                ", applyid='" + applyid + '\'' +
                ", fid='" + fid + '\'' +
                ", flowname='" + flowname + '\'' +
                ", flowstate='" + flowstate + '\'' +
                ", nextnode='" + nextnode + '\'' +
                ", flowauditinfos=" + flowauditinfos +
                '}';
    }
}
