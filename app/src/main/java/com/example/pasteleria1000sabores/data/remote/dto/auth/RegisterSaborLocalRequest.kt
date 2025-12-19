package com.example.pasteleria1000sabores.data.remote.dto.auth

data class RegisterPasteleriaRequest(
    val email: String,
    val password: String,
    val role: String,  // CLIENTE o PRODUCTOR
    val nombre: String,
    val telefono: String? = null,
    val direccion: String? = null,
    val nombreNegocio: String? = null,  // Obligatorio para PRODUCTOR
    val descripcion: String? = null      // Opcional para PRODUCTOR
)
