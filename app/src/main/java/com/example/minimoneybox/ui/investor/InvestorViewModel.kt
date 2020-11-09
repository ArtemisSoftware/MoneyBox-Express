package com.example.minimoneybox.ui.investor

import com.example.minimoneybox.api.models.InvestorProducts
import com.example.minimoneybox.repositories.Repository
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InvestorViewModel @Inject constructor(private val repository: Repository) : BaseViewModel(){

    fun getProducts(){

        repository.getInvestorProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                object : Observer<InvestorProducts>{

                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d);
                    }

                    override fun onNext(t: InvestorProducts) {
                        TODO("Not yet implemented")
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onComplete() {
                        TODO("Not yet implemented")
                    }
                }
            )

    }

}