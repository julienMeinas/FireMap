package com.platine.firemap.presentation.fireworkdisplay.infoFirework.signaler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.platine.firemap.R;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.avis.fragment.AvisFragment;

public class SignalerFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private static SignalerFragment instance = null;
    private Spinner spinner;
    private static final String[] paths = {"Bug", "Information incorrect"};
    private EditText editTextDescription;
    private Button buttonSend;
    private String motif = "Bug";
    private View view;

    public SignalerFragment() {
        // Required empty public constructor
    }

    public static SignalerFragment newInstance() {
        if(instance == null) {
            instance = new SignalerFragment();
        }
        return instance;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.info_fragment_signaler, container, false);
        spinner = (Spinner)this.view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        editTextDescription = this.view.findViewById(R.id.editText);
        buttonSend = this.view.findViewById(R.id.button);

        buttonSend();
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                motif = "Bug";
                break;
            case 1:
                motif = "Information incorrect";
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    public void buttonSend() {
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = editTextDescription.getText().toString();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"julien.meinas.etu@&univ-lille.fr"});
                email.putExtra(Intent.EXTRA_SUBJECT, motif);
                email.putExtra(Intent.EXTRA_TEXT, description);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
    }


}
