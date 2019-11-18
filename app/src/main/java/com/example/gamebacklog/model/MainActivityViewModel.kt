package com.example.gamebacklog.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.data.GameRepository

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val gameRepository = GameRepository(application.applicationContext)
    val game = gameRepository.getAllGames()
}