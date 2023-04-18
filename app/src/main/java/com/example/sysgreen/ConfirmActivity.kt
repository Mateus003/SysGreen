package com.example.sysgreen

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.example.sysgreen.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityConfirmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.background_button_return)
            setTitle("")
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

            //Bloquear a orientação horizontal
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding.textConfirm.setOnClickListener(this)
        binding.backgroundConfirm.setOnClickListener(this)

        val money = binding.textResultMoney
        val shader = LinearGradient(
            0f, 0f, 0f, money.paint.textSize,
            intArrayOf(Color.parseColor("#FFD700"), Color.parseColor("#CD7F32")),
            null, Shader.TileMode.CLAMP
        )
        money.paint.shader = shader

        val  discount = binding.textResultDiscount
        val shaderDiscount = LinearGradient(
            0f, 0f, 0f, discount.paint.textSize,
            intArrayOf(Color.parseColor("#FFD700"), Color.parseColor("#CD7F32")),
            null, Shader.TileMode.CLAMP
        )
        discount.paint.shader = shaderDiscount
        calcReward()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.text_confirm || v.id == R.id.background_confirm){
            startActivity(Intent(this, AnalysisActivity::class.java))
            finish()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() //finaliza a Activity atual
                startActivity(Intent(this, RegisterActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun calcReward() {
        val price = intent.extras?.getDouble("price")
        val quantify = intent.extras?.getInt("quantify")

        val threeAndFive = intent.extras?.getBoolean("threeAndFive")
        val smallerThree = intent.extras?.getBoolean("smallerThree")
        val moreFive = intent.extras?.getBoolean("moreFive")

        if ((smallerThree != null || threeAndFive != null || moreFive != null)
            && price != null && quantify != null
        ) {

            if (smallerThree == true) {
                if(quantify == 1){
                    binding.textResultDiscount.text = "5% de desconto"
                    if (price < 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", price * 0.79).replace(".", ",")
                    } else if (price >= 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", price * 0.75).replace(".", ",")
                    }
                }
                else if (quantify > 1) {
                    var discount = 3.0 * (quantify - 1)
                    binding.textResultDiscount.text = "${5 + discount}% de desconto"
                    if (price < 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", (price * 0.79)*quantify).replace(".", ",")
                    } else if (price >= 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", (price * 0.75)*quantify).replace(".", ",")
                    }
                }
            }
            else if(threeAndFive == true){
                if(quantify == 1){
                    binding.textResultDiscount.text = "11% de desconto"
                    if (price < 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", (price * 0.73)*quantify).replace(".", ",")//"R$ ${price * 0.73}"
                    } else if (price >= 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", price * 0.70).replace(".", ",")//"R$ ${price * 0.70}"
                    }
                }
                else if ( quantify > 1) {
                    var discount = 3.0 * (quantify - 1)
                    binding.textResultDiscount.text = "${11 + discount}% de desconto"
                    if (price < 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", (price * 0.73)*quantify).replace(".", ",")//"R$ ${(price * 0.73) * quantify}"
                    } else if (price >= 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", (price * 0.70)*quantify).replace(".", ",")//"R$ ${(price * 0.70) * quantify}"
                    }
                }
            }
            else if(moreFive == true){
                if(quantify == 1){
                    binding.textResultDiscount.text = "18% de desconto"
                    if (price < 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", price * 0.66).replace(".", ",")//"R$ ${price * 0.66}"
                    } else if (price >= 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", price * 0.62).replace(".", ",")//"R$ ${price * 0.62}"
                    }
                }
                else if ( quantify > 1) {
                    var discount = 3.0 * (quantify - 1)
                    binding.textResultDiscount.text = "${18 + discount}% de desconto"
                    if (price < 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", (price * 0.66)*quantify).replace(".", ",")//"R$ ${(price * 0.66) * quantify}"
                    } else if (price >= 1000.0) {
                        binding.textResultMoney.text = String.format("R$ %.2f", (price * 0.62)*quantify).replace(".", ",")
                    }
                }
            }



        }

        //Menos de 3 anos e preço < R$1000 = 79% do preço + 5% de descoto
        //Menos de 3 anos e preço >=R$1000 = 75% do preço + 5% de desconto


        //3 e 5 anos e preço <R$1000 = 73% + 11% de desconto
        //3 e 5 anos e preço >=R$1000 = 70% + 11% de desconto

        //Mais de 5 anos e preço < R$1000 = 66% do preço + 18% de desconto
        //Mais de 5anos e preço >=R$1000 = 62% do preço + 18% de desconto

        
    }



}