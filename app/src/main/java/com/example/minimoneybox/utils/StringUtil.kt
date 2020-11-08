package com.example.minimoneybox.utils

class StringUtil {


    /**
     * Method to convert a double to a string
     */
    fun convertToString(value: Double): String {
        return value.toBigDecimal().toPlainString()
    }

}