package com.android.image.pixargb.adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.image.pixargb.Interfaces.Optionclicked;
import com.android.image.pixargb.R;

import java.util.ArrayList;

import static com.android.image.pixargb.ApplicationClass.option_name;
import static com.android.image.pixargb.ApplicationClass.options;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.optionHolder> {

    Context context;
    ArrayList<Integer>optionView=options;
    String[]optionName=option_name;

    Optionclicked optionclicked;

    public OptionAdapter(Context context) {
        this.context=context;
        this.optionclicked= (Optionclicked) context;
    }

    @NonNull
    @Override
    public optionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int value= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,context.getResources().getDisplayMetrics());

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.option_single_lay,parent,false);
        v.setPadding(value,0,value,0);
        Log.w("value",value+"");
        return new optionHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull optionHolder holder, int position) {

        holder.option_view_imv.setImageResource(optionView.get(position));
        holder.option_name_tv.setText(optionName[position]);

    }

    @Override
    public int getItemCount() {
        return optionView.size();
    }

    class optionHolder extends RecyclerView.ViewHolder{

        ImageView option_view_imv;
        TextView option_name_tv;

        public optionHolder(@NonNull View itemView) {
            super(itemView);


            option_view_imv=itemView.findViewById(R.id.option_single_view);
            option_name_tv=itemView.findViewById(R.id.option_single_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    optionclicked.onClicked(getAbsoluteAdapterPosition());
                }
            });
        }
    }
}
