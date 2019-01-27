package com.tikal.app.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tikal.app.data.models.TvShow

@Dao
public interface TvShowsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveAll(tvShows: List<TvShow>)

    @Query("SELECT * from tvshows")
    fun loadAll(): List<TvShow>
}