package com.dmitrysergeev.weatherapp.presentation.mainscreen.airquality

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.dmitrysergeev.weatherapp.presentation.views.GradientProgressBar

class AirQualityProgressBar(
    context: Context,
    attrs: AttributeSet
): GradientProgressBar(context, attrs) {

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
    }

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val relativeProgress = progress.toFloat()/max
        val cX = relativeProgress*width
        val color = getColor(relativeProgress)
        circlePaint.color = color
        canvas.drawCircle(cX, height/2f, 3*context.resources.displayMetrics.density, strokePaint)
    }
}