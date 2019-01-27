package com.tikal.app.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tikal.app.R
import com.tikal.app.data.models.BaseItem
import com.tikal.app.data.models.Movie
import com.tikal.app.data.models.TvShow
import com.tikal.app.network.api.TheMovieDbAPI
import kotlin.collections.ArrayList


class GridItemsAdapter(
    private val mContext: Context, val spanCount : Int , private val mOnMovieItemClickListener: (BaseItem) -> Unit
) : RecyclerView.Adapter<GridItemsAdapter.ViewHolder>() {

    private var mDataItems: ArrayList<BaseItem>? = null

    fun setData(newMovies: List<BaseItem>){

        if (mDataItems == null){
            mDataItems = ArrayList(newMovies)
            notifyDataSetChanged()
        } else {
            val positionStart = mDataItems?.size
            mDataItems!!.addAll(newMovies)
            notifyItemRangeInserted(positionStart!!.plus(1), newMovies.size);
        }
    }

    override fun onCreateViewHolder(parentView: ViewGroup, iViewType: Int): ViewHolder
    {
        var view = LayoutInflater.from(mContext).inflate(R.layout.move_item,parentView,false) as View
        val width = parentView.measuredWidth / spanCount
        var layout = view.findViewById(R.id.container) as RelativeLayout
        layout.layoutParams.width = width
        layout.layoutParams.height = (width * 1.8).toInt()
        return ViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        if(mDataItems != null){
            return mDataItems!!.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {

        val currentDataItem: BaseItem = mDataItems!!.get(index)

        if(currentDataItem is Movie){

            viewHolder.mTitleTextView.text = currentDataItem.title + " (" + currentDataItem.rating + ")";

            viewHolder.itemView.setOnClickListener { mOnMovieItemClickListener(currentDataItem) }

            Picasso.get().load(TheMovieDbAPI.ImageUrl(currentDataItem.imagePath))
                .fit()
                .centerCrop()
                .into(viewHolder.mImageView)

        } else if (currentDataItem is TvShow) {

            viewHolder.mTitleTextView.text = currentDataItem.title + " (" + currentDataItem.rating + ")";

            viewHolder.itemView.setOnClickListener { mOnMovieItemClickListener(currentDataItem) }

            Picasso.get().load(TheMovieDbAPI.ImageUrl(currentDataItem.imagePath))
                .fit()
                .centerCrop()
                .into(viewHolder.mImageView)

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val mTitleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        val mImageView: ImageView = itemView.findViewById(R.id.image_view)
    }
}