package com.ywjc.glsyjs.interfaces.plycomresult.entity;

import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "plyfile")
public class PlyfileEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 32, nullable = false, unique = true)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String plyfileid;

    //政策大类编号
    @Column(name = "policycategorynumber")
    private String policycategorynumber;

    //政策大类名称
    @Column(name = "policycategoryname")
    private String policycategoryname;

    //政策小类编号
    @Column(name = "policysubclassno")
    private String policysubclassno;

    //政策小类名称
    @Column(name = "policycategorysubname")
    private String policycategorysubname;

    //政策条件
    @Column(name = "policyconditions")
    private String policyconditions;

    //材料名称
    @Column(name = "materialname")
    private String materialname;

    //是否必传
    @Column(name = "necessarypass")
    private String necessarypass;

    //材料类型
    @Column(name = "materialtype")
    private String materialtype;

    //关联政策id
    @Column(name = "pldtailid")
    private String pldtailid;

    //排序
    @Column(name = "sn")
    private String sn;

    //状态
    @Column(name = "status")
    private String status;



    //材料模板附件
    @Column(name = "modelfileid")
    private String modelfileid;

    @Column(name = "modelfilename")
    private String modelfilename;

    @Transient
    private List<Uploadfile> uploadfiles;

    public List<Uploadfile> getUploadfiles() {
        return uploadfiles;
    }

    public void setUploadfiles(List<Uploadfile> uploadfiles) {
        this.uploadfiles = uploadfiles;
    }

    public String getPlyfileid() {
        return plyfileid;
    }

    public void setPlyfileid(String plyfileid) {
        this.plyfileid = plyfileid;
    }

    public String getPolicycategorynumber() {
        return policycategorynumber;
    }

    public void setPolicycategorynumber(String policycategorynumber) {
        this.policycategorynumber = policycategorynumber;
    }

    public String getPolicycategoryname() {
        return policycategoryname;
    }

    public void setPolicycategoryname(String policycategoryname) {
        this.policycategoryname = policycategoryname;
    }

    public String getPolicysubclassno() {
        return policysubclassno;
    }

    public void setPolicysubclassno(String policysubclassno) {
        this.policysubclassno = policysubclassno;
    }

    public String getPolicycategorysubname() {
        return policycategorysubname;
    }

    public void setPolicycategorysubname(String policycategorysubname) {
        this.policycategorysubname = policycategorysubname;
    }

    public String getPolicyconditions() {
        return policyconditions;
    }

    public void setPolicyconditions(String policyconditions) {
        this.policyconditions = policyconditions;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getNecessarypass() {
        return necessarypass;
    }

    public void setNecessarypass(String necessarypass) {
        this.necessarypass = necessarypass;
    }

    public String getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(String materialtype) {
        this.materialtype = materialtype;
    }

    public String getPldtailid() {
        return pldtailid;
    }

    public void setPldtailid(String pldtailid) {
        this.pldtailid = pldtailid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getModelfileid() {
        return modelfileid;
    }

    public void setModelfileid(String modelfileid) {
        this.modelfileid = modelfileid;
    }

    public String getModelfilename() {
        return modelfilename;
    }

    public void setModelfilename(String modelfilename) {
        this.modelfilename = modelfilename;
    }
}
