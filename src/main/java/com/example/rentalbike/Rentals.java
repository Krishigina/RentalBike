package com.example.rentalbike;

public class Rentals {
    private Integer id;
    private Integer booking_id;
    private String return_date;

    public Rentals(Integer id, Integer booking_id, String return_date) {
        this.id = id;
        this.booking_id = booking_id;
        this.return_date = return_date;
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
}
