package com.brianabbott.a90in90

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_meetings.*
import java.text.SimpleDateFormat
import java.util.*

class MeetingsActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: AppSharedPreferences
    private lateinit var startDate: String
    private lateinit var endDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meetings)

        sharedPreferences = AppSharedPreferences(this@MeetingsActivity)

        startDate = getStartDate()
        endDate = getEndDate(startDate)

        date_range_textview.text = "$startDate - $endDate"
    }

    private fun getStartDate() : String {
        return sharedPreferences.getMonth().toString() + "/" + sharedPreferences.getDay().toString() + "/" + sharedPreferences.getYear().toString()
    }

    private fun getEndDate(startDate: String) : String {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale("English"))
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(startDate)!!
        calendar.add(Calendar.DAY_OF_YEAR, 90)
        return dateFormat.format(calendar.time)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.reset -> clearData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    private fun clearData() {
        sharedPreferences.setYearPreference(0)
        sharedPreferences.setMonthPreference(0)
        sharedPreferences.setDayPreference(0)

        navigateToMainActivity()
    }
}