package com.platine.firemap.presentation.fireworkdisplay.infoFirework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.presentation.fireworkdisplay.editFirework.EditFireworkActivity;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.ProfileFireworkerActivity;
import com.platine.firemap.presentation.viewmodel.FavoriteViewModel;

import java.io.Serializable;
import java.util.List;

public class InfoFireworkActivity extends AppCompatActivity implements InfoFireworkActionInterface{
    private static final String TAG = "InfoFireworkActivity";
    public static final String FIREWORK_MESSAGE = "FIREWORK";
    private FavoriteViewModel fireworkFavoriteViewModel;

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
    private final String msg_duration_short = "Court";
    private final String msg_duration_middle = "Moyen";
    private final String msg_duration_long = "Long";
    private final String msg_crowed_low = "Peu de gens attendu";
    private final String msg_crowed_medium = "Moyennement de gens attendu";
    private final String msg_crowed_high = "Beaucoup de gens attendu";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artivity_info);
        fireworkFavoriteViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFavoriteFactory()).get(FavoriteViewModel.class);

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

        findViewById(R.id.button_fav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFav(firework.getId());
            }
        });

        findViewById(R.id.button_fireworker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfile(firework.getId());
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

    public void setComponent(String address, String date, int price, boolean accessHandicap, String duration, String crowed, List<Parking> parkings, Fireworker fireworker) {
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
        if(duration.equals("Short")){
            this.imageDuration.setImageResource(R.drawable.drawable_duration_short);
            this.textViewDuration.setText(msg_duration_short);
        }else if(duration.equals("Middle")){
            this.imageDuration.setImageResource(R.drawable.drawable_duration_middle);
            this.textViewDuration.setText(msg_duration_middle);
        }else{
            this.imageDuration.setImageResource(R.drawable.drawable_duration_long);
            this.textViewDuration.setText(msg_duration_long);
        }
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
        this.startActivityForResult(intent, 1);
    }

    @Override
    public void onFav(int id) {
        FireworkEntity fireworkEntity = new FireworkEntity();
        fireworkEntity.setId(id);
        fireworkFavoriteViewModel.addFireworkToFavorite(fireworkEntity);
    }

    @Override
    public void onProfile(int id) {
        Intent intent = new Intent(this, ProfileFireworkerActivity.class);
        intent.putExtra(ProfileFireworkerActivity.FIREWORKER_MESSAGE, id);
        this.startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                FireworkModel firework = (FireworkModel)data.getSerializableExtra("result");
                setComponent(firework.getAddress(), firework.getDate(), firework.getPrice(), firework.isHandicAccess(), firework.getDuration(),
                        firework.getCrowded(), firework.getParking(), firework.getFireworker());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }



}
