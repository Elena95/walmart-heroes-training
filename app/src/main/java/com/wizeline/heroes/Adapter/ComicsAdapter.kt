package com.wizeline.heroes.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wizeline.heroes.databinding.ItemComicsBinding
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wizeline.heroes.Adapter.Holder.ComicSeriesHolder
import com.wizeline.heroes.Comic


class ComicsAdapter() : ListAdapter<Comic, ComicSeriesHolder>(COMICSCOMPARATOR) {
    object COMICSCOMPARATOR : DiffUtil.ItemCallback<Comic>() {
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