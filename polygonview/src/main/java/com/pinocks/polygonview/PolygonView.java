package com.pinocks.polygonview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * PolygonView
 */
public class PolygonView extends FrameLayout{

    private PolygonViewEventListener polygonViewEventListener;
    private int buttonsColor = Color.WHITE;
    private Drawable buttonCenterDrawable;
    private Drawable buttonTopLeftDrawable;
    private Drawable buttonTopRightDrawable;
    private Drawable buttonBottomLeftDrawable;
    private Drawable buttonBottomRightDrawable;
    private View rootView;
    private LinearLayout topLeftLayout;
    private LinearLayout centerLayout;
    private LinearLayout topRightLayout;
    private LinearLayout bottomLeftLayout;
    private LinearLayout bottomRightLayout;
    private AppCompatImageView centerImageView;
    private AppCompatImageView topLeftImageView;
    private AppCompatImageView topRightImageView;
    private AppCompatImageView bottomLeftImageView;
    private AppCompatImageView bottomRightImageView;
    private Context context;
    private LinearLayout bottomMask;
    private LinearLayout topMask;
    private LinearLayout leftMask;
    private LinearLayout rightMask;

    public PolygonView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public PolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PolygonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }


    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.context=context;
        rootView = inflate(context, R.layout.layout_polygon_view, this);
        centerLayout = rootView.findViewById(R.id.layout_center);
        centerLayout = rootView.findViewById(R.id.layout_center);
        topLeftLayout = rootView.findViewById(R.id.layout_top_left);
        topRightLayout = rootView.findViewById(R.id.layout_top_right);
        bottomLeftLayout = rootView.findViewById(R.id.layout_bottom_left);
        bottomRightLayout = rootView.findViewById(R.id.layout_bottom_right);
        bottomMask = rootView.findViewById(R.id.mask_bottom);
        topMask = rootView.findViewById(R.id.mask_top);
        leftMask = rootView.findViewById(R.id.mask_left);
        rightMask = rootView.findViewById(R.id.mask_right);
        centerImageView = rootView.findViewById(R.id.center_image_view);
        topLeftImageView = rootView.findViewById(R.id.top_left_image_view);
        topRightImageView = rootView.findViewById(R.id.top_right_image_view);
        bottomLeftImageView = rootView.findViewById(R.id.bottom_left_image_view);
        bottomRightImageView = rootView.findViewById(R.id.bottom_right_image_view);



        leftMask.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        bottomLeftLayout.setBackground(getResources().getDrawable(R.drawable.left_bottom_button_shape_selected));
                    }else{
                        bottomLeftLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_bottom_button_shape_selected));
                    }
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        bottomLeftLayout.setBackground(getResources().getDrawable(R.drawable.left_bottom_button));
                    }else{
                        bottomLeftLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_bottom_button));
                    }

                    if(polygonViewEventListener!=null)
                        polygonViewEventListener.onClickBottomLeftButton();
                }
                return false;

            }
        });
        rightMask.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        topRightLayout.setBackground(getResources().getDrawable(R.drawable.right_top_button_shape_selected));
                    }else{
                        topRightLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_top_button_shape_selected));
                    }
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        topRightLayout.setBackground(getResources().getDrawable(R.drawable.right_top_button));
                    }else{
                        topRightLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_top_button));
                    }
                    if(polygonViewEventListener!=null)
                        polygonViewEventListener.onClickTopRightButton();
                }
                return false;

            }
        });
        centerLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(polygonViewEventListener!=null)
                    polygonViewEventListener.onClickCenterButton();
            }
        });
        topLeftLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(polygonViewEventListener!=null)
                    polygonViewEventListener.onClickTopLeftButton();
            }
        });
        topRightLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(polygonViewEventListener!=null)
                    polygonViewEventListener.onClickTopRightButton();
            }
        });
        bottomLeftLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(polygonViewEventListener!=null)
                    polygonViewEventListener.onClickBottomLeftButton();
            }
        });
        bottomRightLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(polygonViewEventListener!=null)
                    polygonViewEventListener.onClickBottomRightButton();
            }
        });

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PolygonView, defStyle, 0);


        if (a.hasValue(R.styleable.PolygonView_buttonCenterDrawable)) {
            buttonCenterDrawable = a.getDrawable(
                    R.styleable.PolygonView_buttonCenterDrawable);
            buttonCenterDrawable.setCallback(this);
            centerImageView.setImageDrawable(buttonCenterDrawable);
        }
        if (a.hasValue(R.styleable.PolygonView_buttonTopLeftDrawable)) {
            buttonTopLeftDrawable = a.getDrawable(
                    R.styleable.PolygonView_buttonTopLeftDrawable);
            buttonTopLeftDrawable.setCallback(this);
            topLeftImageView.setImageDrawable(buttonTopLeftDrawable);
        }
        if (a.hasValue(R.styleable.PolygonView_buttonTopRightDrawable)) {
            buttonTopRightDrawable = a.getDrawable(
                    R.styleable.PolygonView_buttonTopRightDrawable);
            buttonTopRightDrawable.setCallback(this);
            topRightImageView.setImageDrawable(buttonTopRightDrawable);
        }
        if (a.hasValue(R.styleable.PolygonView_buttonBottomLeftDrawable)) {
            buttonBottomLeftDrawable = a.getDrawable(
                    R.styleable.PolygonView_buttonBottomLeftDrawable);
            buttonBottomLeftDrawable.setCallback(this);
            bottomLeftImageView.setImageDrawable(buttonBottomLeftDrawable);
        }
        if (a.hasValue(R.styleable.PolygonView_buttonBottomRightDrawable)) {
            buttonBottomRightDrawable = a.getDrawable(
                    R.styleable.PolygonView_buttonBottomRightDrawable);
            buttonBottomRightDrawable.setCallback(this);
            bottomRightImageView.setImageDrawable(buttonBottomRightDrawable);
        }

        setDisplayLayout();

        this.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        a.recycle();
    }

    public int getLayoutHeight(){
        if (getHeight()>0){
            return getHeight();
        }
        else if(getLayoutParams() != null && getLayoutParams().height>0){
            return getLayoutParams().height;
        }
        return 1080;

    }
    public int getLayoutWidth(){
        if (getWidth()>0){
            return getWidth();
        }else if(getLayoutParams() != null && getLayoutParams().width>0){
            return getLayoutParams().width;
        }
        return 1080;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setDisplayLayout();
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setDisplayLayout();
    }

    private void setDisplayLayout(){

        int w=getLayoutWidth();
        int h=getLayoutHeight();
        Log.d("LOG_POLY","width:"+w);
        Log.d("LOG_POLY","height:"+h);

        double multiplier= (double) w/197.08;

        int centerWidth= (int) (114*multiplier);
        int centerHeight= (int) (114*multiplier);
        int centerFullWidth= (int) ((114/1.42)*multiplier);
        int centerFullHeight= (int) ((114/1.42)*multiplier);

        int topLeftWidth= (int) (97*multiplier);
        int topLeftHeight= (int) (154*multiplier);

        int topRightWidth= (int) (97*multiplier);
        int topRightHeight= (int) (194*multiplier);

        int bottomLeftWidth= (int) (97*multiplier);
        int bottomLeftHeight= (int) (194*multiplier);

        int bottomRightWidth= (int) (97*multiplier);
        int bottomRightHeight= (int) (154*multiplier);


        int centerX=(w/2)-(centerWidth/2);
        int centerY=(h/2)-(centerHeight/2);
        int centerMaxX=(w/2)+(centerWidth/2);
        int centerMaxY=(h/2)+(centerHeight/2);

        int centerFullX=(w/2)-(centerFullWidth/2);
        int centerFullY=(h/2)-(centerFullHeight/2);

        FrameLayout.LayoutParams centerParams = new FrameLayout.LayoutParams(centerFullWidth, centerFullHeight);
        centerParams.leftMargin = centerFullX;
        centerParams.topMargin = centerFullY;
        FrameLayout.LayoutParams topLeftParams = new FrameLayout.LayoutParams(topLeftWidth, topLeftHeight);
        topLeftParams.leftMargin = (int) (centerX+(centerWidth/2.04)-topLeftWidth);
        topLeftParams.topMargin = (int) (centerY+(centerHeight/2.05)-topLeftHeight);
        FrameLayout.LayoutParams topRightLayoutParams = new FrameLayout.LayoutParams(topRightWidth, topRightHeight);
        topRightLayoutParams.leftMargin = (int) (centerMaxX+(centerHeight/2.75)-topRightWidth);
        topRightLayoutParams.topMargin = (int) (centerY+(centerHeight/1.2)-topRightHeight);
        FrameLayout.LayoutParams bottomLeftLayoutParams = new FrameLayout.LayoutParams(bottomLeftWidth, bottomLeftHeight);
        bottomLeftLayoutParams.leftMargin = (int) (centerX+(centerHeight/2.04)-bottomLeftWidth);
        bottomLeftLayoutParams.topMargin = (int) (centerMaxY+(centerHeight/1.15)-bottomLeftHeight);
        FrameLayout.LayoutParams bottomRightLayoutParams = new FrameLayout.LayoutParams(bottomRightWidth, bottomRightHeight);
        bottomRightLayoutParams.leftMargin = (int) (centerMaxX+(centerHeight/2.74)-bottomRightWidth);
        bottomRightLayoutParams.topMargin = (int) (centerMaxY+(centerHeight/1.153)-bottomRightHeight);

        this.updateViewLayout(centerLayout, centerParams);
        this.updateViewLayout(topLeftLayout, topLeftParams);
        this.updateViewLayout(topRightLayout, topRightLayoutParams);
        this.updateViewLayout(bottomLeftLayout, bottomLeftLayoutParams);
        this.updateViewLayout(bottomRightLayout, bottomRightLayoutParams);

        FrameLayout.LayoutParams bottomMaskParams = new FrameLayout.LayoutParams(bottomLeftHeight, bottomLeftHeight);
        bottomMaskParams.leftMargin = (int) (centerX+(centerHeight/1.98)-bottomLeftWidth);
        bottomMaskParams.topMargin = (int) (centerMaxY+(centerHeight/0.83)-bottomLeftWidth);
        this.updateViewLayout(bottomMask, bottomMaskParams);

        FrameLayout.LayoutParams topMaskParams = new FrameLayout.LayoutParams(bottomLeftHeight, bottomLeftHeight);
        topMaskParams.leftMargin = (int) (centerX-(bottomLeftWidth/2.42));
        topMaskParams.topMargin = (int) (centerY-(bottomLeftWidth*2.43));
        this.updateViewLayout(topMask, topMaskParams);

        FrameLayout.LayoutParams leftMaskParams = new FrameLayout.LayoutParams(bottomLeftWidth, bottomLeftWidth);
        leftMaskParams.leftMargin = (int) (centerX+(centerHeight/4.06)-bottomLeftWidth/0.682);
        leftMaskParams.topMargin = (int) (centerMaxY+(centerHeight/1.2)-bottomLeftHeight);
        this.updateViewLayout(leftMask, leftMaskParams);

        FrameLayout.LayoutParams rightMaskParams = new FrameLayout.LayoutParams(bottomLeftWidth, bottomLeftWidth);
        rightMaskParams.leftMargin = (int) (centerMaxX+(centerHeight/1.01)-bottomLeftWidth);
        rightMaskParams.topMargin = (int) (centerMaxY+(centerHeight/1.4)-bottomLeftHeight);
        this.updateViewLayout(rightMask, rightMaskParams);


        LinearLayout.LayoutParams topLeftDrawableParams = new LinearLayout.LayoutParams(centerWidth/5,centerWidth/5);
        topLeftDrawableParams.leftMargin = -topLeftWidth/4;
        topLeftDrawableParams.topMargin = topLeftHeight/10;
        topLeftLayout.updateViewLayout(topLeftImageView, topLeftDrawableParams);
        LinearLayout.LayoutParams topRightDrawableParams = new LinearLayout.LayoutParams(centerWidth/5,centerWidth/5);
        topRightDrawableParams.leftMargin = topRightWidth/5;
        topRightLayout.updateViewLayout(topRightImageView, topRightDrawableParams);

        LinearLayout.LayoutParams bottomLeftDrawableParams = new LinearLayout.LayoutParams(centerWidth/5,centerWidth/5);
        bottomLeftDrawableParams.leftMargin = -topLeftWidth/4;
        bottomLeftLayout.updateViewLayout(bottomLeftImageView, bottomLeftDrawableParams);

        LinearLayout.LayoutParams bottomRightDrawableParams = new LinearLayout.LayoutParams(centerWidth/5,centerWidth/5);
        bottomRightDrawableParams.leftMargin = topRightWidth/5;
        bottomRightDrawableParams.topMargin = -topLeftHeight/10;
        bottomRightLayout.updateViewLayout(bottomRightImageView, bottomRightDrawableParams);

    }

    public void setPolygonViewEventListener(PolygonViewEventListener polygonViewEventListener) {
        this.polygonViewEventListener = polygonViewEventListener;
    }
}

