package com.example.calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView

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

    var oldValue = 0
    var newValue = 0
    var currValue = ""

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


    }

    fun digitPressed(view: View) {
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
        if (displayCurr.text == "H ITS")
        {
            currValue = digit.toString()
            if (digit == 1)
                displayCurr.text = " $digit"
            else
                displayCurr.text = digit.toString()
        }
        else if (currValue.length <= 8)
        {
            currValue = currValue.plus(digit.toString())

            if (digit == 1)
                displayCurr.text = displayCurr.text.toString() + " ".plus(digit.toString())
            else
                displayCurr.text = displayCurr.text.toString().plus(digit.toString())
        }
    }

}