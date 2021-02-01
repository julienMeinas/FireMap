package com.platine.firemap.presentation.fireworkdisplay.addFirework;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.Ressources.Button;
import com.platine.firemap.presentation.Ressources.Utils;
import com.platine.firemap.presentation.Ressources.Validation;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.fireworker.adapter.AddActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.fireworker.adapter.Fireworker_item;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.fireworker.adapter.RecyclerViewAdapter;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.ProfileFireworkerActivity;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddFireworkActivity extends AppCompatActivity  implements AddActionInterface {
    private static final String TAG = "AddFireworkActivity";
    private FireworkModel firework;
    private ListViewModel fireworkListViewModel;
    private FireworkerViewModel fireworkerViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    // icons
    private TextView incorrect;
    private TextView errorCity;
    private TextView errorPlace;
    private TextView errorDate;
    private TextView errorHour;
    private TextView errorFireworker;
    private EditText city;
    private EditText place;
    private DatePicker date;
    private TimePicker hour;
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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.firework = new FireworkModel();
        initEmptyFirework(firework);
        initComponent();
        Button.ButtonPrice(buttonPriceFree, buttonPriceNotFree, imagePrice, firework);
        Button.ButtonAccessHandicap(buttonAccessHandicap, buttonNotAccessHandicap, imageAccessHandicap, firework);
        Button.ButtonCrowed(buttonCrowedLow, buttonCrowedMedium, buttonCrowedHigh, imagePeople, firework);
        Button.ButtonDuration(buttonDurationShort, buttonDurationMiddle, buttonDurationLong, imageDuration, firework);
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
        firework.setDate(null);
        firework.setPrice(50000);
        firework.setHandicAccess(true);
        firework.setParking(new ArrayList<>());
        firework.setDuration("");
        firework.setCrowded("");
        firework.setFireworker(new ArrayList<>());
    }



    public void initComponent() {
        this.incorrect = findViewById(R.id.incorrect);
        this.errorCity = findViewById(R.id.errorCity);
        this.errorPlace = findViewById(R.id.errorPlace);
        this.errorDate = findViewById(R.id.errorDate);
        this.errorHour = findViewById(R.id.errorHour);
        this.errorFireworker = findViewById(R.id.errorFireworker);
        this.errorCity = findViewById(R.id.errorCity);
        this.city = findViewById(R.id.city);
        this.place = findViewById(R.id.place);
        this.date = findViewById(R.id.datePicker);
        this.hour = findViewById(R.id.hourPicker);
        this.hour.setIs24HourView(true);
        this.imagePrice = findViewById(R.id.price);
        this.imageParking = findViewById(R.id.parking);
        this.imageAccessHandicap = findViewById(R.id.accessHandicap);
        this.imageDuration = findViewById(R.id.duration);
        this.imagePeople = findViewById(R.id.people);
        this.fireworkerName = findViewById(R.id.fireworkerName);

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
        //

    }



    public void ButtonValidation() {
        findViewById(R.id.validation).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                reinitializeError();
                getLocationFromAddress(getApplicationContext(), place.getText().toString() + ", " + city.getText().toString());
                if(validFirework()){
                    firework.setCity(city.getText().toString());
                    firework.setAddress(place.getText().toString());
                    firework.setDate(pickerToDate());
                    fireworkListViewModel.addFirework(firework);
                    finish();
                }
                else {
                    incorrect.setVisibility(View.VISIBLE);
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

    @Override
    public void onClickInfo(int fireworkerId) {
        Intent intent = new Intent(getApplicationContext(), ProfileFireworkerActivity.class);
        intent.putExtra(ProfileFireworkerActivity.FIREWORKER_MESSAGE, fireworkerId);
        startActivity(intent);
    }

    public void getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if(address.size()>0) {
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());

                firework.setLatitude(p1.latitude);
                firework.setLongitude(p1.longitude);
            }else {
                firework.setLatitude(0);
                firework.setLongitude(0);
            }

        } catch (IOException ex) {
            // incorrect address
            firework.setLatitude(0);
            firework.setLongitude(0);
        }

    }



    public boolean validFirework() {
        if(!Validation.validAddress(city.getText().toString())){
            DisplayErrorCity();
            return false;
        }
        if(!Validation.validAddress(place.getText().toString())){
            DisplayErrorPlace();
            return false;
        }
        if(!Validation.validFireworker(firework.getFireworker())){
            DisplayErrorFireworker();
            return false;
        }
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public String pickerToDate() {
        String day;
        String month;
        if(date.getDayOfMonth() < 10) {
            day = "0"+String.valueOf(date.getDayOfMonth());
        }else{
            day = String.valueOf(date.getDayOfMonth());
        }
        if(date.getMonth() < 10){
            month = "0" + String.valueOf(date.getMonth());
        }else {
            month = String.valueOf(date.getMonth());
        }
        String year = String.valueOf(date.getYear());
        String h = String.valueOf(hour.getHour());
        String m = String.valueOf(hour.getMinute());
        return year+"-"+month+"-"+day+"T"+h+":"+m+".000+00:00";
    }


    public void reinitializeError(){
        errorCity.setVisibility(View.GONE);
        errorPlace.setVisibility(View.GONE);
        errorDate.setVisibility(View.GONE);
        errorHour.setVisibility(View.GONE);
        errorFireworker.setVisibility(View.GONE);
    }


    public void DisplayErrorCity() {
        errorCity.setVisibility(View.VISIBLE);
    }

    public void DisplayErrorPlace() {
        errorPlace.setVisibility(View.VISIBLE);
    }

    public void DisplayErrorDate() {
        errorDate.setVisibility(View.VISIBLE);
    }

    public void DisplayErrorHour() {
        errorHour.setVisibility(View.VISIBLE);
    }

    public void DisplayErrorFireworker() {
        errorFireworker.setVisibility(View.VISIBLE);
    }
}
