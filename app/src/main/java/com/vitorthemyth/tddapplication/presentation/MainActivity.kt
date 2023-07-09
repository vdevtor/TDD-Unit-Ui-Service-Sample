package com.vitorthemyth.tddapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vitorthemyth.tddapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, PlaylistFragment.newInstance())
                .commit()
        }
    }
}