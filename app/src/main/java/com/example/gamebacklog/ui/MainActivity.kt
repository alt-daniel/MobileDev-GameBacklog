package com.example.gamebacklog.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.data.GameRepository
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.model.MainActivityViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_game.*
import kotlinx.coroutines.*

const val ADD_ACTVITY_REQUEST_CODE = 100
lateinit var gameRepository: GameRepository
val mainScope =  CoroutineScope(Dispatchers.Main)

class MainActivity : AppCompatActivity() {

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private lateinit var mainActivityViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initView()
        initViewModel()
    }

    private fun initView() {
        fab.setOnClickListener { view ->
           startAddActvity()
        }

        rvGames.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter

        createItemTouchHelper().attachToRecyclerView(rvGames)
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // Observe reminders from the view model, update the list when the data is changed.
        mainActivityViewModel.game.observe(this, Observer { games ->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })

    }

    /**
     * TODO Garbage Bin to delete all Games
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * TODO Delete on SWIPE TO LEFT
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete_games -> {
                deleteAllGames()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createItemTouchHelper() : ItemTouchHelper {
        //Creates TouchHelper object for swiping to the left.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            //Disabled ability to move item up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false;
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]
                mainActivityViewModel.deleteGame(gameToDelete)
                Snackbar.make(rvGames, "${gameToDelete.title} is deleted", Snackbar.LENGTH_SHORT).show()
                }
            }

        return ItemTouchHelper(callback)
    }

    /**
     * Start {AddActivity} class.
     */
    private fun startAddActvity() {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    private fun deleteAllGames() {
        mainActivityViewModel.deleteAllGames()
        Snackbar.make(rvGames, "Succesfully deleted ${games.size} Games", Snackbar.LENGTH_LONG).show()
    }
}
