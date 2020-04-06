package com.arctouch.codechallenge.model

import androidx.paging.PageKeyedDataSource
import com.arctouch.codechallenge.api.TmdbService
import com.arctouch.codechallenge.repository.GenreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MovieDataSource(private val scope: CoroutineScope, private val tmdbService: TmdbService, private val genreRepository: GenreRepository) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        scope.launch {
            val response = tmdbService.upcomingMovies(TmdbService.API_KEY, TmdbService.DEFAULT_LANGUAGE, 1, TmdbService.DEFAULT_REGION)
            val movies = response.results.map { movie ->
                movie.copy(genres = genreRepository.getGenres().filter { movie.genreIds?.contains(it.id) == true })
            }
            callback.onResult(movies, 1, getNextKey(1, response))
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            val response = tmdbService.upcomingMovies(TmdbService.API_KEY, TmdbService.DEFAULT_LANGUAGE, params.key, TmdbService.DEFAULT_REGION)
            val movies = response.results.map { movie ->
                movie.copy(genres = genreRepository.getGenres().filter { movie.genreIds?.contains(it.id) == true })
            }
            callback.onResult(movies, getNextKey(params.key, response))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }

    private fun getNextKey(key: Int, response: UpcomingMoviesResponse): Int? {
        return if (key == response.totalPages) null else key + 1
    }
}