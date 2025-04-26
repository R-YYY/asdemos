package com.example.rydemos.scaledemo

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.widget.ImageView
import com.example.rydemos.util.Utils
import kotlin.math.min

class CustomImageView : ImageView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        scaleType = ScaleType.MATRIX
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        applyCustomScaleType()
    }

    private fun applyCustomScaleType() {
        if (drawable == null) return;

        //获取图片和 ImageView 的尺寸
        val dWidth = drawable.intrinsicWidth
        val dHeight = drawable.intrinsicHeight
        val vWidth = width - paddingStart - paddingEnd
        val vHeight = height - paddingTop - paddingBottom

        //缩放比例
        val scale = min(vWidth.toFloat() / dWidth, vHeight.toFloat() / dHeight)

        // 创建矩阵并应用缩放
        val matrix = Matrix()
        matrix.postScale(scale, scale)

        // 计算平移位置：ltr水平左对齐，rtl水平右对齐，垂直底部对齐
        val dx = if (Utils.isRTL()) (vWidth - dWidth * scale) else 0f
        val dy = vHeight - dHeight * scale
        matrix.postTranslate(dx, dy);

        // 应用矩阵
        imageMatrix = matrix;
    }
}