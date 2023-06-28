package com.example.rentalbike;

public class Booking {
    private Integer id;
    private Integer client_id;
    private Integer bike_id;
    private String pickup_date;

    public Booking(Integer id, Integer client_id, Integer bike_id, String pickup_date) {
        this.id = id;
        this.client_id = client_id;
        this.bike_id = bike_id;
        this.pickup_date = pickup_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getBike_id() {
        return bike_id;
    }

    public void setBike_id(Integer bike_id) {
        this.bike_id = bike_id;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }
}
