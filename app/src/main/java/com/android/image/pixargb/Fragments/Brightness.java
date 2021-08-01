package com.android.image.pixargb.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.android.image.pixargb.Interfaces.StoreFilter;
import com.android.image.pixargb.R;

import java.security.PublicKey;

public class Brightness extends Fragment {

    SeekBar seekBar;
    BrightnessChangeListener brightnessChangeListener;
    StoreFilter storeFilter;
    int prog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.w("called","onCreateView");
        View v=inflater.inflate(R.layout.fragment_brightness, container, false);

        brightnessChangeListener= (BrightnessChangeListener) getActivity();
        storeFilter= (StoreFilter) getActivity();
        seekBar=v.findViewById(R.id.brightness_adjust);


        seekBar.setMax(200);
        seekBar.setProgress(100);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog=progress-100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                brightnessChangeListener.progress(prog);
            }
        });

        return v;
    }

    public   interface BrightnessChangeListener{
        void progress(int progress);

    }

    @Override
    public void onPause() {
        seekBar.setProgress(100);
//        storeFilter.store();
        super.onPause();
    }
}