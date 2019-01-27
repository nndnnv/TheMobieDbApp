package com.tikal.app.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager

abstract class InfiniteRecyclerViewScrollListener : RecyclerView.OnScrollListener {

    // number of items to have below your current scroll position before asking to load more
    private var mNumberOfVisibleItemsThreshold = 6

    // current page index of data you have loaded
    private var mCurrentPage = 0

    // total number of items
    private var mTotalItemsCount = 0

    // indicates if we're in loading state or not
    private var mLoading = true

    private var mLayoutManager: RecyclerView.LayoutManager

    constructor(layoutManager: GridLayoutManager) {
        this.mLayoutManager = layoutManager
        mNumberOfVisibleItemsThreshold = mNumberOfVisibleItemsThreshold * layoutManager.spanCount
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int)
    {
        // find last visible position
        val lastVisibleItemPosition = (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
        val totalItemCount = mLayoutManager.itemCount

        // reset current page & totalItemsCount
        if (totalItemCount < mTotalItemsCount) {
            this.mCurrentPage = 0
            this.mTotalItemsCount = totalItemCount
            if (totalItemCount == 0) {
                this.mLoading = true
            }
        }

        // check if we're in loading and did totalItemCount changed
        // this is our condition to check whether loading has ended
        if (mLoading && totalItemCount > mTotalItemsCount) {
            mTotalItemsCount = totalItemCount
            mLoading = false
        }

        // check if last visible position (plus our threshold) is greater than total items already loaded
        if (!mLoading && lastVisibleItemPosition + mNumberOfVisibleItemsThreshold > totalItemCount) {
            mCurrentPage++
            mLoading = true
            loadMore(mCurrentPage, totalItemCount, view)
        }
    }


    // load more abstract function
    abstract fun loadMore(page: Int, totalItemsCount: Int, view: RecyclerView)
}