package com.meevii.customview.AutoNumber;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;


public class AutoNumberTextView extends android.support.v7.widget.AppCompatTextView {

    private long    duration  = 3000;
    private int     newNumber;
    private int     currentNumber;
    private boolean animation = true;
    private long    delayDuration;

    private Handler      autoHandler = new Handler();
    private AutoRunnable runnable    = new AutoRunnable();

    public AutoNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNumber(int number, boolean animation) {
        this.animation = animation;
        this.newNumber = number;
        delayDuration = duration / number;
        if (!animation) {
            setText(String.valueOf(number));
            return;
        }
        autoHandler.postDelayed(runnable, delayDuration);
    }

    public void setNumber(int number) {
        setNumber(number, true);
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getNumber() {
        return newNumber;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public void enalbe(boolean animation) {
        this.animation = animation;
    }

    public class AutoRunnable implements Runnable {

        @Override
        public void run() {
            currentNumber++;
            setText(String.valueOf(currentNumber));
            if (currentNumber < newNumber) {
                postDelayed(this, delayDuration);
            } else if (currentNumber == newNumber) {
                setText(String.valueOf(newNumber));
            }
        }
    }

}
