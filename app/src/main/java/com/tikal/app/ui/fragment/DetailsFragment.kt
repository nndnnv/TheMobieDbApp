package com.tikal.app.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tikal.app.R
import com.tikal.app.data.models.BaseItem
import com.tikal.app.data.models.Movie
import com.tikal.app.data.models.TvShow
import com.tikal.app.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : Fragment() {

    private lateinit var mViewModel: DetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.details_fragment, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
    }

    private fun initObservables(){

        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        mViewModel.dataItem?.observe(this, Observer { dataItem ->
            bindDataItem(dataItem!!)
        })

    }

    private fun bindDataItem(item: BaseItem)
    {
        if(item is Movie){
            release_date_text_view.text = item.releaseDate
            rating_text_view.text = item.rating.toString() + "/10"
            description_text_view.text = item.description
        } else if(item is TvShow){
            release_date_text_view.text = item.firstAirDate
            rating_text_view.text = item.rating.toString() + "/10"
            description_text_view.text = item.description
        }

    }
}