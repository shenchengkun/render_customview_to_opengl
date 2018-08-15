package com.iglassusa.custom_gl_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.display.DisplayManager;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.iglassusa.custom_gl_test.cuberenerer.CubeGLRenderer;


public class MainActivity extends Activity {
    private GamePanel mv;
    private Button left,up,right,down;
    private Button start;
    private Button gameContinue;
    private Button gameStop;

    private DisplayPresentation mPresentation;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initViews() {
        setContentView(R.layout.activity_main);

        DisplayManager mDisplayManager= (DisplayManager) this.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays=mDisplayManager.getDisplays();
        mPresentation = new DisplayPresentation(this,displays[1]);// displays[1]是副屏
        if(Build.VERSION.SDK_INT >= 26) mPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        else mPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        mPresentation.show();

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        mv = (GamePanel)findViewById(R.id.mv);
        mv.setBackgroundColor(Color.LTGRAY);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mv.getLayoutParams();
        params.width = width;
        params.height = width;
        mv.setLayoutParams(params);

        mv=mPresentation.mv;
        left=(Button)findViewById(R.id.left);
        up=(Button)findViewById(R.id.up);
        right=(Button)findViewById(R.id.right);
        down=(Button)findViewById(R.id.down);
        left.setWidth(width/4);
        left.setHeight(width/5);
        up.setWidth(width/4);
        up.setHeight(width/5);
        right.setWidth(width/4);
        right.setHeight(width/5);
        down.setWidth(width/4);
        down.setHeight(width/5);
        left.setX(width/2-width/8-width/5);
        left.setY(width+(height-width)/3+width/10);
        up.setX(width/2-width/8);
        up.setY(width+(height-width)/3-width/20);
        right.setX(width/2-width/8+width/5);
        right.setY(width+(height-width)/3+width/10);
        down.setX(width/2-width/8);
        down.setY(width+(height-width)/3+width/5+width/20);
        start=(Button)findViewById(R.id.start);
        start.setOnTouchListener(mv);
        gameContinue=(Button)findViewById(R.id.gameContinue);
        gameContinue.setOnTouchListener(mv);
        gameStop=(Button)findViewById(R.id.gameStop);
        gameStop.setOnTouchListener(mv);
        left.setOnTouchListener(mv);
        up.setOnTouchListener(mv);
        right.setOnTouchListener(mv);
        down.setOnTouchListener(mv);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mv.start=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresentation != null) {
            mPresentation.cancel();
            mPresentation.dismiss();
            mPresentation.onDetachedFromWindow();
            mPresentation = null;
        }
    }
}
