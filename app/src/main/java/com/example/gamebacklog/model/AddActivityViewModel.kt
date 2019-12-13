package com.example.gamebacklog.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.data.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivityViewModel (application: Application): AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun insertGame(game: Game) {
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }
}