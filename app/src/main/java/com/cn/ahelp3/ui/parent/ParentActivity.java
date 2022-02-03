package com.cn.ahelp3.ui.parent;

import android.os.Bundle;

import com.cn.ahelp3.BR;
import com.cn.ahelp3.R;
import com.cn.ahelp3.core.action.Returnable;
import com.cn.ahelp3.core.base.BaseActivity;
import com.cn.ahelp3.databinding.ActivityParentBinding;

public class ParentActivity extends BaseActivity<ActivityParentBinding, ParentNavigation,
        ParentViewModel> implements ParentNavigation {

    @Override
    public int layoutId() {
        return R.layout.activity_parent;
    }

    @Override
    public Class<ParentViewModel> vmClass() {
        return ParentViewModel.class;
    }

    @Override
    public ParentNavigation navigation() {
        return this;
    }

    @Override
    public int vmId() {
        return BR.parentViewModel;
    }

//    @Override
//    protected void initUI() {
//
//        String phoneNumber = "0927367827";
//
//        Bundle bundle = getIntent().getBundleExtra("bundle");
//        ((Returnable)bundle.getSerializable("NEW_PHONE_NUMBER")).execute(phoneNumber);
//    }
}