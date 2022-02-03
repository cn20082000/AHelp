package com.cn.ahelp3.core.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseActivity<DB extends ViewDataBinding, NA extends BaseNavigation,
        VM extends BaseViewModel<NA>> extends AppCompatActivity {

    protected DB binding;
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        preCreatedSetupModule();
        super.onCreate(savedInstanceState);
        setupMVVM();
        setupModule();
        initUI();
    }

    private void setupMVVM() {
        binding = DataBindingUtil.setContentView(this, layoutId());
        viewModel = new ViewModelProvider(this).get(vmClass());
        viewModel.setNav(navigation());
        binding.setVariable(vmId(), viewModel);
        binding.executePendingBindings();
    }

    protected void initUI() {}

    protected void setupModule() {}

    protected void preCreatedSetupModule() {}

    public abstract int layoutId();

    public abstract Class<VM> vmClass();

    public abstract NA navigation();

    public abstract int vmId();
}
