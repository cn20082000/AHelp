package com.cn.ahelp3.core.module;

import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;

public abstract class ScrollTopModule {

    public ScrollTopModule() {
        setupScroll();
    }

    private void setupScroll() {
        scrollView().setOnScrollChangeListener((view, i, i1, i2, i3) -> {
            if (i3 - i1 > 10 && i1 > 100) {
                showButton();
            } else if (i1 - i3 > 50 || i1 < 100) {
                hideButton();
            }
        });
        scrollView().setSmoothScrollingEnabled(true);
    }

    public void top() {
        // Cả 2 cái bên dưới đều chạy
        scrollView().fullScroll(ScrollView.FOCUS_UP);
//        binding.sc.smoothScrollTo(0, 0);
    }

    protected abstract ScrollView scrollView();

    protected abstract void showButton();

    protected abstract void hideButton();

    public static abstract class Nested {

        public Nested() {
            setupScroll();
        }

        private void setupScroll() {
            scrollView().setOnScrollChangeListener(
                    (NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (oldScrollY - scrollY > 10 && scrollY > 100) {
                    showButton();
                } else if (scrollY - oldScrollY > 50 || scrollY < 100) {
                    hideButton();
                }
            });
            scrollView().setSmoothScrollingEnabled(true);
        }

        public void top() {
            // Cả 2 cái bên dưới đều chạy
            scrollView().fullScroll(NestedScrollView.FOCUS_UP);
//        binding.sc.smoothScrollTo(0, 0);
        }

        protected abstract NestedScrollView scrollView();

        protected abstract void showButton();

        protected abstract void hideButton();
    }
}
