package com.platine.firemap.presentation.fireworkdisplay.addAvis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.api.model.fireworker.Avis;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.io.Serializable;
import java.util.ArrayList;

public class AddAvis extends AppCompatActivity implements AddAvisActionInterface {
    public static final String FIREWORKER_ID_MSG = "FIREWORKER_ID_MSG";
    private EditText note;
    private EditText title;
    private EditText comment;
    private int idFireworker;
    private Avis avis;
    private FireworkerViewModel fireworkerViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_avis);
        fireworkerViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFireworkerFactory()).get(FireworkerViewModel.class);
        getFireworkId();
        init();
        buttonBack();
        buttonValid();
    }

    public void init() {
        this.avis = new Avis();
        this.avis.setNote(-1);
        this.avis.setTitle("");
        this.avis.setComment("");
        this.note = findViewById(R.id.editNote);
        this.title = findViewById(R.id.editTitle);
        this.comment = findViewById(R.id.editComment);
    }

    public void buttonBack() {
        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBack();
            }
        });
    }

    public void buttonValid() {
        findViewById(R.id.validation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickValid();
            }
        });
    }

    @Override
    public void onClickValid() {
        this.avis.setNote(Double.parseDouble(this.note.getText().toString()));
        this.avis.setTitle(this.title.getText().toString());
        this.avis.setComment(this.comment.getText().toString());
        fireworkerViewModel.addAvis(this.idFireworker, this.avis.getNote(), this.avis.getTitle(), this.avis.getComment());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",(Serializable)this.avis);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onClickBack() {
        finish();
    }

    public void getFireworkId() {
        Intent intent = getIntent();
        this.idFireworker =  intent.getIntExtra(FIREWORKER_ID_MSG, 1);
    }
}
