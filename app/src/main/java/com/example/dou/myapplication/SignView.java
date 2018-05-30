package com.example.dou.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @name MyApplication
 * @class name：com.example.dou.myapplication
 * @anthor dou
 * @time 2018/5/30 22:54
 * @class describe
 */

public class SignView extends View {
    private Paint mPaint;
    private Path mPath = new Path();


    public SignView(Context context) {
        super(context);
        init();
    }

    public SignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            touchDown(event);
        } else if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            touchMove(event);
        }
        return true;
    }


    private void touchMove(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        mPath.lineTo(x, y);
        invalidate();
    }

    private void touchDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        mPath.moveTo(x, y);
        invalidate();
    }


    private void init() {
        setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.black));
        mPaint.setStrokeWidth(4);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);//防抖动
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void clear() {
        mPath.reset();
        invalidate();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        setDrawingCacheEnabled(false);
    }


    public Bitmap getSign() {
        setDrawingCacheEnabled(true);
        return getDrawingCache();
    }
}
