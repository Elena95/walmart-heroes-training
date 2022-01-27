package com.wizeline.heroes.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wizeline.heroes.databinding.ItemComicsBinding
import android.view.LayoutInflater
import com.wizeline.heroes.Adapter.Holder.ComicSeriesHolder
import com.wizeline.heroes.Series


class SeriesAdapter() : ListAdapter<Series, ComicSeriesHolder>(COMICSCOMPARATOR) {
    object COMICSCOMPARATOR : DiffUtil.ItemCallback<Series>() {
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