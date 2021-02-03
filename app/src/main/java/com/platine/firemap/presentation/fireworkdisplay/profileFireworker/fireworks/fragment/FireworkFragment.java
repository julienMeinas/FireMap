package com.platine.firemap.presentation.fireworkdisplay.profileFireworker.fireworks.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkListAdapter;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkViewItem;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.util.List;

public class FireworkFragment extends Fragment implements FireworkActionInterface {
    private static FireworkFragment instance = null;
    private View view;

    private FireworkerDetail fireworkerDetail;
    private ListViewModel fireworkListViewModel;
    private RecyclerView recyclerView;
    private FireworkListAdapter fireworkListAdapter;

    public FireworkFragment() {
        // Required empty public constructor
    }

    public static FireworkFragment newInstance() {
        if(instance == null) {
            instance = new FireworkFragment();
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
        view = inflater.inflate(R.layout.profile_fragment_fireworks, container, false);
        fireworkerDetail = (FireworkerDetail) getArguments().getSerializable("fireworker");
        setupRecyclerView();
        registerViewModels();
        return view;
    }


    private void setupRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        fireworkListAdapter = new FireworkListAdapter(this);
        recyclerView.setAdapter(fireworkListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void registerViewModels() {
        fireworkListViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        fireworkListViewModel.loadAllFireworksByCityByFireworker(fireworkerDetail.getId(),"");
        fireworkListViewModel.getFireworks().observe(getViewLifecycleOwner(), new Observer<List<FireworkViewItem>>() {
            @Override
            public void onChanged(List<FireworkViewItem> fireworkViewModelList) {
                fireworkListAdapter.bindViewModels(fireworkViewModelList);
            }
        });
    }

    @Override
    public void buttonAddFirework() {
        // Nothing
    }

    @Override
    public void onInfoClicked(FireworkModel fireworkModel) {

    }
}
