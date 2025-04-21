package com.dmitrysergeev.weatherapp.presentation.mainscreen.airquality

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
import android.widget.ProgressBar
import androidx.collection.arrayMapOf
import androidx.core.graphics.toColorInt

class MyProgressBar(
    context: Context,
    attrs: AttributeSet
): ProgressBar(context, attrs) {

    private val colorMap: Map<Float,Int> = arrayMapOf(
         0f to "#28FF30".toColorInt(),
         0.22f to "#F0FF47".toColorInt(),
         0.5f to "#FFCC47".toColorInt(),
         0.75f to "#FF1A43".toColorInt(),
         0.93f to "#B519FF".toColorInt(),
         1f to "#76000E".toColorInt()
    )

    private fun getColor(value: Float): Int {
        for (i in 0 until colorMap.size){
            if (value>=colorMap.keys.toFloatArray()[i] && value<colorMap.keys.toFloatArray()[i+1]){
                val startColor = colorMap.values.toIntArray()[i]
                val endColor = colorMap.values.toIntArray()[i+1]
                val sR = Color.red(startColor)
                val sG = Color.green(startColor)
                val sB = Color.blue(startColor)
                val eR = Color.red(endColor)
                val eG = Color.green(endColor)
                val eB = Color.blue(endColor)
                val hsvStart = FloatArray(3)
                val hsvEnd = FloatArray(3)
                Color.RGBToHSV(sR,sG,sB,hsvStart)
                Color.RGBToHSV(eR,eG,eB,hsvEnd)
                val t = (value - colorMap.keys.toFloatArray()[i])/(colorMap.keys.toFloatArray()[i+1]-colorMap.keys.toFloatArray()[i])
                val finalColor = Color.HSVToColor(
                    255, floatArrayOf(
                        linear(hsvStart[0],hsvEnd[0],t),
                        linear(hsvStart[1],hsvEnd[1],t),
                        linear(hsvStart[2],hsvEnd[2],t)
                    )
                )
                return finalColor
            }
        }
        return colorMap.values.last()
    }

    private fun linear(start: Float,end: Float, t:Float): Float{
        return start*(1-t)+end*t
    }

    init {
        val gradientDrawable = object : Drawable() {
            private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

            override fun draw(canvas: Canvas) {
                val shader = LinearGradient(
                    0f,0f,bounds.width().toFloat(),0f,
                    colorMap.values.toIntArray(),
                    colorMap.keys.toFloatArray(),
                    Shader.TileMode.CLAMP
                )
                paint.shader = shader
                val newBounds = RectF(
                    bounds.left.toFloat(),
                    bounds.height()/2-1*context.resources.displayMetrics.density,
                    bounds.right.toFloat(),
                    bounds.height()/2+1*context.resources.displayMetrics.density
                )
                canvas.drawRoundRect(newBounds,10*context.resources.displayMetrics.density,10*context.resources.displayMetrics.density, paint)

                // DebugMode
//                val myPaint = Paint()
//                myPaint.strokeWidth = 1f
//                myPaint.style = Paint.Style.STROKE
//                myPaint.color = Color.BLACK
//                canvas.drawRect(bounds, myPaint)
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
        val relativeProgress = progress.toFloat()/max
        val cX = relativeProgress*width
        val color = getColor(relativeProgress)
        circlePaint.color = color
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