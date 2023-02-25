package com.example.airpollution.api.model

import com.google.gson.annotations.SerializedName

data class AirPollution(
    @SerializedName("response")
    val response: Response
){
    data class Response(
        @SerializedName("body")
        val body: Body
    ) {
        data class Body(
            @SerializedName("items")
            val items: List<Item>
        ) {
            data class Item(
                @SerializedName("districtName")
                val districtName : String,
                @SerializedName("issueTime")
                val issueTime : String,
                @SerializedName("issueDate")
                val issueDate : String,
                @SerializedName("moveName")
                val moveName : String,
                @SerializedName("issueGbn")
                val issueGbn : String,
                @SerializedName("itemCode")
                val itemCode : String
            )
        }
    }
}