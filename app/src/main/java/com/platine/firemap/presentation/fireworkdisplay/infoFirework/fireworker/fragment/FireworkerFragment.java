package com.platine.firemap.presentation.fireworkdisplay.infoFirework.fireworker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.description.fragment.DescriptionFragment;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.ProfileFireworkerActivity;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;

public class FireworkerFragment extends Fragment {
    private static FireworkerFragment instance = null;
    private View view;

    private FireworkerDetail fireworkerDetailId;

    private FireworkerDetail fireworker;
    // data
    // star
    private ImageView[] rateStarsFireworker = new ImageView[5];
    // name
    private TextView nameFireworker;

    private FireworkerViewModel fireworkerViewModel;




    public FireworkerFragment() {
        // Required empty public constructor
    }

    public static FireworkerFragment newInstance() {
        if(instance == null) {
            instance = new FireworkerFragment();
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
        view = inflater.inflate(R.layout.info_fragment_fireworker, container, false);
        fireworkerDetailId = (FireworkerDetail) getArguments().getSerializable("fireworker");
        fireworkerViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFireworkerFactory()).get(FireworkerViewModel.class);
        // initialise les elements du layout
        initElementsLayout();
        // remplace avec les données de l'artificier
        initFireworker();
        // bouton pour aller au profil de l'artificier
        buttonProfilFireworker();
        return view;
    }

    /**
     * initialise les objets avec les élements du layout
     */
    public void initElementsLayout() {
        rateStarsFireworker[0] = this.view.findViewById(R.id.rate_star_one);
        rateStarsFireworker[1] = this.view.findViewById(R.id.rate_star_two);
        rateStarsFireworker[2] = this.view.findViewById(R.id.rate_star_three);
        rateStarsFireworker[3] = this.view.findViewById(R.id.rate_star_four);
        rateStarsFireworker[4] = this.view.findViewById(R.id.rate_star_five);
        nameFireworker = this.view.findViewById(R.id.fireworker);
    }


    public void initDateDescriptionFireworker() {
        for(int i =0; i<5 ;i++){
            if(fireworker.getNote()<i+0.25){
                rateStarsFireworker[i].setImageResource(R.drawable.rate_star_big_off_holo_dark);
            }else if(fireworker.getNote()>i+0.75){
                rateStarsFireworker[i].setImageResource(R.drawable.rate_star_big_on_holo_dark);
            }else {
                rateStarsFireworker[i].setImageResource(R.drawable.rate_star_big_half_holo_dark);
            }
        }
        nameFireworker.setText(fireworker.getName());
    }


    public void buttonProfilFireworker() {
        this.view.findViewById(R.id.button_fireworker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileFireworkerActivity.class);
                intent.putExtra(ProfileFireworkerActivity.FIREWORKER_MESSAGE, fireworker.getId());
                startActivity(intent);
            }
        });
    }


    public void initFireworker() {
        fireworkerViewModel.getFireworkerById(fireworkerDetailId.getId()).observe(getViewLifecycleOwner(), new Observer<FireworkerDetail>() {
            @Override
            public void onChanged(FireworkerDetail fireworkerDetail) {
                fireworker = fireworkerDetail;
                initDateDescriptionFireworker();
            }
        });
    }
}
