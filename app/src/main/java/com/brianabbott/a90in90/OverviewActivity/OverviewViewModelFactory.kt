package com.brianabbott.a90in90.OverviewActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brianabbott.a90in90.database.MeetingsDAO
import java.lang.IllegalArgumentException

class OverviewViewModelFactory(private val context: Context, private val dataSource: MeetingsDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            return OverviewViewModel(context, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}