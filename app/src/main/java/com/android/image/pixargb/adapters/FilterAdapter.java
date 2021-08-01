package com.android.image.pixargb.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.image.pixargb.ImageFilters.DemoImage;
import com.android.image.pixargb.ImageFilters.FilterDemo;
import com.android.image.pixargb.ImageFilters.Filters;
import com.android.image.pixargb.Interfaces.FilterSelected;
import com.android.image.pixargb.R;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder> {

    ArrayList<String >name;
    Context context;
    Bitmap bitmap;
    List<DemoImage>demofilters;
    FilterSelected filterSelected;

    public FilterAdapter(Context context, List<DemoImage> demoFilter) {

        this.context = context;
        this.demofilters=demoFilter;
        this.filterSelected= (FilterSelected) context;
    }

    @NonNull
    @Override
    public FilterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.filter_row,parent,false);
        return new FilterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterHolder holder, int position) {

        holder.name.setText(demofilters.get(position).filterName);
        holder.filter.setImageBitmap(demofilters.get(position).image);

    }

    @Override
    public int getItemCount() {
        return demofilters.size();
    }

    class FilterHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView filter;

        public FilterHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.filter_name);
            filter=itemView.findViewById(R.id.filer_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    filterSelected.filterSelected(demofilters.get(getAbsoluteAdapterPosition()).filter);
                }
            });
        }
    }
}
