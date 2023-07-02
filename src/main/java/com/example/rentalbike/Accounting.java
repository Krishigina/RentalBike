package com.example.rentalbike;

public class Accounting {
    private Integer bikeId;
    private String bikeName;
    private String pickUpDate;

    public Accounting(Integer bikeId, String bikeName, String pickUpDate) {
        this.bikeId = bikeId;
        this.bikeName = bikeName;
        this.pickUpDate = pickUpDate;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }
}
