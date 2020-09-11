package com.brianabbott.a90in90

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.brianabbott.a90in90.OverviewActivity.OverviewActivity
import kotlinx.android.synthetic.main.date_format_dialog.*
import java.lang.ClassCastException

class DateFormatDialog: DialogFragment() {

    interface OnInputListener{
        fun sendInput(dateFormat: String)
    }

    lateinit var monInputListener: OnInputListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val view: View = inflater.inflate(R.layout.date_format_dialog, null)


            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)

                // Add action buttons
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        val spinner = view.findViewById<Spinner>(R.id.date_format_spinner)
                        val dateFormat = spinner.selectedItem.toString()
                        monInputListener.sendInput(dateFormat)
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