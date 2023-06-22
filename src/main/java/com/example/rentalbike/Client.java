package com.example.rentalbike;

public class Client {
    private String firstname;
    private String lastname;
    private String secondname;
    private String npassport;
    private String address;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client(String firstname, String lastname, String secondname, String npassport, String address, String login, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.secondname = secondname;
        this.npassport = npassport;
        this.address = address;
        this.login = login;
        this.password = password;

    }
    public Client(){

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

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getNpassport() {
        return npassport;
    }

    public void setNpassport(String npassport) {
        this.npassport = npassport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
