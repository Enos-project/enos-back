package com.enos.enos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.enos.enos.entity.enums.EPricingPlan;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String email;

    private String firstname;

    private String lastname;

    private Date lastConnection;

    private Date registerDate;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;

    @Nullable
    @OneToOne
    private File icon;

    @Enumerated(EnumType.STRING)
    private EPricingPlan plan;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Installation> installations = new HashSet<>();

    public User() {}

    public User(long id, String username, String email, String firstname, String lastname, Date lastConnection, 
        Date registerDate, String password, String salt, File icon, EPricingPlan plan) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lastConnection = lastConnection;
        this.registerDate = registerDate;
        this.password = password;
        this.icon = icon;
        this.plan = plan;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username)  {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname; 
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(Date lastConnection) {
        this.lastConnection = lastConnection;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public File getIcon() {
        return icon;
    }

    public void setIcon(File icon) {
        this.icon = icon;
    }

    public EPricingPlan getPlan() {
        return plan;
    }

    public void setPrincingPlan(EPricingPlan plan) {
        this.plan = plan;
    }

    public Set<Installation> getInstallations() {
        return installations;
    }
}