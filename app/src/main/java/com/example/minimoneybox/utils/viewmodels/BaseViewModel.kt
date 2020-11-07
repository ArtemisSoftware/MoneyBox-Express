package com.example.minimoneybox.utils.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


open abstract class BaseViewModel : ViewModel() {

    protected var disposables: CompositeDisposable = CompositeDisposable()
    var loading: MutableLiveData<Int> = MutableLiveData()



    override fun onCleared() {
        super.onCleared()
        disposables?.clear();
    }
}