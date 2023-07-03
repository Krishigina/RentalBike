package com.example.rentalbike;

public class Bike {
    private Integer id;
    private Integer model_id;
    private String modelName;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Bike(Integer id, Integer model_id) {
        this.id = id;
        this.model_id = model_id;
    }
    public Bike(Integer id, String modelName) {
        this.id = id;
        this.modelName = modelName;
    }
    public Bike(String modelName) {
        this.modelName = modelName;
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
