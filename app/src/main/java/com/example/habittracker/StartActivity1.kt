package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StartActivity1: AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    val mp= MyPreference(this)
    var auth= mp.getauth()
        var i:Intent
        if( auth.equals("False")){
            i = Intent(this@StartActivity1, WelcomeActivity::class.java)

        }
        else{
            i = Intent(this@StartActivity1, MainActivity::class.java)
        }
    startActivity(i)
        this.finish()
    }


}