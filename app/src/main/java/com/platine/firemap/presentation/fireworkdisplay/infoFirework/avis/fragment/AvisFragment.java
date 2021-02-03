package com.platine.firemap.presentation.fireworkdisplay.infoFirework.avis.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.fireworker.Avis;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.presentation.fireworkdisplay.addAvisFirework.AddAvis;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.fireworker.fragment.FireworkerFragment;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.adapter.FireworkerAvisAdapter;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.mapper.AvisToViewItemMapper;

public class AvisFragment extends Fragment {
    private static AvisFragment instance = null;
    private View view;

    // le feu d'artifice
    private FireworkModel firework;
    // data
    // star
    private ImageView[] rateStarsFirework = new ImageView[5];
    // bouton pour ajouter un avis à un feu
    private Button addAvisFirework;
    // afficher les avis
    private RecyclerView recyclerViewAvis;
    private FireworkerAvisAdapter fireworkAvisAdapter;
    // message si aucun avis
    private TextView avisEmpty;




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
        // initialise les elements du layout
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.info_fragment_avis, container, false);
        firework = (FireworkModel)getArguments().getSerializable("firework");
        // initialise les elements du layout
        initElementsLayout();
        initDataAvisFirework();
        buttonAddAvis();
        return view;
    }

    public void initElementsLayout() {
        rateStarsFirework[0] = this.view.findViewById(R.id.rate_star_oneFirework);
        rateStarsFirework[1] = this.view.findViewById(R.id.rate_star_twoFirework);
        rateStarsFirework[2] = this.view.findViewById(R.id.rate_star_threeFirework);
        rateStarsFirework[3] = this.view.findViewById(R.id.rate_star_fourFirework);
        rateStarsFirework[4] = this.view.findViewById(R.id.rate_star_fiveFirework);

        addAvisFirework = this.view.findViewById(R.id.addAvis);
        avisEmpty = this.view.findViewById(R.id.avisEmpty);
    }



    public void initDataAvisFirework() {
        for(int i =0; i<5 ;i++){
            if(firework.getNote()<i+0.25){
                rateStarsFirework[i].setImageResource(R.drawable.rate_star_big_off_holo_dark);
            }else if(firework.getNote()>i+0.75){
                rateStarsFirework[i].setImageResource(R.drawable.rate_star_big_on_holo_dark);
            }else {
                rateStarsFirework[i].setImageResource(R.drawable.rate_star_big_half_holo_dark);
            }
        }
        setupRecyclerViewAvis();
        registerViewModelsAvis();
    }

    private void setupRecyclerViewAvis() {
        recyclerViewAvis = this.view.findViewById(R.id.recycler_view);
        fireworkAvisAdapter = new FireworkerAvisAdapter();
        recyclerViewAvis.setAdapter(fireworkAvisAdapter);
        recyclerViewAvis.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void registerViewModelsAvis(){
        AvisToViewItemMapper avisToViewItemMapper = new AvisToViewItemMapper();
        fireworkAvisAdapter.bindViewModels(avisToViewItemMapper.map(firework.getAvis()));
        if(firework.getAvis().isEmpty()){
            avisEmpty.setVisibility(View.VISIBLE);
        }else {
            avisEmpty.setVisibility(View.GONE);
        }
    }

    public void buttonAddAvis() {
        this.addAvisFirework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddAvis.class);
                intent.putExtra(com.platine.firemap.presentation.fireworkdisplay.addAvisFirework.AddAvis.FIREWORK_ID_MSG, firework.getId());
                startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        initDataAvisFirework();
    }
}
