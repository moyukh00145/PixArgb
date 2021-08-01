package com.android.image.pixargb.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.android.image.pixargb.R;


public class ColorAdd extends Fragment {

    ColourAddListener colourAddListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    SeekBar red,blue,green;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_color_add, container, false);

        colourAddListener= (ColourAddListener) getActivity();
        red=v.findViewById(R.id.redBar);
        green=v.findViewById(R.id.greenBar);
        blue=v.findViewById(R.id.blueBar);

        blue.setMax(200);
        red.setMax(200);
        green.setMax(200);

        blue.setProgress(100);
        red.setProgress(100);
        green.setProgress(100);



        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pgred,pgblue,pggreen;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pgred=progress-100;
                pggreen=green.getProgress()-100;
                pgblue=blue.getProgress()-100;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                colourAddListener.redAdded(pgred,pggreen,pgblue);
            }
        });


        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pgred,pgblue,pggreen;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pgred=red.getProgress()-100;
                pggreen=green.getProgress()-100;
                pgblue=progress-100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                colourAddListener.blueAdded(pgred,pggreen,pgblue);
            }
        });

        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pgred,pgblue,pggreen;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pgred=red.getProgress()-100;
                pggreen=progress-100;
                pgblue=blue.getProgress()-100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                colourAddListener.greenAdded(pgred,pggreen,pgblue);
            }
        });




        return v;
    }

    public interface ColourAddListener{
        void redAdded(int pgred,int pggreen,int pgblue);
        void blueAdded(int pgred,int pggreen,int pgblue);
        void greenAdded(int pgred,int pggreen,int pgblue);
    }

}