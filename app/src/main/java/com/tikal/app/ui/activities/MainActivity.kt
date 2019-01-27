package com.tikal.app.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tikal.app.R
import com.tikal.app.ui.adapter.MainFragmentsAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}