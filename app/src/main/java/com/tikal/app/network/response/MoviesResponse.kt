package com.tikal.app.network.response

import com.google.gson.annotations.SerializedName
import com.tikal.app.data.models.Movie

data class MoviesResponse(
    @SerializedName("results")
    var movies: List<Movie>)
{
}