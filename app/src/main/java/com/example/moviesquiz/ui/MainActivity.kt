package com.example.moviesquiz.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Database
import com.example.moviesquiz.App
import com.example.moviesquiz.databinding.ActivityMainBinding
import com.example.moviesquiz.ui.fragments.LevelsRoomFragment
import java.util.prefs.Preferences

private const val ARG_IS_DB_CREATED = "ARG_IS_DB_CREATED"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDbCreated = false
    private val viewModel by lazy { (application as App).viewModel }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.container)

        isDbCreated = getPreferences(MODE_PRIVATE).getBoolean(ARG_IS_DB_CREATED, false)
        if (!isDbCreated) {
            viewModel.setDataToDatabase()
            isDbCreated = true
            getPreferences(MODE_PRIVATE).edit().putBoolean(ARG_IS_DB_CREATED, isDbCreated).apply()
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, LevelsRoomFragment.newInstance())
                .commitNow()
        }
    }
}