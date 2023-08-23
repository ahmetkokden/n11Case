package com.ng.n11case.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)
