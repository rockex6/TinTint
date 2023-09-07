package com.rockex6.tintint

import android.R
import android.os.Bundle
import android.view.MenuItem
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
            showDataFragment()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mDataCoordinator.isVisible()) {
                    hideDataFragment()
                } else {
                    finish()
                }
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> hideDataFragment()
        }
        return true
    }

    private fun showDataFragment() {
        binding.buttonNextPage.visibility = View.GONE
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDataCoordinator.showDataList()
    }

    private fun hideDataFragment() {
        mDataCoordinator.hideDataList()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.buttonNextPage.visibility = View.VISIBLE
    }
}