package com.enos.enos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AppFile {

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    private Installation installation;

    @OneToOne
    private File file;

public AppFile() {}

    public AppFile(long id, Installation installation, File file) {
        this.id = id;
        this.installation = installation;
        this.file = file;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}