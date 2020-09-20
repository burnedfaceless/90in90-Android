package com.brianabbott.a90in90.MeetingsActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brianabbott.a90in90.OverviewActivity.OverviewViewModel
import com.brianabbott.a90in90.database.MeetingsDAO
import java.lang.IllegalArgumentException

/**
 * Copyright (c) 2020 Brian Abbott
 * Created by Brian Abbott on 9/20/20
 */
class MeetingsViewModelFactory(private val context: Context, private val dataSource: MeetingsDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeetingsViewModel::class.java)) {
            return MeetingsViewModel(context, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}