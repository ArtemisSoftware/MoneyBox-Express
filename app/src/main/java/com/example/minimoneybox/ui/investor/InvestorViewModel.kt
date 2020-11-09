package com.example.minimoneybox.ui.investor

import androidx.lifecycle.MutableLiveData
import com.example.minimoneybox.api.models.InvestorProducts
import com.example.minimoneybox.api.models.PaymentReceipt
import com.example.minimoneybox.api.models.Product
import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.api.models.requests.Payment
import com.example.minimoneybox.repositories.Repository
import com.example.minimoneybox.utils.Resouce
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InvestorViewModel @Inject constructor(private val repository: Repository) : BaseViewModel(){


    var products : MutableLiveData<List<Product>> = MutableLiveData();

    fun getProducts(){

        repository.getInvestorProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                object : Observer<InvestorProducts>{

                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d);
                    }

                    override fun onNext(result: InvestorProducts) {
                        products.value = result.products
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onComplete() {
                        //TODO("Not yet implemented")
                    }
                }
            )

    }

    fun addPayment(investorProductId : Int, amount: Double){

        val payment = Payment(amount, investorProductId)

        repository.addPayment(payment)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                object : SingleObserver<PaymentReceipt> {


                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onSuccess(result: PaymentReceipt) {
                        //message.value = Resouce.success(result.session.bearerToken, "")

                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }

                }
            )
    }

}