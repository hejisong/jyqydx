package com.ywjc.glsyjs.interfaces.utils;

public class MessageUtil {

    private String errorMsg;
    private boolean result;
    private Object resultObj;

    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public MessageUtil() {
    }

    public MessageUtil(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public MessageUtil(String errorMsg, boolean result) {
        this.errorMsg = errorMsg;
        this.result = result;
    }

    public MessageUtil(String errorMsg, boolean result, Object resultObj) {
        this.errorMsg = errorMsg;
        this.result = result;
        this.resultObj = resultObj;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getResultObj() {
        return this.resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

}
