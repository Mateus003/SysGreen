package com.example.sysgreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.sysgreen.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.buttonLogin.setOnClickListener(this)
        binding.register.setOnClickListener(this)
        binding.containerIcons.setOnClickListener(this)

        binding.textSupport.setOnClickListener(this)


    }

    override fun onStart() {
        super.onStart()
        val user= FirebaseAuth.getInstance().currentUser
        if (user != null){
            startActivity(Intent(this, NavigationActivity::class.java))
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            verifyLogin()
        } else if (v.id == R.id.register) {
            startActivity(Intent(this, RegisterUserActivity::class.java))
        } else if (v.id == R.id.text_support){
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
    }


    private fun verifyLogin() {
        val email = binding.textEmail.text.toString()
        val password = binding.textPassword.text.toString()
        if (email == "" || password == "") {
            Toast.makeText(this, R.string.alert_form_correction, Toast.LENGTH_SHORT).show()
        } else {
            authUser()
        }
    }

    private fun authUser() {
        val email = binding.textEmail.text.toString()
        val password = binding.textPassword.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if ((task.isSuccessful)) {
                    startActivity(Intent(this, NavigationActivity::class.java))
                }else{
                    val error: String = when (task.exception) {
                        is FirebaseAuthInvalidUserException -> "Usuário não existe"
                        is FirebaseAuthInvalidCredentialsException -> {
                            if ((task.exception as FirebaseAuthInvalidCredentialsException).errorCode == "ERROR_INVALID_EMAIL") {
                                "Formato de e-mail inválido"
                            } else {
                                "Senha inválida"
                            }
                        }
                        else -> "Erro ao logar"
                    }
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }
            }

    }


}