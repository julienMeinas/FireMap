package com.platine.firemap.data.api.model.firework;

import java.io.Serializable;
import java.util.List;

public class Parking implements Serializable {
    private int id;
    private String name;
    private int price;

    public Parking(String name, int price, List<String> avis) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{'id':" + getId() +
                ", 'name':'" + getName() + "'"+
                ", 'note':" + getPrice() + "}";
    }

}
