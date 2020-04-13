package com.greate43.sk.infosysassement.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fact")
class Fact {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = ""

    @SerializedName("rows")
    @Ignore
    var rows: List<Rows>? = null
}