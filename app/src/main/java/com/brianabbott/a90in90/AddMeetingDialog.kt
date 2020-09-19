package com.brianabbott.a90in90

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import org.joda.time.format.DateTimeFormat
import java.lang.ClassCastException
import java.text.SimpleDateFormat
import java.util.*

class AddMeetingDialog(startDate: Long, endDate: Long): DialogFragment() {
    private val startDate = startDate
    private val endDate = endDate

    interface OnInputListener {
        fun sendMeetingInfo(meetingName: String, meetingDate: String)
    }

    lateinit var monInputListener: OnInputListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val view: View = inflater.inflate(R.layout.add_meeting_dialog, null)

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)
                // Add action buttons
                    val meetingDatePicker = view.findViewById<DatePicker>(R.id.meeting_date)
                        meetingDatePicker.minDate = startDate
                        meetingDatePicker.maxDate = endDate
                builder.setPositiveButton("Add Meeting",
                    DialogInterface.OnClickListener { dialog, id ->
                        val meetingNameEditText  = view.findViewById<EditText>(R.id.meeting_name)
                        val meetingName: String = meetingNameEditText.text.toString()
                        val month: String = meetingDatePicker.month.plus(1).toString()
                        val day: String = meetingDatePicker.dayOfMonth.toString()
                        val year: String = meetingDatePicker.year.toString()
                        val meetingDate = "$month/$day/$year"
                        monInputListener.sendMeetingInfo(meetingName, meetingDate)
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()!!.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            monInputListener = activity as OnInputListener
        } catch (e: ClassCastException) {
            Log.e(TAG, "onAttach: ClassCastException: " + e.message)
        }
    }
}
