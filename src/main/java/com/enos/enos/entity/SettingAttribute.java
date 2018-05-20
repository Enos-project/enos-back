package com.enos.enos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SettingAttribute {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private boolean defaultSettingAttribute;

    @OneToOne
    private File file;

    @ManyToOne
    private Setting setting;

    public SettingAttribute() {}

    public SettingAttribute(long id, String name, boolean defaultSettingAttribute, File file, Setting setting) {
        this.id = id;
        this.name = name;
        this.defaultSettingAttribute = defaultSettingAttribute;
        this.file = file;
        this.setting = setting;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public boolean getDefaultSettingAttribute() {
        return defaultSettingAttribute;
    }

    public void setDefaultSettingAttribute(boolean defaultSettingAttribute) {
        this.defaultSettingAttribute = defaultSettingAttribute;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

}