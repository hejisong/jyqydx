package com.ywjc.glsyjs.interfaces.utils;


public class YshApiPayEntity {

    //收款账号
    private String inPaymentAccount;
    //付款账号
    private String outPaymentAccount;
    //转账金额
    private String amount;
    //交易备注
    private String remark;

    public String getInPaymentAccount() {
        return inPaymentAccount;
    }

    public void setInPaymentAccount(String inPaymentAccount) {
        this.inPaymentAccount = inPaymentAccount;
    }

    public String getOutPaymentAccount() {
        return outPaymentAccount;
    }

    public void setOutPaymentAccount(String outPaymentAccount) {
        this.outPaymentAccount = outPaymentAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
