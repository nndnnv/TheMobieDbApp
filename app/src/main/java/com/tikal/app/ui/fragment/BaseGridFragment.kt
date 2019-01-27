package com.tikal.app.ui.fragment

import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.tikal.app.utils.AppUtils
import com.tikal.app.R
import com.tikal.app.data.managers.DataRepository
import com.tikal.app.data.models.BaseItem
import com.tikal.app.ui.activities.DetailsActivity
import com.tikal.app.ui.adapter.GridItemsAdapter

open class BaseGridFragment : Fragment() {

    lateinit var mRecyclerView : RecyclerView
    lateinit var mGridItemsAdapter : GridItemsAdapter
    lateinit var mGridLayoutManager: GridLayoutManager

    open fun setupUi(view: View){
        mRecyclerView = view.findViewById(R.id.recycler_view)

        if(AppUtils.isTablet() || AppUtils.isLandscape()){
            mGridLayoutManager = GridLayoutManager(context, 4)
        } else {
            mGridLayoutManager = GridLayoutManager(context, 2)
        }

        mRecyclerView.setLayoutManager(mGridLayoutManager)

        mGridItemsAdapter = GridItemsAdapter(this.context!!,mGridLayoutManager.spanCount) { clickedMovie: BaseItem  -> onItemClick(clickedMovie) }

        mRecyclerView.adapter = mGridItemsAdapter
    }

    private fun onItemClick(selectedItem: BaseItem) {

        context?.let {
            DataRepository.getInstance()?.selectedItem?.value = selectedItem

            if(!AppUtils.isTablet() || !AppUtils.isLandscape()){
                DetailsActivity.open(it)
            }
        }

    }


}