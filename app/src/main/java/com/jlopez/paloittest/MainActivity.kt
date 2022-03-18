package com.jlopez.paloittest

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.jlopez.paloittest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCount.setOnClickListener {
            valideNumber()
        }
    }

    private fun valideNumber() {
        val numbers = binding.etNumer.text.toString()

        if (numbers.length in 14..16){
            printData(numbers)
        }else{
            binding.etNumer.error = "El número debe estar entre 14 y 16 digitos"
        }

    }

    private fun printData(numbers: String) {
        val numberSave = ArrayList<String>()
        val numberSaveCount = ArrayList<Number>()
        val numberSaveCount2 = ArrayList<String>()
        numbers.forEach {

            if(!numberSave.contains(it.toString())){

                var numbersFind = 0

                numbers.forEach { numberSearch ->
                    if(it == numberSearch) numbersFind++
                }
                numberSaveCount2.add("El caracter $it tiene $numbersFind repeticiones")

                numberSave.add(it.toString())
                numberSaveCount.add(Number(it.toString(), numbersFind))
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numberSaveCount2)
        binding.lvResult.adapter= adapter
        
        numberSaveCount.sortByDescending {
            it.count
        }
        binding.tvResult.text = "El número con mayor caracteres es ${numberSaveCount[0].number} con ${numberSaveCount[0].count} repeticiones"

    }


}


