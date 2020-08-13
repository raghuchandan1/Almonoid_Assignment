package com.example.almonoidassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {
    private Context context;
    private String[] tags;
    public TagsAdapter(Context context, String[] tags){
        this.context = context;
        this.tags = tags;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tags_layout, parent, false);
        TagsAdapter.ViewHolder viewHolder = new TagsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tag.setText(tags[position]);
    }

    @Override
    public int getItemCount() {
        if(tags!=null){
            return tags.length;
        }
        else return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tag_textView);
        }
    }
}
