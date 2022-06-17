package com.mgtec.liao.recyclerviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.mgtec.liao.recyclerviewdemo.R;

public class HightLightView extends View {
    public HightLightView(Context context) {
        super(context);
    }

    public HightLightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HightLightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HightLightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(8f);
        canvas.drawPaint(paint);
        super.onDraw(canvas);
    }
}
