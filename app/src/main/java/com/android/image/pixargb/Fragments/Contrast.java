package com.android.image.pixargb.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.android.image.pixargb.Interfaces.StoreFilter;
import com.android.image.pixargb.R;

public class Contrast extends Fragment {

    SeekBar seekBar;
    float value;
    ContrastChange contrastChange;
    StoreFilter storeFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_contrast, container, false);

        contrastChange= (ContrastChange) getActivity();
        storeFilter= (StoreFilter) getActivity();

        seekBar=v.findViewById(R.id.contrast_adjust);

        seekBar.setMax(20);
        seekBar.setProgress(0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int prog=progress+10;
                value=0.10f*prog;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                contrastChange.contrastChange(value);
            }
        });



        return v;
    }

    public interface ContrastChange{
        void contrastChange(float progress);
    }

    @Override
    public void onPause() {
//        storeFilter.store();
        super.onPause();
    }
}