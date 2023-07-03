package com.example.rentalbike;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking {
    private Integer id;
    private String clientName;
    private String passport;
    private Integer bike_id;
    private String storeName;
    private String pickupDate;
    private String clientFirstName;
    private String clientLastName;
    private String clientSecondName;

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientSecondName() {
        return clientSecondName;
    }

    public void setClientSecondName(String clientSecondName) {
        this.clientSecondName = clientSecondName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Booking(String clientLastName, String clientFirstName, String clientSecondName, String passport, String bike_id, String storeName, String pickupDate){
        this.clientLastName = clientLastName;
        this.clientFirstName = clientFirstName;
        this.clientSecondName = clientSecondName;
        this.passport = passport;
        this.bike_id = Integer.valueOf(bike_id);
        this.storeName = storeName;
        this.pickupDate = pickupDate;
    }
    public Booking(){
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Integer getBike_id() {
        return bike_id;
    }

    public void setBike_id(Integer bike_id) {
        this.bike_id = bike_id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

}
