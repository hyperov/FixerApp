package com.fixer.app.model

import com.fixer.app.model.response.CurrenciesResponse
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val api: ApiEndPoints) : Repository {

    override fun getCurrenciesList(accessKey: String): Single<CurrenciesResponse> {
        return api.getCurrenciesList(accessKey)
    }

}