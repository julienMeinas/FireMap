package com.platine.firemap.presentation.fireworkdisplay.home.main.list.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter.FireworkActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter.FireworkListAdapter;
import com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter.FireworkViewModel;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;
import com.platine.firemap.presentation.viewmodel.FireworkListViewModel;

import java.lang.annotation.Annotation;
import java.util.List;

public class ListFragment extends Fragment implements FireworkActionInterface {
    public static final String TAB_NAME = "List";
    private View view;
    private FireworkListAdapter fireworkListAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FireworkListViewModel fireworkListViewModel;

    public ListFragment() {
        // Required empty public constructor
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
    }


    @Override
    public void onInfoClicked(String address, String date, int price, boolean accessHandicap, String crowed) {
        Log.d(TAB_NAME, "onClick call");
        Intent intent = new Intent(view.getContext(), InfoFireworkActivity.class);
        intent.putExtra(InfoFireworkActivity.ADDRESS_MESSAGE, address);
        intent.putExtra(InfoFireworkActivity.DATE_MESSAGE, date);
        intent.putExtra(InfoFireworkActivity.PRICE_MESSAGE, price);
        intent.putExtra(InfoFireworkActivity.ACCESS_HANDICAP_MESSAGE, accessHandicap);
        intent.putExtra(InfoFireworkActivity.PEOPLE_MESSAGE, crowed);
        view.getContext().startActivity(intent);
    }
}
