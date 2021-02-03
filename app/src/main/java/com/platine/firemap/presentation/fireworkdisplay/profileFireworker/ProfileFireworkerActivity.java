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
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.fragment.AvisFragment;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.mapper.AvisToViewItemMapper;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.fireworks.fragment.FireworkFragment;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.profile.fragment.ProfileFragment;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfileFireworkerActivity extends AppCompatActivity implements ProfileFireworkerAcionInterface {
    private static final String TAG = "ProfileFireworkerActivity";
    public static final String FIREWORKER_MESSAGE = "FIREWORKER";
    private FireworkerViewModel fireworkerModelFactory;
    private FireworkerDetail fireworkerDetail;
    private int id;
    private TextView name;

    private BottomNavigationView m_BottomNav;
    public static final List<Fragment> m_listFragment = new ArrayList<Fragment>() {{
        add(ProfileFragment.newInstance());
        add(AvisFragment.newInstance());
        add(FireworkFragment.newInstance());
    }};
    private static final int positionProfileFragment = 0;
    private static final int positionAvisFragment = 1;
    private static final int positionFireworkFragment = 2;
    public int m_currentFragment = positionProfileFragment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_fireworker);
        m_BottomNav = findViewById(R.id.nav_profile_fireworker);
        m_BottomNav.setOnNavigationItemSelectedListener(navListerner);
        Intent intent = getIntent();
        this.id = intent.getIntExtra(ProfileFireworkerActivity.FIREWORKER_MESSAGE, 1);
        initFireworker();
        buttonBack();
    }



    public void initFireworker() {
        fireworkerModelFactory = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFireworkerFactory()).get(FireworkerViewModel.class);
        fireworkerModelFactory.getFireworkerById(id).observe(this, new Observer<FireworkerDetail>() {
            @Override
            public void onChanged(FireworkerDetail fireworker) {
                fireworkerDetail = fireworker;
                createProfileFireworker();
                sendData();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter,
                        m_listFragment.get(m_currentFragment)).commit();
            }
        });
    }

    public void createProfileFireworker() {
        name = findViewById(R.id.name);

        name.setText(fireworkerDetail.getName());

    }

    public void buttonBack() {
        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListerner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_profile:
                            m_currentFragment = positionProfileFragment;
                            break;
                        case R.id.nav_avis:
                            m_currentFragment = positionAvisFragment;
                            break;
                        case R.id.nav_ownFirework:
                            m_currentFragment = positionFireworkFragment;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, m_listFragment.get(m_currentFragment)).commit();
                    return true;
                }
            };


    public void sendData() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("fireworker", (Serializable)fireworkerDetail);
        for(Fragment f : m_listFragment) {
            f.setArguments(bundle);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        initFireworker();
    }

}
