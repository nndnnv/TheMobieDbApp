package com.tikal.app.network.api

import com.tikal.app.network.response.MoviesResponse
import com.tikal.app.network.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.*

interface TheMovieDbAPI
{
    companion object
    {
        val API_BASE_URL = "https://api.themoviedb.org"

        private val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500"

        fun ImageUrl(imageUrl: String): String
        {
            return IMAGES_BASE_URL + imageUrl
        }
    }

    
    @GET("/3/movie/popular")
    fun TOP_MOVIES(
        @Query("api_key") apiKey: String,
        @Query("page") page: String): Call<MoviesResponse>

    @GET("/3/tv/popular")
    fun TOP_TV_SHOWS(
        @Query("api_key") apiKey: String,
        @Query("page") page: String): Call<TvShowsResponse>

}

