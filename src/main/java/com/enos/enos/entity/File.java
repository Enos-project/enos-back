package com.enos.enos.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.lang.Nullable;

import com.enos.enos.entity.enums.EFileType;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class File {

    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private EFileType type;

    private String content;

    @JsonIgnore
    @Nullable
    @OneToOne(mappedBy = "icon")
    private User user;

    @JsonIgnore
    @Nullable
    @OneToOne(mappedBy = "icon")
    private Application application;

    @JsonIgnore
    @Nullable
    @OneToOne(mappedBy = "file")
    private SettingAttribute settingAttribute;

    @JsonIgnore
    @Nullable
    @OneToOne(mappedBy = "file")
    private ApplicationStaticFile applicationStaticFile;

    @JsonIgnore
    @Nullable
    @OneToOne(mappedBy = "file")
    private UserApplicationFile userApplicationFile;

    public File() {}

    public File(long id, EFileType type, String content) {
        this.id = id;
        this.type = type;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public EFileType getType() {
        return type;
    }

    public void setType(EFileType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public Application getApplication() {
        return application;
    }

    public SettingAttribute getSettingAttribute() {
        return settingAttribute;
    }

    public ApplicationStaticFile getApplicationStaticFile() {
        return applicationStaticFile;
    }

    public UserApplicationFile getUserApplicationFile() {
        return userApplicationFile;
    }
}