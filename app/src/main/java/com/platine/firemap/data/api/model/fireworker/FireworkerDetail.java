package com.platine.firemap.data.api.model.fireworker;

import java.util.List;

public class FireworkerDetail {
    private int id;
    private String name;
    private double note;
    private List<Avis> avis;
    private List<String> images;
    private String urlPage;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getNote() {
        return note;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public List<String> getImages() {
        return images;
    }

    public String getUrlPage() {
        return urlPage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setUrlPage(String urlPage) {
        this.urlPage = urlPage;
    }
}
