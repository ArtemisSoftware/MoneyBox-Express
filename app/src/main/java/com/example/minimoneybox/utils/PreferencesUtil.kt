package com.example.minimoneybox.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesUtil {


    companion object {

        const val INVESTOR_PREFERENCES_FILE = "investorPreferences"
        const val INVESTOR_NAME = "investorName"
        const val TOKEN = "token"

        /**
         * Method to save investor data
         */
        fun saveInvestor(context : Context, name : String, token : String) {

            val sharedPreferences: SharedPreferences = context.getSharedPreferences(INVESTOR_PREFERENCES_FILE, Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString(INVESTOR_NAME,name)
            editor.putString(TOKEN,token)
            editor.apply()
            editor.commit()
        }

        /**
         * Method to get the token
         */
        fun getToken(context : Context) : String{

            val sharedPreferences: SharedPreferences = context.getSharedPreferences(INVESTOR_PREFERENCES_FILE, Context.MODE_PRIVATE)
            return sharedPreferences.getString(TOKEN,"defaultToken").toString()
        }


        /**
         * Method to get the investor name
         */
        fun getInvestorName(context : Context) : String{

            val sharedPreferences: SharedPreferences = context.getSharedPreferences(INVESTOR_PREFERENCES_FILE, Context.MODE_PRIVATE)
            return sharedPreferences.getString(INVESTOR_NAME,"").toString()
        }


        /**
         * Method that allows to delete investor file
         */
        fun deleteInvestor(context : Context){

            val sharedPreferences: SharedPreferences = context.getSharedPreferences(INVESTOR_PREFERENCES_FILE, Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor =  sharedPreferences.edit()
            editor.clear()
            editor.apply()
        }
    }
}