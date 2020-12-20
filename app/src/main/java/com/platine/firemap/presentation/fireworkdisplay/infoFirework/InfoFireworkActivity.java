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
import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;
import com.platine.firemap.presentation.fireworkdisplay.editFirework.EditFireworkActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InfoFireworkActivity extends AppCompatActivity implements InfoFireworkActionInterface{
    private static final String TAG = "InfoFireworkActivity";
    public static final String FIREWORK_MESSAGE = "FIREWORK";

    private FireworkModel firework;

    private TextView textViewPlace;
    private TextView textViewDate;
    private ImageView imagePrice;
    private TextView textViewPrice;
    private ImageView imageParking;
    private TextView textViewParking;
    private ImageView imageAccessHandicap;
    private TextView textViewAccessHandicap;
    private TextView textViewDuration;
    private ImageView imageDuration;
    private ImageView imagePeople;
    private TextView textViewPeople;
    private TextView textViewFireworker;

    private final String msg_price_free = "Gratuit";
    private final String msg_price_not_free = "Payant";
    private final String msg_no_parking = "Pas de parking";
    private final String msg_parking_free = "Parking gratuit";
    private final String msg_parking_no_free = "Parking payant";
    private final String msg_access_handicap = "Accès handicapé";
    private final String msg_no_access_handicap = "Pas d'accès handicapé";
    private final String msg_duration = "Minutes";
    private final String msg_crowed_low = "Peu de gens attendu";
    private final String msg_crowed_medium = "Moyennement de gens attendu";
    private final String msg_crowed_high = "Beaucoup de gens attendu";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artivity_info);

        Intent intent = getIntent();

        this.firework = (FireworkModel) intent.getSerializableExtra(FIREWORK_MESSAGE);

        initComponent();
        setComponent(this.firework.getAddress(), this.firework.getDate(), this.firework.getPrice(), this.firework.isHandicAccess(), this.firework.getDuration(), this.firework.getCrowded(), this.firework.getParking(), this.firework.getFireworker());

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.button_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickEdit(firework);
            }
        });
    }

    public void initComponent() {
        this.textViewPlace = findViewById(R.id.lieu);
        this.textViewDate = findViewById(R.id.date);
        this.imagePrice = findViewById(R.id.price);
        this.textViewPrice = findViewById(R.id.textPrice);
        this.imageParking = findViewById(R.id.parking);
        this.textViewParking = findViewById(R.id.textParking);
        this.imageAccessHandicap = findViewById(R.id.accessHandicap);
        this.textViewAccessHandicap = findViewById(R.id.textAccessHandicap);
        this.textViewDuration = findViewById(R.id.textDuration);
        this.imageDuration = findViewById(R.id.duration);
        this.imagePeople = findViewById(R.id.people);
        this.textViewPeople = findViewById(R.id.textPeople);
        this.textViewFireworker = findViewById(R.id.fireworker);
    }

    public void setComponent(String address, String date, int price, boolean accessHandicap, int duration, String crowed, List<Parking> parkings, Fireworker fireworker) {
        // address
        this.textViewPlace.setText(address);
        // date
        this.textViewDate.setText(date);
        //price
        this.imagePrice.setImageResource(price == 0 ? R.drawable.drawable_price_free : R.drawable.drawable_price_no_free);
        this.textViewPrice.setText(price == 0 ? msg_price_free : msg_price_not_free);
        //parking
        this.imageParking.setImageResource(R.drawable.drawable_parking_free);
        this.textViewParking.setText(msg_parking_free);
        // access handicap
        this.imageAccessHandicap.setImageResource(accessHandicap ? R.drawable.drawable_handicap_access : R.drawable.drawable_no_handicap_access);
        this.textViewAccessHandicap.setText(accessHandicap ? msg_access_handicap : msg_no_access_handicap);
        // duration
        this.textViewDuration.setText(duration + " " + msg_duration);
        // crowed
        if(crowed.equals("Low")) {
            this.imagePeople.setImageResource(R.drawable.drawable_people_low);
            this.textViewPeople.setText(msg_crowed_low);
        }else if(crowed.equals("Medium")) {
            this.imagePeople.setImageResource(R.drawable.drawable_people_medium);
            this.textViewPeople.setText(msg_crowed_medium);
        }else {
            this.imagePeople.setImageResource(R.drawable.drawable_people_high);
            this.textViewPeople.setText(msg_crowed_high);
        }
        // parking
        if(parkings.size() == 0) {
            this.imageParking.setImageResource(R.drawable.drawable_no_parking);
            this.textViewParking.setText(msg_no_parking);
        }else{
            boolean freeParking = false;
            for(Parking p : parkings) {
                if(p.getPrice() == 0){
                    this.imageParking.setImageResource(R.drawable.drawable_parking_free);
                    this.textViewParking.setText(msg_parking_free);
                    freeParking = true;
                }
            }
            if(!freeParking) {
                this.imageParking.setImageResource(R.drawable.drawable_parking_no_free);
                this.textViewParking.setText(msg_parking_no_free);
            }
        }
        // fireworker
        this.textViewFireworker.setText(fireworker.getName());
    }

    @Override
    public void onClickEdit(FireworkModel fireworkModel) {
        Intent intent = new Intent(this, EditFireworkActivity.class);
        intent.putExtra(InfoFireworkActivity.FIREWORK_MESSAGE, (Serializable)fireworkModel);
        this.startActivity(intent);
    }
}
