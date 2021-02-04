package com.platine.firemap.presentation.fireworkdisplay.profileFireworker.profile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.fragment.AvisFragment;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;

public class ProfileFragment extends Fragment {
    private static ProfileFragment instance = null;
    private View view;

    private FireworkerDetail fireworkerDetailId;

    private FireworkerDetail fireworkerDetail;

    private TextView littleName;
    private TextView mail;
    private TextView phone;
    private TextView address;
    private TextView url;

    private FireworkerViewModel fireworkerViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        if(instance == null) {
            instance = new ProfileFragment();
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
        view = inflater.inflate(R.layout.profile_fragment_profile, container, false);
        fireworkerDetailId = (FireworkerDetail) getArguments().getSerializable("fireworker");
        fireworkerViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFireworkerFactory()).get(FireworkerViewModel.class);
        initElementsLayout();
        initFireworker();
        return view;
    }

    /**
     * initialise les objets avec les élements du layout
     */
    public void initElementsLayout() {
        littleName = this.view.findViewById(R.id.littleName);
        address = this.view.findViewById(R.id.address);
        mail = this.view.findViewById(R.id.mail);
        phone = this.view.findViewById(R.id.phone);
        url = this.view.findViewById(R.id.link);
    }


    public void initDateInfoFireworker() {
        littleName.setText(fireworkerDetail.getName());
        address.setText(fireworkerDetail.getAddress());
        mail.setText(fireworkerDetail.getMail());
        phone.setText(fireworkerDetail.getTel());
        url.setText(fireworkerDetail.getUrlPage());
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
