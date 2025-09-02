package com.dmitrysergeev.weatherapp.presentation.mainscreen.uvindex

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.content.res.AppCompatResources
import com.dmitrysergeev.weatherapp.R
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

    private fun getTrianglePath(dy: Float, cX: Float): Path {
        val path = Path()
        path.moveTo(cX, height-dy-12.5f-5)
        path.rLineTo(10f,12.5f)
        path.rLineTo(-20f,0f)
        path.rLineTo(10f,-12.5f)
        path.close()

        return path
    }

    override fun onDraw(canvas: Canvas) {
//        canvas.drawRect(Rect(0,0,width,height),boundPaint) // debug

        val dy = height/2f-textHeight-10
        canvas.translate(0f,dy)
        super.onDraw(canvas)
        canvas.translate(0f,-dy)

        canvas.drawText("Low",0f, height.toFloat()-textPaint.fontMetrics.descent, textPaint)
        val startX = width - textPaint.measureText("Extreme danger")
        canvas.drawText("Extreme danger", startX, height.toFloat()-textPaint.fontMetrics.descent, textPaint)

        val cX = width * (progress/max.toFloat())

        boundPaint.color = Color.BLACK
        boundPaint.style = Paint.Style.FILL
        canvas.drawPath(getTrianglePath(dy, cX), boundPaint)
        boundPaint.style = Paint.Style.STROKE
        boundPaint.strokeWidth = 2f
        boundPaint.color = Color.WHITE
//        boundPaint.strokeJoin = Paint.Join.ROUND
        canvas.drawPath(getTrianglePath(dy, cX),boundPaint)

        val drawable = AppCompatResources.getDrawable(context, R.drawable.sunny)
        drawable?.setBounds(cX.toInt()-32,height/2-32,cX.toInt()+32,height/2+32)
        drawable?.draw(canvas)
    }

    companion object {
        const val TAG = "UvIndexProgressBar"
    }
}