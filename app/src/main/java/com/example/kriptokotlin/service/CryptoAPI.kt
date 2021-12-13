package com.example.kriptokotlin.service

import com.example.kriptokotlin.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //GET, POST, UPDATE,DELETE

    // api key : 6650dba29ebf39a9794d50aebf1ed80ad4af8edc
    // https://api.nomics.com/v1/
    // prices?key=6650dba29ebf39a9794d50aebf1ed80ad4af8edc

    @GET("prices?key=6650dba29ebf39a9794d50aebf1ed80ad4af8edc")
    fun getData(): Call<List<CryptoModel>>


}