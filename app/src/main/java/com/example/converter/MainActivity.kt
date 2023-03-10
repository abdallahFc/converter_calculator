package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
         val view:View = binding.root
        setContentView(view)
        binding.clear.setOnClickListener {
            clear()
        }
        binding.binaryEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ( binding.binaryEditText.hasFocus()&&s.toString().isNotEmpty()){
                    if(isValidBinary(s.toString())){
                        binding.decimalEditText.setText(convertNum(s.toString(), 2).toString())
                        binding.octalEditText.setText(convertNum(s.toString(), 2).toString(8))
                        binding.hexEditText.setText(convertNum(s.toString(), 2).toString(16))
                    }
                    else {
                        Toast.makeText(this@MainActivity,"Please enter a valid number",Toast.LENGTH_SHORT).show()
                        binding.binaryEditText.setText("")
                    }
                }
                else if (binding.binaryEditText.hasFocus()&&s.toString().isEmpty()){
                    binding.decimalEditText.setText("")
                    binding. octalEditText.setText("")
                    binding.hexEditText.setText("")
                }
            }
        })
        binding.decimalEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.decimalEditText.hasFocus()&&s.toString().isNotEmpty()) {
                    if (isValidDecimal(s.toString())){
                        binding.binaryEditText.setText(convertNum(s.toString(), 10).toString(2))
                        binding.octalEditText.setText(convertNum(s.toString(), 10).toString(8))
                        binding.hexEditText.setText(convertNum(s.toString(), 10).toString(16))
                    }else{
                        Toast.makeText(this@MainActivity,"Please enter a valid number",Toast.LENGTH_SHORT).show()
                        binding.decimalEditText.setText("")
                    }
                }else if (binding.decimalEditText.hasFocus()&&s.toString().isEmpty()){
                    binding.binaryEditText.setText("")
                    binding. octalEditText.setText("")
                    binding.hexEditText.setText("")
                }
            }
        })
        binding.octalEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.octalEditText.hasFocus()&&s.toString().isNotEmpty()) {
                    if (isValidOctal(s.toString())) {
                        binding.binaryEditText.setText(convertNum(s.toString(), 8).toString(2))
                        binding. decimalEditText.setText(convertNum(s.toString(), 8).toString())
                        binding.hexEditText.setText(convertNum(s.toString(), 8).toString(16))
                    }else{
                        Toast.makeText(this@MainActivity,"Please enter a valid number",Toast.LENGTH_SHORT).show()
                        binding.octalEditText.setText("")
                    }
                }else if (binding.octalEditText.hasFocus()&&s.toString().isEmpty()){
                    binding.binaryEditText.setText("")
                    binding.decimalEditText.setText("")
                    binding. hexEditText.setText("")
                }
            }
        })
        binding.hexEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()&&binding.hexEditText.hasFocus()) {
                    if (isValidHex(s.toString())) {
                        binding.binaryEditText.setText(convertNum(s.toString(), 16).toString(2))
                        binding.decimalEditText.setText(convertNum(s.toString(), 16).toString())
                        binding.octalEditText.setText(convertNum(s.toString(), 16).toString(8))
                    }else{
                        Toast.makeText(this@MainActivity,"Please enter a valid number",Toast.LENGTH_SHORT).show()
                        binding.hexEditText.setText("")
                    }
                }else if (binding.hexEditText.hasFocus()&&s.toString().isEmpty()){
                    binding. binaryEditText.setText("")
                    binding. decimalEditText.setText("")
                    binding. octalEditText.setText("")
                }
            }
        })

    }
    fun convertNum(number: String, base:Int):Long{
        val decimal = when (base) {
            2 -> number.toLong(2)
            8 -> number.toLong(8)
            16 -> number.toLong(16)
            else -> {
                number.toLong()
            }
        }
        return decimal
    }
    private fun clear(){
        binding.binaryEditText.setText("")
        binding.decimalEditText.setText("")
        binding.octalEditText.setText("")
        binding.hexEditText.setText("")
    }
    fun isValidBinary(binary: String): Boolean {
        return binary.matches("[0-1]+".toRegex())
    }
    fun isValidOctal(octal: String): Boolean {
        return octal.matches("[0-7]+".toRegex())
    }
    fun isValidHex(hex: String): Boolean {
        return hex.matches("[0-9A-Fa-f]+".toRegex())
    }
    fun isValidDecimal(decimal: String): Boolean {
        return decimal.matches("[0-9]+".toRegex())
    }

}