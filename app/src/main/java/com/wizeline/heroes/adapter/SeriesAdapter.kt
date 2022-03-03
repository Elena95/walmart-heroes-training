package com.wizeline.heroes.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wizeline.heroes.databinding.ItemComicsBinding
import android.view.LayoutInflater
import com.wizeline.heroes.adapter.holder.ComicSeriesHolder
import com.wizeline.heroes.data.entities.Series


class SeriesAdapter() : ListAdapter<Series, ComicSeriesHolder>(COMPARATOR) {
    object COMPARATOR : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicSeriesHolder {
        val bindingItem =
            ItemComicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicSeriesHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: ComicSeriesHolder, position: Int) {
        holder.ondBind(getItem(position).thumbnail)
    }


}