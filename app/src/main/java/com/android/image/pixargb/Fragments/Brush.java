package com.android.image.pixargb.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.image.pixargb.Interfaces.StoreFilter;
import com.android.image.pixargb.R;
import com.android.image.pixargb.adapters.ShowColorAdapter;

import java.util.ArrayList;

import static com.android.image.pixargb.ApplicationClass.colors;


public class Brush extends Fragment {

    ShowColorAdapter colorAdapter;
   StoreFilter storeFilter;
   ArrayList<Integer >color;


   RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_brush, container, false);



        color=colors;
        storeFilter= (StoreFilter) getActivity();
        recyclerView=v.findViewById(R.id.showColor);
        colorAdapter=new ShowColorAdapter(getActivity(),color);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(colorAdapter);




        return v;
    }



    @Override
    public void onStop() {
        super.onStop();
//        storeFilter.store();
    }
}