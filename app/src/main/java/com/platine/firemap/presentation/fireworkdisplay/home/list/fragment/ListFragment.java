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
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkListAdapter;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkViewItem;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.activity.AddFireworkActivity;
import com.platine.firemap.presentation.viewmodel.FavoriteViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

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
    private ListViewModel fireworkListViewModel;
    private FavoriteViewModel favoriteViewModel;
    private boolean stateDisplayNextFireworks = true;

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
        onClickNextFireworksOn();
        onClickNextFireworksOff();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
        this.view.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFirework();
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        fireworkListAdapter = new FireworkListAdapter(this);
        recyclerView.setAdapter(fireworkListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void registerViewModels() {
        fireworkListViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        favoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFavoriteFactory()).get(FavoriteViewModel.class);
        fireworkListViewModel.loadFireWorks();
        fireworkListViewModel.getFireworks().observe(getViewLifecycleOwner(), new Observer<List<FireworkViewItem>>() {
            @Override
            public void onChanged(List<FireworkViewItem> fireworkViewModelList) {
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
    public void addFirework() {
        Log.d(TAB_NAME, "addFirework call");
        Intent intent = new Intent(view.getContext(), AddFireworkActivity.class);
        view.getContext().startActivity(intent);
    }



    @Override
    public void nextFireworksOn() {
        fireworkListViewModel.loadFireWorks();
    }
    // 4
    @Override
    public void nextFireworksOff() {
        fireworkListViewModel.loadFireWorksFuture();
    }


    public void onClickNextFireworksOn() {
        this.view.findViewById(R.id.nextFireworks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!stateDisplayNextFireworks) {
                    nextFireworksOn();
                    stateDisplayNextFireworks = true;
                }
            }
        });
    }

    public void onClickNextFireworksOff() {
        this.view.findViewById(R.id.nextFireworks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateDisplayNextFireworks) {
                    nextFireworksOff();
                    stateDisplayNextFireworks = false;
                }
            }
        });
    }
}
