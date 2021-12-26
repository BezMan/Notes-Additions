package com.example.android.architecture.blueprints.todoapp.addedittask

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.android.architecture.blueprints.todoapp.R
import kotlinx.android.synthetic.main.detail_activity_dialog_priority.*

class CustomDialogFragment(private val listener: OnPrioritySaveClickListener, private val currPriority: Int) : DialogFragment() {

    companion object {
        const val LOWEST_PRIORITY = 1
        const val HIGHEST_PRIORITY = 3
    }

    /** The system calls this to get the DialogFragment's layout, regardless
    of whether it's being displayed as a dialog or an embedded fragment. */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.detail_activity_dialog_priority, container, false)
    }

    /** The system calls this only when creating the layout in a dialog. */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberPicker.wrapSelectorWheel = false //non-circular
        numberPicker.minValue = LOWEST_PRIORITY
        numberPicker.maxValue = HIGHEST_PRIORITY
        numberPicker.value = currPriority

        savePriorityBtn.setOnClickListener {
            val newPriority = numberPicker.value
            listener.onPriorityDialogSaveCallback(newPriority)
            dismiss()
        }
    }


    interface OnPrioritySaveClickListener {
        fun onPriorityDialogSaveCallback(newPriority: Int)
    }


}
