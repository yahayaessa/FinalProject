package com.yahayaessa.finalandroidproject.model

import com.google.gson.annotations.SerializedName

data class OrderRequestWork(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("id"   ) var id   : Int?    = null
)