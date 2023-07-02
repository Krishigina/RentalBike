package com.example.rentalbike;

public class Manager {
    private Integer id;
    private String lastname;
    private String firstname;

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    private String secondname;
    private String login;
    private String managerName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Manager(Integer id, String lastname, String firstname, String secondname, String login) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.secondname = secondname;
        this.login = login;
    }
    public Manager(Integer id, String managerName, String login) {
        this.id = id;
        this.managerName = managerName;
        this.login = login;
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
