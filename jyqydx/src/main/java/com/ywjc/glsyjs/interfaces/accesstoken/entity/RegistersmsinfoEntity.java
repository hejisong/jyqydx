package com.ywjc.glsyjs.interfaces.accesstoken.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registersmsinfo")
public class RegistersmsinfoEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @Column(length = 32, nullable = false,unique=true)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String smsinfoid;

    @Column(name = "sender")
    private String sender;

    @Column(name = "send_unit")
    private String sendunit;

    @Column(name = "send_time")
    private String sendtime;

    @Column(name = "send_content")
    private String sendcontent;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "receiver_phone_number")
    private String receiverphonenumber;

    @Column(name = "receive_unit")
    private String receiveunit;

    @Column(name = "sequenceid")
    private String sequenceid;

    @Column(name = "srcid")
    private String srcid;

    @Column(name = "respcode")
    private String respcode;

    @Column(name = "respmessage")
    private String respmessage;

    public RegistersmsinfoEntity() {

    }

    public RegistersmsinfoEntity(String smsinfoid, String sender, String sendunit, String sendtime, String sendcontent, String receiver, String receiverphonenumber, String receiveunit, String sequenceid, String srcid, String respcode, String respmessage) {
        this.smsinfoid = smsinfoid;
        this.sender = sender;
        this.sendunit = sendunit;
        this.sendtime = sendtime;
        this.sendcontent = sendcontent;
        this.receiver = receiver;
        this.receiverphonenumber = receiverphonenumber;
        this.receiveunit = receiveunit;
        this.sequenceid = sequenceid;
        this.srcid = srcid;
        this.respcode = respcode;
        this.respmessage = respmessage;
    }

    public String getSmsinfoid() {
        return smsinfoid;
    }

    public void setSmsinfoid(String smsinfoid) {
        this.smsinfoid = smsinfoid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendunit() {
        return sendunit;
    }

    public void setSendunit(String sendunit) {
        this.sendunit = sendunit;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getSendcontent() {
        return sendcontent;
    }

    public void setSendcontent(String sendcontent) {
        this.sendcontent = sendcontent;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverphonenumber() {
        return receiverphonenumber;
    }

    public void setReceiverphonenumber(String receiverphonenumber) {
        this.receiverphonenumber = receiverphonenumber;
    }

    public String getReceiveunit() {
        return receiveunit;
    }

    public void setReceiveunit(String receiveunit) {
        this.receiveunit = receiveunit;
    }

    public String getSequenceid() {
        return sequenceid;
    }

    public void setSequenceid(String sequenceid) {
        this.sequenceid = sequenceid;
    }

    public String getSrcid() {
        return srcid;
    }

    public void setSrcid(String srcid) {
        this.srcid = srcid;
    }

    public String getRespcode() {
        return respcode;
    }

    public void setRespcode(String respcode) {
        this.respcode = respcode;
    }

    public String getRespmessage() {
        return respmessage;
    }

    public void setRespmessage(String respmessage) {
        this.respmessage = respmessage;
    }
}
