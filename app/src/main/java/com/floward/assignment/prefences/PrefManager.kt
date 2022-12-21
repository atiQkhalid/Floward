package com.floward.assignment.prefences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.floward.assignment.utils.Const.SESSION_DETAILS
import com.floward.assignment.App

class PrefManager(private var context: Context) {

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: PrefManager? = null
        fun getInstance(): PrefManager {
            if (instance == null)
                instance =
                    PrefManager(App.getAppContext())
            return instance!!
        }
    }

    var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SESSION_DETAILS, Context.MODE_PRIVATE)

    fun putStringPref(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }


    fun getStringPref(key: String, defaultKey: String?): String? {
        return sharedPreferences.getString(key, defaultKey)
    }
}
