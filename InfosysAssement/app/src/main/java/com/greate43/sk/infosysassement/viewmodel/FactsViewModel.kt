package com.greate43.sk.infosysassement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.greate43.sk.infosysassement.service.model.Fact
import com.greate43.sk.infosysassement.service.repository.FactsRepository


class FactsViewModel : ViewModel() {

    fun getFacts(): LiveData<Fact> {
        return FactsRepository.getFacts()
    }
}