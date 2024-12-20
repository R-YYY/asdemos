package com.example.rydemos.imagedemo

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class ImageRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        isChildrenDrawingOrderEnabled = true
    }

    override fun getChildDrawingOrder(childCount: Int, i: Int): Int {
        val center = childCount / 2
        return if (i < center) {
            i
        } else if (i > center) {
            childCount - i + center
        } else {
            childCount - 1
        }
    }
}