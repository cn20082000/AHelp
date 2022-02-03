package com.cn.ahelp3.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.ahelp3.R;
import com.cn.ahelp3.core.action.EmptyAction;

import java.util.ArrayList;
import java.util.List;

public class NavRecyclerAdapter extends RecyclerView.Adapter<NavRecyclerAdapter.DataViewHolder> {

    private List<NavItem> list;
    private final Context context;
    private int selectedItem;

    public NavRecyclerAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        selectedItem = 0;
    }

    public NavRecyclerAdapter addNavItem(int icon, String title, EmptyAction action) {
        list.add(new NavItem(icon, title, action));
        notifyItemInserted(list.size() - 1);
        return this;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav, parent, false);
        return new NavRecyclerAdapter.DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        int pos = position;
        if (selectedItem == position) {
            holder.lyBackground.setBackgroundResource(R.drawable.bg_nav_selected);

            holder.btnNav.setOnClickListener(view -> {});
        } else {
            holder.lyBackground.setBackgroundColor(Color.TRANSPARENT);

            holder.btnNav.setOnClickListener(view -> {
                int temp = selectedItem;
                selectedItem = pos;
                notifyItemChanged(temp);
                notifyItemChanged(selectedItem);

                list.get(position).action.execute();
            });
        }

        holder.imgIcon.setImageResource(list.get(position).icon);

        holder.tvTitle.setText(list.get(position).title);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        private final ConstraintLayout lyBackground;
        private final AppCompatImageView imgIcon;
        private final TextView tvTitle;
        private final AppCompatButton btnNav;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            lyBackground = itemView.findViewById(R.id.ly_background);
            imgIcon = itemView.findViewById(R.id.img_icon);
            tvTitle = itemView.findViewById(R.id.tv_title);
            btnNav = itemView.findViewById(R.id.btn_nav);
        }
    }

    private class NavItem {
        private int icon;
        private String title;
        private EmptyAction action;

        public NavItem(int icon, String title, EmptyAction action) {
            this.icon = icon;
            this.title = title;
            this.action = action;
        }
    }
}
