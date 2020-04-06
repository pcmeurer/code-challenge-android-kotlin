package com.arctouch.codechallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arctouch.codechallenge.repository.MovieRepository

class HomeViewModel(repository: MovieRepository) : ViewModel() {

    val movies = repository.getMovies(viewModelScope)
}
