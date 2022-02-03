package com.cn.ahelp3.util.binding.obj;

public class VisibilityWAnimBindingObject {

    private int visibility;
    private int anim = -1;

    public int getVisibility() {
        return visibility;
    }

    public int getAnim() {
        return anim;
    }

    public static class Builder {
        private final VisibilityWAnimBindingObject obj = new VisibilityWAnimBindingObject();

        public Builder setVisibility(int visibility) {
            obj.visibility = visibility;
            return this;
        }

        public Builder setAnim(int anim) {
            obj.anim = anim;
            return this;
        }

        public VisibilityWAnimBindingObject build() {
            return obj;
        }
    }
}
