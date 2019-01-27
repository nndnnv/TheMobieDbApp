package com.tikal.app.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tikal.app.R
import com.tikal.app.ui.adapter.InfiniteRecyclerViewScrollListener
import com.tikal.app.viewmodel.TvShowsViewModel

class TvShowsFragment : BaseGridFragment() {

    companion object {
        fun newInstance() = TvShowsFragment()
    }

    private lateinit var mViewModel: TvShowsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.reycler_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservables()
    }

    override fun setupUi(view: View){
        super.setupUi(view)

        val mScrollListener = object: InfiniteRecyclerViewScrollListener(mGridLayoutManager) {
            override fun loadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                mViewModel.fetchNextPage()
            }
        }

        mRecyclerView.addOnScrollListener(mScrollListener)
    }

    private fun initObservables()
    {
        mViewModel = ViewModelProviders.of(this).get(TvShowsViewModel::class.java)

        mViewModel?.tvShows?.observe(this, Observer { shows ->
            mGridItemsAdapter.setData(shows!!)
        })
    }
}