package com.floward.assignment.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.floward.assignment.databinding.ActivityMainBinding
import com.floward.assignment.extensions.replaceFragmentSafely
import com.floward.assignment.ui.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        replaceFragmentSafely(HomeFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null!!
    }
}