package com.cn.ahelp3.ui.tag.adapter;

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
import java.util.Locale;

public abstract class TagRecyclerAdapter extends RecyclerView.Adapter<TagRecyclerAdapter.DataViewHolder> {

    private final Context context;
    private List<Tag> tags;

    public TagRecyclerAdapter(Context context) {
        this.context = context;
        this.tags = new ArrayList<>();
    }

    public void setTags(List<Tag> tags) {
        Collections.sort(tags, (t0, t1) -> t0.getName().compareTo(t1.getName()));
        this.tags = tags;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.btnTag.setOnClickListener(view -> onTagClick(tags.get(position)));

        holder.tvTitle.setText(tags.get(position).getName());
        holder.tvSub.setText(tags.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return tags == null ? 0 : tags.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatButton btnTag;
        private final TextView tvTitle, tvSub;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            btnTag = itemView.findViewById(R.id.btn_tag);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSub = itemView.findViewById(R.id.tv_sub);
        }
    }

    protected abstract void onTagClick(Tag tag);
}
