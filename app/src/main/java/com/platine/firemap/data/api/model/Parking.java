package com.platine.firemap.data.api.model;

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

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
