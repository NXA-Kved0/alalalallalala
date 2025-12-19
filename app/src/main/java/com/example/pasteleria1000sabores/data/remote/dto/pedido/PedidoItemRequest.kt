package com.example.pasteleria1000sabores.data.remote.dto.pedido

data class PedidoItemRequest(
    val producto: String,  // ID del producto
    val cantidad: Int
)
