package com.ywjc.glsyjs.sys.entity;

import java.util.List;

public class Policytag {
    private Integer wordcount;
    private List<Object> tags;

    public Integer getWordcount() {
        return wordcount;
    }

    public void setWordcount(Integer wordcount) {
        this.wordcount = wordcount;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }
}
