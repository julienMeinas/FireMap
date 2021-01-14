package com.platine.firemap.presentation.fireworkdisplay.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.platine.firemap.R;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.fragment.FavoriteFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.list.fragment.ListFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.map.fragment.MapFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private BottomNavigationView m_BottomNav;
    public static final List<Fragment> m_listFragment = new ArrayList<Fragment>() {{
        add(MapFragment.newInstance());
        add(ListFragment.newInstance());
        add(FavoriteFragment.newInstance());
    }};
    private static final int positionMapFragment = 0;
    private static final int positionListFragment = 1;
    private static final int positionFavoriteFragment = 2;
    public int m_currentFragment = positionMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_BottomNav = findViewById(R.id.bottom_navigation);
        m_BottomNav.setOnNavigationItemSelectedListener(navListerner);

        if(savedInstanceState != null) {
            m_currentFragment = savedInstanceState.getInt("currentPositionFragment");
        }
        else {
            m_currentFragment = positionMapFragment;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter,
                m_listFragment.get(m_currentFragment)).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListerner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_map:
                            m_currentFragment = positionMapFragment;
                            break;
                        case R.id.nav_list:
                            m_currentFragment = positionListFragment;
                            break;
                        case R.id.nav_favorite:
                            m_currentFragment = positionFavoriteFragment;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, m_listFragment.get(m_currentFragment)).commit();
                    return true;
                }
            };

    @Override
    protected void onSaveInstanceState(Bundle icicle) {
        super.onSaveInstanceState(icicle);
        icicle.putInt("currentPositionFragment", m_currentFragment);
    }




}
