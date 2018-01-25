package com.nova.bedshow.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2017/12/11.
 */

public class CircularProgressDrawable extends Drawable implements Animatable {
    private static final Interpolator ANGLE_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator SWEEP_INTERPOLATOR = new DecelerateInterpolator();
    private static final int ANGLE_ANIMATOR_DURATION = 1000;
    private static final int SWEEP_ANIMATOR_DURATION = 500;
    private static final int MIN_SWEEP_ANGLE = 30;
    private final RectF fBounds = new RectF();

    private ObjectAnimator mObjectAnimatorSweep;
    private ObjectAnimator mObjectAnimatorAngle;
    private boolean mModeAppearing;
    private Paint mPaint;
    private float mCurrentGlobalAngleOffset;
    private float mCurrentGlobalAngle;

    public float getMCurrentSweepAngle() {
        return mCurrentSweepAngle;
    }

    public void setMCurrentSweepAngle(float mCurrentSweepAngle) {
        this.mCurrentSweepAngle = mCurrentSweepAngle;
        invalidateSelf();
    }

    public float getMCurrentGlobalAngle() {
        return mCurrentGlobalAngle;
    }

    public void setMCurrentGlobalAngle(float mCurrentGlobalAngle) {
        this.mCurrentGlobalAngle = mCurrentGlobalAngle;
        invalidateSelf();
    }

    private float mCurrentSweepAngle;
    private float mBorderWidth;
    private boolean mRunning;

    public CircularProgressDrawable(int color, float borderWidth) {
        mBorderWidth = borderWidth;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setColor(color);

        setupAnimations();
    }

    @Override
    public void draw(Canvas canvas) {
        // canvas.drawCircle(fBounds.centerX(),fBounds.centerY(),fBounds.bottom/2f,mPaint);
        // Paint p=new Paint();
        // p.setColor(Color.RED);
        // canvas.drawRect(fBounds,p);

        float startAngle = mCurrentGlobalAngle - mCurrentGlobalAngleOffset;
        float sweepAngle = mCurrentSweepAngle;
        if (!mModeAppearing) {
            startAngle = startAngle + sweepAngle;
            sweepAngle = 360 - sweepAngle - MIN_SWEEP_ANGLE;
        } else {
            sweepAngle += MIN_SWEEP_ANGLE;
        }
        canvas.drawArc(fBounds, startAngle, sweepAngle, false, mPaint);

    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    private void toggleAppearingMode() { // 切换显示的Mode:即改变头尾的位置
        mModeAppearing = !mModeAppearing;
        if (mModeAppearing) {
            mCurrentGlobalAngleOffset = (mCurrentGlobalAngleOffset + MIN_SWEEP_ANGLE * 2) % 360;// 改变
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        Log.i("onBoundsChange", "l,r,t,b" + bounds.left + "," + bounds.right
                + "," + bounds.top + "," + bounds.bottom);

        fBounds.left = bounds.left + mBorderWidth / 2f + .5f;
        fBounds.right = bounds.right - mBorderWidth / 2f - .5f;
        fBounds.top = bounds.top + mBorderWidth / 2f + .5f;
        fBounds.bottom = bounds.bottom - mBorderWidth / 2f - .5f;
    }

    // ////////////////////////////////////////////////////////////////////////////
    // Animation

    // private Property<CircularProgressDrawable , Float> mAngleProperty
    // = new Property<CircularProgressDrawable , Float>(Float.class, "angle") {
    // @Override
    // public Float get(CircularProgressDrawable object) {
    // return object.getCurrentGlobalAngle();
    // }
    //
    // @Override
    // public void set(CircularProgressDrawable object, Float value) {
    // object.setCurrentGlobalAngle(value);
    // }
    // };
    //
    // private Property<CircularProgressDrawable , Float> mSweepProperty
    // = new Property<CircularProgressDrawable , Float>(Float.class, "arc") {
    // @Override
    // public Float get(CircularProgressDrawable object) {
    // return object.getCurrentSweepAngle();
    // }
    //
    // @Override
    // public void set(CircularProgressDrawable object, Float value) {
    // object.setCurrentSweepAngle(value);
    // }
    // };

    private void setupAnimations() {
        mObjectAnimatorAngle = ObjectAnimator.ofFloat(this,
                "mCurrentGlobalAngle", 360f);
        mObjectAnimatorAngle.setInterpolator(ANGLE_INTERPOLATOR);
        mObjectAnimatorAngle.setDuration(ANGLE_ANIMATOR_DURATION);
        mObjectAnimatorAngle.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimatorAngle.setRepeatCount(ValueAnimator.INFINITE);

        mObjectAnimatorSweep = ObjectAnimator.ofFloat(this,
                "mCurrentSweepAngle", 360f - MIN_SWEEP_ANGLE * 2);
        mObjectAnimatorSweep.setInterpolator(SWEEP_INTERPOLATOR);
        mObjectAnimatorSweep.setDuration(SWEEP_ANIMATOR_DURATION);
        mObjectAnimatorSweep.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimatorSweep.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimatorSweep.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                toggleAppearingMode();
            }
        });
    }

    @Override
    public void start() {
        if (isRunning()) {
            return;
        }
        mRunning = true;
        mObjectAnimatorAngle.start();
        mObjectAnimatorSweep.start();
        invalidateSelf();
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            return;
        }
        mRunning = false;
        mObjectAnimatorAngle.cancel();
        mObjectAnimatorSweep.cancel();
        invalidateSelf();
    }

    @Override
    public boolean isRunning() {
        return mRunning;
    }

    public void setCurrentGlobalAngle(float currentGlobalAngle) {
        mCurrentGlobalAngle = currentGlobalAngle;
        invalidateSelf();
    }

    public float getCurrentGlobalAngle() {
        return mCurrentGlobalAngle;
    }

    public void setCurrentSweepAngle(float currentSweepAngle) {
        mCurrentSweepAngle = currentSweepAngle;
        invalidateSelf();
    }

    public float getCurrentSweepAngle() {
        return mCurrentSweepAngle;
    }

}
