package com.view.lp.androidview.stepcount;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.view.lp.androidview.R;

/**
 * 创建者：L.P
 * 创建时间：on 2018/1/23
 * 类描述：
 */

public class QQStepCountActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private QQStepCountView mQQStepCountView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_step_count);
        mQQStepCountView = (QQStepCountView) findViewById(R.id.qq_step);
        mQQStepCountView.setStepMax(4000);
    }
    public void onStatCount(View view){
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,4000);
        valueAnimator.setDuration(4000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){


            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                animation.getAnimatedValue();
                float currentStep = (float) animation.getAnimatedValue();
                mQQStepCountView.setCurrentStep((int) currentStep);
                //  mQQStepCountView.setCurrentStep(Integer.valueOf(animation.getAnimatedValue().toString()));
            }
        });
        valueAnimator.start();
    }
}
