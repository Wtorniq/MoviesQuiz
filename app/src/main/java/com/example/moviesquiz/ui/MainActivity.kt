package com.example.moviesquiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesquiz.R
import com.example.moviesquiz.ui.fragments.QuestionFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, QuestionFragment.newInstance())
                .commitNow()
        }
    }
}