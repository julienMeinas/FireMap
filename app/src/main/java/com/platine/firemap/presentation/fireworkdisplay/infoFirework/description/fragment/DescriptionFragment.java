package com.platine.firemap.presentation.fireworkdisplay.infoFirework.description.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.presentation.Ressources.Utils;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.info.fragment.InfoFragment;

public class DescriptionFragment extends Fragment {
    private static DescriptionFragment instance = null;
    private View view;

    // le feu d'artifice
    private FireworkModel firework;

    // data
    private TextView textViewDescription;



    public DescriptionFragment() {
        // Required empty public constructor
    }

    public static DescriptionFragment newInstance() {
        if(instance == null) {
            instance = new DescriptionFragment();
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
        view = inflater.inflate(R.layout.info_fragment_description, container, false);
        firework = (FireworkModel)getArguments().getSerializable("firework");
        initElementsLayout();
        initDateInfoFirework();
        return view;
    }

    /**
     * initialise les objets avec les élements du layout
     */
    public void initElementsLayout() {
        this.textViewDescription = this.view.findViewById(R.id.description);
    }


    public void initDateInfoFirework() {
        textViewDescription.setText(firework.getDescription());
    }


}
