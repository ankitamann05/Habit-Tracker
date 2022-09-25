package com.example.habittracker

import android.content.Context

class MyPreference(context : Context) {

    val PREFERENCE_NAME = "UserName"
    val PREFERENCE_UNAME= "uname"
    val AUTHENTICATION = "auth"

    val preference= context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getusername() : String? {
        return preference.getString(PREFERENCE_UNAME, "User")
    }

    fun setusername(uname:String){
        val editor= preference.edit()
        editor.putString(PREFERENCE_UNAME, uname)
        editor.apply()
    }

    fun getauth() : String? {
        return preference.getString(AUTHENTICATION, "False")
    }

    fun setauth(a:String){
        val editor= preference.edit()
        editor.putString(AUTHENTICATION, a)
        editor.apply()
    }

}