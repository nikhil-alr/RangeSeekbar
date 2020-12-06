package com.example.myapplication

import android.content.Context
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

    var rectBodyColor = Color.RED

    companion object{
        const val TAG = "RangeSeekBar"
    }

    init {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.RangeSeekBar,
            0, 0)?.apply {

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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        Log.d(TAG,"drawing")


         val reactPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
             color = rectBodyColor

        }

        canvas.apply {
            drawRect(xx, yy, getWidth().toFloat()-w, getHeight().toFloat()-h, reactPaint );

        }
    }

}