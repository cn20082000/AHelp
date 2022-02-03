package com.cn.ahelp3.ui.main.module;

public class MainStateModule {

    private BaseState baseState;
    private CoverState coverState;

    public MainStateModule() {
        baseState = BaseState.BASE_SCREEN;
        coverState = CoverState.NONE;
    }

    public BaseState getBaseState() {
        return baseState;
    }

    public void setBaseState(BaseState baseState) {
        this.baseState = baseState;
    }

    public CoverState getCoverState() {
        return coverState;
    }

    public void setCoverState(CoverState coverState) {
        this.coverState = coverState;
    }

    public enum BaseState {
        BASE_SCREEN,
        CHILD_SCREEN
    }

    public enum CoverState {
        NONE,
        SEARCH,
        NAVIGATION
    }
}
