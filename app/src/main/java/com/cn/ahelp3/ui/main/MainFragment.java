package com.cn.ahelp3.ui.main;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.ahelp3.BR;
import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseFragment;
import com.cn.ahelp3.core.listener.OnEdgeSwipeListener;
import com.cn.ahelp3.core.module.EdgeSwipeModule;
import com.cn.ahelp3.core.module.RecyclerModule;
import com.cn.ahelp3.databinding.FragmentMainBinding;
import com.cn.ahelp3.ui.main.adapter.NavRecyclerAdapter;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainNavigation,
        MainViewModel> implements MainNavigation {

    private EdgeSwipeModule edgeModule;
    private RecyclerModule<NavRecyclerAdapter.DataViewHolder, NavRecyclerAdapter> navModule;

    @Override
    public int layoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public Class<MainViewModel> vmClass() {
        return MainViewModel.class;
    }

    @Override
    public MainNavigation navigation() {
        return this;
    }

    @Override
    public int vmId() {
        return BR.mainViewModel;
    }

    @Override
    protected void setupModule() {
        App.globalModule = viewModel;
        edgeModule = new EdgeSwipeModule() {
            @Override
            protected View view() {
                return binding.vCover;
            }

            @Override
            protected OnEdgeSwipeListener onEdgeSwipeListener() {
                return new OnEdgeSwipeListener(getContext())
                        .setEdgeWith(30)
                        .setLeftEdgeAction(() -> viewModel.menuButtonClick());
            }
        };
        navModule = new RecyclerModule<NavRecyclerAdapter.DataViewHolder, NavRecyclerAdapter>() {
            @Override
            protected RecyclerView recyclerView() {
                return binding.rvNavigation;
            }

            @Override
            protected NavRecyclerAdapter setAdapter() {
                return new NavRecyclerAdapter(getContext())
                        .addNavItem(R.drawable.ic_home, App.res.getString(R.string.home_page), () -> {
                            viewModel.menuButtonClick();
                            Navigation.findNavController(binding.fragParent).popBackStack();
                            Navigation.findNavController(binding.fragParent).navigate(R.id.homeFragment);
                        })
                        .addNavItem(R.drawable.ic_category, App.res.getString(R.string.all_tag_page), () -> {
                            viewModel.menuButtonClick();
                            Navigation.findNavController(binding.fragParent).popBackStack();
                            Navigation.findNavController(binding.fragParent).navigate(R.id.allTagFragment);
                        });
            }

            @Override
            protected RecyclerView.LayoutManager setLayoutManager() {
                return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            }
        };
    }

    @Override
    public void backPress() {
        getActivity().onBackPressed();
    }
}
