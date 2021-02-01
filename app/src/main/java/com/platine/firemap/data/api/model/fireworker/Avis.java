package com.platine.firemap.data.api.model.fireworker;

import java.io.Serializable;

public class Avis implements Serializable {
    private double note;
    private String comment;

    public double getNote() {
        return note;
    }


    public String getComment() {
        return comment;
    }

    public void setNote(double note) {
        this.note = note;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }
}
