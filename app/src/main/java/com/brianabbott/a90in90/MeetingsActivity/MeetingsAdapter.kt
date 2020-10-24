package com.brianabbott.a90in90.MeetingsActivity

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brianabbott.a90in90.AppSharedPreferences
import com.brianabbott.a90in90.R
import com.brianabbott.a90in90.database.Meeting
import kotlinx.android.synthetic.main.add_meeting_dialog.view.*
import kotlinx.android.synthetic.main.meeting_row.view.*
import java.text.SimpleDateFormat
import java.util.*

class MeetingsAdapter(private val meetings: List<Meeting>?, context: Context) : RecyclerView.Adapter<MeetingsAdapter.ViewHolder>() {
    val sharedPreferences = AppSharedPreferences(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meeting_row, parent, false)
        return ViewHolder(view)
   }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (meetings != null) {
            holder.itemView.date.text = formatDate(meetings[position].date)
            holder.itemView.name.text = meetings[position].name
        } else {
            holder.itemView.date.text = ""
            holder.itemView.name.text = ""
        }

    }

    override fun getItemCount() = meetings!!.size

    private fun formatDate(date: String): String {
        val dateFormatPreference = sharedPreferences.getDateFormatPreference()
        val dateFormatFrom = SimpleDateFormat("yyyy-MM-dd", Locale("English"))
        val dateFormatTo = SimpleDateFormat(dateFormatPreference, Locale("English"))
        val dateObject = dateFormatFrom.parse(date)
        return dateFormatTo.format(dateObject)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
