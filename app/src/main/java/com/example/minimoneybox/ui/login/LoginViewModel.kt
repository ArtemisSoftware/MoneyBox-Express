package com.example.minimoneybox.ui.login

import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.repositories.Repository
import com.example.minimoneybox.utils.Resouce
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val repository: Repository) : BaseViewModel(){


    fun login(email: String, password: String){

        repository.authenticate(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                object : SingleObserver<Session?> {


                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onSuccess(result: Session) {
                        message.value = Resouce.success(result.session.bearerToken, "")

                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }

                }
            )

    }

}