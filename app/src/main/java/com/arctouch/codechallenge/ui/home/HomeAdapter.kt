package com.arctouch.codechallenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.arctouch.codechallenge.AppExecutors
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.MovieItemBinding
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.ui.common.DataBoundPagedListAdapter

class HomeAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val itemClickCallback: ((Movie, ImageView) -> Unit)
) : DataBoundPagedListAdapter<Movie, MovieItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                    oldItem.id == newItem.id
                            && oldItem.title == newItem.title
                            && oldItem.overview == newItem.overview
        }
) {
    override fun createBinding(parent: ViewGroup): MovieItemBinding {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.movie_item,
                parent,
                false,
                dataBindingComponent
        )

        binding.root.setOnClickListener {
            binding.movie?.let {
                itemClickCallback.invoke(it, binding.posterImageView)
            }
        }
        return binding
    }

    override fun bind(binding: MovieItemBinding, item: Movie?, position: Int) {
        binding.movie = item
    }

}