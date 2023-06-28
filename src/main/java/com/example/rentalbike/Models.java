package com.example.rentalbike;

public class Models {
    private Integer id;
    private String name;
    private String type;
    private Integer gear_count;

    public Models(Integer id, String name, String type, Integer gear_count) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.gear_count = gear_count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGear_count() {
        return gear_count;
    }

    public void setGear_count(Integer gear_count) {
        this.gear_count = gear_count;
    }
}
