package com.example.gamebacklog.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.gamebacklog.R

import kotlinx.android.synthetic.main.activity_main.*

const val ADD_ACTVITY_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initView()
        initModel()
    }

    private fun initView() {
        fab.setOnClickListener { view ->
           startAddActvity()
        }
    }

    private fun initModel() {}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete_games -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startAddActvity() {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }
}
