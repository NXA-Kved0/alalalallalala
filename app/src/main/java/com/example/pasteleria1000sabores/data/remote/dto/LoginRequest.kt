package com.example.pasteleria1000sabores.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 *
 * @property email Dirección de correo electrónico del usuario registrado.
 *                 Debe ser un email válido y existente en el sistema.
 * @property password Contraseña del usuario. Se envía en texto plano por HTTPS,
 *                    el servidor se encarga de verificarla contra el hash almacenado.
 *
 * @see com.example.pasteleria1000sabores.model.dto.AuthResponse
 * @see com.example.pasteleria1000sabores.network.AuthApiService.login
 * @see com.example.pasteleria1000sabores.viewmodel.LoginViewModel
 */
data class LoginRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)
