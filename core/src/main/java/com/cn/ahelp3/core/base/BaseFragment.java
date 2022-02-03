package com.cn.ahelp3.core.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public abstract class BaseFragment<DB extends ViewDataBinding, NA extends BaseNavigation,
        VM extends BaseViewModel<NA>> extends Fragment {

    protected DB binding;
    protected VM viewModel;
    protected NavController navController;
    protected Bundle argument;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        preCreatedSetupModule();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setupMVVM(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupModule();
        initUI();
    }

    private void setupMVVM(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        viewModel = new ViewModelProvider(this).get(vmClass());
        viewModel.setNav(navigation());
        binding.setVariable(vmId(), viewModel);
        binding.executePendingBindings();
        navController = NavHostFragment.findNavController(this);
        argument = getArguments();
    }

    protected void initUI() {}

    protected void setupModule() {}

    protected void preCreatedSetupModule() {}

    public abstract int layoutId();

    public abstract Class<VM> vmClass();

    public abstract NA navigation();

    public abstract int vmId();
}
