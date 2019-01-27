package com.tikal.app.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey
    val id : Int = 0,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val imagePath: String,

    @SerializedName("vote_average")
    val rating: Double,

    @SerializedName("overview")
    val description: String,

    @SerializedName("release_date")
    val releaseDate: String): Serializable, BaseItem
{
}
