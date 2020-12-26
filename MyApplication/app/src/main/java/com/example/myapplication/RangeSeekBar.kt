package com.example.myapplication

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.util.Log
import android.view.View


class RangeSeekBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var mShowText = false
    var textPos = 0;
    var xx = 0f
    var yy = 0f
    var w = 0f
    var h = 0f
    var thumRadius = 0f;

    var rectBodyColor = Color.RED
    var circleBodyColor = Color.GREEN
    var thumbBarHeight = 0f;

    companion object{
        const val TAG = "RangeSeekBar"
    }

    init {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.RangeSeekBar,
            0, 0)?.apply {

            thumRadius =  getResources().getDimension(R.dimen.thumb_radius).toInt().toFloat()
            thumbBarHeight = getResources().getDimension(R.dimen.thumb_bar_height).toInt().toFloat()

            try {
                mShowText = getBoolean(R.styleable.RangeSeekBar_showText, false)
                textPos = getInteger(R.styleable.RangeSeekBar_labelPosition, 0)
            } finally {
                recycle()
            }
         }

        Log.d(TAG,mShowText.toString())
        Log.d(TAG,textPos.toString())
    }

     fun updateColor(color: Int)
    {
        rectBodyColor = color;
       invalidate()
       requestLayout()
    }


    fun updateSize(x: Float,y:Float,w:Float,h:Float)
    {

        this.xx = x
        this.yy = y
        this.w = w
        this.h = h
        invalidate()
        requestLayout()
    }


    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
            Log.e("ChartView", "Exactly")
        } else {
            Log.e("ChartView", "NotExact")
            result = desiredSize
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
//                if(specSize>result)
//                    result = specSize
            }

        }
        if (result < desiredSize) {
            Log.e("ChartView", "The view is too small, the content might get cut")
        }
        return result
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.v("Chart onMeasure w", MeasureSpec.toString(widthMeasureSpec))
        Log.v("Chart onMeasure h", MeasureSpec.toString(heightMeasureSpec))
        var desiredWidth = 0
        var desiredHeight = 0

        getResources().apply {desiredWidth =
            (getDimension(R.dimen.range_seekbar_width)+
            getDimension(R.dimen.range_seekbar_padding_left)+
            getDimension(R.dimen.range_seekbar_padding_right)).toInt()}

        getResources().apply {desiredHeight =
            (getDimension(R.dimen.range_seekbar_height)+
               getDimension(R.dimen.range_seekbar_padding_top)+
                    getDimension(R.dimen.range_seekbar_padding_bottom)).toInt()}

                setMeasuredDimension(
            measureDimension(desiredWidth, widthMeasureSpec),
            measureDimension(desiredHeight, heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        Log.d(TAG,"drawing")


        Log.v("SHOW_TEXT","drawing");
         val reactPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
             color = rectBodyColor

        }

        val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = circleBodyColor

        }

        canvas.apply {
            //drawRect(xx, yy, getWidth().toFloat()-w, getHeight().toFloat()-h, reactPaint );
            drawRect(xx, getHeight()/2f-thumbBarHeight, getWidth().toFloat()-w, getHeight()/2f+thumbBarHeight, reactPaint );
            drawCircle(w+thumRadius, getHeight()/2f, thumRadius, circlePaint);
            //drawCircle(width.toFloat()-thumRadius+w, getHeight()/2f, thumRadius, circlePaint);

        }
    }

}