package com.example.sysgreen

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sysgreen.databinding.ActivityAnalysisBinding

class AnalysisActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAnalysisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding.buttonReturnInitial.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_return_initial){
            startActivity(Intent(this,NavigationActivity::class.java))
            finish()
        }
    }
}