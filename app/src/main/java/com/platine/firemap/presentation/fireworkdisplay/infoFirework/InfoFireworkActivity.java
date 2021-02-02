package com.platine.firemap.presentation.fireworkdisplay.infoFirework;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.presentation.Ressources.Utils;
import com.platine.firemap.presentation.fireworkdisplay.editFirework.EditFireworkActivity;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.parking.adapter.ParkingViewAdapter;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.parking.mapper.ParkingToParkingViewItemMapper;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.ProfileFireworkerActivity;
import com.platine.firemap.presentation.viewmodel.FavoriteViewModel;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class InfoFireworkActivity extends AppCompatActivity implements InfoFireworkActionInterface {
    private static final String TAG = "InfoFireworkActivity";
    public static final String FIREWORK_MESSAGE = "FIREWORK";
    private FavoriteViewModel fireworkFavoriteViewModel;
    private ParkingViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private ParkingToParkingViewItemMapper parkingToParkingViewItemMapper = new ParkingToParkingViewItemMapper();

    private ListViewModel listViewModel;
    private FireworkerViewModel fireworkerViewModel;
    private int fireworkId;
    private FireworkModel firework;
    private FireworkerDetail fireworker;

    private TextView textViewCity;
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
    
    private BottomNavigationView nav_info;
    private RelativeLayout layoutDescription;
    private RelativeLayout layoutInfo;
    private RelativeLayout layoutFireworker;
    private RelativeLayout layoutSignaler;

    private ImageView[] rateStars = new ImageView[5];




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artivity_info);
        fireworkerViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFireworkerFactory()).get(FireworkerViewModel.class);
        listViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        fireworkFavoriteViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFavoriteFactory()).get(FavoriteViewModel.class);
        nav_info = findViewById(R.id.nav_info);
        nav_info.setOnNavigationItemSelectedListener(navListerner);
        initFirework();
        initComponent();

        buttonBack();
        buttonEdit();
        buttonFav();
        buttonItinéraire();
        buttonProfilFireworker();
    }

    public void registerViewModel() {
        recyclerViewAdapter.bindViewModels(parkingToParkingViewItemMapper.map(firework.getParking()));
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewParking);
        recyclerViewAdapter = new ParkingViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void initComponent() {
        this.textViewCity = findViewById(R.id.city);
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
        this.layoutDescription = findViewById(R.id.layoutDescription);
        this.layoutInfo = findViewById(R.id.layoutInfo);
        this.layoutFireworker = findViewById(R.id.layoutFireworker);
        this.layoutSignaler = findViewById(R.id.layoutSignaler);
        rateStars[0] = findViewById(R.id.rate_star_one);
        rateStars[1] = findViewById(R.id.rate_star_two);
        rateStars[2] = findViewById(R.id.rate_star_three);
        rateStars[3] = findViewById(R.id.rate_star_four);
        rateStars[4] = findViewById(R.id.rate_star_five);
    }


    public void initFirework() {
        Intent intent = getIntent();
        this.fireworkId =  intent.getIntExtra(FIREWORK_MESSAGE, 1);
        listViewModel.getFireworkById(this.fireworkId).observe(this, new Observer<FireworkModel>() {
            @Override
            public void onChanged(FireworkModel fireworkModel) {
                firework = fireworkModel;
                Utils.setComponent(firework.getCity(), textViewCity, firework.getAddress(), textViewPlace,
                        firework.getDate(), textViewDate, firework.getPrice(), imagePrice, textViewPrice,
                        firework.isHandicAccess(), imageAccessHandicap, textViewAccessHandicap, firework.getDuration(), imageDuration, textViewDuration,
                        firework.getCrowded(), imagePeople, textViewPeople, firework.getParking(), imageParking);
                initFireworker();
                initParkings();
            }
        });
    }

    public void initFireworker() {
        fireworkerViewModel.getFireworkerById(firework.getIdFireworker()).observe(this, new Observer<FireworkerDetail>() {
            @Override
            public void onChanged(FireworkerDetail fireworkerDetail) {
                fireworker = fireworkerDetail;
                textViewFireworker.setText(fireworkerDetail.getName());
                for(int i =0; i<5 ;i++){
                    if(fireworkerDetail.getNote()<i+0.25){
                        rateStars[i].setImageResource(R.drawable.rate_star_big_off_holo_dark);
                    }else if(fireworkerDetail.getNote()>i+0.75){
                        rateStars[i].setImageResource(R.drawable.rate_star_big_on_holo_dark);
                    }else {
                        rateStars[i].setImageResource(R.drawable.rate_star_big_half_holo_dark);
                    }
                }
            }
        });
    }

    public void initParkings() {
        setupRecyclerView();
        registerViewModel();
    }


    @Override
    public void buttonBack() {
        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void buttonEdit() {
        findViewById(R.id.button_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditFireworkActivity.class);
                intent.putExtra(InfoFireworkActivity.FIREWORK_MESSAGE, (Serializable)firework);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void buttonFav() {
        findViewById(R.id.button_fav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireworkEntity fireworkEntity = new FireworkEntity();
                fireworkEntity.setId(firework.getId());
                fireworkFavoriteViewModel.addFireworkToFavorite(fireworkEntity);
                RelativeLayout scrollView = findViewById(R.id.parent);
                Snackbar.make(scrollView, "Ajout aux favoris !", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void buttonProfilFireworker() {
        findViewById(R.id.button_fireworker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileFireworkerActivity.class);
                intent.putExtra(ProfileFireworkerActivity.FIREWORKER_MESSAGE, firework.getIdFireworker());
                startActivity(intent);
            }
        });
    }


    public void buttonItinéraire() {
        this.findViewById(R.id.itinéraire).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "google.navigation:q="+firework.getLatitude()+","+firework.getLongitude()+"&mode=d";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                if(intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListerner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_description:
                            layoutDescription.setVisibility(View.VISIBLE);
                            layoutInfo.setVisibility(View.GONE);
                            layoutFireworker.setVisibility(View.GONE);
                            layoutSignaler.setVisibility(View.GONE);
                            break;
                        case R.id.nav_info:
                            layoutDescription.setVisibility(View.GONE);
                            layoutInfo.setVisibility(View.VISIBLE);
                            layoutFireworker.setVisibility(View.GONE);
                            layoutSignaler.setVisibility(View.GONE);
                            break;
                        case R.id.nav_fireworker:
                            layoutDescription.setVisibility(View.GONE);
                            layoutInfo.setVisibility(View.GONE);
                            layoutFireworker.setVisibility(View.VISIBLE);
                            layoutSignaler.setVisibility(View.GONE);
                            break;
                        case R.id.nav_signaler:
                            layoutDescription.setVisibility(View.GONE);
                            layoutFireworker.setVisibility(View.GONE);
                            layoutInfo.setVisibility(View.GONE);
                            layoutSignaler.setVisibility(View.VISIBLE);
                            break;
                    }
                    return true;
                }
            };


    @Override
    protected void onResume() {
        super.onResume();
        initFirework();
    }

}
