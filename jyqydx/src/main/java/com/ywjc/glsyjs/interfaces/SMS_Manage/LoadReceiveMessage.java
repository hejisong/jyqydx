
package com.ywjc.glsyjs.interfaces.SMS_Manage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sendPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sendPhoneNum",
        "startTime",
        "endTime"
})
@XmlRootElement(name = "LoadReceiveMessage")
public class LoadReceiveMessage {

    protected String sendPhoneNum;
    protected String startTime;
    protected String endTime;

    /**
     * 获取sendPhoneNum属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSendPhoneNum() {
        return sendPhoneNum;
    }

    /**
     * 设置sendPhoneNum属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSendPhoneNum(String value) {
        this.sendPhoneNum = value;
    }

    /**
     * 获取startTime属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置startTime属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * 获取endTime属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置endTime属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

}
