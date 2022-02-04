package com.cn.ahelp3.ui.home;

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
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.data.model.Tag;
import com.cn.ahelp3.databinding.FragmentHomeBinding;
import com.cn.ahelp3.ui.post.adapter.PostRecyclerAdapter;
import com.cn.ahelp3.ui.main.module.MainStateModule;

import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeNavigation,
        HomeViewModel> implements HomeNavigation {

    private SwipeRefreshModule refreshModule;
    private RecyclerModule<PostRecyclerAdapter.DataViewHolder, PostRecyclerAdapter> lastUpdateModule;

    @Override
    public int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public Class<HomeViewModel> vmClass() {
        return HomeViewModel.class;
    }

    @Override
    public HomeNavigation navigation() {
        return this;
    }

    @Override
    public int vmId() {
        return BR.homeViewModel;
    }

    @Override
    protected void setupModule() {
        App.globalModule.changeScreen(MainStateModule.BaseState.BASE_SCREEN, App.res.getString(R.string.home_page));
        refreshModule = new SwipeRefreshModule() {
            @Override
            protected SwipeRefreshLayout refreshLayout() {
                return binding.refresh;
            }

            @Override
            protected void update() {
                viewModel.getLastUpdatedPost();
            }
        };
        lastUpdateModule = new RecyclerModule<PostRecyclerAdapter.DataViewHolder, PostRecyclerAdapter>() {
            @Override
            protected RecyclerView recyclerView() {
                return binding.rvLastUpdate;
            }

            @Override
            protected PostRecyclerAdapter setAdapter() {
                return new PostRecyclerAdapter(getContext()) {
                    @Override
                    public void onPostClick(Post post) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("post", post);
                        navController.navigate(R.id.action_homeFragment_to_postDetailFragment, bundle);
                    }

                    @Override
                    public void onTagClick(Tag tag) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tag", tag);
                        navController.navigate(R.id.action_homeFragment_to_singleTagFragment, bundle);
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
    public void getLastUpdatedPostSuccess(List<Post> list) {
        refreshModule.done();
        lastUpdateModule.getAdapter().setPosts(list);
    }

    @Override
    public void openAllPost() {
        navController.navigate(R.id.action_homeFragment_to_allPostFragment);
    }
}
