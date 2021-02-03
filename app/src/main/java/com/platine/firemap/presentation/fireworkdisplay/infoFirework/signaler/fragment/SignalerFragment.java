package com.platine.firemap.presentation.fireworkdisplay.infoFirework.signaler.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.platine.firemap.R;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.avis.fragment.AvisFragment;

public class SignalerFragment extends Fragment {
    private static SignalerFragment instance = null;
    private View view;

    public SignalerFragment() {
        // Required empty public constructor
    }

    public static SignalerFragment newInstance() {
        if(instance == null) {
            instance = new SignalerFragment();
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
        view = inflater.inflate(R.layout.info_fragment_signaler, container, false);
        return view;
    }
}
