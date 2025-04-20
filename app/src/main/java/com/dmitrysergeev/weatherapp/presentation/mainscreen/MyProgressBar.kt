package com.dmitrysergeev.weatherapp.presentation.mainscreen

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.widget.ProgressBar
import androidx.core.graphics.toColorInt

class MyProgressBar(
    context: Context,
    attrs: AttributeSet
): ProgressBar(context, attrs) {

    init {
        val gradientDrawable = object : Drawable() {
            private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

            override fun draw(canvas: Canvas) {
                val shader = LinearGradient(
                    0f,0f,bounds.width().toFloat(),0f,
                    intArrayOf(
                        "#28FF30".toColorInt(),
                        "#F0FF47".toColorInt(),
                        "#FFCC47".toColorInt(),
                        "#FF1A43".toColorInt(),
                        "#B519FF".toColorInt(),
                        "#76000E".toColorInt()
                    ),
                    floatArrayOf(
                        0f,
                        0.22f,
                        0.5f,
                        0.75f,
                        0.93f,
                        1f
                    ),
                    Shader.TileMode.CLAMP
                )
                paint.shader = shader
                Log.d(TAG, bounds.toString())
                val newBounds = RectF(
                    bounds.left.toFloat(),
                    bounds.height()/2-1*context.resources.displayMetrics.density,
                    bounds.right.toFloat(),
                    bounds.height()/2+1*context.resources.displayMetrics.density
                )
//                canvas.drawRect(newBounds, paint)
                canvas.drawRoundRect(newBounds,10*context.resources.displayMetrics.density,10*context.resources.displayMetrics.density, paint)

                // DebugMode
                val myPaint = Paint()
                myPaint.strokeWidth = 1f
                myPaint.style = Paint.Style.STROKE
                myPaint.color = Color.BLACK
                canvas.drawRect(bounds, myPaint)
            }

            override fun setAlpha(alpha: Int) {
                paint.alpha = alpha
            }

            override fun setColorFilter(colorFilter: ColorFilter?) {
                paint.colorFilter = colorFilter
            }

            override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

        }
        progressDrawable = gradientDrawable
    }

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
        val cX = progress.toFloat()/max*width
        canvas.drawCircle(cX, height/2f, 3*context.resources.displayMetrics.density, strokePaint)
        canvas.drawCircle(cX, height/2f, 2*context.resources.displayMetrics.density, circlePaint)
    }

    override fun setProgress(progress: Int, animate: Boolean) {
        super.setProgress(progress, animate)
        invalidate()
    }

    override fun setProgress(progress: Int) {
        super.setProgress(progress)
        invalidate()
    }

    companion object {
        const val TAG = "MyProgressBar"
    }
}