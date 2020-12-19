package com.platine.firemap.presentation.fireworkdisplay.infoFirework;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.platine.firemap.R;

public class InfoFireworkActivity extends AppCompatActivity {
    private static final String TAG = "InfoFireworkActivity";
    private static final String PLACE_MESSAGE = "PLACE";
    private static final String DATE_MESSAGE = "DATE";
    private static final String PRICE_MESSAGE = "PRICE";
    private static final String PARKING_MESSAGE = "PARKING";
    private static final String ACCESS_HANDICAP_MESSAGE = "ACCESS_HANDICAP";
    private static final String PEOPLE_MESSAGE = "PEOPLE";
    private static final String FIREWORKER_MESSAGE = "FIREWOERKER";

    private TextView place;
    private TextView date;
    private ImageView imagePrice;
    private TextView price;
    private ImageView imageParking;
    private TextView parking;
    private ImageView imageAccessHandicap;
    private TextView accessHandicap;
    private ImageView imagePeople;
    private TextView people;
    private TextView fireworker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artivity_info);

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
