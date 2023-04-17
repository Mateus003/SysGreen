package com.example.sysgreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sysgreen.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.buttonReturn.setOnClickListener(this)
        binding.buttonRegister.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_return){
            startActivity(Intent(this, NavigationActivity::class.java))
        }else if(v.id == R.id.button_register){
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
            (smallerThree.isChecked || moreFive.isChecked || threeAndFive.isChecked)
        ){
            val intent = Intent(this, ConfirmActivity::class.java)

            intent.putExtra("price", textPrice.toDouble())
            intent.putExtra("quantify", quantifyItems.toInt())


            intent.putExtra("smallerThree", smallerThree.isChecked)
            intent.putExtra("threeAndFive", threeAndFive.isChecked)
            intent.putExtra("moreFive", moreFive.isChecked)





            startActivity(intent)
        }else{
            Toast.makeText(this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).
            show()
        }

    }
}