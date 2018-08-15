package com.iglassusa.custom_gl_test;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class TestView extends TimeClock {

    private ViewToGLRenderer mViewToGLRenderer;

    // default constructors

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // draw magic
    @Override
    public void draw( Canvas canvas ) {
        //returns canvas attached to gl texture to draw on
        Canvas glAttachedCanvas = mViewToGLRenderer.onDrawViewBegin();
        if(glAttachedCanvas != null) {
            //translate canvas to reflect view scrolling
            float xScale = glAttachedCanvas.getWidth() / (float)canvas.getWidth();
            glAttachedCanvas.scale(xScale, xScale);
            glAttachedCanvas.translate(-getScrollX(), -getScrollY());
            //draw the view to provided canvas
            super.draw(glAttachedCanvas);
        }
        // notify the canvas is updated
        mViewToGLRenderer.onDrawViewEnd();
        postInvalidateDelayed(17);
    }

    public void setViewToGLRenderer(ViewToGLRenderer viewTOGLRenderer){
        mViewToGLRenderer = viewTOGLRenderer;
    }
}
