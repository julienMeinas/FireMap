package com.platine.firemap.presentation.fireworkdisplay.infoFirework;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.google.android.material.snackbar.Snackbar;
import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.presentation.Ressources.Utils;
import com.platine.firemap.presentation.fireworkdisplay.addAvisFirework.AddAvis;
import com.platine.firemap.presentation.fireworkdisplay.editFirework.EditFireworkActivity;
import com.platine.firemap.presentation.fireworkdisplay.home.credit.CreditFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.fragment.FavoriteFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.list.fragment.ListFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.map.fragment.MapFragment;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.avis.fragment.AvisFragment;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.description.fragment.DescriptionFragment;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.fireworker.fragment.FireworkerFragment;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.info.fragment.InfoFragment;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.signaler.fragment.SignalerFragment;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.ProfileFireworkerActivity;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.adapter.FireworkerAvisAdapter;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.mapper.AvisToViewItemMapper;
import com.platine.firemap.presentation.viewmodel.FavoriteViewModel;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InfoFireworkActivity extends AppCompatActivity implements InfoFireworkActionInterface {
    private static final String TAG = "InfoFireworkActivity";
    public static final String FIREWORK_MESSAGE = "FIREWORK";
    private FavoriteViewModel fireworkFavoriteViewModel;

    private ListViewModel listViewModel;
    private FireworkerViewModel fireworkerViewModel;
    private int fireworkId;
    private FireworkModel firework;
    private FireworkerDetail fireworker;

    private TextView textViewCity;
    private TextView textViewPlace;
    private TextView textViewDate;
    
    private BottomNavigationView nav_info;
    public static final List<Fragment> m_listFragment = new ArrayList<Fragment>() {{
        add(DescriptionFragment.newInstance());
        add(InfoFragment.newInstance());
        add(FireworkerFragment.newInstance());
        add(AvisFragment.newInstance());
        add(SignalerFragment.newInstance());
    }};
    private static final int positionDescriptionFragment = 0;
    private static final int positionInfoFragment = 1;
    private static final int positionFireworkerFragment = 2;
    private static final int positionAvisFragment = 3;
    private static final int positionSignalerFragment = 4;
    public int m_currentFragment = positionDescriptionFragment;


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
        buttonFav();
        buttonItinéraire();
        if(savedInstanceState != null) {
            m_currentFragment = savedInstanceState.getInt("currentPositionFragment");
        }
        else {
            m_currentFragment = positionDescriptionFragment;
        }
    }


    public void initComponent() {
        this.textViewCity = findViewById(R.id.city);
        this.textViewPlace = findViewById(R.id.lieu);
        this.textViewDate = findViewById(R.id.date);
    }

    public void initDataFirework() {
        textViewCity.setText(firework.getCity());
        textViewPlace.setText(firework.getAddress());
        textViewDate.setText(Utils.convertJsonToStringDate(firework.getDate()));
    }

    public void initFragment() {

    }


    public void initFirework() {
        Intent intent = getIntent();
        this.fireworkId =  intent.getIntExtra(FIREWORK_MESSAGE, 1);
        listViewModel.getFireworkById(this.fireworkId).observe(this, new Observer<FireworkModel>() {
            @Override
            public void onChanged(FireworkModel fireworkModel) {
                firework = fireworkModel;
                initDataFirework();
                initFireworker();
            }
        });
    }

    public void initFireworker() {
        fireworkerViewModel.getFireworkerById(firework.getIdFireworker()).observe(this, new Observer<FireworkerDetail>() {
            @Override
            public void onChanged(FireworkerDetail fireworkerDetail) {
                fireworker = fireworkerDetail;
                sendData();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter,
                        m_listFragment.get(m_currentFragment)).commit();
            }
        });
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
                            m_currentFragment = positionDescriptionFragment;
                            break;
                        case R.id.nav_info:
                            m_currentFragment = positionInfoFragment;
                            break;
                        case R.id.nav_fireworker:
                            m_currentFragment = positionFireworkerFragment;
                            break;
                        case R.id.nav_avis:
                            m_currentFragment = positionAvisFragment;
                            break;
                        case R.id.nav_signaler:
                            m_currentFragment = positionSignalerFragment;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, m_listFragment.get(m_currentFragment)).commit();
                    return true;
                }
            };


    public void sendData() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("firework", (Serializable)firework);
        bundle.putSerializable("fireworker", (Serializable)fireworker);
        for(Fragment f : m_listFragment) {
            f.setArguments(bundle);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        initFirework();
    }


}
