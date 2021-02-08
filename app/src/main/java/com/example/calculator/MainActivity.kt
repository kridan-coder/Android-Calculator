package com.example.calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

/*    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button*/

    var oldValue = "0"
    var newValue = "0"
    var currValue = "0"
    var operation = "nothing"
    var gotErr = false

    private lateinit var displayCurr: TextView

    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var buttonModulo: Button
    private lateinit var buttonPlusMinus: Button

    private lateinit var buttonEqual: Button

    private lateinit var buttonAC: Button

    private lateinit var buttonComma: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*        button0 = findViewById(R.id.button_0)
        button1 = findViewById(R.id.button_1)
        button2 = findViewById(R.id.button_2)
        button3 = findViewById(R.id.button_3)
        button4 = findViewById(R.id.button_4)
        button5 = findViewById(R.id.button_5)
        button6 = findViewById(R.id.button_6)
        button7 = findViewById(R.id.button_7)
        button8 = findViewById(R.id.button_8)
        button9 = findViewById(R.id.button_9)*/

        displayCurr = findViewById(R.id.textViewDigits)

        buttonPlus = findViewById(R.id.button_plus)
        buttonMinus = findViewById(R.id.button_minus)
        buttonMultiply = findViewById(R.id.button_multiply)
        buttonDivide = findViewById(R.id.button_divide)
        buttonModulo = findViewById(R.id.button_percent)
        buttonPlusMinus = findViewById(R.id.button_plusminus)
        buttonEqual = findViewById(R.id.button_equal)
        buttonAC = findViewById(R.id.button_AC)
        buttonComma = findViewById(R.id.button_comma)

        buttonAC.setOnClickListener {
            allClear()
        }

        buttonEqual.setOnClickListener {
            equalPressed()
        }

        buttonComma.setOnClickListener {
            commaPressed()
        }

        buttonPlusMinus.setOnClickListener {
            plusMinusPressed()
        }
    }

    private fun plusMinusPressed(){
        if (displayCurr.text.toString() != "0")
        {
            displayCurr.text = (displayCurr.text.toString().toFloat() * -1).toString()
        }
    }

    private fun isFloat(value:String): Boolean{
        if (value.indexOf('.') != -1)
        {
            return true
        }
        return false
    }

    private fun commaPressed(){
        if (!isFloat(displayCurr.text.toString())){
            displayCurr.text = displayCurr.text.toString() + "."
        }
    }

    private fun equalPressed(){
        if (operation == "/" && displayCurr.text.toString() == "0"){
            Toast.makeText(applicationContext, "А на ноль делить нельзя!", Toast.LENGTH_LONG).show()
        }
        else if (oldValue != "0")
        {
            when (operation) {
                "/" -> {
                    displayCurr.text = (oldValue.substring(0,oldValue.length-1).toFloat() / displayCurr.text.toString().toFloat()).toString()
                }
                "*" -> {
                    displayCurr.text = (oldValue.substring(0,oldValue.length-1).toFloat() * displayCurr.text.toString().toFloat()).toString()
                }
                "+" -> {
                    displayCurr.text = (oldValue.substring(0,oldValue.length-1).toFloat() + displayCurr.text.toString().toFloat()).toString()
                }
                "-" -> {
                    displayCurr.text = (oldValue.substring(0,oldValue.length-1).toFloat() - displayCurr.text.toString().toFloat()).toString()
                }
                "%" -> {
                    displayCurr.text = (oldValue.substring(0,oldValue.length-1).toFloat() % displayCurr.text.toString().toFloat()).toString()
                }
            }
        }
        oldValue = "0"
    }

    private fun allClear(){
        displayCurr.text = "0"
        oldValue="0"
        currValue = "0"
        operation="nothing"
    }

    fun digitButtonPressed(view: View) {
        when(view.id)
        {
            R.id.button_0 -> numPressed(0)
            R.id.button_1 -> numPressed(1)
            R.id.button_2 -> numPressed(2)
            R.id.button_3 -> numPressed(3)
            R.id.button_4 -> numPressed(4)
            R.id.button_5 -> numPressed(5)
            R.id.button_6 -> numPressed(6)
            R.id.button_7 -> numPressed(7)
            R.id.button_8 -> numPressed(8)
            R.id.button_9 -> numPressed(9)
        }
    }



    private fun numPressed(digit: Int){
        if (displayCurr.text == "H ITS" || displayCurr.text == "0")
        {
            displayCurr.text = digit.toString()
            if (digit == 1)
                displayCurr.text = "$digit"
            else
                displayCurr.text = digit.toString()
        }
        else if (displayCurr.text.length <= 8)
        {
            //displayCurr.text = displayCurr.text.toString().plus(digit.toString())

            if (digit == 1)
                displayCurr.text = displayCurr.text.toString() + "".plus(digit.toString())
            else
                displayCurr.text = displayCurr.text.toString().plus(digit.toString())
        }
    }

    fun operationButtonPressed(view: View) {
        when(view.id)
        {
            R.id.button_plus -> operationPressed("+")
            R.id.button_minus -> operationPressed("-")
            R.id.button_multiply -> operationPressed("*")
            R.id.button_divide -> operationPressed("/")
            R.id.button_percent -> operationPressed("%")
        }
    }

    private fun operationPressed(value: String)
    {
        val regex = """[%/*\-+]""".toRegex()
        //val charArray = currValue.toCharArray()
        if (regex.matches(oldValue.toCharArray()[oldValue.length - 1].toString()))
        {
            stashResult()
            operation = value
            if (!gotErr)
                oldValue += operation;
            else {
                gotErr = false;
            }

        }
        else
        {
            operation = value;
            oldValue = displayCurr.text.toString() + operation;
            displayCurr.text = "0";
            currValue = "0"
        }
    }

    private fun stashResult(){
        if (operation == "/" && displayCurr.text.toString() == "0"){
            Toast.makeText(applicationContext, "А на ноль делить нельзя!", Toast.LENGTH_LONG).show()
        }
        else
        {
            when (operation) {
                "/" -> {
                    oldValue = (oldValue.substring(0,oldValue.length-1).toFloat() / displayCurr.text.toString().toFloat()).toString()
                }
                "*" -> {
                    oldValue = (oldValue.substring(0,oldValue.length-1).toFloat() * displayCurr.text.toString().toFloat()).toString()
                }
                "+" -> {
                    oldValue = (oldValue.substring(0,oldValue.length-1).toFloat() + displayCurr.text.toString().toFloat()).toString()
                }
                "-" -> {
                    oldValue = (oldValue.substring(0,oldValue.length-1).toFloat() - displayCurr.text.toString().toFloat()).toString()
                }
                "%" -> {
                    oldValue = (oldValue.substring(0,oldValue.length-1).toFloat() % displayCurr.text.toString().toFloat()).toString()
                }
            }
            newValue = "0"
            displayCurr.text = "0"
        }
    }


}