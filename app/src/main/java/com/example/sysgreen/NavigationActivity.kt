package com.example.sysgreen

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import com.example.sysgreen.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        //Bloquear a orientação horizontal
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val text = binding.balance
        val shader = LinearGradient(
            0f, 0f, 0f, text.paint.textSize,
            intArrayOf(Color.parseColor("#FFD700"), Color.parseColor("#CD7F32")),
            null, Shader.TileMode.CLAMP
        )

        text.paint.shader = shader

        binding.containerCollectPoint.setOnClickListener(this)
        binding.containerRegisterIcon.setOnClickListener(this)
        binding.textRegister.setOnClickListener(this)

        binding.kabum.setOnClickListener(this)
        binding.ibyte.setOnClickListener(this)
        binding.magalu.setOnClickListener(this)
        binding.terabyte.setOnClickListener(this)
        binding.pichau.setOnClickListener(this)

        binding.terabytePromotion.setOnClickListener(this)
        binding.ibytePromotion.setOnClickListener(this)
        binding.magaluPromotion.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.container_register_icon || v.id == R.id.text_register){
            startActivity(Intent(this, RegisterActivity::class.java))
        }else if(v.id == R.id.kabum){
            val url = "https://www.kabum.com.br"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }else if(v.id == R.id.ibyte || v.id == R.id.ibyte_promotion){
            val url = "https://www.ibyte.com.br"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        else if(v.id == R.id.magalu || v.id == R.id.magalu_promotion){
            val url = "https://www.magazineluiza.com.br"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        else if(v.id == R.id.pichau){
            val url = "https://www.pichau.com.br"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }else if(v.id == R.id.terabyte || v.id == R.id.terabyte_promotion){
            val url = "https://www.terabyteshop.com.br"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }else if(v.id == R.id.container_collect_point){
            startActivity(Intent(this, CollectPointActivity::class.java))
        }

    }
}