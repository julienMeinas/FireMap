package com.platine.firemap.presentation.fireworkdisplay.addAvisFirework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.platine.firemap.R;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.Ressources.Validation;
import com.platine.firemap.presentation.viewmodel.FireworkerViewModel;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

public class AddAvis extends AppCompatActivity implements AddAvisActionInterface {
    public static final String FIREWORK_ID_MSG = "FIREWORK_ID_MSG";
    private TextView errorComment;
    private EditText comment;
    private int idFireworker;
    private ListViewModel fireworkViewModel;
    private int note = 0;
    private ImageView starOne;
    private ImageView starTwo;
    private ImageView starThree;
    private ImageView starFour;
    private ImageView starFive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_avis);
        fireworkViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        getFireworkId();
        init();
        buttonBack();
        buttonValid();
        buttonNote();
    }

    public void init() {
        this.comment = findViewById(R.id.editComment);
        this.errorComment = findViewById(R.id.errorComment);
        this.starOne = findViewById(R.id.rate_star_one);
        this.starTwo = findViewById(R.id.rate_star_two);
        this.starThree = findViewById(R.id.rate_star_three);
        this.starFour = findViewById(R.id.rate_star_four);
        this.starFive = findViewById(R.id.rate_star_five);
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
                    fireworkViewModel.addAvis(idFireworker, note, comment.getText().toString());
                    RelativeLayout relativeLayout = findViewById(R.id.parent);
                    Snackbar.make(relativeLayout, "Avis ajouté !", Snackbar.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }


    public void buttonNote() {
        this.starOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note = 1;
                starOne.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starTwo.setImageResource(R.drawable.rate_star_big_off_holo_dark);
                starThree.setImageResource(R.drawable.rate_star_big_off_holo_dark);
                starFour.setImageResource(R.drawable.rate_star_big_off_holo_dark);
                starFive.setImageResource(R.drawable.rate_star_big_off_holo_dark);
            }
        });

        this.starTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note = 2;
                starOne.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starTwo.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starThree.setImageResource(R.drawable.rate_star_big_off_holo_dark);
                starFour.setImageResource(R.drawable.rate_star_big_off_holo_dark);
                starFive.setImageResource(R.drawable.rate_star_big_off_holo_dark);
            }
        });

        this.starThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note = 3;
                starOne.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starTwo.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starThree.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starFour.setImageResource(R.drawable.rate_star_big_off_holo_dark);
                starFive.setImageResource(R.drawable.rate_star_big_off_holo_dark);
            }
        });

        this.starFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note = 4;
                starOne.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starTwo.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starThree.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starFour.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starFive.setImageResource(R.drawable.rate_star_big_off_holo_dark);
            }
        });

        this.starFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note = 5;
                starOne.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starTwo.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starThree.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starFour.setImageResource(R.drawable.rate_star_big_on_holo_dark);
                starFive.setImageResource(R.drawable.rate_star_big_on_holo_dark);
            }
        });
    }


    public boolean validation() {
        if(!Validation.validText(comment.getText().toString())) {
            this.errorComment.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    public void resetError() {
        this.errorComment.setVisibility(View.GONE);
    }


    public void getFireworkId() {
        Intent intent = getIntent();
        this.idFireworker =  intent.getIntExtra(FIREWORK_ID_MSG, 1);
    }

}
