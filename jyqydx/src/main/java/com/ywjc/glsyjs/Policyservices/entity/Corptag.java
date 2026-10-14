package com.ywjc.glsyjs.Policyservices.entity;

import java.util.List;

public class Corptag {



    private String companyname;

    private String uscreditcode;

    private List<Object> tags;



    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getUscreditcode() {
        return uscreditcode;
    }

    public void setUscreditcode(String uscreditcode) {
        this.uscreditcode = uscreditcode;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }
}
