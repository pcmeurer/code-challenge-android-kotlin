package com.arctouch.codechallenge.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.repository.MovieRepository

class HomeViewModel(repository: MovieRepository) : ViewModel() {
    val movies: LiveData<List<Movie>> = repository.getMovies().asLiveData()
}
