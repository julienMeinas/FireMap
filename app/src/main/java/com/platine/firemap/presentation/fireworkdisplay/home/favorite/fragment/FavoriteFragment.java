package com.platine.firemap.presentation.fireworkdisplay.home.favorite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkFavoriteAdapter;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkViewItem;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;
import com.platine.firemap.presentation.viewmodel.FavoriteViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;

public class FavoriteFragment extends Fragment implements FireworkActionInterface {
    public static final String TAB_NAME = "Favorites";
    private static FavoriteFragment instance;
    private View view;
    private ArrayList<FireworkViewItem> m_articles = new ArrayList<>();
    private FavoriteViewModel m_favoriteViewModel;
    private FireworkFavoriteAdapter m_recyclerViewAdapter;
    private ProgressBar progressBar;

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
        progressBar = view.findViewById(R.id.progress_bar);
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
        m_favoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFavoriteFactory()).get(FavoriteViewModel.class);
        m_favoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<FireworkViewItem>>() {
            @Override
            public void onChanged(List<FireworkViewItem> articleItemViewModelList) {
                m_recyclerViewAdapter.bindViewModels(articleItemViewModelList);
            }
        });

        m_favoriteViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDataLoading) {
                progressBar.setVisibility(isDataLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void removeFavorite(int id) {
        m_favoriteViewModel.removeFireworkFromFavorites(id);
        ConstraintLayout relativeLayout = this.view.findViewById(R.id.fragment_favorite);
        Snackbar.make(relativeLayout, "Feux d'artifice supprimé des favoris !", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onInfoClicked(FireworkModel fireworkModel) {
        Log.d(TAB_NAME, "onClick call");
        Intent intent = new Intent(view.getContext(), InfoFireworkActivity.class);
        intent.putExtra(InfoFireworkActivity.FIREWORK_MESSAGE, (Serializable)fireworkModel);
        view.getContext().startActivity(intent);
    }
}
