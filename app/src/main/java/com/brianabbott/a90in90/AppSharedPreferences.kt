package com.brianabbott.a90in90

import android.content.Context

class AppSharedPreferences(context: Context) {
    private val preferences = context.getSharedPreferences("90in90", Context.MODE_PRIVATE)

    fun setMonthPreference(month: Int) {
        val editor = preferences.edit()

        editor.putInt("month", month)

        editor.apply()
    }

    fun setDayPreference(day: Int) {
        val editor = preferences.edit()
        editor.putInt("day", day)
        editor.apply()
    }

    fun setYearPreference(year: Int) {
        val editor = preferences.edit()
        editor.putInt("year", year)
        editor.apply()
    }

    fun getMonth() : Int {
        return preferences.getInt("month", 0)
    }

    fun getDay() : Int {
        return preferences.getInt("day", 0)
    }

    fun getYear() : Int {
        return preferences.getInt("year", 0)
    }
}