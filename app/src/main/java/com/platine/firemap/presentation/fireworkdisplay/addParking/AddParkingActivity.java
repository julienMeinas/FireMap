package com.platine.firemap.presentation.fireworkdisplay.addParking;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.di.FakeDependencyInjection;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter.AddActionInterface;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.util.ArrayList;

public class AddParkingActivity extends AppCompatActivity implements AddParkingActionInterface {
    private Parking parking;
    private EditText name;
    private EditText price;
    private int idFirework = 1;
    private ListViewModel fireworkListViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);
        fireworkListViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(ListViewModel.class);
        init();
        buttonBack();
        buttonValid();
    }

    @Override
    public void onClickValid() {
        parking.setId(this.idFirework);
        parking.setName(this.name.getText().toString());
        parking.setPrice(Integer.parseInt(this.price.getText().toString()));
        fireworkListViewModel.addParking(this.idFirework, parking.getName(), parking.getPrice());
        finish();
    }

    @Override
    public void onClickBack() {
        finish();
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



    public void init() {
        this.parking = new Parking("", 0, new ArrayList<>());
        this.name = findViewById(R.id.editName);
        this.price = findViewById(R.id.editPrice);
        this.idFirework = 1;
    }
}
