package com.platine.firemap.data.api.model.fireworker;

public class Avis {
    private double note;
    private String title;
    private String comment;

    public double getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
