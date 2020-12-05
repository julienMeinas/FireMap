package com.platine.firemap.presentation.fireworkdisplay.home.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.platine.firemap.R;
import com.platine.firemap.presentation.fireworkdisplay.home.HomeActivity;
import com.platine.firemap.presentation.fireworkdisplay.home.main.list.fragment.ListFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.main.map.fragment.MapFragment;

public class MainFragment extends Fragment {
    public static final String TAB_NAME = "Map and List";
    private View view;

    private ViewPager viewPager;
    private HomeActivity myContext;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myContext= (HomeActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_main, container, false);
        setupViewPagerAndTabs();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void setupViewPagerAndTabs() {
        viewPager = view.findViewById(R.id.map_and_list_view_pager);
        final MapFragment mapFragment = new MapFragment();
        final ListFragment listFragment = new ListFragment();

        viewPager.setAdapter(new FragmentPagerAdapter(myContext.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return mapFragment;
                }
                return listFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return MapFragment.TAB_NAME;
                }
                return ListFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
