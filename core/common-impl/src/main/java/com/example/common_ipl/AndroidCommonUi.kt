package com.example.common_ipl

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.common.AlertDialogConfig
import com.example.common.CommonUi
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Default implementation of [CommonUI] to display toasts and dialogs.
 * Dialogs are displayed only when activity is started. Otherwise the are
 * postponed.
 *
 */



class AndroidCommonUi(
    private val applicationContext: Context
): CommonUi, ActivityRequired {

    private var currentActivity: FragmentActivity? = null
    private var isStarted = false
    private val dialogRecord = mutableListOf<DialogRecord>()


    override fun toast(message: String) {
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }

    override suspend fun alertDialog(config: AlertDialogConfig): Boolean = suspendCancellableCoroutine {
        val record = DialogRecord(config, it)
        dialogRecord.add(record)
        if (isStarted) {
            startDialog(record)
        }
        it.invokeOnCancellation {
            record.dialog?.dismiss()
            dialogRecord.remove(record)
        }
    }
    override fun onCreated(activity: FragmentActivity) {
        this.currentActivity = activity
    }

    override fun onStarted() {
        isStarted = true
        dialogRecord.forEach{startDialog(it)}
    }

    override fun onStopped() {
        isStarted = false
        dialogRecord.forEach{it.dialog?.dismiss()}
    }

    override fun onDestroy() {
        if (this.currentActivity?.isFinishing == true){
            this.dialogRecord.clear()
        }
        this.currentActivity = null
    }


    private fun startDialog(record: DialogRecord){
       val activity = this.currentActivity ?: return
        val builder = AlertDialog.Builder(activity)
            .setTitle(record.config.title)
            .setMessage(record.config.message)
            .setCancelable(true)
            .setOnCancelListener{
                record.continuation.resume(false)
                dialogRecord.remove(record)
            }
            .setPositiveButton(record.config.positiveButton){_,_->
                record.continuation.resume(false)
                dialogRecord.remove(record)
            }
        if(record.config.negativeButton != null){
            builder.setNegativeButton(record.config.negativeButton){_,_->
                record.continuation.resume(false)
                dialogRecord.remove(record)
            }
        }
        val dialog = builder.create()
        record.dialog = dialog
        dialog.show()
    }



    private class DialogRecord(
        val config: AlertDialogConfig,
        val continuation: CancellableContinuation<Boolean>,
        var dialog: Dialog? = null
    )

}