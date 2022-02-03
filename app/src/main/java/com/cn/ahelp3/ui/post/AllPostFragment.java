package com.cn.ahelp3.ui.post;

import android.os.Bundle;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cn.ahelp3.BR;
import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseFragment;
import com.cn.ahelp3.core.model.PagedObject;
import com.cn.ahelp3.core.module.RecyclerModule;
import com.cn.ahelp3.core.module.ScrollTopModule;
import com.cn.ahelp3.core.module.SwipeRefreshModule;
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.data.model.Tag;
import com.cn.ahelp3.databinding.FragmentAllPostBinding;
import com.cn.ahelp3.ui.main.module.MainStateModule;
import com.cn.ahelp3.ui.post.adapter.PostRecyclerAdapter;

public class AllPostFragment extends BaseFragment<FragmentAllPostBinding, AllPostNavigation,
        AllPostViewModel> implements AllPostNavigation {

    private SwipeRefreshModule refreshModule;
    private RecyclerModule<PostRecyclerAdapter.DataViewHolder, PostRecyclerAdapter> allPostModule;
    private ScrollTopModule.Nested scrollModule;

    @Override
    public int layoutId() {
        return R.layout.fragment_all_post;
    }

    @Override
    public Class<AllPostViewModel> vmClass() {
        return AllPostViewModel.class;
    }

    @Override
    public AllPostNavigation navigation() {
        return this;
    }

    @Override
    public int vmId() {
        return BR.allPostViewModel;
    }

    @Override
    protected void setupModule() {
        App.globalModule.changeScreen(MainStateModule.BaseState.CHILD_SCREEN, App.res.getString(R.string.all_post_page));
        refreshModule = new SwipeRefreshModule() {
            @Override
            protected SwipeRefreshLayout refreshLayout() {
                return binding.refresh;
            }

            @Override
            protected void update() {
                viewModel.getAllPost();
            }
        };
        allPostModule = new RecyclerModule<PostRecyclerAdapter.DataViewHolder, PostRecyclerAdapter>() {
            @Override
            protected RecyclerView recyclerView() {
                return binding.rvAllPost;
            }

            @Override
            protected PostRecyclerAdapter setAdapter() {
                return new PostRecyclerAdapter(getContext()) {
                    @Override
                    public void onPostClick(Post post) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("post", post);
                        navController.navigate(R.id.action_allPostFragment_to_postDetailFragment, bundle);
                    }

                    @Override
                    public void onTagClick(Tag tag) {

                    }
                };
            }

            @Override
            protected RecyclerView.LayoutManager setLayoutManager() {
                return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            }
        };
        scrollModule = new ScrollTopModule.Nested() {
            @Override
            protected NestedScrollView scrollView() {
                return binding.sc;
            }

            @Override
            protected void showButton() {
                viewModel.showTop();
            }

            @Override
            protected void hideButton() {
                viewModel.hideTop();
            }
        };
    }

    @Override
    public void getAllPostSuccess(PagedObject<Post> paged) {
        refreshModule.done();
        allPostModule.getAdapter().setPosts(paged.getContent());
    }

    @Override
    public void scrollTop() {
        scrollModule.top();
    }
}
