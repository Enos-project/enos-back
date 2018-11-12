package com.enos.enos.pojo.entity;

public class LogPojo {

    private long id;
    private String type;
    private String content;

    public LogPojo() { }

    public LogPojo(long id, String type, String content) {
        this.id = id;
        this.type = type;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}