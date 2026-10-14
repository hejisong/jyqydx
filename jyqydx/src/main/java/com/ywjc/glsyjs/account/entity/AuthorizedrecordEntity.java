package com.ywjc.glsyjs.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "authorizedrecord")
public class AuthorizedrecordEntity implements Serializable {

    @Id
    @Column(name = "authorizedid")
    private String authorizedid;
    @Column(name = "companyid")
    private String companyid;
    @Column(name = "companyname")
    private String companyname;
    @Column(name = "legalpersonid")
    private String legalpersonid;
    @Column(name = "legalperson")
    private String legalperson;
    @Column(name = "authorizedpersonid")
    private String authorizedpersonid;
    @Column(name = "authorizedperson")
    private String authorizedperson;
    @Column(name = "authorizedtime")
    private Date authorizedtime;
    @Column(name = "cancellationtime")
    private Date cancellationtime;
    @Column(name = "state")
    private String state;

    public String getAuthorizedid() {
        return authorizedid;
    }

    public void setAuthorizedid(String authorizedid) {
        this.authorizedid = authorizedid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getLegalpersonid() {
        return legalpersonid;
    }

    public void setLegalpersonid(String legalpersonid) {
        this.legalpersonid = legalpersonid;
    }

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }

    public String getAuthorizedpersonid() {
        return authorizedpersonid;
    }

    public void setAuthorizedpersonid(String authorizedpersonid) {
        this.authorizedpersonid = authorizedpersonid;
    }

    public String getAuthorizedperson() {
        return authorizedperson;
    }

    public void setAuthorizedperson(String authorizedperson) {
        this.authorizedperson = authorizedperson;
    }

    public Date getAuthorizedtime() {
        return authorizedtime;
    }

    public void setAuthorizedtime(Date authorizedtime) {
        this.authorizedtime = authorizedtime;
    }

    public Date getCancellationtime() {
        return cancellationtime;
    }

    public void setCancellationtime(Date cancellationtime) {
        this.cancellationtime = cancellationtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
