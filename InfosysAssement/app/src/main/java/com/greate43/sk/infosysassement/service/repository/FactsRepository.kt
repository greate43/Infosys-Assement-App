package com.greate43.sk.infosysassement.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.greate43.sk.infosysassement.service.model.Fact
import io.reactivex.rxjava3.schedulers.Schedulers


object FactsRepository {
    fun getFacts(): LiveData<Fact> {
        return LiveDataReactiveStreams.fromPublisher(
            ServiceGenerator.getRequestApi()
                .listFacts()
                .subscribeOn(Schedulers.io())
        )
    }
}