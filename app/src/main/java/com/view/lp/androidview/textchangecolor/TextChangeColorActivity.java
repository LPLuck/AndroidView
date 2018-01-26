package com.view.lp.androidview.textchangecolor;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.view.lp.androidview.R;


/**
 * 创建者：L.P
 * 创建时间：on 2018/1/24
 * 类描述：
 */

public class TextChangeColorActivity extends AppCompatActivity {

    TextChangeColorView mTextChangeColor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_change_color);
        mTextChangeColor = (TextChangeColorView) findViewById(R.id.text_change_color);
    }

    public void onLeftToRight(View view){
        mTextChangeColor.setCurrentDirection(TextChangeColorView.Direction.LEFT_TO_RIGHT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mTextChangeColor.setCurrentProgress(value);
            }
        });
        valueAnimator.start();
    }
    public void onRightToLeft(View view){
        mTextChangeColor.setCurrentDirection(TextChangeColorView.Direction.RIGHT_TOLEFT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mTextChangeColor.setCurrentProgress(value);
            }
        });
        valueAnimator.start();
    }

}
