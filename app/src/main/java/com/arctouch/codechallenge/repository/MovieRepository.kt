package com.arctouch.codechallenge.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arctouch.codechallenge.api.TmdbService
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.MovieDataSource
import kotlinx.coroutines.CoroutineScope

class MovieRepository(private val tmdbService: TmdbService,
                      private val genreRepository: GenreRepository) {

    fun getMovies(scope: CoroutineScope): LiveData<PagedList<Movie>> {

        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()


        val dataSourceFactory = object : DataSource.Factory<Int, Movie>() {
            override fun create(): DataSource<Int, Movie> {
                return MovieDataSource(scope, tmdbService, genreRepository)
            }

        }

        return LivePagedListBuilder(dataSourceFactory, config).build()
    }

    suspend fun getMovie(movieId: Int) = tmdbService.movie(movieId, TmdbService.API_KEY, TmdbService.DEFAULT_LANGUAGE)
}