package com.fixer.app.model

import com.fixer.app.model.response.CurrenciesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoints {

    @GET("latest")
    fun getCurrenciesList(@Query("access_key") accessKey: String): Single<CurrenciesResponse>

    companion object {
        const val BASE_URL = "http://data.fixer.io/api/"
    }
}