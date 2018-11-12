package com.enos.enos.pojo.entity;

import java.util.Set;

public class ApplicationPojo {

    private long id;
    private String name;
    private String description;
    private String currentVersion;
    private boolean defaultApplication;
    private String publisher;

    private FilePojo icon;
    private String type;
    private Set<FilePojo> applicationStaticFiles;

    public ApplicationPojo () { }

    public ApplicationPojo (long id, String name, String description, 
        String currentVersion, boolean defaultApplication, String publisher, 
        FilePojo icon, String type, Set<FilePojo> applicationStaticFiles) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.currentVersion = currentVersion;
        this.defaultApplication = defaultApplication;
        this.publisher = publisher;
        this.icon = icon;
        this.type = type;
        this.applicationStaticFiles = applicationStaticFiles;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public boolean getDefaultApplication() {
        return defaultApplication;
    }

    public void setDefaultApplication(boolean defaultApplication) {
        this.defaultApplication = defaultApplication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public FilePojo getIcon() {
        return icon;
    }

    public void setIcon(FilePojo icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<FilePojo> getApplicationStaticFiles() {
        return applicationStaticFiles;
    }

    public void setApplicationStaticFiles(Set<FilePojo> applicationStaticFiles) {
        this.applicationStaticFiles = applicationStaticFiles;
    }
}
