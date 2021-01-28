package com.platine.firemap.presentation.fireworkdisplay.infoFirework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.presentation.Ressources.Utils;
import com.platine.firemap.presentation.fireworkdisplay.editFirework.EditFireworkActivity;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.parking.adapter.ParkingViewAdapter;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.parking.mapper.ParkingToParkingViewItemMapper;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.ProfileFireworkerActivity;
import com.platine.firemap.presentation.viewmodel.FavoriteViewModel;
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
    private int fireworkId;
    private FireworkModel firework;

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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artivity_info);
        listViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        fireworkFavoriteViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFavoriteFactory()).get(FavoriteViewModel.class);

        initFirework();
        initComponent();

        buttonBack();
        buttonEdit();
        buttonFav();
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
                        firework.getCrowded(), imagePeople, textViewPeople, firework.getParking(), imageParking,
                        firework.getFireworker(), textViewFireworker);
                initParkings();
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
            }
        });
    }

    @Override
    public void buttonProfilFireworker() {
        findViewById(R.id.button_fireworker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileFireworkerActivity.class);
                intent.putExtra(ProfileFireworkerActivity.FIREWORKER_MESSAGE, firework.getFireworker().get(0));
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        initFirework();
    }

}
