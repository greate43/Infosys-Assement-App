package com.greate43.sk.infosysassement.service.repository

import com.greate43.sk.infosysassement.service.model.Facts
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


interface DropboxFactsApi {

    @GET("facts.json")
    fun listFacts(): Flowable<Facts>
}