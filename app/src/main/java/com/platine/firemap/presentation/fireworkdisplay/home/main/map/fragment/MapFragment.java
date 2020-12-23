package com.platine.firemap.presentation.fireworkdisplay.home.main.map.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.platine.firemap.R;

public class MapFragment extends Fragment {
    public static final String TAB_NAME = "Map";
    private static MapFragment instance;
    private View view;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        if(instance == null) {
            instance = new MapFragment();
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
        view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
