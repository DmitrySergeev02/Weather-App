package com.dmitrysergeev.weatherapp.presentation.mainscreen.uvindex

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import com.dmitrysergeev.weatherapp.presentation.views.GradientProgressBar

class UvIndexProgressBar(
    context: Context,
    attrs: AttributeSet
): GradientProgressBar(context, attrs) {

    private val textHeight: Float
        get() = textPaint.fontMetrics.bottom - textPaint.fontMetrics.top

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec) + 200
//        Log.d(TAG, "$width, $height")
        setMeasuredDimension(width, height)
    }

    private val textPaint = TextPaint().apply {
        color = Color.BLACK
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, context.resources.displayMetrics)
        textAlign = Paint.Align.LEFT
    }

    private val boundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 1f
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(Rect(0,0,width,height),boundPaint) // debug
        canvas.translate(0f,height/2f-textHeight-10)
        super.onDraw(canvas)
        canvas.translate(0f,-(height/2f-textHeight-10))
        canvas.drawText("Low",0f, height.toFloat()-textPaint.fontMetrics.descent, textPaint)
        val startX = width - textPaint.measureText("Extreme danger")
        canvas.drawText("Extreme danger", startX, height.toFloat()-textPaint.fontMetrics.descent, textPaint)
    }

}