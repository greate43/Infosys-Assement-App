package com.greate43.sk.infosysassement.service.repository

import com.greate43.sk.infosysassement.service.model.Fact
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET


interface DropboxFactsApi {

    @GET("facts.json")
    fun listFacts(): Flowable<Fact>
}