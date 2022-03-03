package com.wizeline.heroes.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wizeline.heroes.databinding.ItemComicsBinding
import android.view.LayoutInflater
import com.wizeline.heroes.adapter.holder.ComicSeriesHolder
import com.wizeline.heroes.data.entities.Comic


class ComicsAdapter() : ListAdapter<Comic, ComicSeriesHolder>(COMPARATOR) {
    object COMPARATOR : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
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