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
        setComponent(this.firework.getCity(), this.firework.getAddress(), this.firework.getDate(), this.firework.getPrice(), this.firework.isHandicAccess(), this.firework.getDuration(), this.firework.getCrowded(), this.firework.getParking(), this.firework.getFireworker().get(0));
        ButtonPrice();
        ButtonAccessHandicap();
        ButtonCrowed();
        ButtonValidation();
        ButtonReport();
        ButtonDuration();
        ButtonAddParking();

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        registerViewModel();
    }


    public void registerViewModel() {
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


    public void setComponent(String city, String address, String date, int price, boolean accessHandicap, String duration, String crowed, List<Parking> parkings, Fireworker fireworker) {
        this.textViewCity.setText(city);
        // address
        this.textViewPlace.setText(address);
        // date
        this.textViewDate.setText(date);
        //price
        if(price == 0) {
            this.imagePrice.setImageResource(R.drawable.drawable_price_free);
        } else if(price < 50000) {
            this.imagePrice.setImageResource(R.drawable.drawable_price_no_free);
        }
        else{
            this.imagePrice.setImageResource(R.drawable.drawable_empty_price);
        }
        //parking
        this.imageParking.setImageResource(R.drawable.drawable_parking_free);
        // access handicap
        this.imageAccessHandicap.setImageResource(accessHandicap ? R.drawable.drawable_handicap_access : R.drawable.drawable_no_handicap_access);
        // duration
        if(duration.equals("Short")){
            this.imageDuration.setImageResource(R.drawable.drawable_duration_short);
        }else if(duration.equals("Middle")){
            this.imageDuration.setImageResource(R.drawable.drawable_duration_middle);
        }else if (duration.equals("Long")){
            this.imageDuration.setImageResource(R.drawable.drawable_duration_long);
        } else {
            this.imageDuration.setImageResource(R.drawable.drawable_empty_duration);
        }
        // crowed
        if(crowed.equals("Low")) {
            this.imagePeople.setImageResource(R.drawable.drawable_people_low);
        }else if(crowed.equals("Medium")) {
            this.imagePeople.setImageResource(R.drawable.drawable_people_medium);
        }else if(crowed.equals("High")){
            this.imagePeople.setImageResource(R.drawable.drawable_people_high);
        } else {
            this.imagePeople.setImageResource(R.drawable.drawable_empty_people);
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
                onClickValidation();
            }
        });
    }
    public void ButtonReport() {
        findViewById(R.id.report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReport();
            }
        });
    }


    public void ButtonAddParking() {
        findViewById(R.id.buttonAddParking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClikAddParking();
            }
        });
    }


    @Override
    public void onClikAddParking() {
        Intent intent = new Intent(this, AddParkingActivity.class);
        intent.putExtra(AddParkingActivity.MSG_ID_FIREWORK, firework.getId());
        startActivityForResult(intent, 1);
    }

    @Override
    public void onClickValidation() {
        fireworkListViewModel.updateFirework(firework.getId(), firework.getPrice(), firework.isHandicAccess(),
                firework.getDuration(), firework.getCrowded());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",(Serializable)firework);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    public void onClickReport() {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra(EditFireworkActivity.FIREWORK_REPORT_ID, firework.getId());
        this.startActivity(intent);
    }

    public String mapDate(Date date) {
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);
        return shortDateFormat.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Parking parking = (Parking) data.getSerializableExtra("result");
                firework.getParking().add(parking);
                setComponent(firework.getCity(), firework.getAddress(), firework.getDate(), firework.getPrice(), firework.isHandicAccess(), firework.getDuration(),
                        firework.getCrowded(), firework.getParking(), firework.getFireworker().get(0));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
