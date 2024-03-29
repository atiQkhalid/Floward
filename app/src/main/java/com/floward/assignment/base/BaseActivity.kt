package com.floward.assignment.base

import androidx.appcompat.app.AppCompatActivity
import com.floward.assignment.extensions.gone
import com.floward.assignment.extensions.visible
import com.floward.assignment.utils.InternetMonitor
import kotlinx.android.synthetic.main.ui_internet_monitor.*

/**
 * The BaseActivity.kt
 */

abstract class BaseActivity : AppCompatActivity() {

    //internet monitor, to monitor the internet state to to update the app
    private val internetMonitor = InternetMonitor(object :
        InternetMonitor.OnInternetConnectivityListener {
        override fun onInternetAvailable() {
            //getting the data in Background thread and showing it to the view on UI thread
            runOnUiThread {
                top_connection_shower?.gone()
            }
        }

        override fun onInternetLost() {
            //onInternetLost, getting the data in Background thread and showing it to the view on UI thread
            runOnUiThread {
                top_connection_shower?.visible()
            }
        }
    })

    //registering the internet monitor on resume
    override fun onResume() {
        super.onResume()
        internetMonitor.register(this)
    }

    //unregistering the internet monitor on pause
    override fun onPause() {
        super.onPause()
        internetMonitor.unregister(this)
    }

}