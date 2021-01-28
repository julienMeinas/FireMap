package com.platine.firemap.presentation.Ressources;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;

public class Button extends AppCompatActivity {

    public static void ButtonPrice(AppCompatButton free, AppCompatButton notFree, ImageView image, FireworkModel firework) {
        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_price_free);
                firework.setPrice(0);
            }
        });

        notFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_price_no_free);
                firework.setPrice(30);
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_empty_price);
                firework.setPrice(50000);
            }
        });
    }


    public static void ButtonAccessHandicap(AppCompatButton access, AppCompatButton notAccess, ImageView image, FireworkModel firework) {
        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_handicap_access);
                firework.setHandicAccess(true);
            }
        });

        notAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_no_handicap_access);
                firework.setHandicAccess(false);
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_empty_accesshandicap);
                firework.setHandicAccess(false);
            }
        });
    }


    public static void ButtonCrowed(AppCompatButton low, AppCompatButton medium, AppCompatButton high, ImageView image, FireworkModel firework) {
        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_people_low);
                firework.setCrowded("Low");
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_people_medium);
                firework.setCrowded("Medium");
            }
        });

        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_people_high);
                firework.setCrowded("High");
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_empty_people);
                firework.setCrowded("");
            }
        });
    }


    public static void ButtonDuration(AppCompatButton aShort, AppCompatButton middle, AppCompatButton aLong, ImageView image, FireworkModel firework) {
        aShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_duration_short);
                firework.setDuration("Short");
            }
        });

        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_duration_middle);
                firework.setDuration("Middle");
            }
        });

        aLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_duration_long);
                firework.setDuration("Long");
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.drawable_empty_duration);
                firework.setDuration("");
            }
        });
    }
}
