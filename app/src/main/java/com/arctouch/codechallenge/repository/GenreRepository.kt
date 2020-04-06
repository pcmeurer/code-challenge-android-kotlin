package com.arctouch.codechallenge.repository

import com.arctouch.codechallenge.api.TmdbService
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.model.Genre

class GenreRepository (private val tmdbService: TmdbService) {

    suspend fun getGenres(): List<Genre> {
        if (Cache.genres.isEmpty()){
            Cache.cacheGenres(
                    tmdbService.genres(TmdbService.API_KEY, TmdbService.DEFAULT_LANGUAGE)
                            .genres
            )
        }
        return Cache.genres
    }
}