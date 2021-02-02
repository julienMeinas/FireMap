package com.platine.firemap.presentation.fireworkdisplay.profileFireworker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.addAvis.AddAvis;
import com.platine.firemap.presentation.fireworkdisplay.home.credit.CreditFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.fragment.FavoriteFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.list.fragment.ListFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.map.fragment.MapFragment;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.adapter.FireworkerAvisAdapter;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.mapper.AvisToViewItemMapper;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFireworkerActivity extends AppCompatActivity implements ProfileFireworkerAcionInterface {
    private static final String TAG = "ProfileFireworkerActivity";
    public static final String FIREWORKER_MESSAGE = "FIREWORKER";
    private FireworkerViewModel fireworkerModelFactory;
    private FireworkerDetail fireworkerDetail;
    private int id;
    private TextView name;
    private TextView littleName;
    private TextView mail;
    private TextView phone;
    private TextView address;
    private TextView url;
    private ImageView[] rateStars = new ImageView[5];

    private RecyclerView recyclerView;
    private FireworkerAvisAdapter fireworkerAvisAdapter;
    private BottomNavigationView m_BottomNav;
    private RelativeLayout layoutProfile;
    private RelativeLayout layoutAvis;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_fireworker);
        m_BottomNav = findViewById(R.id.nav_profile_fireworker);
        m_BottomNav.setOnNavigationItemSelectedListener(navListerner);
        Intent intent = getIntent();
        this.id = intent.getIntExtra(ProfileFireworkerActivity.FIREWORKER_MESSAGE, 1);
        layoutProfile = findViewById(R.id.layoutProfile);
        layoutAvis = findViewById(R.id.layoutAvis);
        initFireworker();
        buttonBack();
        buttonAddAvis();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        fireworkerAvisAdapter = new FireworkerAvisAdapter();
        recyclerView.setAdapter(fireworkerAvisAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void registerViewModels(){
        AvisToViewItemMapper avisToViewItemMapper = new AvisToViewItemMapper();
        fireworkerAvisAdapter.bindViewModels(avisToViewItemMapper.map(fireworkerDetail.getAvis()));
    }

    public void initFireworker() {
        fireworkerModelFactory = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFireworkerFactory()).get(FireworkerViewModel.class);
        fireworkerModelFactory.getFireworkerById(id).observe(this, new Observer<FireworkerDetail>() {
            @Override
            public void onChanged(FireworkerDetail fireworker) {
                fireworkerDetail = fireworker;
                createProfileFireworker();
            }
        });
    }

    public void createProfileFireworker() {
        name = findViewById(R.id.name);
        littleName = findViewById(R.id.littleName);
        address = findViewById(R.id.address);
        mail = findViewById(R.id.mail);
        phone = findViewById(R.id.phone);
        url = findViewById(R.id.link);

        name.setText(fireworkerDetail.getName());
        littleName.setText(fireworkerDetail.getName());
        address.setText(fireworkerDetail.getAddress());
        mail.setText(fireworkerDetail.getMail());
        phone.setText(fireworkerDetail.getTel());
        url.setText(fireworkerDetail.getUrlPage());

        //favorite binding
        rateStars[0] = findViewById(R.id.rate_star_one);
        rateStars[1] = findViewById(R.id.rate_star_two);
        rateStars[2] = findViewById(R.id.rate_star_three);
        rateStars[3] = findViewById(R.id.rate_star_four);
        rateStars[4] = findViewById(R.id.rate_star_five);

        for(int i =0; i<5 ;i++){
            if(fireworkerDetail.getNote()<i+0.25){
                rateStars[i].setImageResource(R.drawable.rate_star_big_off_holo_dark);
            }else if(fireworkerDetail.getNote()>i+0.75){
                rateStars[i].setImageResource(R.drawable.rate_star_big_on_holo_dark);
            }else {
                rateStars[i].setImageResource(R.drawable.rate_star_big_half_holo_dark);
            }
        }

        setupRecyclerView();
        registerViewModels();
    }

    public void buttonBack() {
        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void buttonAddAvis() {
        findViewById(R.id.addAvis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAvis.class);
                intent.putExtra(AddAvis.FIREWORKER_ID_MSG, id);
                startActivityForResult(intent, 1);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListerner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_profile:
                            layoutProfile.setVisibility(View.VISIBLE);
                            layoutAvis.setVisibility(View.GONE);
                            break;
                        case R.id.nav_avis:
                            layoutProfile.setVisibility(View.GONE);
                            layoutAvis.setVisibility(View.VISIBLE);
                            break;
                    }
                    return true;
                }
            };


    @Override
    protected void onResume() {
        super.onResume();
        initFireworker();
    }

}
