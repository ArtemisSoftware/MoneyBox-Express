package com.example.minimoneybox.ui.login

import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.repositories.Repository
import com.example.minimoneybox.utils.Resource
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val repository: Repository) : BaseViewModel(){


    /**
     * Method to login a user
     */
    fun login(email: String, password: String){

        showProgressBar(true)

        repository.authenticate(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                object : SingleObserver<Session?> {

                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onSuccess(result: Session) {
                        message.value = Resource.success(result.session.bearerToken, "")
                        showProgressBar(false)
                    }

                    override fun onError(e: Throwable) {
                        e.message?.let {
                            message.value = Resource.error(it)
                        }
                        showProgressBar(false)
                    }

                }
            )

    }

}