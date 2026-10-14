package com.ywjc.glsyjs.Policyservices.entity;

import java.util.List;

public class PolicyDalei {

    //政策id
    private String plfidid;

    //政策名称
    private String policyname;

    //大类名称
    private String nplcategory;

    //申报材料
    private List<Policymateriallist> materiallists;

    public String getPlfidid() {
        return plfidid;
    }

    public void setPlfidid(String plfidid) {
        this.plfidid = plfidid;
    }

    public String getPolicyname() {
        return policyname;
    }

    public void setPolicyname(String policyname) {
        this.policyname = policyname;
    }

    public String getNplcategory() {
        return nplcategory;
    }

    public void setNplcategory(String nplcategory) {
        this.nplcategory = nplcategory;
    }

    public List<Policymateriallist> getMateriallists() {
        return materiallists;
    }

    public void setMateriallists(List<Policymateriallist> materiallists) {
        this.materiallists = materiallists;
    }
}
