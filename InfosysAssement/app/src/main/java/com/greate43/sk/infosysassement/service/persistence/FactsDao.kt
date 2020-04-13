package com.greate43.sk.infosysassement.service.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.greate43.sk.infosysassement.service.model.Fact
import com.greate43.sk.infosysassement.service.model.Rows

@Dao
interface FactsDao {
    @Insert(onConflict = REPLACE)
    fun insertFacts(facts: Fact)

    @Insert(onConflict = REPLACE)
    fun insertRows(rows: Rows)

    @Query("SELECT * FROM fact ")
    fun getFacts(): LiveData<Fact>

    @Query("SELECT * FROM rows ")
    fun getRows(): LiveData<List<Rows>>
}