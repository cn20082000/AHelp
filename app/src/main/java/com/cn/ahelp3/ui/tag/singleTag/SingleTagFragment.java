package com.cn.ahelp3.ui.tag.singleTag;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cn.ahelp3.BR;
import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseFragment;
import com.cn.ahelp3.core.model.PagedObject;
import com.cn.ahelp3.core.module.RecyclerModule;
import com.cn.ahelp3.core.module.SwipeRefreshModule;
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.data.model.Tag;
import com.cn.ahelp3.databinding.FragmentSingleTagBinding;
import com.cn.ahelp3.ui.main.module.MainStateModule;
import com.cn.ahelp3.ui.post.adapter.PostRecyclerAdapter;

public class SingleTagFragment extends BaseFragment<FragmentSingleTagBinding, SingleTagNavigation,
        SingleTagViewModel> implements SingleTagNavigation {

    private Tag tag;
    private SwipeRefreshModule refreshModule;
    private RecyclerModule<PostRecyclerAdapter.DataViewHolder, PostRecyclerAdapter> postModule;

    @Override
    public int layoutId() {
        return R.layout.fragment_single_tag;
    }

    @Override
    public Class<SingleTagViewModel> vmClass() {
        return SingleTagViewModel.class;
    }

    @Override
    public SingleTagNavigation navigation() {
        return this;
    }

    @Override
    public int vmId() {
        return BR.singleTagViewModel;
    }

    @Override
    protected void setupModule() {
        tag = (Tag) argument.getSerializable("tag");
        App.globalModule.changeScreen(MainStateModule.BaseState.CHILD_SCREEN, tag.getName());

        refreshModule = new SwipeRefreshModule() {
            @Override
            protected SwipeRefreshLayout refreshLayout() {
                return binding.refresh;
            }

            @Override
            protected void update() {
                viewModel.getPostByTag(tag);
            }
        };
        postModule = new RecyclerModule<PostRecyclerAdapter.DataViewHolder, PostRecyclerAdapter>() {
            @Override
            protected RecyclerView recyclerView() {
                return binding.rvPost;
            }

            @Override
            protected PostRecyclerAdapter setAdapter() {
                return new PostRecyclerAdapter(getContext()) {
                    @Override
                    public void onPostClick(Post post) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("post", post);
                        navController.navigate(R.id.action_singleTagFragment_to_postDetailFragment, bundle);
                    }

                    @Override
                    public void onTagClick(Tag tag) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tag", tag);
                        navController.navigate(R.id.action_singleTagFragment_self, bundle);
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
    public void getPostByTagSuccess(PagedObject<Post> paged) {
        refreshModule.done();
        postModule.getAdapter().setPosts(paged.getContent());
    }
}
