package com.floward.assignment.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.floward.assignment.App
import com.floward.assignment.CShowProgress
import com.floward.assignment.prefences.PrefManager
import com.floward.assignment.ui.activities.MainActivity
import org.koin.java.KoinJavaComponent

/**
 * The BaseFragment.kt
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var mainActivity: MainActivity
    val dialoge: CShowProgress = CShowProgress(App.getAppContext())

    val preferences: PrefManager by KoinJavaComponent.inject(
        PrefManager::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        dialoge.getInstance()
    }
}