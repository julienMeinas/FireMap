package com.platine.firemap.presentation.fireworkdisplay.home.favorite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkFavoriteAdapter;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkViewItem;
import com.platine.firemap.presentation.viewmodel.FireworkFavoriteViewModel;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;

public class FavoriteFragment extends Fragment implements FireworkActionInterface {
    public static final String TAB_NAME = "Favorites";
    private static FavoriteFragment instance;
    private View view;
    private ArrayList<FireworkViewItem> m_articles = new ArrayList<>();
    private FireworkFavoriteViewModel m_favoriteViewModel;
    private FireworkFavoriteAdapter m_recyclerViewAdapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        if(instance == null) {
            instance = new FavoriteFragment();
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
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        m_recyclerViewAdapter = new FireworkFavoriteAdapter(this);
        recyclerView.setAdapter(m_recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        m_favoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFavoriteFactory()).get(FireworkFavoriteViewModel.class);
        m_favoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<FireworkViewItem>>() {

            @Override
            public void onChanged(List<FireworkViewItem> articleItemViewModelList) {
                m_recyclerViewAdapter.bindViewModels(articleItemViewModelList);
            }
        });
    }

    @Override
    public void removeFavorite(int id) {
        //todo
    }

    @Override
    public void onInfoClicked(int id, String address, String date, int price, boolean accessHandicap, int duration, String crowed, Fireworker fireworker, List<Parking> parkings) {
        //todo
    }
}
