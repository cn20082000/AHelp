package com.cn.ahelp3.ui.main;

import android.view.View;

import androidx.databinding.ObservableField;

import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseViewModel;
import com.cn.ahelp3.ui.main.module.MainGlobalModule;
import com.cn.ahelp3.ui.main.module.MainStateModule;
import com.cn.ahelp3.util.binding.obj.VisibilityWAnimBindingObject;

public class MainViewModel extends BaseViewModel<MainNavigation> implements MainGlobalModule {

    private MainStateModule stateModule;

    public ObservableField<Integer> menuIcon = new ObservableField<>(R.drawable.ic_menu);
    public ObservableField<Float> menuRotate = new ObservableField<>(45f);
    public ObservableField<String> title = new ObservableField<>(App.res.getString(R.string.home_page));
    public ObservableField<String> searchKey = new ObservableField<>("");
    public ObservableField<VisibilityWAnimBindingObject> titleVisi
            = new ObservableField<>(new VisibilityWAnimBindingObject.Builder()
            .setVisibility(View.VISIBLE)
            .build());
    public ObservableField<VisibilityWAnimBindingObject> searchBoxVisi
            = new ObservableField<>(new VisibilityWAnimBindingObject.Builder()
            .setVisibility(View.GONE)
            .build());
    public ObservableField<VisibilityWAnimBindingObject> navVisi
            = new ObservableField<>(new VisibilityWAnimBindingObject.Builder()
            .setVisibility(View.GONE)
            .build());
    public ObservableField<VisibilityWAnimBindingObject> backNavVisi
            = new ObservableField<>(new VisibilityWAnimBindingObject.Builder()
            .setVisibility(View.GONE)
            .build());

    @Override
    protected void setupModule() {
        stateModule = new MainStateModule();
    }

    public void searchButtonClick() {
        switch (stateModule.getCoverState()) {
            case NONE: {
                stateModule.setCoverState(MainStateModule.CoverState.SEARCH);
                menuIcon.set(R.drawable.ic_close);
                menuRotate.set(0f);
                titleVisi.set(new VisibilityWAnimBindingObject.Builder()
                        .setVisibility(View.GONE)
                        .setAnim(R.anim.med_slide_out_left)
                        .build());
                searchBoxVisi.set(new VisibilityWAnimBindingObject.Builder()
                        .setVisibility(View.VISIBLE)
                        .setAnim(R.anim.med_slide_in_right)
                        .build());
                break;
            }
            case SEARCH: {
                break;
            }
        }
    }

    public void menuButtonClick() {
        switch (stateModule.getBaseState()) {
            case BASE_SCREEN: {
                switch (stateModule.getCoverState()) {
                    case NONE: {
                        stateModule.setCoverState(MainStateModule.CoverState.NAVIGATION);
                        menuIcon.set(R.drawable.ic_close);
                        menuRotate.set(0f);
                        navVisi.set(new VisibilityWAnimBindingObject.Builder()
                                .setVisibility(View.VISIBLE)
                                .setAnim(R.anim.long_slide_in_left)
                                .build());
                        backNavVisi.set(new VisibilityWAnimBindingObject.Builder()
                                .setVisibility(View.VISIBLE)
                                .setAnim(R.anim.long_fade_in)
                                .build());
                        break;
                    }
                    case SEARCH: {
                        stateModule.setCoverState(MainStateModule.CoverState.NONE);
                        menuIcon.set(R.drawable.ic_menu);
                        menuRotate.set(45f);
                        searchKey.set("");
                        titleVisi.set(new VisibilityWAnimBindingObject.Builder()
                                .setVisibility(View.VISIBLE)
                                .setAnim(R.anim.med_slide_in_left)
                                .build());
                        searchBoxVisi.set(new VisibilityWAnimBindingObject.Builder()
                                .setVisibility(View.GONE)
                                .setAnim(R.anim.med_slide_out_right)
                                .build());
                        break;
                    }
                    case NAVIGATION: {
                        stateModule.setCoverState(MainStateModule.CoverState.NONE);
                        menuIcon.set(R.drawable.ic_menu);
                        menuRotate.set(45f);
                        navVisi.set(new VisibilityWAnimBindingObject.Builder()
                                .setVisibility(View.GONE)
                                .setAnim(R.anim.long_slide_out_left)
                                .build());
                        backNavVisi.set(new VisibilityWAnimBindingObject.Builder()
                                .setVisibility(View.GONE)
                                .setAnim(R.anim.long_fade_out)
                                .build());
                        break;
                    }
                }
                break;
            }
            case CHILD_SCREEN: {
                switch (stateModule.getCoverState()) {
                    case NONE: {
                        nav.backPress();
                        break;
                    }
                    case SEARCH: {
                        stateModule.setCoverState(MainStateModule.CoverState.NONE);
                        menuIcon.set(R.drawable.ic_navigate_left);
                        menuRotate.set(0f);
                        searchKey.set("");
                        titleVisi.set(new VisibilityWAnimBindingObject.Builder()
                                .setVisibility(View.VISIBLE)
                                .setAnim(R.anim.med_slide_in_left)
                                .build());
                        searchBoxVisi.set(new VisibilityWAnimBindingObject.Builder()
                                .setVisibility(View.GONE)
                                .setAnim(R.anim.med_slide_out_right)
                                .build());
                        break;
                    }
                }
                break;
            }
        }
    }

    public void backNavButtonClick() {
        menuButtonClick();
    }

    @Override
    public void changeScreen(MainStateModule.BaseState state, String ti) {
        switch (stateModule.getBaseState()) {
            case BASE_SCREEN: {
                switch (stateModule.getCoverState()) {
                    case NAVIGATION:
                    case SEARCH: {
                        menuButtonClick();
                        break;
                    }
                }
                if (state == MainStateModule.BaseState.CHILD_SCREEN) {
                    stateModule.setBaseState(MainStateModule.BaseState.CHILD_SCREEN);
                    menuIcon.set(R.drawable.ic_navigate_left);
                    menuRotate.set(0f);
                }
                title.set(ti);
                break;
            }
            case CHILD_SCREEN: {
                switch (stateModule.getCoverState()) {
                    case NAVIGATION:
                    case SEARCH: {
                        menuButtonClick();
                        break;
                    }
                }
                if (state == MainStateModule.BaseState.BASE_SCREEN) {
                    stateModule.setBaseState(MainStateModule.BaseState.BASE_SCREEN);
                    menuIcon.set(R.drawable.ic_menu);
                    menuRotate.set(45f);
                }
                title.set(ti);
                break;
            }
        }
    }
}
