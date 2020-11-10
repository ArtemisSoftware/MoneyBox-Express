package com.example.minimoneybox.utils.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minimoneybox.utils.Resouce
import io.reactivex.disposables.CompositeDisposable


open abstract class BaseViewModel : ViewModel() {

    protected var disposables: CompositeDisposable = CompositeDisposable()
    var loading: MutableLiveData<Int> = MutableLiveData()
    var message: MutableLiveData<Resouce<String>> = MutableLiveData()


    open fun observeMessage(): MutableLiveData<Resouce<String>> {
        return message
    }


    protected open fun showProgressBar(visible: Boolean) {
        loading.value = if (visible) View.VISIBLE else View.INVISIBLE
    }


    override fun onCleared() {
        super.onCleared()
        disposables?.clear();
    }
}