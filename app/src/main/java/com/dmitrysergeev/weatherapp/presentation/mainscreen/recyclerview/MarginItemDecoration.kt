package com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val context: Context,
    private val marginStartDp: Int,
    private val marginEndDp: Int,
    private val marginTopDp: Int,
    private val marginBottomDp: Int
): RecyclerView.ItemDecoration() {

    constructor(context: Context, marginHorizontalDp: Int = 0, marginVerticalDp: Int = 0) : this(context, marginHorizontalDp, marginHorizontalDp, marginVerticalDp, marginVerticalDp)

    constructor(context: Context, marginDp: Int = 0) : this (context, marginDp, marginDp, marginDp, marginDp)

    private fun toPixels(value: Int): Int{
        return (value * context.resources.displayMetrics.density).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val viewType = parent.layoutManager?.getItemViewType(view)

        when (viewType){
            MainScreenItemListAdapter.WEATHER_CARDS_VIEW -> {}
            else-> {
                outRect.top = toPixels(marginTopDp)
                outRect.bottom = toPixels(marginBottomDp)
                outRect.left = toPixels(marginStartDp)
                outRect.right = toPixels(marginEndDp)
            }
        }
    }
}