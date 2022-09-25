package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

     lateinit var b: Button
     lateinit var et: EditText

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.login)

          b = findViewById(R.id.ls)
          et = findViewById(R.id.user_name)

          b.setOnClickListener{
               var msg = et.text.toString()
               var a= "True"
               if(msg==""){
                    Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show()
               }
               else {

                    val mp = MyPreference(this)
                    mp.setauth("True")
                    mp.setusername(msg)
                    val i = Intent(this@WelcomeActivity, StartActivity1::class.java)
                    startActivity(i)
                    this.finish()
               }

          }

     }

}