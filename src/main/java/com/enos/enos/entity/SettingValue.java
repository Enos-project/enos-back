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
    private Installation installation;

    @ManyToOne
    private SettingAttribute settingAttribute;

    public SettingValue() {}

    public SettingValue(long id, Installation installation, SettingAttribute settingAttribute) {
        this.id = id;
        this.installation = installation;
        this.settingAttribute = settingAttribute;
    }

    public long getId() {
        return id;
    }

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    public SettingAttribute getSettingAttribute() {
        return settingAttribute;
    }

    public void setSettingAttribute(SettingAttribute settingAttribute) {
        this.settingAttribute = settingAttribute;
    }
}