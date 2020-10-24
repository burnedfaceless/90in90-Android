package com.brianabbott.a90in90.MeetingsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.brianabbott.a90in90.OverviewActivity.OverviewViewModel
import com.brianabbott.a90in90.OverviewActivity.OverviewViewModelFactory
import com.brianabbott.a90in90.R
import com.brianabbott.a90in90.database.Meeting
import com.brianabbott.a90in90.database.MeetingsDatabase
import com.brianabbott.a90in90.databinding.ActivityMeetingsBinding

class MeetingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMeetingsBinding
    private lateinit var viewModel: MeetingsViewModel
    private lateinit var viewModelFactory: MeetingsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_meetings)

        val dataSource = MeetingsDatabase.getInstance(application).meetingsDao

        viewModelFactory = MeetingsViewModelFactory(this@MeetingsActivity, dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MeetingsViewModel::class.java)

        viewModel.meetings.observe(this, androidx.lifecycle.Observer { newMeetings ->
            binding.meetingsRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MeetingsActivity)
                adapter = MeetingsAdapter(viewModel.meetings.value, this@MeetingsActivity)
            }
        })
    }

}