package com.example.pasteleria1000sabores.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

/**
 * Response de autenticación
 * Retornado por login y register
 */
data class AuthPasteleriaData(
    val user: UserDto,
    @SerializedName("access_token")
    val accessToken: String
)
