package com.chicoikr.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View) {

        tvInput?.append((view as Button).text)
        lastNumeric = true



    }

    fun onCLear (view : View){

        tvInput?.text=""
        lastDot = false

    }
    fun onDecimalPoint (view: View){

        if (lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }



    }
    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }


    }

    fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")

        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()

            try{
                val splitValue = tvValue.split("-")
                var one = splitValue[0]
                var two = splitValue[1]

                tvInput?.text = (one.toDouble() - two.toDouble()).toString()

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }

}