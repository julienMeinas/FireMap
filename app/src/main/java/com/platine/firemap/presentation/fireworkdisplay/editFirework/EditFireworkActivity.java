package com.platine.firemap.presentation.fireworkdisplay.editFirework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.Ressources.Button;
import com.platine.firemap.presentation.Ressources.Utils;
import com.platine.firemap.presentation.fireworkdisplay.addParking.AddParkingActivity;
import com.platine.firemap.presentation.fireworkdisplay.contact.ContactActivity;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class EditFireworkActivity extends AppCompatActivity implements EditFireworkActionInterface {
    private static final String TAG = "EditFireworkActivity";
    private FireworkModel firework;
    private ListViewModel fireworkListViewModel;
    public static final String FIREWORK_REPORT_ID = "REPORTED ID";

    // icons
    private TextView empty;
    private TextView textViewCity;
    private TextView textViewPlace;
    private TextView textViewDate;
    private ImageView imagePrice;
    private ImageView imageParking;
    private ImageView imageAccessHandicap;
    private ImageView imageDuration;
    private ImageView imagePeople;
    private TextView textViewFireworker;

    private AppCompatButton buttonPriceFree;
    private AppCompatButton buttonPriceNotFree;
    private AppCompatButton buttonAccessHandicap;
    private AppCompatButton buttonNotAccessHandicap;
    private AppCompatButton buttonDurationShort;
    private AppCompatButton buttonDurationMiddle;
    private AppCompatButton buttonDurationLong;
    private AppCompatButton buttonCrowedLow;
    private AppCompatButton buttonCrowedMedium;
    private AppCompatButton buttonCrowedHigh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        this.firework = (FireworkModel)intent.getSerializableExtra(InfoFireworkActivity.FIREWORK_MESSAGE);
        initComponent();
        empty = findViewById(R.id.empty);
        Utils.setComponent(this.firework.getCity(), this.textViewCity, this.firework.getAddress(), this.textViewPlace,
                           this.firework.getDate(), this.textViewDate, this.firework.getPrice(), this.imagePrice, empty,
                           this.firework.isHandicAccess(), this.imageAccessHandicap, empty, this.firework.getDuration(), this.imageDuration, empty,
                           this.firework.getCrowded(), this.imagePeople, empty, this.firework.getParking(), this.imageParking,
                           this.firework.getFireworker(), this.textViewFireworker);

        Button.ButtonPrice(buttonPriceFree, buttonPriceNotFree, imagePrice, firework);
        Button.ButtonAccessHandicap(buttonAccessHandicap, buttonNotAccessHandicap, imageAccessHandicap, firework);
        Button.ButtonCrowed(buttonCrowedLow, buttonCrowedMedium, buttonCrowedHigh, imagePeople, firework);
        Button.ButtonDuration(buttonDurationShort, buttonDurationMiddle, buttonDurationLong, imageDuration, firework);

        ButtonValidation();
        ButtonReport();
        ButtonAddParking();
        ButtonBack();

        initViewModel();
    }


    public void initViewModel() {
        fireworkListViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
    }



    public void initComponent() {
        this.textViewCity = findViewById(R.id.city);
        this.textViewPlace = findViewById(R.id.lieu);
        this.textViewDate = findViewById(R.id.date);
        this.imagePrice = findViewById(R.id.price);
        this.imageParking = findViewById(R.id.parking);
        this.imageAccessHandicap = findViewById(R.id.accessHandicap);
        this.imageDuration = findViewById(R.id.duration);
        this.imagePeople = findViewById(R.id.people);
        this.textViewFireworker = findViewById(R.id.fireworker);

        // price
        this.buttonPriceFree = findViewById(R.id.buttonPriceFree);
        this.buttonPriceFree.setText(Utils.msg_price_free);
        this.buttonPriceNotFree = findViewById(R.id.buttonPriceNotFree);
        this.buttonPriceNotFree.setText(Utils.msg_price_not_free);
        // handicap
        this.buttonAccessHandicap = findViewById(R.id.buttonAccessHandicap);
        this.buttonAccessHandicap.setText(Utils.msg_access_handicap);
        this.buttonNotAccessHandicap = findViewById(R.id.buttonNotAccessHandicap);
        this.buttonNotAccessHandicap.setText(Utils.msg_no_access_handicap);
        // duration
        this.buttonDurationShort = findViewById(R.id.button_duration_short);
        this.buttonDurationShort.setText(Utils.msg_duration_short);
        this.buttonDurationMiddle = findViewById(R.id.button_duration_middle);
        this.buttonDurationMiddle.setText(Utils.msg_duration_middle);
        this.buttonDurationLong = findViewById(R.id.button_duration_long);
        this.buttonDurationLong.setText(Utils.msg_duration_long);
        // people
        this.buttonCrowedLow = findViewById(R.id.buttonCrowedLow);
        this.buttonCrowedLow.setText(Utils.msg_crowed_low);
        this.buttonCrowedMedium = findViewById(R.id.buttonCrowedMedium);
        this.buttonCrowedMedium.setText(Utils.msg_crowed_medium);
        this.buttonCrowedHigh = findViewById(R.id.buttonCrowedHigh);
        this.buttonCrowedHigh.setText(Utils.msg_crowed_high);
        // duration

    }


    public void ButtonValidation() {
        findViewById(R.id.validation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireworkListViewModel.updateFirework(firework.getId(), firework.getPrice(), firework.isHandicAccess(),
                        firework.getDuration(), firework.getCrowded());
                RelativeLayout relativeLayout = findViewById(R.id.parent);
                Snackbar.make(relativeLayout, "Feux d'artifice modifié !", Snackbar.LENGTH_LONG).show();
                finish();
            }
        });
    }
    public void ButtonReport() {
        findViewById(R.id.report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                intent.putExtra(EditFireworkActivity.FIREWORK_REPORT_ID, firework.getId());
                getApplicationContext().startActivity(intent);
            }
        });
    }


    public void ButtonAddParking() {
        findViewById(R.id.buttonAddParking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddParkingActivity.class);
                intent.putExtra(AddParkingActivity.MSG_ID_FIREWORK, firework.getId());
                startActivityForResult(intent, 1);
            }
        });
    }

    public void ButtonBack(){
        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
