package com.example.minimoneybox.utils.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minimoneybox.api.models.ErrorMessage
import com.example.minimoneybox.utils.Resource
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONObject
import retrofit2.HttpException


open abstract class BaseViewModel : ViewModel() {

    protected var disposables: CompositeDisposable = CompositeDisposable()
    var loading: MutableLiveData<Int> = MutableLiveData()
    var message: MutableLiveData<Resource<String>> = MutableLiveData()

    init{
        loading.value = View.INVISIBLE
    }

    fun observeMessage(): MutableLiveData<Resource<String>> {
        return message
    }


    protected fun showProgressBar(visible: Boolean) {
        loading.value = if (visible) View.VISIBLE else View.INVISIBLE
    }


    protected fun formatError(e: Throwable){

        if((e as HttpException).code() == 401){

            val gson = Gson()
            val error = gson.fromJson((e as HttpException).response()!!.errorBody()!!.charStream().readText(), ErrorMessage::class.java)
            message.value = Resource.error<String>((e as HttpException).code().toString(), error.message)
        }
        else{
            message.value = Resource.error<String>(e.message())
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables?.clear();
    }
}