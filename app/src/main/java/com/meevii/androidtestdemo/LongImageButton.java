package com.meevii.androidtestdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.lang.ref.WeakReference;

public class LongImageButton extends android.support.v7.widget.AppCompatImageButton {

    private AutoUpHandler handler = new AutoUpHandler(new WeakReference<>(this));
    private AddListener addListener;
    private int add = 0;
    private int messageCount;

    private int duration = 300;

    public LongImageButton(Context context) {
        super(context);
    }

    public LongImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LongImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = event.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                handler.sendEmptyMessageDelayed(1, duration);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                handler.removeCallbacksAndMessages(null);
                if (messageCount < 1) {
                    performClick();
                }
                add = 0;
                messageCount = 0;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public static class AutoUpHandler extends Handler {
        private WeakReference<LongImageButton> mLongImageButton;

        AutoUpHandler(WeakReference<LongImageButton> longImageButton) {
            mLongImageButton = longImageButton;
        }

        @Override
        public void handleMessage(Message msg) {
            LongImageButton longImageButton = mLongImageButton.get();
            int messageCount = longImageButton.getMessageCount();
            int add = longImageButton.getAdd();
            messageCount++;
            add++;
            longImageButton.setAdd(add);
            longImageButton.setMessageCount(messageCount);
            sendEmptyMessageDelayed(1, longImageButton.getDuration());
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public int getAdd() {
        return add;
    }

    public void setAdd(int add) {
        this.add = add;
        if (addListener != null) {
            addListener.add(add);
        }
    }

    public void setAddListener(AddListener addListener) {
        this.addListener = addListener;
    }

    interface AddListener {
        void add(int add);
    }
}
