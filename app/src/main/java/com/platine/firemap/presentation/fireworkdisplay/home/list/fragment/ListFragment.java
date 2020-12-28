package com.platine.firemap.presentation.fireworkdisplay.home.list.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkListAdapter;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkViewModel;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;
import com.platine.firemap.presentation.viewmodel.FireworkFavoriteViewModel;
import com.platine.firemap.presentation.viewmodel.FireworkListViewModel;

import java.io.Serializable;
import java.util.List;

public class ListFragment extends Fragment implements FireworkActionInterface {
    public static final String TAB_NAME = "List";
    private static ListFragment instance;
    private View view;
    private FireworkListAdapter fireworkListAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView textViewErrorConnexion;
    private FireworkListViewModel fireworkListViewModel;
    private FireworkFavoriteViewModel favoriteViewModel;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        if(instance == null) {
            instance = new ListFragment();
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
        view = inflater.inflate(R.layout.fragment_list, container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        textViewErrorConnexion = view.findViewById(R.id.textViewErrorConnexion);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void setupRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        fireworkListAdapter = new FireworkListAdapter(this);
        recyclerView.setAdapter(fireworkListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void registerViewModels() {
        fireworkListViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(FireworkListViewModel.class);
        favoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFavoriteFactory()).get(FireworkFavoriteViewModel.class);

        fireworkListViewModel.getFireworks().observe(getViewLifecycleOwner(), new Observer<List<FireworkViewModel>>() {
            @Override
            public void onChanged(List<FireworkViewModel> fireworkViewModelList) {
                fireworkListAdapter.bindViewModels(fireworkViewModelList);
            }
        });

        fireworkListViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDataLoading) {
                progressBar.setVisibility(isDataLoading ? View.VISIBLE : View.GONE);
            }
        });

        fireworkListViewModel.getErrorConnexion().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isErrorConnexion) {
                textViewErrorConnexion.setVisibility(isErrorConnexion ? View.VISIBLE : View.GONE);
            }
        });
    }


    @Override
    public void onInfoClicked(FireworkModel fireworkModel) {
        Log.d(TAB_NAME, "onClick call");
        Intent intent = new Intent(view.getContext(), InfoFireworkActivity.class);
        intent.putExtra(InfoFireworkActivity.FIREWORK_MESSAGE, (Serializable)fireworkModel);
        view.getContext().startActivity(intent);
    }

    @Override
    public void onFav(int id) {
        FireworkEntity fireworkEntity = new FireworkEntity();
        fireworkEntity.setId(id);
        favoriteViewModel.addFireworkToFavorite(fireworkEntity);
    }
}
