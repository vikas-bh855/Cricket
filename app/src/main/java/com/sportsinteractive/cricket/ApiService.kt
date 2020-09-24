package com.sportsinteractive.cricket

import com.sportsinteractive.cricket.model.Cricket
import com.sportsinteractive.cricket.model.STATUS
import retrofit2.Response
import retrofit2.http.GET
import java.lang.Exception

interface ApiService {
    @GET("sapk01222019186652.json")
    suspend fun getCricket(): Response<Cricket>
}

data class ResultData<out T>(
    val status: STATUS,
    val cricket: T? = null,
    val message: String? = null
)

abstract class Result {
    suspend fun <T> getResult(call: suspend () -> Response<T>): ResultData<T> {
        try {
            val response = call()
            return if (response.isSuccessful)
                ResultData(STATUS.SUCCESS, response.body()!!, "")
            else
                ResultData(STATUS.ERROR, null, response.message())
        } catch (e: Exception) {
            e.printStackTrace()
            return ResultData(STATUS.ERROR, null, "No internet connection!..Please try again")
        }
    }
}