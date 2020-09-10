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
import com.example.leaderbord.models.Skill;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {
    private Context context;
    private List<Skill> skillList;

    public SkillAdapter(Context context, List<Skill> list){
        this.context = context;
        this.skillList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hours,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Skill skill = skillList.get(position);
        if (skill != null ){
            Glide.with(context).load(skill.getBadgeUrl()).into(holder.image);
            holder.name.setText(skill.getName());
            holder.other.setText(context.getString(R.string.skill_iq_score,skill.getScore(),skill.getCountry()));
        }
    }

    @Override
    public int getItemCount() {
        return skillList.size();
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
