package com.cn.ahelp3.ui.wakeup;

import com.cn.ahelp3.BR;
import com.cn.ahelp3.R;
import com.cn.ahelp3.core.base.BaseFragment;
import com.cn.ahelp3.databinding.FragmentWakeUpBinding;

public class WakeUpFragment extends BaseFragment<FragmentWakeUpBinding, WakeUpNavigation,
        WakeUpViewModel> implements WakeUpNavigation {

    @Override
    public int layoutId() {
        return R.layout.fragment_wake_up;
    }

    @Override
    public Class<WakeUpViewModel> vmClass() {
        return WakeUpViewModel.class;
    }

    @Override
    public WakeUpNavigation navigation() {
        return this;
    }

    @Override
    public int vmId() {
        return BR.wakeUpViewModel;
    }

    @Override
    protected void initUI() {
        viewModel.wakeServerUp();
    }

    @Override
    public void wakeServerUpSuccess() {
        navController.navigate(R.id.action_wakeUpFragment_to_mainFragment);
    }
}
