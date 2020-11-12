package com.example.minimoneybox.utils.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minimoneybox.utils.ErrorsUtil
import com.example.minimoneybox.utils.Resource
import io.reactivex.disposables.CompositeDisposable
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


    /**
     * Method to present the progression bar
     */
    protected fun showProgressBar(visible: Boolean) {
        loading.value = if (visible) View.VISIBLE else View.INVISIBLE
    }


    /**
     * Method to format errors so they can be presented
     */
    protected fun formatError(e: Throwable){

        if(e is HttpException){
            message.value = ErrorsUtil.httpErrors(e)
        }
        else{
            message.value = Resource.error<String>(e.message!!)
        }
    }


    override fun onCleared() {
        super.onCleared()
        disposables?.clear();
    }
}