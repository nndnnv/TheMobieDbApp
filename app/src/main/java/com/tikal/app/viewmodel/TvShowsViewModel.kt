package com.tikal.app.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.tikal.app.data.managers.DataRepository
import com.tikal.app.data.models.TvShow

class TvShowsViewModel(application: Application) : AndroidViewModel(application) {

    var tvShows: MutableLiveData<List<TvShow>>? = null

    var page: Int = 1

    init {
        tvShows = DataRepository.getInstance()?.getTvShows(page)
    }

    fun fetchNextPage()
    {
        page++
        DataRepository.getInstance()?.getTvShows(page)
    }
}
