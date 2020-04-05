package com.arctouch.codechallenge.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.arctouch.codechallenge.AppExecutors
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.MovieItemBinding
import com.arctouch.codechallenge.databinding.MovieItemBindingImpl
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.ui.common.DataBoundListAdapter
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val itemClickCallback: ((Movie) -> Unit)
):DataBoundListAdapter<Movie, MovieItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                    oldItem.id == newItem.id
                            && oldItem.title == newItem.title
                            && oldItem.overview == newItem.overview
        }
){
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
                itemClickCallback.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: MovieItemBinding, item: Movie, position: Int) {
        binding.movie = item
        // TODO refactor
        /*val movieImageUrlBuilder = MovieImageUrlBuilder()

            binding.titleTextView.text = item.title
            binding.genresTextView.text = item.genres?.joinToString(separator = ", ") { it.name }
            binding.releaseDateTextView.text = item.releaseDate

            Glide.with(binding.root)
                    .load(item.posterPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
                    .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(binding.posterImageView)
                    */
    }

}

/*class HomeAdapter(private val movies: List<Movie>) : androidx.recyclerview.widget.RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        private val movieImageUrlBuilder = MovieImageUrlBuilder()

        fun bind(movie: Movie) {
            itemView.titleTextView.text = movie.title
            itemView.genresTextView.text = movie.genres?.joinToString(separator = ", ") { it.name }
            itemView.releaseDateTextView.text = movie.releaseDate

            Glide.with(itemView)
                .load(movie.posterPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(itemView.posterImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])
}*/
