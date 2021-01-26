package com.platine.firemap.presentation.fireworkdisplay.profileFireworker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.addAvis.AddAvis;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.adapter.FireworkerAvisAdapter;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.mapper.AvisToViewItemMapper;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;

public class ProfileFireworkerActivity extends AppCompatActivity implements ProfileFireworkerAcionInterface {
    private static final String TAG = "ProfileFireworkerActivity";
    public static final String FIREWORKER_MESSAGE = "FIREWORKER";
    private FireworkerViewModel fireworkerModelFactory;
    private FireworkerDetail fireworkerDetail;
    private int id;

    private TextView name;
    private TextView url;
    private ImageView[] rateStars = new ImageView[5];

    private RecyclerView recyclerView;
    private FireworkerAvisAdapter fireworkerAvisAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_fireworker);

        Intent intent = getIntent();
        this.id = intent.getIntExtra(ProfileFireworkerActivity.FIREWORKER_MESSAGE, 1);

        initFireworker();

        ButtonBack();
        ButtonAddAvis();
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
        name.setText(fireworkerDetail.getName());

        url = findViewById(R.id.url);
        url.setText(fireworkerDetail.getUrlPage());

        //favorite binding
        rateStars[0] = findViewById(R.id.rate_star_one);
        rateStars[1] = findViewById(R.id.rate_star_two);
        rateStars[2] = findViewById(R.id.rate_star_three);
        rateStars[3] = findViewById(R.id.rate_star_four);
        rateStars[4] = findViewById(R.id.rate_star_five);

        for(int i =0; i<5 ;i++){
            if(fireworkerDetail.getNote()<i+1.25){
                rateStars[i].setImageResource(R.drawable.rate_star_big_off_holo_dark);
            }else if(fireworkerDetail.getNote()>i+1.75){
                rateStars[i].setImageResource(R.drawable.rate_star_big_on_holo_dark);
            }else {
                rateStars[i].setImageResource(R.drawable.rate_star_big_half_holo_dark);
            }
        }

        setupRecyclerView();
        registerViewModels();
    }

    public void ButtonBack() {
        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicOnBack();
            }
        });
    }


    public void ButtonAddAvis() {
        findViewById(R.id.addAvis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicOnAddAvis(id);
            }
        });
    }

    @Override
    public void clicOnBack() {
        finish();
    }

    @Override
    public void clicOnAddAvis(int id) {
        Intent intent = new Intent(this, AddAvis.class);
        intent.putExtra(AddAvis.FIREWORKER_ID_MSG, id);
        this.startActivity(intent);
    }
}
