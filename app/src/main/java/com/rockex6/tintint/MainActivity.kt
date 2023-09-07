package com.rockex6.tintint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rockex6.tintint.databinding.ActivityMainBinding
import com.rockex6.tintint.datapage.DataActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        binding.buttonNextPage.setOnClickListener {
            startActivity(Intent(this@MainActivity, DataActivity::class.java))
        }
    }
}