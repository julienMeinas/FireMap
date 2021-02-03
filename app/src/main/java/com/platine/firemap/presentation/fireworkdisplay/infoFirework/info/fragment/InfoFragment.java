package com.platine.firemap.presentation.fireworkdisplay.infoFirework.info.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.presentation.Ressources.Utils;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.info.fragment.parking.adapter.ParkingViewAdapter;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.info.fragment.parking.mapper.ParkingToParkingViewItemMapper;

public class InfoFragment extends Fragment {
    private static InfoFragment instance = null;
    private View view;

    // le feu d'artifice
    private FireworkModel firework;

    // element affichés
    private ImageView imagePrice;
    private TextView textViewPrice;
    private ImageView imageParking;
    private TextView textViewParking;
    private ImageView imageAccessHandicap;
    private TextView textViewAccessHandicap;
    private TextView textViewDuration;
    private ImageView imageDuration;
    private ImageView imagePeople;
    private TextView textViewPeople;

    // pour afficher le recycler view parking
    private ParkingViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private ParkingToParkingViewItemMapper parkingToParkingViewItemMapper = new ParkingToParkingViewItemMapper();

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance() {
        if(instance == null) {
            instance = new InfoFragment();
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
        view = inflater.inflate(R.layout.info_fragment_info, container, false);
        firework = (FireworkModel)getArguments().getSerializable("firework");
        initElementsLayout();
        initDateInfoFirework();
        return view;
    }

    /**
     * initialise les objets avec les élements du layout
     */
    public void initElementsLayout() {
        this.imagePrice = this.view.findViewById(R.id.price);
        this.textViewPrice = this.view.findViewById(R.id.textPrice);
        this.imageParking = this.view.findViewById(R.id.parking);
        this.textViewParking = this.view.findViewById(R.id.textParking);
        this.imageAccessHandicap = this.view.findViewById(R.id.accessHandicap);
        this.textViewAccessHandicap = this.view.findViewById(R.id.textAccessHandicap);
        this.textViewDuration = this.view.findViewById(R.id.textDuration);
        this.imageDuration = this.view.findViewById(R.id.duration);
        this.imagePeople = this.view.findViewById(R.id.people);
        this.textViewPeople = this.view.findViewById(R.id.textPeople);
    }


    public void initDateInfoFirework() {
        Utils.setComponent(firework.getPrice(), imagePrice, textViewPrice,
                firework.isHandicAccess(), imageAccessHandicap, textViewAccessHandicap,
                firework.getDuration(), imageDuration, textViewDuration,
                firework.getCrowded(), imagePeople, textViewPeople, firework.getParking(), imageParking);
        setupRecyclerView();
        registerViewModel();
    }


    public void registerViewModel() {
        recyclerViewAdapter.bindViewModels(parkingToParkingViewItemMapper.map(firework.getParking()));
    }

    private void setupRecyclerView() {
        recyclerView = this.view.findViewById(R.id.recyclerViewParking);
        recyclerViewAdapter = new ParkingViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
