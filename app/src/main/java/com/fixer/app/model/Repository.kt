package com.fixer.app.model

import com.fixer.app.model.response.CurrenciesResponse
import io.reactivex.Single

interface Repository {

    fun getCurrenciesList(accessKey: String):Single<CurrenciesResponse>
}