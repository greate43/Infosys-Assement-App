package com.greate43.sk.infosysassement.service.model

import com.google.gson.annotations.SerializedName

data class Rows (

    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("imageHref") val imageHref : String
)