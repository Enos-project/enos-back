package com.enos.enos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Param {

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    private Application application;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToOne
    private File file;

    @Column(name = "param_key")
    private String key;

    @Column(name = "param_value")
    private String value;

    public Param() {}

    public Param(long id, Application application, User user, String key, String value, File file) {
        this.id = id;
        this.application = application;
        this.user = user;
        this.key = key;
        this.value = value;
        this.file = file;
    }
    
    public long getId() {
        return id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}