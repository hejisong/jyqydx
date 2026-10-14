package com.ywjc.glsyjs.interfaces.flow.entity;

import com.sun.star.setup.ActionType;

import javax.swing.text.html.HTMLDocument;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BizCommentModel {
    //数据模型编码
    private String schemaCode;
    //业务对象id
    private String bizObjectId;
    //流程实力id
    private String workflowInstanceId;
    //数据项编码
    private String bizPropertyCode;
    //审批意见内容
    private String content;
    //审批对应操作类型 1审批 2协办 3加签 4转办 5作废 6流程结束 7系统自动添加 其中2,3不需要审批结果
    private String actiontype;

    //审批结果 1同意 2不同意 3转办 0或null;空，无意义的值
    private String result;

    //协办，加签关联用户，格式同选人控件的存储值 [{id:\"xxxx"\,type:3}]
    private List<Map<String,Object>> relUsers;

    //节点编码
    private String activityCode;

    //节点名称
    private String activityName;

    //任务id
    private String workItemId;

    //审批时间
    private Date commentTime;

    //任务的tokenid
    private String workflowTokenId;

    //创建人
    private  List<Map<String,Object>> creater;

    //TokenNumber
    private Integer tokenId;

    //关联附件资源
    private  List<Object> resources;

    //创建时间
    private Date createdTime;

    public String getSchemaCode() {
        return schemaCode;
    }

    public void setSchemaCode(String schemaCode) {
        this.schemaCode = schemaCode;
    }

    public String getBizObjectId() {
        return bizObjectId;
    }

    public void setBizObjectId(String bizObjectId) {
        this.bizObjectId = bizObjectId;
    }

    public String getWorkflowInstanceId() {
        return workflowInstanceId;
    }

    public void setWorkflowInstanceId(String workflowInstanceId) {
        this.workflowInstanceId = workflowInstanceId;
    }

    public String getBizPropertyCode() {
        return bizPropertyCode;
    }

    public void setBizPropertyCode(String bizPropertyCode) {
        this.bizPropertyCode = bizPropertyCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getActiontype() {
        return actiontype;
    }

    public void setActiontype(String actiontype) {
        this.actiontype = actiontype;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Map<String, Object>> getRelUsers() {
        return relUsers;
    }

    public void setRelUsers(List<Map<String, Object>> relUsers) {
        this.relUsers = relUsers;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getWorkflowTokenId() {
        return workflowTokenId;
    }

    public void setWorkflowTokenId(String workflowTokenId) {
        this.workflowTokenId = workflowTokenId;
    }

    public List<Map<String, Object>> getCreater() {
        return creater;
    }

    public void setCreater(List<Map<String, Object>> creater) {
        this.creater = creater;
    }

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public List<Object> getResources() {
        return resources;
    }

    public void setResources(List<Object> resources) {
        this.resources = resources;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
