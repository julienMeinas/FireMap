package com.platine.firemap.presentation.fireworkdisplay.home.map.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.AddFireworkActivity;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkViewItem;
import com.platine.firemap.presentation.fireworkdisplay.home.map.adapter.MapActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;
import com.platine.firemap.presentation.viewmodel.ListViewModel;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkViewItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapFragment extends Fragment implements MapActionInterface {
    public static final String TAB_NAME = "Map";
    private static MapFragment instance;
    private View view;
    private SearchView search;
    private Switch aSwitch;
    private Button filter;
    private SupportMapFragment mSupportMapFragment;
    private ListViewModel listViewModel;
    private GoogleMap map;
    private List<FireworkViewItem> fireworks;
    private HashMap<String, FireworkViewItem> markerMap;

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
        markerMap = new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_map, container, false);
        this.search = this.view.findViewById(R.id.search);
        this.aSwitch = this.view.findViewById(R.id.nextFireworks);
        this.filter = this.view.findViewById(R.id.filter);
        listViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        setupMap();
        buttonFilter();
        buttonAddFirework();
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
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(47.008981, 2.574844)).zoom(5.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        map.moveCamera(cameraUpdate);
                        listViewModel.loadFireWorksFutureWithSearch("");
                        addMarkers();
                        onClickMap();
                    }
                }
            });
        }

    }

    public void addMarker(double latitude, double longitude, String title) {
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(title);
        map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                .title(title));
    }



    public void addMarkers() {
        listViewModel.getFireworks().observe(getViewLifecycleOwner(), new Observer<List<FireworkViewItem>>() {
            @Override
            public void onChanged(List<FireworkViewItem> fireworkViewItems) {
                fireworks = fireworkViewItems;
                map.clear();
                for(FireworkViewItem fireworkViewItem : fireworks) {
                    MarkerOptions marker = new MarkerOptions().position(new LatLng(fireworkViewItem.getLatitude(), fireworkViewItem.getLongitude())).title(String.valueOf(fireworkViewItem.getId()));
                    markerMap.put(String.valueOf(fireworkViewItem.getId()), fireworkViewItem);
                    map.addMarker(marker);
                }
            }
        });
    }



    public void onClickMap() {
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String t = marker.getTitle();
                FireworkViewItem fireworkViewItem = markerMap.get(marker.getTitle());
                FireworkModel fireworkModel = new FireworkModel(fireworkViewItem.getId(), fireworkViewItem.getCity(),
                        fireworkViewItem.getAddress(), fireworkViewItem.getDate(),
                        fireworkViewItem.getDescription(), fireworkViewItem.getPrice(),
                        fireworkViewItem.isHandicapAccess(), fireworkViewItem.getDuration(),
                        fireworkViewItem.getCrowded(), fireworkViewItem.getIdFireworker(),
                        fireworkViewItem.getParkings(), fireworkViewItem.getAvis(),
                        fireworkViewItem.getNote());
                onInfoClicked(fireworkModel);
                return false;
            }
        });
    }


    public void buttonAddFirework() {
        this.view.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAB_NAME, "addFirework call");
                Intent intent = new Intent(view.getContext(), AddFireworkActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void buttonFilter() {
        this.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFireworks();
            }
        });
    }



    public void loadFireworks() {
        if(!aSwitch.isChecked()) {
            listViewModel.loadFireWorksFutureWithSearch(search.getQuery().toString());
            addMarkers();
        }
        else {
            listViewModel.loadFireWorksWithSearch(search.getQuery().toString());
            addMarkers();
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        loadFireworks();
    }


    @Override
    public void onInfoClicked(FireworkModel fireworkModel) {
        Log.d(TAB_NAME, "onClick call");
        Intent intent = new Intent(view.getContext(), InfoFireworkActivity.class);
        intent.putExtra(InfoFireworkActivity.FIREWORK_MESSAGE, fireworkModel.getId());
        view.getContext().startActivity(intent);
    }


    public void removeAllMarker() {
        map.clear();
    }


}
