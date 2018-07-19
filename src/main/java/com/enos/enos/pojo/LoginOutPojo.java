package com.enos.enos.pojo;

public class LoginOutPojo {

    private String token;
    private long id;
    private String firstname;
    private String lastname;

    public LoginOutPojo() { }

    public LoginOutPojo(String token, long id, String firstname, String lastname) {
        this.token = token;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}