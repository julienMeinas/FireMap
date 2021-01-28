package com.platine.firemap.presentation.fireworkdisplay.editFirework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.di.FakeDependencyInjection;
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



    private final String msg_price_free = "Gratuit";
    private final String msg_price_not_free = "Payant";
    private final String msg_no_parking = "Pas de parking";
    private final String msg_parking_free = "Parking gratuit";
    private final String msg_parking_no_free = "Parking payant";
    private final String msg_access_handicap = "Accès handicapé";
    private final String msg_no_access_handicap = "Pas d'accès handicapé";
    private final String msg_duration_short = "Court";
    private final String msg_duration_middle = "Moyen";
    private final String msg_duration_long = "Long";
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
        Utils.setComponent(this.firework.getCity(), this.textViewCity, this.firework.getAddress(), this.textViewPlace,
                           this.firework.getDate(), this.textViewDate, this.firework.getPrice(), this.imagePrice,
                           this.firework.isHandicAccess(), this.imageAccessHandicap, this.firework.getDuration(), this.imageDuration,
                           this.firework.getCrowded(), this.imagePeople, this.firework.getParking(), this.imageParking,
                           this.firework.getFireworker(), this.textViewFireworker);
        ButtonPrice();
        ButtonAccessHandicap();
        ButtonCrowed();
        ButtonValidation();
        ButtonReport();
        ButtonDuration();
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
        this.buttonPriceFree.setText(msg_price_free);
        this.buttonPriceNotFree = findViewById(R.id.buttonPriceNotFree);
        this.buttonPriceNotFree.setText(msg_price_not_free);
        // handicap
        this.buttonAccessHandicap = findViewById(R.id.buttonAccessHandicap);
        this.buttonAccessHandicap.setText(msg_access_handicap);
        this.buttonNotAccessHandicap = findViewById(R.id.buttonNotAccessHandicap);
        this.buttonNotAccessHandicap.setText(msg_no_access_handicap);
        // duration
        this.buttonDurationShort = findViewById(R.id.button_duration_short);
        this.buttonDurationShort.setText(msg_duration_short);
        this.buttonDurationMiddle = findViewById(R.id.button_duration_middle);
        this.buttonDurationMiddle.setText(msg_duration_middle);
        this.buttonDurationLong = findViewById(R.id.button_duration_long);
        this.buttonDurationLong.setText(msg_duration_long);
        // people
        this.buttonCrowedLow = findViewById(R.id.buttonCrowedLow);
        this.buttonCrowedLow.setText(msg_crowed_low);
        this.buttonCrowedMedium = findViewById(R.id.buttonCrowedMedium);
        this.buttonCrowedMedium.setText(msg_crowed_medium);
        this.buttonCrowedHigh = findViewById(R.id.buttonCrowedHigh);
        this.buttonCrowedHigh.setText(msg_crowed_high);
        // duration

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

        imagePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePrice.setImageResource(R.drawable.drawable_empty_price);
                firework.setPrice(50000);
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

        imageAccessHandicap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageAccessHandicap.setImageResource(R.drawable.drawable_empty_accesshandicap);
                firework.setHandicAccess(true);
            }
        });
    }

    public void ButtonDuration() {
        this.buttonDurationShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDuration.setImageResource(R.drawable.drawable_duration_short);
                firework.setDuration("Short");
            }
        });

        this.buttonDurationMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDuration.setImageResource(R.drawable.drawable_duration_middle);
                firework.setDuration("Middle");
            }
        });

        this.buttonDurationLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDuration.setImageResource(R.drawable.drawable_duration_long);
                firework.setDuration("Long");
            }
        });

        imageDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDuration.setImageResource(R.drawable.drawable_empty_duration);
                firework.setDuration("");
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

        imagePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePeople.setImageResource(R.drawable.drawable_empty_people);
            }
        });
    }


    public void ButtonValidation() {
        findViewById(R.id.validation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireworkListViewModel.updateFirework(firework.getId(), firework.getPrice(), firework.isHandicAccess(),
                        firework.getDuration(), firework.getCrowded());
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
                Intent returnIntent = new Intent();
                returnIntent.putExtra("resultBack",(Serializable)firework);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }

}
