package com.szabo.additioncalculator

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //Create setOnClickListener for calcButton so that it can trigger calculate function.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calcButton: Button = findViewById(R.id.calcButton)

        calcButton.setOnClickListener { calculate() }


    } // /onCreate

    //Calculator function
    private fun calculate() {
        //Sends log message as soon as function was triggered.
        Log.i("TRIGGER", getString(R.string.logButtonTriggerMessage))

        //Defining variables, getting input values.
        val firstNumber: EditText = findViewById(R.id.editText_first_no)
        val secondNumber: EditText = findViewById(R.id.editText_second_no)
        val answerNumber: TextView = findViewById(R.id.textView_answer)
        val firstNumberString = firstNumber.text.toString()
        val secondNumberString = secondNumber.text.toString()

        /*
        If statement within try catch statement. If statement checks if any number is input
        into input fields (EditText editText_first_no and editText_second_no) and gives appropriate
        error (TextUtils & log) if not the case, else statement will convert string input from
        variables to integer and calculate the sum. It will output the result to textView answerNumber
        and the log. Try catch statement tries to alleviate issue with the string conversion where the string
        is bigger than the maximum size allowed for integers. It gives an appropriate error and
        sends a log message.
         */
        try {
            if (TextUtils.isEmpty(firstNumberString) || TextUtils.isEmpty(secondNumberString)) {
                if (TextUtils.isEmpty(firstNumberString))
                    firstNumber.error = getString(R.string.errorMessageNoInput)
                else if (TextUtils.isEmpty(secondNumberString))
                    secondNumber.error = getString(R.string.errorMessageNoInput)
                else if (TextUtils.isEmpty(firstNumberString) && TextUtils.isEmpty(secondNumberString))
                    firstNumber.error = getString(R.string.errorMessageNoInput)
                    secondNumber.error = getString(R.string.errorMessageNoInput)
            } else {
                val firstNumberInt = firstNumberString.toInt()
                val secondNumberInt = secondNumberString.toInt()
                val sum = firstNumberInt + secondNumberInt
                answerNumber.text = "$sum"
                Log.i("SUCCESS", "CALCULATION RESULT NUMBER $sum")
            }
        }catch(e:NumberFormatException){
            firstNumber.error = getString(R.string.errorMessageNumberTooBig)
            secondNumber.error = getString(R.string.errorMessageNumberTooBig)
            Log.i("EXCEPTION", getString(R.string.logIntExceptionMessage))
        }

    }
}





