package com.floward.assignment

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView

class CShowProgress(context: Context) {
    var s_m_oCShowProgress: CShowProgress? = null
    lateinit var m_Dialog: Dialog
    private var m_ProgressBar: ProgressBar? = null
    private var progressText: TextView? = null

    fun getInstance(): CShowProgress {
        if (s_m_oCShowProgress == null) {
            s_m_oCShowProgress = CShowProgress(App.getAppContext())
        }
        return s_m_oCShowProgress as CShowProgress
    }

    fun show(m_Context: Context, message: String) {
        m_Dialog = Dialog(m_Context)
        m_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        m_Dialog.setContentView(R.layout.progress_bar)
        m_ProgressBar = m_Dialog.findViewById(R.id.progressBar_cyclic)
        m_ProgressBar!!.visibility = View.VISIBLE
        m_ProgressBar!!.isIndeterminate = true
        m_Dialog.setCancelable(false)
        m_Dialog.setCanceledOnTouchOutside(false)
        m_Dialog.show()
    }

    fun dismiss() {
        //no need to re initialise dialog only check for if the dialog is showing
        if(m_Dialog.isShowing){
            m_Dialog.dismiss()
        }
    }
}