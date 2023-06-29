package com.example.rentalbike;

public class History {
    private String bikename;
    private String storename;
    private String pickupdate;
    private String returndate;

    public History(String bikename, String storename, String pickupdate, String returndate) {
        this.bikename = bikename;
        this.storename = storename;
        this.pickupdate = pickupdate;
        this.returndate = returndate;
    }

    public String getBikename() {
        return bikename;
    }

    public void setBikename(String bikename) {
        this.bikename = bikename;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(String pickupdate) {
        this.pickupdate = pickupdate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }
}
