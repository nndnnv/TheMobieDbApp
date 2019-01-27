package com.tikal.app.network.response

import com.google.gson.annotations.SerializedName
import com.tikal.app.data.models.TvShow

data class TvShowsResponse(
    @SerializedName("results")
    var shows: List<TvShow>)
{
}