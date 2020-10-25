package com.example.calculatorapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE){ findViewById<TextView>(R.id.operation)}

    private var operand1: Double?= null
    private var operand2: Double=0.0
    private var pendingOPeration = "="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result=findViewById(R.id.result)
        newNumber=findViewById(R.id.newNumber)

        //button brojevi
        val button0: Button =findViewById(R.id.button0)
        val button1: Button =findViewById(R.id.button1)
        val button2: Button =findViewById(R.id.button2)
        val button3: Button =findViewById(R.id.button3)
        val button4: Button =findViewById(R.id.button4)
        val button5: Button =findViewById(R.id.button5)
        val button6: Button =findViewById(R.id.button6)
        val button7: Button =findViewById(R.id.button7)
        val button8: Button =findViewById(R.id.button8)
        val button9: Button =findViewById(R.id.button9)
        val buttonDot: Button =findViewById(R.id.buttonDot)

        //button operacije
        val buttonEquals: Button =findViewById(R.id.buttonEqual)
        val buttonPlus: Button=findViewById(R.id.buttonPlus)
        val buttonMinus: Button=findViewById(R.id.buttonMinus)
        val buttonMultiply: Button=findViewById(R.id.buttonMultiply)
        val buttonDivide: Button=findViewById(R.id.buttonDivide)


        val listener= View.OnClickListener{ v->
            val b= v as Button
            newNumber.append(b.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val opListener= View.OnClickListener{ v->
            val op=(v as Button).text.toString()
            val value=newNumber.text.toString()
            if(value.isNotEmpty()){
                performOperation(value, op){
                }
                pendingOPeration=op
                displayOperation.text=pendingOPeration
            }
        }

        buttonMultiply.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
        buttonEquals.setOnClickListener(opListener)

        private fun performOperation(value:String, operation:String){
            if(operand1==null){
                operand1=value.toDouble()
            }
            else{
                operand2=value.toDouble()
                if(pendingOPeration=="="){
                    pendingOPeration=operation
                }
                when(pendingOPeration){
                    "=" -> operand1 = operand2
                    "*" -> operand1 = operand1!! * operand2
                    "-" -> operand1 = operand1!! - operand2
                    "+" -> operand1 = operand1!! + operand2
                    "/" -> if(operand2 == 0.0){
                        operand1=Double.NaN
                    } else {
                        operand1 = operand1!! / operand2
                    }
                }
                result.setText(operand1.toString())
                newNumber.setText("")
            }
        }

    }
}
