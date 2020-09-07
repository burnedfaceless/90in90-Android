package com.brianabbott.a90in90

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var sharedPreferences: AppSharedPreferences
    var day = 0
    var month = 0
    var year = 0

    var savedMonth = 0
    var savedDay = 0
    var savedYear = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = AppSharedPreferences(this@MainActivity)


        /** If the user has selected a date, navigate to the meetings activity */
        if ((sharedPreferences.getDay() != 0) && (sharedPreferences.getMonth() != 0) && (sharedPreferences.getYear() != 0)) {
            navigateToMeetingsActivity()
        }

        setContentView(R.layout.activity_main)

        // Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        pickDate()
    }

    private fun getDate() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun pickDate() {
        button.setOnClickListener {
            getDate()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month + 1
        savedYear = year

        getDate()
        saveDate()
        navigateToMeetingsActivity()
    }

    private fun navigateToMeetingsActivity() {
        val intent = Intent(this, MeetingsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    private fun saveDate() {
        sharedPreferences.setDayPreference(savedDay)
        sharedPreferences.setMonthPreference(savedMonth)
        sharedPreferences.setYearPreference(savedYear)
    }

}