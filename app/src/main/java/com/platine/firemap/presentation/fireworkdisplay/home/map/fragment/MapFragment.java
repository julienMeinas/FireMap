package com.platine.firemap.presentation.fireworkdisplay.home.map.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;
import com.platine.firemap.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkViewItem;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.util.List;

public class MapFragment extends Fragment {
    public static final String TAB_NAME = "Map";
    private static MapFragment instance;
    private View view;
    private SupportMapFragment mSupportMapFragment;
    private ListViewModel listViewModel;
    private GoogleMap map;
    private List<FireworkViewItem> fireworks;

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
        setupMap();
        return view;
    }


    public void setupMap() {
        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapwhere);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapwhere, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;
                    if (map != null) {
                        map.getUiSettings().setAllGesturesEnabled(true);
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(50.609091, 3.142121)).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        map.moveCamera(cameraUpdate);
                        addMarker(50.609091, 3.142121, "Lille 1");
                        addMarkers();
                        onClickMap();
                    }
                }
            });
        }

    }

    public void addMarker(double latitude, double longitude, String title) {
        map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                .title(title));
    }


    public void addMarkers() {
        listViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        listViewModel.getFireworks().observe(getViewLifecycleOwner(), new Observer<List<FireworkViewItem>>() {
            @Override
            public void onChanged(List<FireworkViewItem> fireworkViewItems) {
                fireworks = fireworkViewItems;
                for(FireworkViewItem fireworkViewItem : fireworks) {
                    addMarker(fireworkViewItem.getLatitude(), fireworkViewItem.getLongitude(), fireworkViewItem.getAddress());
                }
            }
        });
    }


    public void onClickMap() {
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d(TAB_NAME, "Click on marker " + marker.getTitle());
                return false;
            }
        });
    }






}
