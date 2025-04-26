package com.example.rydemos.gifdemo

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.util.Log
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.rydemos.MainActivity
import com.example.rydemos.databinding.ActivityGifBinding
import com.example.rydemos.util.RYLog


class GifActivity : ComponentActivity() {

    private lateinit var mBinding: ActivityGifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGifBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun loadGif() {

        Glide.with(this)
            .asGif()
//            .load("https://cdn.pixabay.com/photo/2023/11/30/08/36/chhathpuja-8421051_1280.jpg")
            .load("https://5b0988e595225.cdn.sohucs.com/images/20170919/1ce5d4c52c24432e9304ef942b764d37.gif")
            .addListener(object : RequestListener<GifDrawable> {
                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    RYLog.e(MainActivity.TAG, "onLoadFailed error: ${e?.message}")
                    return false
                }
            })
            .into(object : SimpleTarget<GifDrawable>() {
                override fun onResourceReady(
                    resource: GifDrawable,
                    transition: Transition<in GifDrawable>?
                ) {
                    resource.setLoopCount(GifDrawable.LOOP_FOREVER)
                    val imageSpan =
                        CustomImageSpan(resource)
                    val spannable = SpannableString("smile")
                    spannable.setSpan(
                        imageSpan,
                        0,
                        5,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    mBinding.tv1.post {
                        resource.callback = object : Drawable.Callback {
                            override fun invalidateDrawable(p0: Drawable) {
                                mBinding.tv1.invalidate()
                            }

                            override fun scheduleDrawable(p0: Drawable, p1: Runnable, p2: Long) {
                                mBinding.tv1.scheduleDrawable(p0, p1, p2)
                            }

                            override fun unscheduleDrawable(p0: Drawable, p1: Runnable) {
                                mBinding.tv1.unscheduleDrawable(p0, p1)
                            }
                        }
                        mBinding.tv1.text = spannable
                    }
//                    mBinding.iv1.post { mBinding.iv1.setImageDrawable(resource) }
                    resource.start()
                }
            })
    }

    private fun loadStatic() {
        Glide.with(this)
//            .load("https://cdn.pixabay.com/photo/2023/11/30/08/36/chhathpuja-8421051_1280.jpg")
            .load("https://5b0988e595225.cdn.sohucs.com/images/20170919/1ce5d4c52c24432e9304ef942b764d37.gif")
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    RYLog.e(MainActivity.TAG, "onLoadFailed error: ${e?.message}")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    val imageSpan =
                        CustomImageSpan(resource)
                    val spannable = SpannableString("smile")
                    spannable.setSpan(
                        imageSpan,
                        0,
                        5,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    mBinding.tv2.post { mBinding.tv2.text = spannable }
                    mBinding.iv2.post { mBinding.iv2.setImageDrawable(resource) }
                }
            })
    }
}