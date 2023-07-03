package com.example.rentalbike;

public class Rentals {
    private Integer id;
    private Integer booking_id;
    private String return_date;
    private String clientName;
    private String pickup_date;
    private Integer bike_id;
    private String clientLastName;
    private String clientFirstName;
    private String clientSecondName;

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientSecondName() {
        return clientSecondName;
    }

    public void setClientSecondName(String clientSecondName) {
        this.clientSecondName = clientSecondName;
    }

    public Rentals(Integer id, String clientName, Integer bike_id, String pickup_date, String return_date) {
        this.id = id;
        this.clientName = clientName;
        this.bike_id = bike_id;
        this.pickup_date = pickup_date;
        this.return_date = return_date;
    }
    public Rentals(String clientLastName, String clientFirstName, String clientSecondName, Integer bike_id, String pickup_date, String return_date) {
        this.clientLastName = clientLastName;
        this.clientFirstName = clientFirstName;
        this.clientSecondName = clientSecondName;
        this.bike_id = bike_id;
        this.pickup_date = pickup_date;
        this.return_date = return_date;
    }
    public Rentals() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }
    public String getClientName(){
        return clientName;
    }
    public void setClientName(String clientName){
        this.clientName = clientName;
    }
    public Integer getBike_id(){
        return bike_id;
    }
    public void setBike_id(Integer bike_id){
        this.bike_id = bike_id;
    }
}
