package com.platine.firemap.presentation.fireworkdisplay.home.main.list.fragment;

import android.os.Bundle;
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
import com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter.FireworkListAdapter;
import com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter.FireworkViewModel;
import com.platine.firemap.presentation.viewmodel.FireworkListViewModel;

import java.util.List;

public class ListFragment extends Fragment {
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
        fireworkListAdapter = new FireworkListAdapter();
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


}
