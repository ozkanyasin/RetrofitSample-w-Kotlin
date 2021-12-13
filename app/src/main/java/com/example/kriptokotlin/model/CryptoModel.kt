package com.example.kriptokotlin.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(

    // @SerializedName("currency") data class sayesinde modelde gelen veri ismini kullanırsak kotlinde bu annotationa gerek yok
    val currency:String,
    // @SerializedName("price")
    val price:String)