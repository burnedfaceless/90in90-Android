package com.brianabbott.a90in90.OverviewActivity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.brianabbott.a90in90.*
import com.brianabbott.a90in90.database.MeetingsDatabase
import com.brianabbott.a90in90.databinding.ActivityOverviewBinding

class OverviewActivity : AppCompatActivity(), DateFormatDialog.OnInputListener {
    private lateinit var binding: ActivityOverviewBinding
    private lateinit var viewModel: OverviewViewModel
    private lateinit var viewModelFactory: OverviewViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_overview)

        val dataSource = MeetingsDatabase.getInstance(application).meetingsDao

        viewModelFactory = OverviewViewModelFactory(this@OverviewActivity, dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)

        binding.lifecycleOwner = this

        viewModel.generateDateRange()

        viewModel.dateRange.observe(this, androidx.lifecycle.Observer { newDateRange ->
            binding.dateRangeTextview.text = newDateRange
        })


        val button = binding.addMeetingButton

        button.setOnClickListener {
            openDialog()
        }

    }

    override fun sendInput(input: String) {
        viewModel.updateDateRange(input)
    }

    private fun openDateFormatDialog() {
        val dateFormatDialog = DateFormatDialog()
        dateFormatDialog.show(supportFragmentManager, "Select Date Format")
    }


    private fun openDialog() {
        val addMeetingDialog = AddMeetingDialog()
        addMeetingDialog.show(supportFragmentManager, "Add Meeting")
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.reset -> reset()
            R.id.date_format -> openDateFormatDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    private fun reset() {
        viewModel.resetSharedPreferences()
        navigateToMainActivity()
    }


}