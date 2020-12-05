package com.platine.firemap.presentation.fireworkdisplay.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.platine.firemap.R;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.fragment.FavoriteFragment;
import com.platine.firemap.presentation.fireworkdisplay.home.main.fragment.MainFragment;

public class HomeActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.view_pager);
        final MainFragment mainFragment = new MainFragment();
        final FavoriteFragment favoriteFragment = new FavoriteFragment();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return mainFragment;
                }
                return favoriteFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return MainFragment.TAB_NAME;
                }
                return FavoriteFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
