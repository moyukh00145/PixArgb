package com.android.image.pixargb.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.android.image.pixargb.Interfaces.StoreFilter;
import com.android.image.pixargb.R;


public class Saturation extends Fragment {

    SeekBar seekBar;
    float value;
    SaturationChange saturationChange;
    StoreFilter storeFilter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_saturation, container, false);


        storeFilter= (StoreFilter) getActivity();
        saturationChange= (SaturationChange) getActivity();
        seekBar=v.findViewById(R.id.saturation_adjust);

        seekBar.setMax(30);
        seekBar.setProgress(0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                value=0.10f*progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               saturationChange.saturationChange(value);
            }
        });



        return v;
    }

    public interface SaturationChange{
        void saturationChange(float progress);
    }

    @Override
    public void onPause() {
//        storeFilter.store();
        super.onPause();
    }
}