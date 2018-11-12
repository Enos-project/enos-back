package com.enos.enos.pojo.entity;

import java.util.Set;

public class InstallationPojo {

    private long id;
    private ApplicationPojo application;
    private String installedVersion;
    private String installationDate;
    private Set<FilePojo> appFiles;
    private Set<ParamPojo> params;

    public InstallationPojo() { }

    public InstallationPojo(long id, ApplicationPojo application, 
        String installedVersion, String installationDate, 
        Set<FilePojo> appFiles, Set<ParamPojo> params) {

        this.id = id;
        this.application = application;
        this.installedVersion = installedVersion;
        this.installationDate = installationDate;
        this.appFiles = appFiles;
        this.params = params;
    }

    public long getId() {
        return id;
    }

    public ApplicationPojo getApplication() {
        return application;
    }

    public void setApplication(ApplicationPojo application) {
        this.application = application;
    }

    public String getInstalledVersion() {
        return installedVersion;
    }

    public void setInstalledVersion(String installedVersion) {
        this.installedVersion = installedVersion;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }

    public Set<FilePojo> getAppFiles() {
        return appFiles;
    }

    public void setAppFiles(Set<FilePojo> appFiles) {
        this.appFiles = appFiles;
    }

    public Set<ParamPojo> getParams() {
        return params;
    }

    public void setParams(Set<ParamPojo> params) {
        this.params = params;
    }
}