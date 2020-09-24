package com.sportsinteractive.cricket

import javax.inject.Inject

class Response @Inject constructor(private val apiService: ApiService) : Result() {
   suspend fun getResponse() = getResult { apiService.getCricket() }
}
