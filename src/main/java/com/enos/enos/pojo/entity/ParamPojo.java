package com.enos.enos.pojo.entity;

public class ParamPojo {

    private long id;
    private FilePojo file;
    private String key;
    private String value;

    public ParamPojo () { }

    public ParamPojo (long id, FilePojo file, String key, String value) {
        this.id = id;
        this.file = file;
        this.key = key;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public FilePojo getFile() {
        return file;
    }

    public void setFile(FilePojo file) {
        this.file = file;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}