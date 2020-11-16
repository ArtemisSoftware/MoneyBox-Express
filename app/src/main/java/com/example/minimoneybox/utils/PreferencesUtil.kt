package com.example.minimoneybox.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesUtil {


    companion object {

        const val INVESTOR_PREFERENCES_FILE = "investorPreferences"
        const val INVESTOR_NAME = "investorName"
        const val INVESTOR_EMAIL = "investorEmail"
        const val TOKEN = "token"

        const val APP_PREFERENCES_FILE = "appPreferences"
        const val FIRST_USE = "firstUse"

        /**
         * Method to save investor data
         */
        fun saveInvestor(context : Context, email : String, name : String, token : String) {

            val sharedPreferences: SharedPreferences = context.getSharedPreferences(INVESTOR_PREFERENCES_FILE, Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString(INVESTOR_EMAIL,email)
            editor.putString(INVESTOR_NAME,name)
            editor.putString(TOKEN,token)
            editor.apply()
            editor.commit()

            setFirstUse(context, false)
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
         * Method to get the investor email
         */
        fun getInvestorEmail(context : Context) : String{

            val sharedPreferences: SharedPreferences = context.getSharedPreferences(INVESTOR_PREFERENCES_FILE, Context.MODE_PRIVATE)
            return sharedPreferences.getString(INVESTOR_EMAIL,"").toString()
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


        /**
         * Method to set the first usage of the app
         */
        fun setFirstUse(context : Context, firstUse : Boolean) {

            val sharedPreferences: SharedPreferences = context.getSharedPreferences(APP_PREFERENCES_FILE, Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putBoolean(FIRST_USE,firstUse)
            editor.apply()
            editor.commit()
        }


        /**
         * Method to get the state of the first usage of the app
         */
        fun getFirstUse(context : Context) : Boolean{

            val sharedPreferences: SharedPreferences = context.getSharedPreferences(APP_PREFERENCES_FILE, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(FIRST_USE,true)
        }


        /**
         * Method to get the validity of the user session.
         */
        fun getSessionValidity(context : Context) : Boolean{

            when {
                getFirstUse(context) == true -> {
                    return false
                }
                getInvestorEmail(context).equals("") == true -> {
                    return false
                }
                else -> {
                    return true
                }
            }
        }

    }
}