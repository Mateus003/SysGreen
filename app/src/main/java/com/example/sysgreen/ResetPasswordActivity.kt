package com.example.sysgreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.sysgreen.databinding.ActivityResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.background_button_return) // ícone da seta branca
            setTitle("")
            setHomeAsUpIndicator(R.drawable.ic_arrow_back) // ícone da seta branca
        }

        binding.buttonSentEmail.setOnClickListener(this)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // finaliza a Activity atual
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button_sent_email->{
                resetPassword()
            }
        }
    }

    private fun resetPassword(){
        val auth = FirebaseAuth.getInstance()

        val email = binding.edittextEmail.text.toString()

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Um e-mail de redefinição de senha foi enviado para $email", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Não foi possível enviar um e-mail de redefinição de senha para $email", Toast.LENGTH_LONG).show()
                }
            }

    }
}