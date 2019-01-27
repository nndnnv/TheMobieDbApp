package com.tikal.app.data.managers

import android.arch.lifecycle.MutableLiveData
import com.tikal.app.utils.AppUtils
import com.tikal.app.data.models.BaseItem
import com.tikal.app.data.models.Movie
import com.tikal.app.data.models.TvShow
import com.tikal.app.network.response.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import com.tikal.app.network.api.TheMovieDbAPI
import com.tikal.app.network.response.TvShowsResponse
import com.tikal.app.network.client.WebServiceClient


class DataRepository
{
    /**
     * Executor for disk writing operations
     */
    private val mDbExecutor: Executor = Executors.newSingleThreadExecutor()

    companion object
    {
        private var mInstance: DataRepository? = null

        @Synchronized
        fun getInstance(): DataRepository? {
            if (mInstance == null) {
                synchronized(DataRepository::class) {
                    mInstance =
                        DataRepository()
                }
            }
            return mInstance
        }
    }

    /**
     * Live data items
     */
    var selectedItem: MutableLiveData<BaseItem>? = MutableLiveData()

    var movies: MutableLiveData<List<Movie>>? = MutableLiveData()

    var tvShows: MutableLiveData<List<TvShow>>? = MutableLiveData()

    private val moviesResponseCallback = object: Callback<MoviesResponse> {

        override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {

            movies?.value = response?.body()?.movies

            mDbExecutor.execute {
                LocalDatabase.getInstance()?.moviesDao()?.saveAll(response?.body()?.movies!!)
            }
        }

        override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {}
    }

    private val tvShowsResponseCallback = object: Callback<TvShowsResponse> {

        override fun onResponse(call: Call<TvShowsResponse>?, response: Response<TvShowsResponse>?) {

            tvShows?.value = response?.body()?.shows

            mDbExecutor.execute {
                LocalDatabase.getInstance()?.tvShowsDao()?.saveAll(response?.body()?.shows!!)
            }
        }

        override fun onFailure(call: Call<TvShowsResponse>?, t: Throwable?) {}
    }

    fun getMovies(page
                  : Int) : MutableLiveData<List<Movie>>?
    {
        if(AppUtils.hasInternetConnection()){

            WebServiceClient.client.create(TheMovieDbAPI::class.java).
                TOP_MOVIES(
                    apiKey = "fcdfd79c30e154916697e57b429c7b07",
                    page = page.toString()).
                enqueue(moviesResponseCallback)

        } else {
            Thread {
                val localMovies = LocalDatabase?.getInstance()?.moviesDao()?.loadAll()
                movies?.postValue(localMovies)
            }.start()

        }

        return movies
    }

    fun getTvShows(page : Int) : MutableLiveData<List<TvShow>>?
    {
        if(AppUtils.hasInternetConnection()) {

            WebServiceClient.client.create(TheMovieDbAPI::class.java).
                TOP_TV_SHOWS(
                    apiKey = "fcdfd79c30e154916697e57b429c7b07",
                    page = page.toString()).
                enqueue(tvShowsResponseCallback)

        } else {

            Thread {
                val localTvShows = LocalDatabase?.getInstance()?.tvShowsDao()?.loadAll()
                tvShows?.postValue(localTvShows)
            }.start()

        }

        return tvShows
    }
}