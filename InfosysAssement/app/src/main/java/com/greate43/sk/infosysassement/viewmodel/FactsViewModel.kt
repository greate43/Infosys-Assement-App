package com.greate43.sk.infosysassement.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.greate43.sk.infosysassement.service.model.Fact
import com.greate43.sk.infosysassement.service.model.Rows
import com.greate43.sk.infosysassement.service.persistence.FactsDatabase
import com.greate43.sk.infosysassement.service.repository.FactsRepository


class FactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FactsRepository

    init {
        val factsDao = FactsDatabase.getDatabase(application).factsDao()
        repository = FactsRepository(factsDao)
    }


    fun getFacts(): LiveData<Fact> {
        return repository.getFacts()
    }

    fun getRows(): LiveData<List<Rows>> {
        return repository.getRows()
    }
}