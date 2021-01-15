package com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter;

import com.platine.firemap.data.api.model.fireworker.Avis;

import java.util.List;

public class Fireworker_item {
    private int id;
    private String name;
    private float note;
    private List<Avis> avis;

    public Fireworker_item(int id, String name, List<Avis> avis, float note) {
        this.id = id;
        this.name = name;
        this.avis = avis;
        this.note = note;
    }

    public Fireworker_item() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Avis> getAvis() {
        return avis;
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

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
