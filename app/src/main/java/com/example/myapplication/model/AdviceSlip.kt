package com.example.myapplication.model

import com.squareup.moshi.Json

data class AdviceSlip(

    @Json(name = "slip")
    val slip: Slip

)

data class Slip(

    @Json(name = "id")
    val id: Int,

    @Json(name = "advice")
    val advice: String

)