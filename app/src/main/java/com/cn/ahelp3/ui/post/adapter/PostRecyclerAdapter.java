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
import com.cn.ahelp3.core.module.RecyclerModule;
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.data.model.Tag;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.DataViewHolder> {

    private List<Post> posts;
    private final Context context;

    public PostRecyclerAdapter(Context context) {
        this.context = context;
        this.posts = new ArrayList<>();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.tvTitle.setText(posts.get(position).getTitle());

        String sub = "";
        sub += Post.getTypeStr(posts.get(position).getType());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        sub += ", " + context.getResources().getString(R.string.updated_at_day) + " " + formatter.format(posts.get(position).getUpdatedAt().getTime());
        holder.tvSub.setText(sub);

        RecyclerModule<InlineTagRecyclerAdapter.DataViewHolder, InlineTagRecyclerAdapter> tagModule =
                new RecyclerModule<InlineTagRecyclerAdapter.DataViewHolder, InlineTagRecyclerAdapter>() {
            @Override
            protected RecyclerView recyclerView() {
                return holder.rvTag;
            }

            @Override
            protected InlineTagRecyclerAdapter setAdapter() {
                return new InlineTagRecyclerAdapter(context) {
                    @Override
                    public void onTagClick(Tag tag) {
                        PostRecyclerAdapter.this.onTagClick(tag);
                    }
                };
            }

            @Override
            protected RecyclerView.LayoutManager setLayoutManager() {
                FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(context);
                layoutManager.setFlexDirection(FlexDirection.ROW);
                return layoutManager;
            }
        };
        tagModule.getAdapter().setTags(posts.get(position).getTags());

        holder.btnPost.setOnClickListener(view -> onPostClick(posts.get(position)));
    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvSub;
        private final RecyclerView rvTag;
        private final AppCompatButton btnPost;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSub = itemView.findViewById(R.id.tv_sub);
            rvTag = itemView.findViewById(R.id.rv_tag);
            btnPost = itemView.findViewById(R.id.btn_post);
        }
    }

    public abstract void onPostClick(Post post);

    public abstract void onTagClick(Tag tag);
}