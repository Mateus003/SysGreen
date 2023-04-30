package com.example.sysgreen

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.sysgreen.databinding.ActivityRegisterUserBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class RegisterUserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityRegisterUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.background_button_return) // ícone da seta branca
            setTitle("")
            setHomeAsUpIndicator(R.drawable.ic_arrow_back) // ícone da seta branca
        }
        binding.buttonRegisterUser.setOnClickListener(this)
        initComponents()


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
        if (v.id == R.id.button_register_user){
            verifyForm()
        }
    }

    private fun initComponents(){
        val email = binding.edittextEmail
        val password = binding.edittextPassword
        val buttonRegister = binding.buttonRegisterUser
    }

    private fun verifyForm(){
        val email = binding.edittextEmail.text.toString()
        val lastName = binding.edittextLastName.text.toString()
        val password = binding.edittextPassword.text.toString()
        val firstName = binding.edittextFirstName.text.toString()
        if (email == "" || lastName == "" || password == ""|| firstName == ""){
            Toast.makeText(this, R.string.alert_form_correction, Toast.LENGTH_LONG).show()
        }else{
            registerUser()
        }
    }
    private fun registerUser(){
        val email = binding.edittextEmail.text.toString()
        val password = binding.edittextPassword.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(OnCompleteListener {task->
                if (task.isSuccessful){
                    saveDateUser()
                    Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    val error:String
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        error = "Digite uma senha com no minímo 6 caracteres"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        error = "E-mail inválido"
                    } catch (e: FirebaseAuthUserCollisionException) {
                        error = "Esse e-mail já foi usado"
                    } catch (e: Exception) {
                        error = "Erro ao cadastrar usuário"
                    }
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }

            })
    }
    private fun saveDateUser(){
        val lastName = binding.edittextLastName.text.toString()
        val firstName = binding.edittextFirstName.text.toString()

        val dataBase = FirebaseFirestore.getInstance()
        val user = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName
        )
        dataBase.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot adicionado com ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error ao adicionar documento", e)
            }

    }
}