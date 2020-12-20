package com.platine.firemap.presentation.fireworkdisplay.editFirework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;

public class EditFireworkActivity extends AppCompatActivity {
    private static final String TAG = "EditFireworkActivity";
    private FireworkModel firework;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        // id ici pour récupérer l'artificier lié à ce feu d'artifice (id feu d'artifce == id artificier dans l'api)
        this.firework = (FireworkModel)intent.getSerializableExtra(InfoFireworkActivity.FIREWORK_MESSAGE);

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
