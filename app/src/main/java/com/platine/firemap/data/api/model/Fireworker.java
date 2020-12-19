package com.platine.firemap.data.api.model;

import java.io.Serializable;

public class Fireworker implements Serializable {
    private int id;
    private String name;
    private float note;

    public Fireworker(int id, String name, float note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
