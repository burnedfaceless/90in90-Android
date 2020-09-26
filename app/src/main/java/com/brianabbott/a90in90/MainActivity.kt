package com.brianabbott.a90in90

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.brianabbott.a90in90.OverviewActivity.OverviewActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var sharedPreferences: AppSharedPreferences
    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0

    private var savedMonth: Int = 0
    private var savedDay: Int = 0
    private var savedYear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = AppSharedPreferences(this@MainActivity)

        /**
         * If the user has previously selected a date, navigate to the meetings activity
         * */
        if (sharedPreferences.getStartingDatePreference() != null) {
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
        val intent = Intent(this, OverviewActivity::class.java)
        startActivity(intent)
    }

    private fun saveDate() {
        val month = savedMonth.toString()
        val day = savedDay.toString()
        val year = savedYear.toString()
        sharedPreferences.setStartingDayPreference("$month/$day/$year")
    }

}