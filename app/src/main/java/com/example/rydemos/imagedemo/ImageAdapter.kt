package com.example.rydemos.imagedemo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rydemos.databinding.ItemImageBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val mData = arrayListOf<ImageInfo>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<ImageInfo>) {
        mData.clear()
        mData.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBindData(mData[position], position)
    }

    class ImageViewHolder(private val mBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun onBindData(data: ImageInfo, position: Int) {
            when (position) {
                0, 4 -> {
                    mBinding.image.scaleX = 0.5f
                    mBinding.image.scaleY = 0.5f
                }

                1, 3 -> {
                    mBinding.image.scaleX = 0.75f
                    mBinding.image.scaleY = 0.75f
                }
            }
            Glide.with(itemView).load(data.iconUrl).into(mBinding.image)
        }

    }
}