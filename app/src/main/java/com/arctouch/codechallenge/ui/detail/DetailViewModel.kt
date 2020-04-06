package com.arctouch.codechallenge.ui.detail

import androidx.lifecycle.*
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repository.MovieRepository

class DetailViewModel(repository: MovieRepository) : ViewModel() {

    private var movieId = MutableLiveData<Int>()

    val movie: LiveData<Movie> =
            movieId.switchMap { movieId ->
                liveData {
                    emit(repository.getMovie(movieId))
                }
            }

    fun setMovieId(movieId: Int) {
        this.movieId.value = movieId
    }
}