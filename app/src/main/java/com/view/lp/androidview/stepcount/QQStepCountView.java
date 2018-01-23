package com.view.lp.androidview.stepcount;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.view.lp.androidview.R;
import com.view.lp.androidview.ScreenUtil;

/**
 * 创建者：L.P
 * 创建时间：on 2018/1/23
 * 类描述：计步器和原型进度条
 */

public class QQStepCountView extends View {

    private final String TAG = this.getClass().getSimpleName();
    private int mOuterColor = Color.parseColor("#3F51B5");//默认值  蓝
    private int mInnerColor = Color.parseColor("#FF4081");//默认值  红
    private int mBorderWidth = 5;//dp
    private int mStepTextColor = Color.parseColor("#FF4081");
    private int mStepTextSize = 20;//sp
    private Paint mOuterPaint,mInnerPaint,mTestPaint;
    private int mStepMax = 100;//最大值
    private int mStepCurrent;//当前值
    private int mStartAngle = 135;
    private int mEndAngle = 270;


    public QQStepCountView(Context context) {
        this(context,null);
    }

    public QQStepCountView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQStepCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepCountView);
        mOuterColor = array.getColor(R.styleable.QQStepCountView_outerColor,mOuterColor);
        mInnerColor = array.getColor(R.styleable.QQStepCountView_innerColor,mInnerColor);
        mBorderWidth = array.getDimensionPixelSize(R.styleable.QQStepCountView_borderWidth,mBorderWidth);
        mStepTextColor = array.getColor(R.styleable.QQStepCountView_stepTextColor,mStepTextColor);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.QQStepCountView_stepTextSize,mStepTextSize);
        mStartAngle = array.getInteger(R.styleable.QQStepCountView_stepStartAngle,mStartAngle);
        mEndAngle =  array.getInteger(R.styleable.QQStepCountView_stepEndAngle,mEndAngle);
        //回收
        array.recycle();


        mOuterPaint = new Paint();
        //锯齿
        mOuterPaint.setAntiAlias(true);
        //设置结束是否是圆的 线条的末端
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);
        //线条链接的地方
        //  mPaint.setStrokeJoin(Paint.Join.ROUND);
        mOuterPaint.setColor(Color.parseColor("#3F51B5"));
        mOuterPaint.setStrokeWidth(mBorderWidth);
        mOuterPaint.setStyle(Paint.Style.STROKE);

        mInnerPaint = new Paint();
        //锯齿
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setColor(Color.parseColor("#FF4081"));
        mInnerPaint.setStrokeWidth(mBorderWidth);
        //是否填充，Paint.Style.STROKE不填充
        mInnerPaint.setStyle(Paint.Style.STROKE);

        mTestPaint = new Paint();
        mTestPaint.setAntiAlias(true);
        mTestPaint.setColor(Color.parseColor("#FF4081"));
        mTestPaint.setTextSize(mStepTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec)-getPaddingLeft()-getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec-getPaddingBottom()-getPaddingTop());
        if(widthMode == MeasureSpec.AT_MOST){
            width = ScreenUtil.dp2px(100,this.getContext());
        }

        if(heightMode == MeasureSpec.AT_MOST){
            height = ScreenUtil.dp2px(100,this.getContext());
        }
        setMeasuredDimension(width<height?width:height,width<height?width:height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectf = new RectF(mBorderWidth/2+getPaddingLeft(),mBorderWidth/2+getPaddingTop(),getWidth()-mBorderWidth/2,getHeight()-mBorderWidth/2);
        canvas.drawArc(rectf,mStartAngle,mEndAngle,false,mOuterPaint);
        float sweepAngle = (float)mStepCurrent/mStepMax;
        //第四个参数表示是否封闭
        canvas.drawArc(rectf,mStartAngle,sweepAngle*mEndAngle,false,mInnerPaint);
        //画文字
        String stepText = mStepCurrent+"步";
        Rect rect = new Rect();
        mTestPaint.getTextBounds(stepText,0,stepText.length(),rect);
        Paint.FontMetricsInt fontMetrics = mTestPaint.getFontMetricsInt();
        int baseline = (getHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        int leftStart = (int) (rectf.width() / 2 - rect.width() / 2)+getPaddingLeft();
        canvas.drawText(stepText,leftStart,baseline,mTestPaint);

    }

    public synchronized  void setCurrentStep(int currentStep){
        if (currentStep < 0) {
            throw new IllegalArgumentException("progress 不能小于0!");
        }
        this.mStepCurrent = currentStep;
        invalidate();
    }

    public synchronized  void setStepMax(int stepMax){
        this.mStepMax = stepMax;
    }
}
