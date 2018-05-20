package com.enos.enos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.lang.Nullable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Application {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;

    private String currentVersion;

    private boolean defaultApplication;

    @Nullable
    @OneToOne
    private File icon;

    public Application() {}

    public Application(long id, String name, String description, String currentVersion, boolean defaultApplication, File icon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentVersion = currentVersion;
        this.defaultApplication = defaultApplication;
        this.icon = icon;
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

    public boolean isDefaultApplication() {
        return defaultApplication;
    }

    public void setDefaultApplication(boolean defaultApplication) {
        this.defaultApplication = defaultApplication;
    }

    public File getIcon() {
        return icon;
    }

    public void setIcon(File icon) {
        this.icon = icon;
    }
}