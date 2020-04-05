package com.arctouch.codechallenge.api.retrofit

import android.app.Application
import com.arctouch.codechallenge.api.TmdbService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitInitializer(val app: Application) {

    fun tmdbService(): TmdbService {
        return createWebService()
    }

    private fun baseUrl() = "https://api.themoviedb.org/3/"

    private fun createCache(): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(app.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    private fun createOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val cache = createCache()

        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
//            .addInterceptor(
//                HostSelectionInterceptor(
//                    app
//                )
//            )
            .build()

        // TODO: não está sendo passado o cabeçalho de autenticação
    }

    private fun createGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }


    private inline fun <reified T> createWebService(): T {
        val okHttpClient = createOkHttpClient()
        val url = baseUrl()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

        return retrofit.create(T::class.java)
    }
}
