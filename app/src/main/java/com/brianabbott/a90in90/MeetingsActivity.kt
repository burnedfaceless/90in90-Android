package com.brianabbott.a90in90

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_meetings.*
import java.text.SimpleDateFormat
import java.util.*

class MeetingsActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: AppSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meetings)

        sharedPreferences = AppSharedPreferences(this@MeetingsActivity)

        val startDate: String = getDate()
        val endDate = endDate(startDate)

        date_range_textview.text = "$startDate - $endDate"
    }

    private fun getDate() : String {
        return sharedPreferences.getMonth().toString() + "/" + sharedPreferences.getDay().toString() + "/" + sharedPreferences.getYear().toString()
    }

    private fun endDate(startDate: String) : String {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale("English"))
        val calendar = Calendar.getInstance()
        calendar.setTime(dateFormat.parse(startDate)!!)
        calendar.add(Calendar.DAY_OF_YEAR, 90)
        return dateFormat.format(calendar.time)
    }
}