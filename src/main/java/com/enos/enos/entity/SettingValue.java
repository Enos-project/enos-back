package com.enos.enos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SettingValue {

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToOne
    private SettingAttribute settingAttribute;

    public SettingValue() {}

    public SettingValue(long id, User user, SettingAttribute settingAttribute) {
        this.id = id;
        this.user = user;
        this.settingAttribute = settingAttribute;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SettingAttribute getSettingAttribute() {
        return settingAttribute;
    }

    public void setSettingAttribute(SettingAttribute settingAttribute) {
        this.settingAttribute = settingAttribute;
    }
}