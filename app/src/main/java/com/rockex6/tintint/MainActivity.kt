package com.rockex6.tintint

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.rockex6.tintint.databinding.ActivityMainBinding
import com.rockex6.tintint.datapage.DataListCoordinator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mDataCoordinator: DataListCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mDataCoordinator = DataListCoordinator(supportFragmentManager)
        initListener()
    }


    private fun initListener() {
        binding.buttonNextPage.setOnClickListener {
            binding.buttonNextPage.visibility = View.GONE
            mDataCoordinator.showDataList()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mDataCoordinator.isVisible()) {
                    mDataCoordinator.hideDataList()
                    binding.buttonNextPage.visibility = View.VISIBLE
                } else {
                    finish()
                }
            }

        })
    }
}