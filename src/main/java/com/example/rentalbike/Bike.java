package com.example.rentalbike;

public class Bike {
    private Integer id;
    private Integer model_id;

    public Bike(Integer id, Integer model_id) {
        this.id = id;
        this.model_id = model_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModel_id() {
        return model_id;
    }

    public void setModel_id(Integer model_id) {
        this.model_id = model_id;
    }
}
