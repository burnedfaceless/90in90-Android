package com.brianabbott.a90in90

import android.content.Context

class AppSharedPreferences(context: Context) {
    private val preferences = context.getSharedPreferences("90in90", Context.MODE_PRIVATE)

    fun setStartingDayPreference(date: String?) {
        val editor = preferences.edit()
        editor.putString("startingDate", date)
        editor.apply()
    }

    fun setDateFormatPreference(dateFormat: String?) {
        val editor = preferences.edit()
        editor.putString("dateFormat", dateFormat)
        editor.apply()
    }

    fun getDateFormatPreference() : String? {
        return preferences.getString("dateFormat", "MM/dd/yyyy")
    }

    fun getStartingDatePreference() : String? {
        return preferences.getString("startingDate", null)
    }

}