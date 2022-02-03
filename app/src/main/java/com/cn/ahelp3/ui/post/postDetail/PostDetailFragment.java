package com.cn.ahelp3.ui.post.postDetail;

import android.webkit.WebView;
import android.widget.ScrollView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cn.ahelp3.BR;
import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseFragment;
import com.cn.ahelp3.core.module.ContentWebViewModule;
import com.cn.ahelp3.core.module.ScrollTopModule;
import com.cn.ahelp3.core.module.SwipeRefreshModule;
import com.cn.ahelp3.core.util.FileIO;
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.data.model.PostDetail;
import com.cn.ahelp3.databinding.FragmentPostDetailBinding;
import com.cn.ahelp3.ui.main.module.MainStateModule;

public class PostDetailFragment extends BaseFragment<FragmentPostDetailBinding, PostDetailNavigation,
        PostDetailViewModel> implements PostDetailNavigation {

    private Post post;
    private SwipeRefreshModule refreshModule;
    private ContentWebViewModule webModule;
    private ScrollTopModule scrollModule;

    @Override
    public int layoutId() {
        return R.layout.fragment_post_detail;
    }

    @Override
    public Class<PostDetailViewModel> vmClass() {
        return PostDetailViewModel.class;
    }

    @Override
    public PostDetailNavigation navigation() {
        return this;
    }

    @Override
    public int vmId() {
        return BR.postDetailViewModel;
    }

    @Override
    protected void setupModule() {
        App.globalModule.changeScreen(MainStateModule.BaseState.CHILD_SCREEN, App.res.getString(R.string.post_detail_page));
        post = (Post) argument.getSerializable("post");
        refreshModule = new SwipeRefreshModule() {
            @Override
            protected SwipeRefreshLayout refreshLayout() {
                return binding.refresh;
            }

            @Override
            protected void update() {
                viewModel.getPost(post);
            }
        };
        webModule = new ContentWebViewModule() {
            @Override
            protected WebView webView() {
                return binding.wvContent;
            }
        };
        scrollModule = new ScrollTopModule() {
            @Override
            protected ScrollView scrollView() {
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
    public void getPostSuccess(PostDetail postDetail) {
        refreshModule.done();
        webModule.setContent(() -> {
            String s = FileIO.readAsString(getContext(), "contentTemplate.html");
            s = s.replace("{0}", postDetail.getContent());
            return s;
        });
    }

    @Override
    public void scrollTop() {
        scrollModule.top();
    }
}
