package com.greate43.sk.infosysassement.service.repository

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceGenerator {
    private val TAG = ServiceGenerator::class.java.simpleName
    const val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retrofitBuilder.build()

    private val requestApi: DropboxFactsApi = retrofit.create(DropboxFactsApi::class.java)

    fun getRequestApi(): DropboxFactsApi {
        return requestApi
    }
}