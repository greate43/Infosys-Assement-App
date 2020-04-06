package com.greate43.sk.infosysassement.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.greate43.sk.infosysassement.service.model.Facts
import io.reactivex.rxjava3.schedulers.Schedulers


object FactsRepository {
    fun getFacts(): LiveData<Facts> {
        return LiveDataReactiveStreams.fromPublisher(
            ServiceGenerator.getRequestApi()
                .listFacts()
                .subscribeOn(Schedulers.io())
        )
    }
}