package com.example.moviesquiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.moviesquiz.App
import com.example.moviesquiz.R
import com.example.moviesquiz.databinding.ActivityMainBinding
import com.example.moviesquiz.ui.fragments.LevelsRoomFragment

private const val ARG_IS_DB_CREATED = "ARG_IS_DB_CREATED"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var isDbCreated = false
    private val viewModel by lazy { (application as App).viewModel }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MoviesQuiz)
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

        viewModel.getNotificationDialogLiveData().observe(this) { event ->
            event.getContentIfNotHandled()?.let { message ->
                AlertDialog.Builder(this).apply {
                    setMessage(message)
                    create()
                    show()
                }
            }
        }
    }
}