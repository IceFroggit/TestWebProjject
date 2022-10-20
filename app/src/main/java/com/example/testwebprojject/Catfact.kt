package com.example.testwebprojject

import com.google.gson.annotations.SerializedName

data class  Catfact(
    @SerializedName("fact")
    val message:String,
    @SerializedName("length")
    val msgLen:Int
)