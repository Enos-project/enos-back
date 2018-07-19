package com.enos.enos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.lang.Nullable;

import com.enos.enos.entity.enums.EApplicationType;

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

    private String publisher;

    @Nullable
    @OneToOne
    private File icon;

    @Enumerated(EnumType.STRING)
    private EApplicationType type;

    public Application() {}

    public Application(long id, String name, String description, String currentVersion, boolean defaultApplication, File icon, String publisher, EApplicationType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentVersion = currentVersion;
        this.defaultApplication = defaultApplication;
        this.icon = icon;
        this.publisher = publisher;
        this.type = type;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public EApplicationType getType() {
        return type;
    }

    public void setType(EApplicationType type) {
        this.type = type;
    }
}