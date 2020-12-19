package com.platine.firemap.presentation.fireworkdisplay.infoFirework;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;

import java.util.ArrayList;
import java.util.List;

public class InfoFireworkActivity extends AppCompatActivity {
    private static final String TAG = "InfoFireworkActivity";
    public static final String ADDRESS_MESSAGE = "PLACE";
    public static final String DATE_MESSAGE = "DATE";
    public static final String PRICE_MESSAGE = "PRICE";
    public static final String PARKING_MESSAGE = "PARKING";
    public static final String ACCESS_HANDICAP_MESSAGE = "ACCESS_HANDICAP";
    public static final String PEOPLE_MESSAGE = "PEOPLE";
    public static final String FIREWORKER_MESSAGE = "FIREWOERKER";

    private TextView place;
    private TextView date;
    private ImageView imagePrice;
    private TextView price;
    private ImageView imageParking;
    private TextView parking;
    private ImageView imageAccessHandicap;
    private TextView accessHandicap;
    private ImageView imagePeople;
    private TextView people;
    private TextView fireworker;

    private final String msg_price_free = "Gratuit";
    private final String msg_price_not_free = "Payant";
    private final String msg_no_parking = "Pas de parking";
    private final String msg_parking_free = "Parking gratuit";
    private final String msg_parking_no_free = "Parking payant";
    private final String msg_access_handicap = "Accès handicapé";
    private final String msg_no_access_handicap = "Pas d'accès handicapé";
    private final String msg_crowed_low = "Peu de gens attendu";
    private final String msg_crowed_medium = "Moyennement de gens attendu";
    private final String msg_crowed_high = "Beaucoup de gens attendu";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artivity_info);

        Intent intent = getIntent();
        String address = intent.getStringExtra(ADDRESS_MESSAGE);
        String date = intent.getStringExtra(DATE_MESSAGE);
        int price = intent.getIntExtra(PRICE_MESSAGE, 0);
        boolean accessHandicap = intent.getBooleanExtra(ACCESS_HANDICAP_MESSAGE, false);
        String crowed = intent.getStringExtra(PEOPLE_MESSAGE);
        ArrayList<Parking> parkings = (ArrayList<Parking>)intent.getSerializableExtra(PARKING_MESSAGE);
        Fireworker fireworker = (Fireworker)intent.getSerializableExtra(FIREWORKER_MESSAGE);
        initComponent();
        setComponent(address, date, price, accessHandicap, crowed, parkings, fireworker);

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initComponent() {
        this.place = findViewById(R.id.lieu);
        this.date = findViewById(R.id.date);
        this.imagePrice = findViewById(R.id.price);
        this.price = findViewById(R.id.textPrice);
        this.imageParking = findViewById(R.id.parking);
        this.parking = findViewById(R.id.textParking);
        this.imageAccessHandicap = findViewById(R.id.accessHandicap);
        this.accessHandicap = findViewById(R.id.textAccessHandicap);
        this.imagePeople = findViewById(R.id.people);
        this.people = findViewById(R.id.textPeople);
        this.fireworker = findViewById(R.id.fireworker);
    }

    public void setComponent(String address, String date, int price, boolean accessHandicap, String crowed, List<Parking> parkings, Fireworker fireworker) {
        // address
        this.place.setText(address);
        // date
        this.date.setText(date);
        //price
        this.imagePrice.setImageResource(price == 0 ? R.drawable.drawable_price_free : R.drawable.drawable_price_no_free);
        this.price.setText(price == 0 ? msg_price_free : msg_price_not_free);
        //parking
        this.imageParking.setImageResource(R.drawable.drawable_parking_free);
        this.parking.setText(msg_parking_free);
        // access handicap
        this.imageAccessHandicap.setImageResource(accessHandicap ? R.drawable.drawable_handicap_access : R.drawable.drawable_no_handicap_access);
        this.accessHandicap.setText(accessHandicap ? msg_access_handicap : msg_no_access_handicap);
        // crowed
        if(crowed.equals("Low")) {
            this.imagePeople.setImageResource(R.drawable.drawable_people_low);
            this.people.setText(msg_crowed_low);
        }else if(crowed.equals("Medium")) {
            this.imagePeople.setImageResource(R.drawable.drawable_people_medium);
            this.people.setText(msg_crowed_medium);
        }else {
            this.imagePeople.setImageResource(R.drawable.drawable_people_high);
            this.people.setText(msg_crowed_high);
        }
        // parking
        if(parkings.size() == 0) {
            this.imageParking.setImageResource(R.drawable.drawable_no_parking);
            this.parking.setText(msg_no_parking);
        }else{
            boolean freeParking = false;
            for(Parking p : parkings) {
                if(p.getPrice() == 0){
                    this.imageParking.setImageResource(R.drawable.drawable_parking_free);
                    this.parking.setText(msg_parking_free);
                    freeParking = true;
                }
            }
            if(!freeParking) {
                this.imageParking.setImageResource(R.drawable.drawable_parking_no_free);
                this.parking.setText(msg_parking_no_free);
            }
        }
        // fireworker
        this.fireworker.setText(fireworker.getName());
    }
}
