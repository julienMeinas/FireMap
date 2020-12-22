package com.platine.firemap.presentation.fireworkdisplay.editFirework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;
import com.platine.firemap.presentation.viewmodel.FireworkListViewModel;

import java.io.Serializable;
import java.util.List;

public class EditFireworkActivity extends AppCompatActivity {
    private static final String TAG = "EditFireworkActivity";
    private FireworkModel firework;
    private FireworkListViewModel fireworkListViewModel;

    // icons
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
    private AppCompatButton buttonCrowedLow;
    private AppCompatButton buttonCrowedMedium;
    private AppCompatButton buttonCrowedHigh;
    private EditText editTextDuration;


    private final String msg_price_free = "Gratuit";
    private final String msg_price_not_free = "Payant";
    private final String msg_no_parking = "Pas de parking";
    private final String msg_parking_free = "Parking gratuit";
    private final String msg_parking_no_free = "Parking payant";
    private final String msg_access_handicap = "Accès handicapé";
    private final String msg_no_access_handicap = "Pas d'accès handicapé";
    private final String msg_duration = "Minutes";
    private final String msg_crowed_low = "Peu";
    private final String msg_crowed_medium = "Moyen";
    private final String msg_crowed_high = "Beaucoup";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        this.firework = (FireworkModel)intent.getSerializableExtra(InfoFireworkActivity.FIREWORK_MESSAGE);

        initComponent();
        setComponent(this.firework.getAddress(), this.firework.getDate(), this.firework.getPrice(), this.firework.isHandicAccess(), this.firework.getDuration(), this.firework.getCrowded(), this.firework.getParking(), this.firework.getFireworker());
        ButtonPrice();
        ButtonAccessHandicap();
        ButtonCrowed();
        ButtonValidation();

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        registerViewModel();
    }


    public void registerViewModel() {
        fireworkListViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(FireworkListViewModel.class);
    }



    public void initComponent() {
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
        this.buttonPriceFree.setText(msg_price_free);
        this.buttonPriceNotFree = findViewById(R.id.buttonPriceNotFree);
        this.buttonPriceNotFree.setText(msg_price_not_free);
        // handicap
        this.buttonAccessHandicap = findViewById(R.id.buttonAccessHandicap);
        this.buttonAccessHandicap.setText(msg_access_handicap);
        this.buttonNotAccessHandicap = findViewById(R.id.buttonNotAccessHandicap);
        this.buttonNotAccessHandicap.setText(msg_no_access_handicap);
        // people
        this.buttonCrowedLow = findViewById(R.id.buttonCrowedLow);
        this.buttonCrowedLow.setText(msg_crowed_low);
        this.buttonCrowedMedium = findViewById(R.id.buttonCrowedMedium);
        this.buttonCrowedMedium.setText(msg_crowed_medium);
        this.buttonCrowedHigh = findViewById(R.id.buttonCrowedHigh);
        this.buttonCrowedHigh.setText(msg_crowed_high);
        //
        this.editTextDuration = findViewById(R.id.editTextDuration);
        this.editTextDuration.setHint(String.valueOf(firework.getDuration()));
    }


    public void setComponent(String address, String date, int price, boolean accessHandicap, int duration, String crowed, List<Parking> parkings, Fireworker fireworker) {
        // address
        this.textViewPlace.setText(address);
        // date
        this.textViewDate.setText(date);
        //price
        this.imagePrice.setImageResource(price == 0 ? R.drawable.drawable_price_free : R.drawable.drawable_price_no_free);
        //parking
        this.imageParking.setImageResource(R.drawable.drawable_parking_free);
        // access handicap
        this.imageAccessHandicap.setImageResource(accessHandicap ? R.drawable.drawable_handicap_access : R.drawable.drawable_no_handicap_access);
        // crowed
        if(crowed.equals("Low")) {
            this.imagePeople.setImageResource(R.drawable.drawable_people_low);
        }else if(crowed.equals("Medium")) {
            this.imagePeople.setImageResource(R.drawable.drawable_people_medium);
        }else {
            this.imagePeople.setImageResource(R.drawable.drawable_people_high);
        }
        // parking
        if(parkings.size() == 0) {
            this.imageParking.setImageResource(R.drawable.drawable_no_parking);
        }else{
            boolean freeParking = false;
            for(Parking p : parkings) {
                if(p.getPrice() == 0){
                    this.imageParking.setImageResource(R.drawable.drawable_parking_free);
                    freeParking = true;
                }
            }
            if(!freeParking) {
                this.imageParking.setImageResource(R.drawable.drawable_parking_no_free);
            }
        }
        // fireworker
        this.textViewFireworker.setText(fireworker.getName());
    }



    public void ButtonPrice() {
        this.buttonPriceFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Bug", "click on button free");
                imagePrice.setImageResource(R.drawable.drawable_price_free);
                firework.setPrice(0);
            }
        });

        this.buttonPriceNotFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePrice.setImageResource(R.drawable.drawable_price_no_free);
                firework.setPrice(30);
            }
        });
    }

    public void ButtonAccessHandicap() {
        this.buttonAccessHandicap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageAccessHandicap.setImageResource(R.drawable.drawable_handicap_access);
                firework.setHandicAccess(true);
            }
        });

        this.buttonNotAccessHandicap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageAccessHandicap.setImageResource(R.drawable.drawable_no_handicap_access);
                firework.setHandicAccess(false);
            }
        });
    }


    public void ButtonCrowed() {
        this.buttonCrowedLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePeople.setImageResource(R.drawable.drawable_people_low);
                firework.setCrowded("Low");
            }
        });

        this.buttonCrowedMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePeople.setImageResource(R.drawable.drawable_people_medium);
                firework.setCrowded("Medium");
            }
        });

        this.buttonCrowedHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePeople.setImageResource(R.drawable.drawable_people_high);
                firework.setCrowded("High");
            }
        });
    }



    public void ButtonValidation() {
        findViewById(R.id.validation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireworkListViewModel.updateFirework(firework, firework.getId());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",(Serializable)firework);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }






}
