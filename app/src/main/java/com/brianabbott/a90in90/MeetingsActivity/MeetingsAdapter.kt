package com.brianabbott.a90in90.MeetingsActivity

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brianabbott.a90in90.R
import com.brianabbott.a90in90.database.Meeting
import kotlinx.android.synthetic.main.add_meeting_dialog.view.*
import kotlinx.android.synthetic.main.meeting_row.view.*

class MeetingsAdapter(private val meetings: List<Meeting>?) : RecyclerView.Adapter<MeetingsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meeting_row, parent, false)
        return ViewHolder(view)
   }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.date.text = meetings!![position].date
        holder.itemView.name.text = meetings!![position].name
    }

    override fun getItemCount() = meetings!!.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
