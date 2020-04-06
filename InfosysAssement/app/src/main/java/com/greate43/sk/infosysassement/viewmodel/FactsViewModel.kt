package com.greate43.sk.infosysassement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.greate43.sk.infosysassement.service.model.Facts
import com.greate43.sk.infosysassement.service.repository.FactsRepository
import okhttp3.ResponseBody


class FactsViewModel : ViewModel() {

    fun getFacts(): LiveData<Facts> {
        return FactsRepository.getFacts()
    }
}