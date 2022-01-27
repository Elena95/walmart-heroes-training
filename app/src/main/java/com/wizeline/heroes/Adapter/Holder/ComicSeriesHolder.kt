package com.wizeline.heroes.Adapter.Holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wizeline.heroes.Thumbnail
import com.wizeline.heroes.databinding.ItemComicsBinding

class ComicSeriesHolder(private val binding: ItemComicsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun ondBind(thumbnail: Thumbnail) = with(binding) {
        Glide.with(binding.root.context)
            .load("${thumbnail.path}.${thumbnail.extension}")
            .into(imageComic)
    }

}
