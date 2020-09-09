package com.brianabbott.a90in90.OverviewActivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brianabbott.a90in90.AppSharedPreferences
import java.text.SimpleDateFormat
import java.util.*

class OverviewViewModel(context: Context): ViewModel() {
  private val context: Context = context
  private val sharedPreferences = AppSharedPreferences(context)

  /**
   * Date Range of 90 in 90
   */
  private val _dateRange = MutableLiveData<String>()
  val dateRange: LiveData<String>
    get() = _dateRange

  fun generateDateRange() {
    val startDate = getStartDate()
    val endDate = getEndDate(startDate)
    _dateRange.value = "$startDate - $endDate"
  }

  private fun getStartDate() : String {
    val date: String? = sharedPreferences.getStartingDatePreference()
    val dateFormatPreference = sharedPreferences.getDateFormatPreference()
    val dateFormatFrom = SimpleDateFormat("MM/dd/yyyy", Locale("English"))
    val dateFormatTo = SimpleDateFormat(dateFormatPreference, Locale("English"))
    val dateObject = dateFormatFrom.parse(date)
    return dateFormatTo.format(dateObject)
  }

  private fun getEndDate(startDate: String) : String {
    val dateFormatPreference = sharedPreferences.getDateFormatPreference()
    val dateFormat = SimpleDateFormat(dateFormatPreference, Locale("English"))
    val calendar = Calendar.getInstance()
    calendar.time = dateFormat.parse(startDate)!!
    calendar.add(Calendar.DAY_OF_YEAR, 89)
    return dateFormat.format(calendar.time)
  }

  fun resetSharedPreferences() {
    sharedPreferences.setStartingDayPreference(null)
    sharedPreferences.setDateFormatPreference("MM/dd/yyyy")
  }

}
