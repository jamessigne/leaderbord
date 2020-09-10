package com.example.leaderbord.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.leaderbord.R;
import com.example.leaderbord.models.Hours;

import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.ViewHolder> {
    private Context context;
    private List<Hours> hoursList;

    public HoursAdapter(Context context, List<Hours> list){
        this.context = context;
        this.hoursList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hours,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hours hours = hoursList.get(position);
        if (hours != null ){
            Glide.with(context).load(hours.getBadgeUrl()).into(holder.image);
            holder.name.setText(hours.getName());
            holder.other.setText(context.getString(R.string.other_text,hours.gethours(),hours.getCountry()));
        }
    }

    @Override
    public int getItemCount() {
        return hoursList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView other;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.participant_name);
            other = itemView.findViewById(R.id.other_infos);
        }
    }
}
