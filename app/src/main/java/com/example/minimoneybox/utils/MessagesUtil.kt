package com.example.minimoneybox.utils

import android.content.Context
import android.content.DialogInterface
import com.example.minimoneybox.R
import com.yarolegovich.lovelydialog.LovelyInfoDialog

class MessagesUtil {

    companion object{

        fun success(context : Context, message : String) {

            var dialog = LovelyInfoDialog(context)
            dialog
                .setTopColorRes(R.color.designMainColor)
                .setIcon(R.drawable.ic_cash)

                .setTitle("Success")
                .setMessage(message)
                .show();
        }


        fun error(context : Context, message : String) {

            var dialog = LovelyInfoDialog(context)
            dialog
                .setTopColorRes(R.color.light_grey)
                .setIcon(android.R.drawable.stat_notify_error)

                .setTitle("Error")
                .setMessage(message)
                .show();
        }

        fun error(context : Context, message : String,  onKeyListener : DialogInterface.OnKeyListener) {

            var dialog = LovelyInfoDialog(context)
                .setTopColorRes(R.color.light_grey)
                .setIcon(android.R.drawable.stat_notify_error)

                .setTitle("Error")
                .setMessage(message)
                .create();


            dialog.setOnKeyListener(onKeyListener);
            dialog.show();
        }


    }

}