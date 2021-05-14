package com.htk.assginment2.data.request

import com.google.gson.annotations.SerializedName
import java.util.*

data class RequestSignUpData(
    @SerializedName("email")
    val id : String,
    val password : String,
    val nickname : String,
    val sex : String,
    val phone : String,
    val birth : String
)
