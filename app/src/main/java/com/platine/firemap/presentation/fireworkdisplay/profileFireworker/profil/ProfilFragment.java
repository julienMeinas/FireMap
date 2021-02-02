package com.platine.firemap.presentation.fireworkdisplay.profileFireworker.profil;

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
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.ProfileFireworkerActivity;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;

public class ProfilFragment extends Fragment {
    private static ProfilFragment instance = null;
    private FireworkerViewModel fireworkerModelFactory;
    private FireworkerDetail fireworkerDetail;
    private int id;
    private View view;
    private TextView url;
    private TextView tel;
    private TextView mail;
    private TextView address;
    private TextView description;

    public ProfilFragment(){
    }

    public static ProfilFragment newInstance() {
        if(instance == null){
            instance = new ProfilFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt(ProfileFireworkerActivity.ARGUMENT);
        initFireworker();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_profil, container, false);
        return view;
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

        url = this.view.findViewById(R.id.url);
        url.setText(fireworkerDetail.getUrlPage());


    }
}
