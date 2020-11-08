package com.example.minimoneybox.utils

class StringUtil {


    companion object {
        /**
         * Method to convert a double to a string
         */
        @JvmStatic
        fun convertToString(value: Double) : String = value.toBigDecimal().toPlainString()
    }


}