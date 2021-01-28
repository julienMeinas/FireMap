package com.platine.firemap.presentation.Ressources;

import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.util.List;

public class Validation {

    public static boolean validDate(String date) {
        if(date == null)
            return false;
        if(date.length() < 10)
            return false;
        if(! (date.substring(2,3).equals("/") || date.substring(5,6).equals("/")) )
            return false;
        if(Integer.parseInt(date.substring(0, 2)) < 0 || Integer.parseInt(date.substring(0, 2)) > 31)
            return false;
        if(Integer.parseInt(date.substring(3, 5)) < 0 || Integer.parseInt(date.substring(3, 5)) > 12)
            return false;
        return true;
    }



    public static boolean validHour(String hour) {
        if(hour == null)
            return false;
        if(hour.length() < 5)
            return false;
        if(Integer.parseInt(hour.substring(0, 2)) < 0 || Integer.parseInt(hour.substring(0, 2)) > 23)
            return false;
        if(Integer.parseInt(hour.substring(3)) < 0 || Integer.parseInt(hour.substring(3)) > 59)
            return false;
        return true;
    }

    public static boolean validMarker(double longitude, double latitude) {
        if(latitude == 0 && longitude == 0)
            return false;
        return true;
    }

    public static boolean validAddress(String address) {
        if(address == null)
            return false;
        if(address.trim() == "")
            return false;
        return true;
    }


    public static boolean validFireworker(List<Fireworker> fireworkers) {
        if(fireworkers.size() <= 0) {
            return false;
        }
        return true;
    }

    public static boolean validNote(int note) {
        if(note < 0 || note > 5)
            return false;
        return true;
    }

    public static boolean validText(String text) {
        if(text == null)
            return false;
        return true;
    }


    public static boolean validPrice(int price) {
        if(price < 0)
            return false;
        return true;
    }
}
