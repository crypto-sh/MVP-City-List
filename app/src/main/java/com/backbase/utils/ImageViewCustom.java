package com.backbase.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.backbase.R;

public class ImageViewCustom extends ImageView {

    Boolean imageSquare = false;

    public ImageViewCustom(Context context) {
        super(context);
    }

    public ImageViewCustom(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageViewCustom);
        imageSquare = typedArray.getBoolean(R.styleable.ImageViewCustom_image_square,false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (imageSquare){
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }else {
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }
    }
}

