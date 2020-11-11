package com.example.minimoneybox.ui.investor

import androidx.lifecycle.MutableLiveData
import com.example.minimoneybox.api.models.*
import com.example.minimoneybox.api.models.requests.Payment
import com.example.minimoneybox.repositories.Repository
import com.example.minimoneybox.utils.Resource
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InvestorViewModel @Inject constructor(private val repository: Repository) : BaseViewModel(){

    var products : MutableLiveData<InvestorProducts> = MutableLiveData();
    var product : MutableLiveData<Product> = MutableLiveData();

    /**
     * Method to get all the products
     */
    fun getProducts(){

        showProgressBar(true)

        repository.getInvestorProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                object : Observer<InvestorProducts>{

                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d);
                    }

                    override fun onNext(result: InvestorProducts) {
                        products.value = result
                        showProgressBar(false)
                    }

                    override fun onError(e: Throwable) {
                        showProgressBar(false)
                    }

                    override fun onComplete() {
                        //TODO("Not yet implemented")
                    }
                }
            )

    }


    /**
     * Method to add a payment
     */
    fun addPayment(investorProductId : Int, amount: Double){

        val payment = Payment(amount, investorProductId)

        showProgressBar(true)

        repository.addPayment(payment)
            .flatMap { t ->  repository.getInvestorProducts()}
            .map { result: InvestorProducts ->   Observable.fromIterable(result.products)}
            .flatMap { t ->  t}
            .filter (Predicate { t -> t.id == investorProductId  })
            .toList()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                object : Observer<List<Product>>{

                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onNext(result: List<Product>) {
                        product.value = result.get(0);
                    }

                    override fun onError(e: Throwable) {
                        formatError(e)
                    }

                    override fun onComplete() {
                        message.value = Resource.success("Payment added")
                        showProgressBar(false)
                    }
                }
            )
    }


}