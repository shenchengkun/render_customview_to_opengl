package com.iglassusa.custom_gl_test;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.Display;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iglassusa.custom_gl_test.cuberenerer.CubeGLRenderer;
import com.iglassusa.custom_gl_test.iglassrender.GLRender;
import com.iglassusa.custom_gl_test.iglassrender.GLRender;
import com.iglassusa.custom_gl_test.iglassrender.Grid;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class DisplayPresentation extends Presentation {
    public  Context context;
    public  CubeGLRenderer viewToGlRenderer;
    private GLRenderable mGLLinearLayout;
    private WebView mWebView;
    private GLSurfaceView mGLSurfaceView;

    public GamePanel mv;
    private GLRender glRenderer;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public DisplayPresentation(Context outerContext, Display display) {
        super(outerContext, display);
        context=outerContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iglass_layout);

        viewToGlRenderer = new CubeGLRenderer(context);
        mGLLinearLayout = (GLRenderable) findViewById(R.id.gl_layout1);
        mGLSurfaceView = (GLSurfaceView) findViewById(R.id.gl_surface_view1);
        mGLSurfaceView.setEGLContextClientVersion(2);

        Grid grid=new Grid(20,20);
        glRenderer=new GLRender(getContext(),grid);//"android.resource://"+context.getPackageName()+"/raw/cat"

        mGLSurfaceView.setRenderer(glRenderer);
        mGLLinearLayout.setViewToGLRenderer(glRenderer);
        //mWebView.setWebViewClient(new WebViewClient());
        //mWebView.setWebChromeClient(new WebChromeClient());
        //mWebView.loadUrl("http://stackoverflow.com/questions/12499396/is-it-possible-to-render-an-android-view-to-an-opengl-fbo-or-texture");

        //DisplayMetrics metric = new DisplayMetrics();
        //getOwnerActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        //int width = metric.widthPixels;     // 屏幕宽度（像素）
        //int height = metric.heightPixels;   // 屏幕高度（像素）
        mv = (GamePanel)findViewById(R.id.mv1);
        //DisplayMetrics metric = new DisplayMetrics();
        //getDisplay().getMetrics(metric);
        //int width = metric.widthPixels;     // 屏幕宽度（像素）
        //int height = metric.heightPixels;   // 屏幕高度（像素）
        //mv.setBackgroundColor(Color.LTGRAY);
        //LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)mv.getLayoutParams();
        //params.width = width;
        //params.height = width;
        //mv.setLayoutParams(params);
    }

}
