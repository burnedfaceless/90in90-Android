package com.brianabbott.a90in90.OverviewActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.brianabbott.a90in90.AddMeetingDialog
import com.brianabbott.a90in90.AppSharedPreferences
import com.brianabbott.a90in90.MainActivity
import com.brianabbott.a90in90.R
import com.brianabbott.a90in90.databinding.ActivityOverviewBinding

class OverviewActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: AppSharedPreferences
    private lateinit var startDate: String
    private lateinit var endDate: String
    private lateinit var binding: ActivityOverviewBinding
    private lateinit var viewModel: OverviewViewModel
    private lateinit var viewModelFactory: OverviewViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_overview)

        //viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        //sharedPreferences = AppSharedPreferences(this@MeetingsActivity)



        viewModelFactory = OverviewViewModelFactory(this@OverviewActivity)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)

        viewModel.generateDateRange()

        viewModel.dateRange.observe(this, androidx.lifecycle.Observer { newDateRange ->
            binding.dateRangeTextview.text = newDateRange
        })


        val button = binding.addMeetingButton

        button.setOnClickListener {
            openDialog()
        }
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