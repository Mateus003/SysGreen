package com.example.sysgreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sysgreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.buttonLogin.setOnClickListener(this)

        binding.containerIcons.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login){
           verifyLogin()
        }else if (v.id == R.id.container_icons){
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
        }
    }

    private fun verifyLogin(){
        val email = binding.textEmail.text.toString()
        val password = binding.textPassword.text.toString()
        if (email!="" && password!=""){
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, R.string.alert_form_correction, Toast.LENGTH_SHORT).
            show()
        }
    }



}