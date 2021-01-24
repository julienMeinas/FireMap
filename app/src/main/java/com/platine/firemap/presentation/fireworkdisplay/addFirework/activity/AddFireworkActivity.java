package com.platine.firemap.presentation.fireworkdisplay.addFirework.activity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter.AddActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter.Fireworker_item;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter.RecyclerViewAdapter;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkListAdapter;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddFireworkActivity extends AppCompatActivity  implements AddActionInterface {
    private static final String TAG = "AddFireworkActivity";
    private FireworkModel firework;
    private ListViewModel fireworkListViewModel;
    private FireworkerViewModel fireworkerViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    // icons
    private EditText city;
    private EditText place;
    private EditText date;
    private ImageView imagePrice;
    private ImageView imageParking;
    private ImageView imageAccessHandicap;
    private ImageView imageDuration;
    private ImageView imagePeople;
    private TextView fireworkerName;

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
        setContentView(R.layout.activity_add);

        this.firework = new FireworkModel();
        initEmptyFirework(firework);
        initComponent();
        ButtonPrice();
        ButtonAccessHandicap();
        ButtonCrowed();
        ButtonDuration();
        ButtonValidation();
        setupRecyclerView();
        registerViewModel();

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    public void registerViewModel() {
        fireworkListViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        fireworkerViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFireworkerFactory()).get(FireworkerViewModel.class);

        fireworkerViewModel.getFireworkers().observe(this, new Observer<List<Fireworker_item>>() {
            @Override
            public void onChanged(List<Fireworker_item> fireworker_items) {
                recyclerViewAdapter.bindViewModels(fireworker_items);
            }
        });
    }


    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewFireworker);
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void initEmptyFirework(FireworkModel firework) {
        firework.setCity("");
        firework.setAddress("");
        firework.setDate("");
        firework.setPrice(50000);
        firework.setParking(new ArrayList<>());
        firework.setDuration("");
        firework.setCrowded("");
        firework.setFireworker(new ArrayList<>());
    }



    public void initComponent() {
        this.city = findViewById(R.id.city);
        this.place = findViewById(R.id.place);
        this.date = findViewById(R.id.date);
        this.imagePrice = findViewById(R.id.price);
        this.imageParking = findViewById(R.id.parking);
        this.imageAccessHandicap = findViewById(R.id.accessHandicap);
        this.imageDuration = findViewById(R.id.duration);
        this.imagePeople = findViewById(R.id.people);
        this.fireworkerName = findViewById(R.id.fireworkerName);

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
        //

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

        imagePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePeople.setImageResource(R.drawable.drawable_empty_people);
                firework.setCrowded("");
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



    public void ButtonValidation() {
        findViewById(R.id.validation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validFirework()){
                    firework.setCity(city.getText().toString());
                    firework.setAddress(place.getText().toString());
                    LatLng marker = getLocationFromAddress(getApplicationContext(), place.getText().toString());
                    firework.setLatitude(marker.latitude);
                    firework.setLongitude(marker.longitude);
                    firework.setDate(date.getText().toString());
                    fireworkListViewModel.addFirework(firework);
                    finish();
                }
            }
        });
    }

    @Override
    public void selectFireworker(Fireworker fireworker) {
        this.fireworkerName.setText(fireworker.getName());
        List<Fireworker> fireworkers = new ArrayList<>();
        fireworkers.add(fireworker);
        firework.setFireworker(fireworkers);
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    public boolean validDate(String date) {
        if(date == null)
            return false;
        if(date.length() != 10)
            return false;
        if(! (date.substring(2,3).equals("/") && date.substring(5,6).equals("/")) )
            return false;
        if(Integer.parseInt(date.substring(0, 2)) < 0 && Integer.parseInt(date.substring(0, 2)) > 31)
            return false;
        if(Integer.parseInt(date.substring(3, 5)) < 0 && Integer.parseInt(date.substring(3, 5)) > 12)
            return false;
        return true;
    }


    public boolean validFirework() {
        return validDate(date.getText().toString()) &&
                place.getText().toString() != null &&
                firework.getFireworker().size() > 0;
    }
}
