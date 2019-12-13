package com.example.gamebacklog.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.gamebacklog.R
import com.example.gamebacklog.model.AddActivityViewModel
import com.example.gamebacklog.model.Game

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import kotlinx.android.synthetic.main.item_game.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var addActivityViewModel: AddActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        initView()
        initModel()
    }

    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Add Game")

        fab.setOnClickListener { view ->
            insertGame()
        }
    }

    private fun initModel() {
        addActivityViewModel = ViewModelProviders.of(this).get(AddActivityViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun insertGame() {
        val title = etTitle.text.toString()
        val platform = etPlatform.text.toString()

        val day =  etDay.text.toString()
        val month = etMonth.text.toString()
        val year = etYear.text.toString()

        if (day.isNotBlank() || month.isNotBlank() || year.isNotBlank()) {

            val date: Date
            date = formatCustomDate(day, month, year)

            if (title.isNotBlank() || !platform.isNotBlank()) {
                println("Date: $date")
                val game = Game(title, platform, date)
                addActivityViewModel.insertGame(game)

            } else {
                Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Date is not valid", Toast.LENGTH_LONG).show()
        }
    }

    fun formatCustomDate(day: String, month: String, year: String) : Date{
        val formatter = SimpleDateFormat("dd MM yyyy")
        var date: Date?= null
        date = formatter.parse("$day $month $year")

        return date

    }
}
