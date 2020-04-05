package com.arctouch.codechallenge.repository

import com.arctouch.codechallenge.api.TmdbService
import com.arctouch.codechallenge.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(private val tmdbService: TmdbService){

    fun getMovies(): Flow<List<Movie>> = flow {
        //TODO: refactor cause genders
        val response = tmdbService.upcomingMovies(TmdbService.API_KEY, TmdbService.DEFAULT_LANGUAGE, 1, TmdbService.DEFAULT_REGION)
        emit(response.results)
    }
}