package com.brianabbott.a90in90.OverviewActivity

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.brianabbott.a90in90.AppSharedPreferences
import com.brianabbott.a90in90.database.Meeting
import com.brianabbott.a90in90.database.MeetingsDAO
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class OverviewViewModel(context: Context, database: MeetingsDAO): ViewModel() {
  private val context: Context = context
  private val database = database
  private val sharedPreferences = AppSharedPreferences(context)
  private var viewModelJob = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

  val numOfMeetings = database.countMeetings()



  /**
   * Date Range of 90 in 90
   */
  private val _dateRange = MutableLiveData<String>()
  val dateRange: LiveData<String>
    get() = _dateRange

  /**
   * The text that populates the meetings_attended_textview
   */
  private val _meetingsAttendedText = MutableLiveData<String>()
  val meetingsAttendedText: LiveData<String>
    get() = _meetingsAttendedText

  /**
   * The text that populates the meetings_remaining_textview
   */
  private val _meetingsRemaingingText = MutableLiveData<String>()
  val meetingsRemainingText: LiveData<String>
    get() = _meetingsRemaingingText

  fun generateMeetingsAttendedText() {
    if (numOfMeetings.value == 1) {
      _meetingsAttendedText.value = "You have attended ${numOfMeetings.value.toString()} meeting."
    } else {
      _meetingsAttendedText.value = "You have attended ${numOfMeetings.value.toString()} meetings."
    }
  }

  fun generateMeetingsRemainingText() {
    when(val remainingMeetings = 90 - numOfMeetings.value!!) {
      0 -> _meetingsRemaingingText.value = "You have ${remainingMeetings.toString()} meetings remaining."
      1 -> _meetingsRemaingingText.value = "You have ${remainingMeetings.toString()} meeting remaining."
      in 2 .. 90 -> _meetingsRemaingingText.value = "You have ${remainingMeetings.toString()} meetings remaining."
      else -> _meetingsRemaingingText.value = "You have completed your 90 in 90, but feel free to catch more meetings."
    }
  }


  override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
  }


  fun addMeeting(meeting: Meeting) {
    uiScope.launch {
      insert(meeting)
    }
    generateMeetingsAttendedText()
  }

  private suspend fun insert(meeting: Meeting) {
    withContext(Dispatchers.IO) {
      database.insert(meeting)
    }
  }

  fun clearDatabase() {
    uiScope.launch {
      delete()
    }
  }

  private suspend fun delete() {
    withContext(Dispatchers.IO) {
      database.clear()
    }
  }

  fun generateDateRange() {
    val startDate = getStartDate()
    val endDate = getEndDate(startDate)
    _dateRange.value = "$startDate - $endDate"
  }

  fun updateDateRange(dateFormat: String) {
    if (dateFormat == "Month/Day/Year") {
      sharedPreferences.setDateFormatPreference("MM/dd/yyyy")
    } else {
      sharedPreferences.setDateFormatPreference("dd/MM/yyyy")
    }
    generateDateRange()
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
