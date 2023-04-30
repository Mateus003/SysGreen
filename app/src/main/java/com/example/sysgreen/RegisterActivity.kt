package com.example.sysgreen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.sysgreen.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRegister.setOnClickListener(this)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.background_button_return) // ícone da seta branca
            setTitle("")
            setHomeAsUpIndicator(R.drawable.ic_arrow_back) // ícone da seta branca
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // finaliza a Activity atual
                startActivity(Intent(this, NavigationActivity::class.java)) // inicia a NavigationActivity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onClick(v: View) {
        if (v.id == R.id.button_register){
            verifyItemsForm()
        }
    }

    private fun verifyItemsForm(){
        val textName = binding.edittextNameElectronic.text.toString()
        val textPrice = binding.edittextPrice.text.toString()
        val quantifyItems = binding.edittextQuantifyItems.text.toString()
        val cep = binding.edittextCep.text.toString()
        val numberHouse = binding.edittextNumberHouse.text.toString()
        val dateCollect = binding.dateCollect.text.toString()
        val hours = binding.edittextHoursCollect.text.toString()
        val smallerThree = binding.optionSmallerThree
        val moreFive = binding.optionMoreFive
        val threeAndFive = binding.optionThreeAndFive

        if (textName != "" && textPrice !="" && textPrice!= "0"&& quantifyItems != ""&&
                quantifyItems!="0" && cep!="" && numberHouse!="" && dateCollect!="" && hours!="" &&
            (smallerThree.isChecked || moreFive.isChecked || threeAndFive.isChecked)){
            if (hours.toInt() in 7..18){
                val intent = Intent(this, ConfirmActivity::class.java)

                intent.putExtra("price", textPrice.toDouble())
                intent.putExtra("quantify", quantifyItems.toInt())


                intent.putExtra("smallerThree", smallerThree.isChecked)
                intent.putExtra("threeAndFive", threeAndFive.isChecked)
                intent.putExtra("moreFive", moreFive.isChecked)
                startActivity(intent)
            }else{
                Toast.makeText(this, R.string.alert_hour_collect, Toast.LENGTH_LONG).
                show()
            }

        }else{
            Toast.makeText(this, R.string.alert_form_correction, Toast.LENGTH_SHORT).
            show()
        }

    }
}