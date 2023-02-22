package com.example.airpollution.api.request

import com.example.airpollution.api.model.AirPollution
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo")
    suspend fun getData(
        @Query("year") year: String,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("returnType") returnType: String,
        @Query("serviceKey") serviceKey: String,
        @Query("itemCode") itemCode: String
    ): Response<AirPollution>
}