package com.view.lp.androidview.textchangecolor;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import com.view.lp.androidview.R;


/**
 * 创建者：L.P
 * 创建时间：on 2018/1/24
 * 类描述：字体变色
 */

public class TextChangeColorView extends TextView {

    private int mLeftToRightColor = Color.parseColor("#FF4081");//默认红色
    private int mRightToLeftColor = Color.parseColor("#FF19191A");//默认黑色
    private float mCurrentProgress;
    private Paint mLeftPaint,mRightPaint;
    private Direction mDirection;

    public TextChangeColorView(Context context) {
        this(context,null);
    }

    public TextChangeColorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextChangeColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextChangeColorView);
        mLeftToRightColor = typedArray.getColor(R.styleable.TextChangeColorView_leftToRightColor,mLeftToRightColor);
        mRightToLeftColor = typedArray.getColor(R.styleable.TextChangeColorView_rightToLLeft,mRightToLeftColor);
        typedArray.recycle();
        mLeftPaint =initPaint(mLeftToRightColor);
        mRightPaint = initPaint(mRightToLeftColor);
    }

    private Paint initPaint(int color) {
        Paint paint = new Paint();
        paint.setTextSize(getTextSize());
        paint.setColor(color);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //自己画
//        super.onDraw(canvas);
        int progress = (int) (mCurrentProgress*getWidth());
        if(mDirection == Direction.LEFT_TO_RIGHT){
            drawTextChange(canvas,progress,mLeftPaint,mRightPaint);
        } else {
            drawTextChange(canvas,getWidth()-progress,mRightPaint,mLeftPaint);
        }

    }

    public enum Direction{
        LEFT_TO_RIGHT,RIGHT_TOLEFT
    }

    private void drawTextChange(Canvas canvas,int progress,Paint mLeftPaint,Paint mRightPaint){
        //保存
        canvas.save();
        Rect clipLeft = new Rect(0,0,progress,getHeight());
        canvas.clipRect(clipLeft);
        Paint.FontMetricsInt fontMetricsInt = mLeftPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top)/2 -fontMetricsInt.bottom;
        //基线
        int baseLine = getHeight()/2 + dy;
        canvas.drawText(getText().toString(),0,baseLine,mLeftPaint);
        canvas.restore();
        canvas.save();
        Rect clipRight = new Rect(progress,0,getWidth(),getHeight());
        canvas.clipRect(clipRight);
        canvas.drawText(getText().toString(),0,baseLine,mRightPaint);
        canvas.restore();
    }
    public void setCurrentDirection(Direction direction){
        this.mDirection = direction;
    }
    public void setCurrentProgress(float currentprogress){
        this.mCurrentProgress = currentprogress;
        invalidate();
    }
}
