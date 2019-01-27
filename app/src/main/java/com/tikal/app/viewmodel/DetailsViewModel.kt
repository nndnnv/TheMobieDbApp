package com.tikal.app.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.tikal.app.data.managers.DataRepository
import com.tikal.app.data.models.BaseItem

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    var dataItem: MutableLiveData<BaseItem>? = null

    init {
        dataItem = DataRepository.getInstance()?.selectedItem
    }

}
