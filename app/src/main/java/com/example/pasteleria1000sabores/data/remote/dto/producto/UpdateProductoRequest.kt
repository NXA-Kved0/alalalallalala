package com.example.pasteleria1000sabores.data.remote.dto.producto

/**
 * Request para actualizar un Producto
 */
data class UpdateProductoRequest(
    val nombre: String? = null,
    val descripcion: String? = null,
    val precio: Double? = null,
    val unidad: String? = null,
    val stock: Int? = null,
    val productor: String? = null,
    val imagen: String? = null,
    val imagenThumbnail: String? = null
)
