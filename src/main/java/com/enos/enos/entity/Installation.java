package com.enos.enos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Installation {

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Application application;

    private String installedVersion;

    private Date installationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "installation")
    private Set<Log> logs = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "installation")
    private Set<SettingValue> settingValues = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "installation")
    private Set<Param> params = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "installation")
    private Set<AppFile> appFiles = new HashSet<>();

    public Installation() {}

    public Installation(long id, User user, Application application, String installedVersion, Date installationDate) {
        this.id = id;
        this.user = user;
        this.application =  application;
        this.installedVersion = installedVersion;
        this.installationDate = installationDate;
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getInstalledVersion() {
        return installedVersion;
    }

    public void setInstalledVersion(String installedVersion) {
        this.installedVersion = installedVersion;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public Set<SettingValue> getSettingValues() {
        return settingValues;
    }

    public Set<Param> getParams() {
        return params;
    }

    public Set<Log> getLogs() {
        return logs;
    }
    
    public Set<AppFile> getAppFiles() {
        return appFiles;
    }
}
