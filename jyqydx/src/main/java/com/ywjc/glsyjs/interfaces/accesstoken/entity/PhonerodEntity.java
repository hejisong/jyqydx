package com.ywjc.glsyjs.interfaces.accesstoken.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "phonerod")
public class PhonerodEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "phone")
    private String phone;
    @Column(name = "rod")
    private String rod;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "extime")
    private Date extime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRod() {
        return rod;
    }

    public void setRod(String rod) {
        this.rod = rod;
    }

    public Date getExtime() {
        return extime;
    }

    public void setExtime(Date extime) {
        this.extime = extime;
    }


}
