package com.example.moviesquiz

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.moviesquiz.domain.QuizRepo
import com.example.moviesquiz.domain.QuizRepoImpl
import com.example.moviesquiz.domain.QuizRepoStupidImpl
import com.example.moviesquiz.ui.MainViewModel

class App: Application() {
    private val stupidRepo: QuizRepo by lazy { QuizRepoStupidImpl() }
    private val repo: QuizRepo by lazy { QuizRepoImpl() }
    val viewModel: MainViewModel by lazy { MainViewModel(repo) }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var appInstance: Context
    }
}



val Fragment.app: App
    get() = requireActivity().application as App