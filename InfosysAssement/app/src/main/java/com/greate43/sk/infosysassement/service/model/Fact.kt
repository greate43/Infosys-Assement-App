package com.greate43.sk.infosysassement.service.model

import com.google.gson.annotations.SerializedName

data class Fact(

    @SerializedName("title") val title: String,
    @SerializedName("rows") val rows: List<Rows>
)