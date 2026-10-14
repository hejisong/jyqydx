package com.ywjc.glsyjs.interfaces.flow.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "om_flow")
public class FlowEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    //流程名
    @Column(name = "flowname")
    private String flowname;

    @Column(name = "nextid")
    private String nextid;

    //是否开始节点 1是；0：否
    @Column(name = "isbegin")
    private String isbegin;

    //是否结束节点 1是；0：否
    @Column(name = "isend")
    private String isend;

    //是否审批节点 1是；0：否
    @Column(name = "isapproval")
    private String isapproval;

    //是否支付节点 1是；0：否
    @Column(name = "ispay")
    private String ispay;

    //0：正常，1：删除
    @Column(name = "state")
    private String state;

    //流程的类型，msjx:免申即享的类型,bczl:补充资料的类型
    @Column(name = "type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getIsbegin() {
        return isbegin;
    }

    public void setIsbegin(String isbegin) {
        this.isbegin = isbegin;
    }

    public String getIsend() {
        return isend;
    }

    public void setIsend(String isend) {
        this.isend = isend;
    }

    public String getIsapproval() {
        return isapproval;
    }

    public void setIsapproval(String isapproval) {
        this.isapproval = isapproval;
    }

    public String getIspay() {
        return ispay;
    }

    public void setIspay(String ispay) {
        this.ispay = ispay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNextid() {
        return nextid;
    }

    public void setNextid(String nextid) {
        this.nextid = nextid;
    }
}
