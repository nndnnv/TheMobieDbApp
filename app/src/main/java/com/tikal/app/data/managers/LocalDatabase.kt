package com.tikal.app.data.managers

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.tikal.app.utils.App
import com.tikal.app.data.models.Movie
import com.tikal.app.data.dao.MoviesDao
import com.tikal.app.data.models.TvShow
import com.tikal.app.data.dao.TvShowsDao

@Database(entities = [Movie::class, TvShow::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowsDao(): TvShowsDao

    companion object {
        private var mInstance: LocalDatabase? = null

        @Synchronized
        fun getInstance(): LocalDatabase? {
            if (mInstance == null) {
                synchronized(LocalDatabase::class) {
                    mInstance = Room.databaseBuilder(
                        App.context,
                        LocalDatabase::class.java, LocalDatabase::class.simpleName.toString()
                    )
                        .build()
                }
            }
            return mInstance
        }
    }
}
