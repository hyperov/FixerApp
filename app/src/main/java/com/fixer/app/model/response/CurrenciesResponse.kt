package com.fixer.app.model.response

data class CurrenciesResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)