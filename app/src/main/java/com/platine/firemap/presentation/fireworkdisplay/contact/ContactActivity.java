package com.platine.firemap.presentation.fireworkdisplay.contact;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.data.repository.fireworkdisplay.FireworkDisplayDataRepository;
import com.platine.firemap.presentation.fireworkdisplay.editFirework.EditFireworkActivity;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int fireworkId = intent.getIntExtra(EditFireworkActivity.FIREWORK_REPORT_ID,-1);

        setContentView(R.layout.activity_contact);

        FloatingActionButton fab = findViewById(R.id.fab);

        TextInputEditText msg = findViewById(R.id.message);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FireworkDisplayDataRepository fireworkDisplayDataRepository = FakeDependencyInjection.getArticleDisplayRepository();
                fireworkDisplayDataRepository.sendEmail(msg.getText().toString(),"Report on firework : "+fireworkId);
                Snackbar.make(view, "message envoyé", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                finish();
            }
        });
    }
}