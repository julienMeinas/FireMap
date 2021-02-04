package com.platine.firemap.presentation.fireworkdisplay.addParking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.Ressources.Validation;
import com.platine.firemap.presentation.viewmodel.ListViewModel;


public class AddParkingActivity extends AppCompatActivity implements AddParkingActionInterface {
    public final static String MSG_ID_FIREWORK = "MSG_ID_FIREWORKER";
    private EditText name;
    private NumberPicker price;
    private TextView errorName;
    private TextView errorPrice;
    private int idFirework;
    private ListViewModel fireworkListViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);
        fireworkListViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        name = findViewById(R.id.editName);
        price = findViewById(R.id.numberPicker);
        price.setMinValue(0);
        price.setMaxValue(100);
        errorName = findViewById(R.id.errorName);
        errorPrice = findViewById(R.id.errorPrice);
        init();
        buttonBack();
        buttonValid();
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
                    RelativeLayout relativeLayout = findViewById(R.id.parent);
                    Snackbar.make(relativeLayout, "Parking ajouté !", Snackbar.LENGTH_LONG).show();
                    fireworkListViewModel.addParking(idFirework, name.getText().toString(), price.getValue());
                    finish();
                }
            }
        });
    }



    public boolean validation() {
        if(!Validation.validPrice(price.getValue())) {
            errorPrice.setVisibility(View.VISIBLE);
            return false;
        }
        if(! Validation.validAddress(name.getText().toString())){
            errorName.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }



    public void resetError() {
        errorName.setVisibility(View.GONE);
        errorPrice.setVisibility(View.GONE);
    }


    public void init() {
        Intent intent = getIntent();
        this.idFirework = intent.getIntExtra(AddParkingActivity.MSG_ID_FIREWORK, 1);
    }
}
