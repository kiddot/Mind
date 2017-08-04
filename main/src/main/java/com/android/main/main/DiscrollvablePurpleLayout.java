package com.android.main.main;

/**
 * Created by kiddo on 17-8-4.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.main.R;
import com.flavienlaurent.discrollview.lib.Discrollvable;

/**
 *
 */
public class DiscrollvablePurpleLayout extends LinearLayout implements Discrollvable {

    private static final String TAG = "DiscrollvablePurpleLayout";

    private View mPurpleView1;
    private View mPurpleView2;

    private float mPurpleView1TranslationX;
    private float mPurpleView2TranslationX;

    public DiscrollvablePurpleLayout(Context context) {
        super(context);
    }

    public DiscrollvablePurpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DiscrollvablePurpleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mPurpleView1 = findViewById(R.id.purpleView1);
        if (mPurpleView1 != null)
            mPurpleView1TranslationX = mPurpleView1.getTranslationX();
        mPurpleView2 = findViewById(R.id.purpleView2);
        if (mPurpleView2 != null)
            mPurpleView2TranslationX = mPurpleView2.getTranslationX();
    }

    @Override
    public void onResetDiscrollve() {
        if (mPurpleView1 != null)
            mPurpleView1.setAlpha(0);
        if (mPurpleView2 != null)
            mPurpleView2.setAlpha(0);
        if (mPurpleView1 != null)
            mPurpleView1.setTranslationX(mPurpleView1TranslationX);
        if (mPurpleView2 != null)
            mPurpleView2.setTranslationX(mPurpleView2TranslationX);
    }

    @Override
    public void onDiscrollve(float ratio) {
        if(ratio <= 0.5f) {
            if (mPurpleView2 != null){
                mPurpleView2.setTranslationX(0);
                mPurpleView2.setAlpha(0.0f);
            }
            float rratio = ratio / 0.5f;
            if (mPurpleView1 != null){
                mPurpleView1.setTranslationX(mPurpleView1TranslationX * (1 - rratio));
                mPurpleView1.setAlpha(rratio);
            }
        } else {
            if (mPurpleView1 != null) {
                mPurpleView1.setTranslationX(0);
                mPurpleView1.setAlpha(1.0f);
            }
            float rratio = (ratio - 0.5f) / 0.5f;
            if (mPurpleView2 != null) {
                mPurpleView2.setTranslationX(mPurpleView2TranslationX * (1 - rratio));
                mPurpleView2.setAlpha(rratio);
            }
        }
    }
}
