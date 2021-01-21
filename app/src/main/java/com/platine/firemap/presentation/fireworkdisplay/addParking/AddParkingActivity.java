package com.platine.firemap.presentation.fireworkdisplay.addParking;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter.AddActionInterface;

public class AddParkingActivity extends AppCompatActivity implements AddParkingActionInterface {
    private Parking parking;
    private EditText name = findViewById(R.id.editName);
    private EditText price = findViewById(R.id.editPrice);
    private int idFirework;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);
    }

    @Override
    public void onClickValid() {
        parking.setId(0);
        parking.setName(this.name.getText().toString());
        parking.setPrice(Integer.parseInt(this.price.getText().toString()));
        finish();
    }

    @Override
    public void onClickBack() {
        finish();
    }
}
