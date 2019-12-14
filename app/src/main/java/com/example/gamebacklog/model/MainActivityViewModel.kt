package com.example.gamebacklog.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.data.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gameRepository = GameRepository(application.applicationContext)
    val game = gameRepository.getAllGames()

    fun deleteAllGames() {
        ioScope.launch {
            gameRepository.deleteAllGames()
        }
    }

    fun deleteGame (game: Game) {
        ioScope.launch {
            gameRepository.deleteGame(game)
        }
    }

    fun undoDeleteGame(game: Game) {
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }

}