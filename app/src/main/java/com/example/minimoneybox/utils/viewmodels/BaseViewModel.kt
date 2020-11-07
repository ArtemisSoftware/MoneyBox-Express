package com.example.minimoneybox.utils.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel : ViewModel() {

    protected lateinit var disposables: CompositeDisposable
    lateinit var loading: MutableLiveData<Int>



    open fun BaseViewModel() {
        disposables = CompositeDisposable()
        loading = MutableLiveData()

    }

    override fun onCleared() {
        super.onCleared()
        disposables?.clear();
    }
}