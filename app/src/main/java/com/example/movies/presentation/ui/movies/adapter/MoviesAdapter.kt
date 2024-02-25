package com.example.movies.presentation.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemMovieBinding
import com.example.movies.presentation.model.movies.MovieUIModel
import com.example.movies.presentation.utils.loadImageUrl

class MoviesAdapter(private val listener: MovieClickListener) :
    PagingDataAdapter<MovieUIModel, MoviesAdapter.Holder>(
        PopularMovieUiStateItemCallback()
    ) {

    inner class Holder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            with(binding)
            {
                loadImageUrl(movieImage, item?.getImageUrl(itemView.context))
                movieTitleTextView.text = item?.title
                movieRateTextView.text = item?.getRate()
                movieDateTextView.text = item?.releaseDate
            }
            binding.root.setOnClickListener {
                listener.onMovieItemClicked(
                    item?.id ?: return@setOnClickListener
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    class PopularMovieUiStateItemCallback :
        DiffUtil.ItemCallback<MovieUIModel>() {
        override fun areItemsTheSame(
            oldItem: MovieUIModel,
            newItem: MovieUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieUIModel,
            newItem: MovieUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}