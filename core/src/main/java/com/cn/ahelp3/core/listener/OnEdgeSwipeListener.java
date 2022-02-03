package com.cn.ahelp3.core.listener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.cn.ahelp3.core.action.EmptyAction;

public class OnEdgeSwipeListener implements View.OnTouchListener{

    private final GestureDetector gestureDetector;
    private final GestureListener gestureListener;
    private EmptyAction leftEdgeAction;
    private EmptyAction rightEdgeAction;
    private EmptyAction topEdgeAction;
    private EmptyAction bottomEdgeAction;
    private int edgeWith;

    public OnEdgeSwipeListener(Context context) {
        gestureListener = new GestureListener();
        gestureDetector = new GestureDetector(context, gestureListener);
        edgeWith = 20;
    }

    public OnEdgeSwipeListener setEdgeWith(int edgeWith) {
        this.edgeWith = edgeWith;
        return this;
    }

    public OnEdgeSwipeListener setLeftEdgeAction(EmptyAction leftEdgeAction) {
        this.leftEdgeAction = leftEdgeAction;
        return this;
    }

    public OnEdgeSwipeListener setRightEdgeAction(EmptyAction rightEdgeAction) {
        this.rightEdgeAction = rightEdgeAction;
        return this;
    }

    public OnEdgeSwipeListener setTopEdgeAction(EmptyAction topEdgeAction) {
        this.topEdgeAction = topEdgeAction;
        return this;
    }

    public OnEdgeSwipeListener setBottomEdgeAction(EmptyAction bottomEdgeAction) {
        this.bottomEdgeAction = bottomEdgeAction;
        return this;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureListener.setView(view);
        return gestureDetector.onTouchEvent(motionEvent);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        private View view;

        public void setView(View view) {
            this.view = view;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return (leftEdgeAction != null && e.getX() < edgeWith)
                    || (rightEdgeAction != null && view.getWidth() - e.getX() < edgeWith)
                    || (topEdgeAction != null && e.getY() < edgeWith)
                    || (bottomEdgeAction != null && view.getHeight() - e.getY() < edgeWith);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0 && leftEdgeAction != null && e1.getX() < edgeWith) {
                        leftEdgeAction.execute();
                        return true;
                    } else if (diffX < 0 && rightEdgeAction != null && view.getWidth() - e1.getX() < edgeWith) {
                        rightEdgeAction.execute();
                        return true;
                    }
                }
            } else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0 && topEdgeAction != null && e1.getY() < edgeWith) {
                        topEdgeAction.execute();
                        return true;
                    } else if (diffY < 0 && bottomEdgeAction != null && view.getHeight() - e1.getY() < edgeWith) {
                        bottomEdgeAction.execute();
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
