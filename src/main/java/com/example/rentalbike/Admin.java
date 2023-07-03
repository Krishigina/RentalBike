package com.example.rentalbike;

public class Admin {
    private Integer id;
    private String lastname;
    private String firstname;
    private String secondname;
    private String adminName;
    private String login;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Admin(String lastname, String firstname, String secondname, String login, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.secondname = secondname;
        this.login = login;
        this.password = password;
    }
    public Admin(Integer id, String adminName, String login) {
        this.id = id;
        this.adminName = adminName;
        this.login = login;
    }
    public Admin() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }
}
