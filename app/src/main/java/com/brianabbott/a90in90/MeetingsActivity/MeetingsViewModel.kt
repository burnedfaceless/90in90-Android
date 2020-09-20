package com.brianabbott.a90in90.MeetingsActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import com.brianabbott.a90in90.database.MeetingsDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Copyright (c) 2020 Brian Abbott
 * Created by Brian Abbott on 9/20/20
 */
class MeetingsViewModel(context: Context, database: MeetingsDAO): ViewModel() {
    private val context: Context = context
    private val database = database
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val meetings = database.getAllMeetings()
}