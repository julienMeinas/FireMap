package com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.addAvis.AddAvis;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.info.fragment.InfoFragment;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.adapter.FireworkerAvisAdapter;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.mapper.AvisToViewItemMapper;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;

public class AvisFragment extends Fragment {
    private static AvisFragment instance = null;
    private View view;

    private FireworkerDetail fireworkerDetailId;

    private FireworkerDetail fireworkerDetail;

    private ImageView[] rateStars = new ImageView[5];

    private RecyclerView recyclerView;
    private FireworkerAvisAdapter fireworkerAvisAdapter;

    private FireworkerViewModel fireworkerViewModel;

    public AvisFragment() {
        // Required empty public constructor
    }

    public static AvisFragment newInstance() {
        if(instance == null) {
            instance = new AvisFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.profile_fragment_avis, container, false);
        fireworkerDetailId = (FireworkerDetail) getArguments().getSerializable("fireworker");
        fireworkerViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFireworkerFactory()).get(FireworkerViewModel.class);
        initElementsLayout();
        buttonAddAvis();
        return view;
    }

    /**
     * initialise les objets avec les élements du layout
     */
    public void initElementsLayout() {
        //favorite binding
        rateStars[0] = this.view.findViewById(R.id.rate_star_one);
        rateStars[1] = this.view.findViewById(R.id.rate_star_two);
        rateStars[2] = this.view.findViewById(R.id.rate_star_three);
        rateStars[3] = this.view.findViewById(R.id.rate_star_four);
        rateStars[4] = this.view.findViewById(R.id.rate_star_five);
    }


    public void initDateInfoFireworker() {
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

    private void setupRecyclerView() {
        recyclerView = this.view.findViewById(R.id.recycler_view);
        fireworkerAvisAdapter = new FireworkerAvisAdapter();
        recyclerView.setAdapter(fireworkerAvisAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void registerViewModels(){
        AvisToViewItemMapper avisToViewItemMapper = new AvisToViewItemMapper();
        fireworkerAvisAdapter.bindViewModels(avisToViewItemMapper.map(fireworkerDetail.getAvis()));
    }

    public void buttonAddAvis() {
        this.view.findViewById(R.id.addAvis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddAvis.class);
                intent.putExtra(AddAvis.FIREWORKER_ID_MSG, fireworkerDetail.getId());
                startActivityForResult(intent, 1);
            }
        });
    }


    public void initFireworker() {
        fireworkerViewModel.getFireworkerById(fireworkerDetailId.getId()).observe(getViewLifecycleOwner(), new Observer<FireworkerDetail>() {
            @Override
            public void onChanged(FireworkerDetail fireworker) {
                fireworkerDetail = fireworker;
                initDateInfoFireworker();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        initFireworker();
    }
}
