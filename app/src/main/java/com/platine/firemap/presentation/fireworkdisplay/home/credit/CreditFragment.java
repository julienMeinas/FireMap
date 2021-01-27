package com.platine.firemap.presentation.fireworkdisplay.home.credit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.platine.firemap.R;

public class CreditFragment extends Fragment {
    private static CreditFragment instance;
    private View view;

    public CreditFragment() {
    }

    public static CreditFragment newInstance() {
        if(instance == null) {
            instance = new CreditFragment();
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
        view = inflater.inflate(R.layout.fragment_credit, container, false);
        return view;
    }


}
