package com.greate43.sk.infosysassement.service.repository

import androidx.lifecycle.LiveData
import com.greate43.sk.infosysassement.MyApplication
import com.greate43.sk.infosysassement.service.model.Fact
import com.greate43.sk.infosysassement.service.model.Rows
import com.greate43.sk.infosysassement.service.persistence.FactsDao
import io.reactivex.rxjava3.schedulers.Schedulers


class FactsRepository(private val factsDao: FactsDao) {
    val TAG = FactsRepository::class.java.simpleName
    fun getFacts(): LiveData<Fact> {
        if (MyApplication.getInstance()?.hasNetwork()!!) {
            updateFromOnliestFact()
        }
        return factsDao.getFacts()
    }

    fun getRows(): LiveData<List<Rows>> {
        return factsDao.getRows()
    }

    private fun updateFromOnliestFact() {
        var allow = true
        val oldData = getRows()
        oldData.observeForever {
            try {
                if (it[0].title != null) {
                    allow = false
                }
            } catch (ex: IndexOutOfBoundsException) {
                ex.stackTrace
            } finally {
                if (allow) {
                    ServiceGenerator.getRequestApi()
                        .listFacts().observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io()).subscribe { updateData ->
                            factsDao.insertFacts(updateData)
                            for (row in updateData.rows!!) {
                                factsDao.insertRows(row)
                            }
                        }
                }
            }

            oldData.removeObserver { }
        }

    }

}