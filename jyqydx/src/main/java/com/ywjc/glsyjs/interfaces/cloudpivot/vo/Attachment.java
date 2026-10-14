package com.ywjc.glsyjs.interfaces.cloudpivot.vo;

public class Attachment {
    /**
     * 附件id
     */
    private String refId;
    /**
     * 附件名称
     */
    private String name;

    private Integer fileSize;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "refId='" + refId + '\'' +
                ", name='" + name + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
