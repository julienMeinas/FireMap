package com.platine.firemap.presentation.fireworkdisplay.contact;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.platine.firemap.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        FloatingActionButton fab = findViewById(R.id.fab);

        TextInputEditText msg = findViewById(R.id.message);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, msg.getText(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}