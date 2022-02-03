package com.cn.ahelp3.ui.post.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.ahelp3.R;
import com.cn.ahelp3.data.model.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class InlineTagRecyclerAdapter extends RecyclerView.Adapter<InlineTagRecyclerAdapter.DataViewHolder> {

    private List<Tag> tags;
    private final Context context;

    public InlineTagRecyclerAdapter(Context context) {
        this.context = context;
        this.tags = new ArrayList<>();
    }

    public void setTags(List<Tag> ts) {
        Collections.sort(ts, (t0, t1) -> t0.getName().compareTo(t1.getName()));
        tags = ts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inline_tag, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.tvName.setText(tags.get(position).getName());

        holder.btnTag.setOnClickListener(view -> {
            onTagClick(tags.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return tags == null ? 0 : tags.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final AppCompatButton btnTag;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            btnTag = itemView.findViewById(R.id.btn_tag);
        }
    }

    public abstract void onTagClick(Tag tag);
}
