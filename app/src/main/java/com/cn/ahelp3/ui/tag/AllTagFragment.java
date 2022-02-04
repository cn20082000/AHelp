package com.cn.ahelp3.ui.tag;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cn.ahelp3.BR;
import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseFragment;
import com.cn.ahelp3.core.module.RecyclerModule;
import com.cn.ahelp3.core.module.SwipeRefreshModule;
import com.cn.ahelp3.data.model.Tag;
import com.cn.ahelp3.databinding.FragmentAllTagBinding;
import com.cn.ahelp3.ui.main.module.MainStateModule;
import com.cn.ahelp3.ui.tag.adapter.TagRecyclerAdapter;

import java.util.List;

public class AllTagFragment extends BaseFragment<FragmentAllTagBinding, AllTagNavigation,
        AllTagViewModel> implements AllTagNavigation {

    private RecyclerModule<TagRecyclerAdapter.DataViewHolder, TagRecyclerAdapter> tagModule;
    private SwipeRefreshModule refreshModule;

    @Override
    public int layoutId() {
        return R.layout.fragment_all_tag;
    }

    @Override
    public Class<AllTagViewModel> vmClass() {
        return AllTagViewModel.class;
    }

    @Override
    public AllTagNavigation navigation() {
        return this;
    }

    @Override
    public int vmId() {
        return BR.allTagViewModel;
    }

    @Override
    protected void setupModule() {
        App.globalModule.changeScreen(MainStateModule.BaseState.BASE_SCREEN, App.res.getString(R.string.all_tag_page));
        refreshModule = new SwipeRefreshModule() {
            @Override
            protected SwipeRefreshLayout refreshLayout() {
                return binding.refresh;
            }

            @Override
            protected void update() {
                viewModel.getAllTag();
            }
        };
        tagModule = new RecyclerModule<TagRecyclerAdapter.DataViewHolder, TagRecyclerAdapter>() {
            @Override
            protected RecyclerView recyclerView() {
                return binding.rvAllTag;
            }

            @Override
            protected TagRecyclerAdapter setAdapter() {
                return new TagRecyclerAdapter(getContext()) {
                    @Override
                    protected void onTagClick(Tag tag) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tag", tag);
                        navController.navigate(R.id.action_allTagFragment_to_singleTagFragment, bundle);
                    }
                };
            }

            @Override
            protected RecyclerView.LayoutManager setLayoutManager() {
                return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            }
        };
    }

    @Override
    public void getAllTagSuccess(List<Tag> list) {
        refreshModule.done();
        tagModule.getAdapter().setTags(list);
    }
}
