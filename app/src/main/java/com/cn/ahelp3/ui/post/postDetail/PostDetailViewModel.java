package com.cn.ahelp3.ui.post.postDetail;

import android.view.View;

import androidx.databinding.ObservableField;

import com.cn.ahelp3.R;
import com.cn.ahelp3.common.App;
import com.cn.ahelp3.core.base.BaseViewModel;
import com.cn.ahelp3.core.util.Lg;
import com.cn.ahelp3.data.model.Post;
import com.cn.ahelp3.util.binding.obj.VisibilityWAnimBindingObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PostDetailViewModel extends BaseViewModel<PostDetailNavigation> {

    private boolean isShowTop = false;

    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> subTitle = new ObservableField<>("");
    public ObservableField<VisibilityWAnimBindingObject> topVisi
            = new ObservableField<>(new VisibilityWAnimBindingObject.Builder()
            .setVisibility(View.GONE)
            .build());
    public ObservableField<String> info = new ObservableField<>("");

    public void getPost(Post post) {
        App.dataManager.getPost(post.getId())
                .whenSuccess(data -> {
                    title.set(data.getTitle());

                    String sub = "";
                    sub += Post.getTypeStr(data.getType());
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.ENGLISH);
                    sub += ", " + App.res.getString(R.string.updated_at_time) + " " + formatter.format(data.getUpdatedAt().getTime());
                    subTitle.set(sub);

                    info.set(data.getInfo());

                    nav.getPostSuccess(data);
                })
                .whenFailure(error -> Lg.ar(error.getStatusCode(), error.getMessage()))
                .call();
    }

    public void showTop() {
        if (!isShowTop) {
            isShowTop = true;
            topVisi.set(new VisibilityWAnimBindingObject.Builder()
                    .setVisibility(View.VISIBLE)
                    .setAnim(R.anim.med_float_in_up)
                    .build());
        }
    }

    public void hideTop() {
        if (isShowTop) {
            isShowTop = false;
            topVisi.set(new VisibilityWAnimBindingObject.Builder()
                    .setVisibility(View.GONE)
                    .setAnim(R.anim.med_dive_out_down)
                    .build());
        }
    }

    public void scrollTopClick() {
        nav.scrollTop();
    }
}
