package com.greate43.sk.infosysassement.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "rows")
data class Rows(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String?,
    @SerializedName("imageHref")
    @ColumnInfo(name = "imageHref")
    var imageHref: String?
)