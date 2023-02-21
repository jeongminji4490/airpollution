package com.example.airpollution.api.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("response")
    val response : Body
)

data class Body(
    @SerializedName("body")
    val body : ItemList
)

data class Header(
    @SerializedName("header")
    val body : Result
)

data class ItemList(
    @SerializedName("items")
    val item : List<Item>
)

data class Item(
    @SerializedName("districtName")
    val districtName : String,
    @SerializedName("issueVal")
    val issueVal : String,
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

data class Result(
    @SerializedName("resultMsg")
    val resultMsg : String,
    @SerializedName("resultCode")
    val resultCode : String
)
