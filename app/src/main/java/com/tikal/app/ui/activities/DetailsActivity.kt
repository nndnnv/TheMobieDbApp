package com.tikal.app.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.tikal.app.data.models.Movie
import kotlinx.android.synthetic.main.content_movie_details.*
import android.graphics.drawable.Drawable
import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.tikal.app.R
import com.tikal.app.data.models.BaseItem
import com.tikal.app.viewmodel.DetailsViewModel
import android.transition.ChangeImageTransform
import com.tikal.app.data.models.TvShow
import com.tikal.app.network.api.TheMovieDbAPI


class DetailsActivity : AppCompatActivity() {

    private lateinit var mViewModel: DetailsViewModel
    private lateinit var mMovieImageView: ImageView

    companion object {
        fun open(activityContext: Context){
            val intent = Intent(activityContext, DetailsActivity::class.java)
            activityContext.startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home ->
                onBackPressed()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        window.sharedElementEnterTransition = ChangeImageTransform()

        initObservables()
    }

    private fun initObservables(){

        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        mViewModel.dataItem?.observe(this, Observer { dataItem ->
            bindDataItem(dataItem!!)
        })

    }

    private fun bindDataItem(dataItem: BaseItem) {

        mMovieImageView = findViewById(R.id.movie_image_view)

        val target = object: com.squareup.picasso.Target {
            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                mMovieImageView.setImageBitmap(bitmap)

                Palette.from(bitmap!!).generate { palette ->
                    if(palette != null){
                        if(palette.darkVibrantSwatch != null){
                            getWindow()?.statusBarColor = palette.darkVibrantSwatch?.rgb!!;
                        }

                        if(palette.vibrantSwatch != null){
                            collapsing_toolbar.setContentScrimColor(palette.vibrantSwatch?.rgb!!)
                        }
                    }
                }

            }
        }

        val toolbar = findViewById(R.id.toolbar) as android.support.v7.widget.Toolbar

        if(dataItem is Movie){

            Picasso.get().load(TheMovieDbAPI.ImageUrl(dataItem.imagePath))
                .into(target)
            toolbar.title = dataItem.title

        } else if (dataItem is TvShow) {
            Picasso.get().load(TheMovieDbAPI.ImageUrl(dataItem.imagePath))
                .into(target)
            toolbar.title = dataItem.title
        }

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
