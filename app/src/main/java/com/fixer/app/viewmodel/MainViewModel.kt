package com.fixer.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fixer.app.model.Repository
import com.fixer.app.model.response.CurrenciesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(val repo: Repository) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    val currenciesLiveData = MutableLiveData<List<Pair<String, Double>>>()
    var errorLiveData = MutableLiveData<String>()
    var progressLiveData = MutableLiveData<Boolean>()

    val selectedCurrency = MutableLiveData<Pair<String, Double>>()

    fun getCurrencies(accessKey: String) {
        compositeDisposable.add(repo.getCurrenciesList(accessKey)
            .map { res: CurrenciesResponse -> res.rates.toList() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressLiveData.value = false }
            .subscribe({ list -> currenciesLiveData.value = list },
                { e -> errorLiveData.value = e.message })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}