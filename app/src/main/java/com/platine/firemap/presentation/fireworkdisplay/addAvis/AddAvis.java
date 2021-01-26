package com.platine.firemap.presentation.fireworkdisplay.addAvis;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.api.model.fireworker.Avis;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.util.ArrayList;

public class AddAvis extends AppCompatActivity implements AddAvisActionInterface {
    private EditText note;
    private EditText title;
    private EditText comment;
    private int idFireworker = 1;
    private Avis avis;
    private FireworkerViewModel fireworkerViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_avis);
        fireworkerViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFireworkerFactory()).get(FireworkerViewModel.class);
        init();
        buttonBack();
        buttonValid();
    }

    public void init() {
        this.avis = new Avis();
        this.avis.setNote(-1);
        this.avis.setTitle("");
        this.avis.setComment("");
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
        finish();
    }

    @Override
    public void onClickBack() {
        finish();
    }
}
