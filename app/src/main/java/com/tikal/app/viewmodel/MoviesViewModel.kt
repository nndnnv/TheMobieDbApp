package com.tikal.app.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.tikal.app.data.managers.DataRepository
import com.tikal.app.data.models.Movie

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    var movies: MutableLiveData<List<Movie>>? = null

    var page: Int = 1

    init {
        movies = DataRepository.getInstance()?.getMovies(page)
    }

    fun fetchNextPage()
    {
        page++
        DataRepository.getInstance()?.getMovies(page)
    }
}
