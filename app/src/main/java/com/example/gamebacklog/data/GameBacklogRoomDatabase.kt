package com.example.gamebacklog.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameBacklogRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_BACKLOG_DATABASE"

        @Volatile
        private var INSTANCE: GameBacklogRoomDatabase? = null

        fun getDatabase(context: Context): GameBacklogRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(GameBacklogRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GameBacklogRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            database.gameDao().insertGame(Game("Title", "", Date()))
                                        }
                                    }
                                }
                            })

                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}