package com.example.appagenda.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.appagenda.R

class DeleteDialogFragment(var deleteListener: DeleteListener) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.delete_dialog_message)
                .setPositiveButton(R.string.delete,
                    DialogInterface.OnClickListener { dialog, id ->
                        deleteListener.deletePersonDialog()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    interface DeleteListener{
        fun deletePersonDialog()
    }
}