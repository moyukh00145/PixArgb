package com.android.image.pixargb.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.image.pixargb.R;

import java.util.ArrayList;

public class ShowColorAdapter extends RecyclerView.Adapter<ShowColorAdapter.colorHolder> {

    Context context;
    ArrayList<Integer >color;
    BrushSelected brushSelected;

    public ShowColorAdapter(Context context,ArrayList<Integer >color) {
        this.context = context;
        this.color=color;
        this.brushSelected= (BrushSelected) context;
    }

    @NonNull
    @Override
    public colorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.show_color_row,parent,false);

        return new colorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull colorHolder holder, int position) {
        Log.w("Color","="+color.get(position));
        holder.colorview.setCardBackgroundColor(color.get(position));
    }

    @Override
    public int getItemCount() {
        return color.size();
    }

    class colorHolder extends RecyclerView.ViewHolder{

        CardView colorview;

        public colorHolder(@NonNull View itemView) {
            super(itemView);

            colorview=itemView.findViewById(R.id.color_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    brushSelected.BrushSelected(getAbsoluteAdapterPosition());
                }
            });
        }
    }


    public interface BrushSelected{
        void BrushSelected(int index);
    }
}
