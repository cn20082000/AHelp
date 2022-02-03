package com.cn.ahelp3.core.module;

import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerModule<VH extends RecyclerView.ViewHolder,
        AD extends RecyclerView.Adapter<VH>> {

    private AD adapter;
    private RecyclerView.LayoutManager layoutManager;

    public RecyclerModule() {
        adapter = setAdapter();
        layoutManager = setLayoutManager();
        setupRcv();
    }

    private void setupRcv() {
        recyclerView().setLayoutManager(layoutManager);
        recyclerView().setHasFixedSize(true);
        recyclerView().setAdapter(adapter);
    }

    protected abstract RecyclerView recyclerView();

    protected abstract AD setAdapter();

    protected abstract RecyclerView.LayoutManager setLayoutManager();

    public AD getAdapter() {
        return adapter;
    }
}
