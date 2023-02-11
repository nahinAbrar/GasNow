package com.isd.gasnow.DashBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.isd.gasnow.R;

import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder>{

    ArrayList<MostViewedAdapterHelperClass> mostViewedAdapterHelperClassArrayList;

    public MostViewedAdapter(ArrayList<MostViewedAdapterHelperClass> mostViewedAdapterHelperClassArrayList) {
        this.mostViewedAdapterHelperClassArrayList = mostViewedAdapterHelperClassArrayList;
    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design,parent, false);
        MostViewedAdapter.MostViewedViewHolder mostViewedViewHolder = new MostViewedAdapter.MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewHolder holder, int position) {
        MostViewedAdapterHelperClass mostViewedAdapterHelperClass = mostViewedAdapterHelperClassArrayList.get(position);

        holder.image.setImageResource(mostViewedAdapterHelperClass.getImage());
        holder.title.setText(mostViewedAdapterHelperClass.getTitle());
        holder.desc.setText(mostViewedAdapterHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return mostViewedAdapterHelperClassArrayList.size();
    }


    public static class MostViewedViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title, desc;

        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.mostViewed_Image1);
            title = itemView.findViewById(R.id.mostViewed_Title);
            desc = itemView.findViewById(R.id.dashboardMostviewed_Desc);
        }
    }


}
