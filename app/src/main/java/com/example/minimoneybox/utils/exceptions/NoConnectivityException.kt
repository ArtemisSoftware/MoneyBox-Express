package com.example.minimoneybox.utils.exceptions

import java.io.IOException

class NoConnectivityException(override val message: String = "No Internet Connection") : IOException(message) {


}