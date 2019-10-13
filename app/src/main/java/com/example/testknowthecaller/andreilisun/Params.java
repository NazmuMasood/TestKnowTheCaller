package com.example.testknowthecaller.andreilisun;
import android.graphics.Color;
/*import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;*/

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.LayoutRes;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

import android.view.View;

import com.example.testknowthecaller.R;

public class Params {
    public View view = null;
    @LayoutRes
    public int layoutRes = 0;
    @FloatRange(from = 0, to = 1.0)
    public float flingVelocity = 0.1f;
    @ColorInt
    public int overlayColor = 0;//Color.parseColor("#80444444");
    @Nullable
    public OnSwipeDismissListener swipeDismissListener;
    @Nullable
    public OnCancelListener cancelListener;
    @FloatRange(from = 0.0, to = 35.0)
    public float horizontalOscillation = 35.0f;
    public boolean dismissOnCancel = true;

}
