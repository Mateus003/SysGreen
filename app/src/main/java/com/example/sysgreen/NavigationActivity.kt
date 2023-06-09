package com.example.sysgreen

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import com.example.sysgreen.databinding.ActivityNavigationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class NavigationActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityNavigationBinding
    val dataBase = FirebaseFirestore.getInstance()
    private lateinit  var userId: String
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

        buttonSetOnClickListener()
    }

    override fun onStart() {
        super.onStart()
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val dataBase = FirebaseFirestore.getInstance()

        dataBase.collection("Usuarios").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val name = document.getString("name")
                    // exibe o nome na tela
                    binding.textNameUser.text = "Olá, $name"
                }
            }
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.container_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            R.id.container_collect_point -> {
                startActivity(Intent(this, CollectPointActivity::class.java))
            }

            R.id.image_logout->{
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))

            }
            R.id.kabum -> {
                val url = "https://www.kabum.com.br"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
            R.id.ibyte, R.id.ibyte_promotion -> {
                val url = "https://www.ibyte.com.br"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
            R.id.magalu, R.id.magalu_promotion -> {
                val url = "https://www.magazineluiza.com.br"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
            R.id.pichau -> {
                val url = "https://www.pichau.com.br"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
            R.id.terabyte, R.id.terabyte_promotion -> {
                val url = "https://www.terabyteshop.com.br"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
    }

    private fun buttonSetOnClickListener(){
        binding.imageLogout.setOnClickListener(this)
        binding.containerCollectPoint.setOnClickListener(this)
        binding.containerRegister.setOnClickListener(this)

        binding.kabum.setOnClickListener(this)
        binding.ibyte.setOnClickListener(this)
        binding.magalu.setOnClickListener(this)
        binding.terabyte.setOnClickListener(this)
        binding.pichau.setOnClickListener(this)

        binding.terabytePromotion.setOnClickListener(this)
        binding.ibytePromotion.setOnClickListener(this)
        binding.magaluPromotion.setOnClickListener(this)
    }
}