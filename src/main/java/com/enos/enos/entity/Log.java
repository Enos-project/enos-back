package com.enos.enos.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.enos.enos.entity.enums.ELogType;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Log {

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    private Installation installation;

    @Enumerated(EnumType.STRING)
    private ELogType type;

    private String content;

    public Log() {}

    public Log(long id, Installation installation, ELogType type, String content) {
        this.id = id;
        this.installation = installation;
        this.type = type;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public ELogType getType() {
        return type;
    }

    public void setType(ELogType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }
}