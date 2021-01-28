package com.platine.firemap.presentation.fireworkdisplay.addAvis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.api.model.fireworker.Avis;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.Ressources.Validation;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.io.Serializable;
import java.util.ArrayList;

public class AddAvis extends AppCompatActivity implements AddAvisActionInterface {
    public static final String FIREWORKER_ID_MSG = "FIREWORKER_ID_MSG";
    private TextView errorNote;
    private TextView errorTitle;
    private TextView errorComment;
    private EditText note;
    private EditText title;
    private EditText comment;
    private int idFireworker;
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
        this.note = findViewById(R.id.editNote);
        this.title = findViewById(R.id.editTitle);
        this.comment = findViewById(R.id.editComment);
        this.errorNote = findViewById(R.id.errorNote);
        this.errorTitle = findViewById(R.id.errorTitle);
        this.errorComment = findViewById(R.id.errorComment);
    }

    public void buttonBack() {
        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void buttonValid() {
        findViewById(R.id.validation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetError();
                if(validation()) {
                    fireworkerViewModel.addAvis(idFireworker, Double.parseDouble(note.getText().toString()),
                            title.getText().toString(), comment.getText().toString());
                    RelativeLayout relativeLayout = findViewById(R.id.parent);
                    Snackbar.make(relativeLayout, "Avis ajouté !", Snackbar.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }


    public boolean validation() {
        if(!Validation.validNote(Integer.parseInt(note.getText().toString()))) {
            this.errorNote.setVisibility(View.VISIBLE);
            return false;
        }
        if(!Validation.validText(title.getText().toString())) {
            this.errorTitle.setVisibility(View.VISIBLE);
            return false;
        }
        if(!Validation.validText(comment.getText().toString())) {
            this.errorComment.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    public void resetError() {
        this.errorNote.setVisibility(View.GONE);
        this.errorTitle.setVisibility(View.GONE);
        this.errorComment.setVisibility(View.GONE);
    }


    public void getFireworkId() {
        Intent intent = getIntent();
        this.idFireworker =  intent.getIntExtra(FIREWORKER_ID_MSG, 1);
    }
}
