package com.qkopy.example.textviewformatter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.qkopy.textviewformatter.QkoViewCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        QkoViewCompat.applyFormatting(editText)
        QkoViewCompat.applyFormatting(textView)

        button.setOnClickListener {


            textView.text = editText.text.toString()
        }
    }
}
