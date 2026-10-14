
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
 *         &lt;element name="messageContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="receivers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="singn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "messageContent",
        "sender",
        "receivers",
        "singn"
})
@XmlRootElement(name = "ExecSendBatchMessage")
public class ExecSendBatchMessage {

    protected String messageContent;
    protected String sender;
    protected String receivers;
    protected String singn;

    /**
     * 获取messageContent属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * 设置messageContent属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMessageContent(String value) {
        this.messageContent = value;
    }

    /**
     * 获取sender属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSender() {
        return sender;
    }

    /**
     * 设置sender属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * 获取receivers属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceivers() {
        return receivers;
    }

    /**
     * 设置receivers属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceivers(String value) {
        this.receivers = value;
    }

    /**
     * 获取singn属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSingn() {
        return singn;
    }

    /**
     * 设置singn属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSingn(String value) {
        this.singn = value;
    }

}
