package com.tikal.app.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tikal.app.data.models.Movie

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveAll(movies: List<Movie>)

    @Query("SELECT * from movies")
    fun loadAll(): List<Movie>
}