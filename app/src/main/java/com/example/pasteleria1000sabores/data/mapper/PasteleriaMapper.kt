package com.example.pasteleria1000sabores.data.mapper


import com.example.pasteleria1000sabores.data.remote.dto.producto.ProductoDto
import com.example.pasteleria1000sabores.data.remote.dto.cliente.ClienteDto
import com.example.pasteleria1000sabores.data.remote.dto.pedido.PedidoDto
import com.example.pasteleria1000sabores.data.remote.dto.pedido.PedidoItemDto
import com.example.pasteleria1000sabores.data.remote.dto.entrega.EntregaDto
import com.example.pasteleria1000sabores.model.*
import com.example.saborlocalspa.data.remote.dto.productor.ProductorDto

private const val IMAGE_BASE_URL = "https://pasteleria-api.onrender.com"

private fun buildImageUrl(path: String?): String? {
    if (path.isNullOrBlank()) return null
    return if (path.startsWith("http")) path else IMAGE_BASE_URL + path
}

/**
 * Convierte ProductorDto a Productor
 */
fun ProductorDto.toModel(): Productor {
    return Productor(
        id = id,
        nombre = nombre,
        ubicacion = ubicacion ?: "",
        telefono = telefono ?: "",
        email = email ?: "",
        imagen = imagen,
        imagenThumbnail = imagenThumbnail
    )
}

/**
 * Convierte ProductoDto a Producto
 */
fun ProductoDto.toModel(): Producto {
    val productorObj = when (productor) {
        is String -> {
            Productor(
                id = productor,
                nombre = null,
                ubicacion = null,
                telefono = null,
                email = null
            )
        }
        is Map<*, *> -> {
            val map = productor as Map<String, Any>
            Productor(
                id = map["_id"] as? String ?: "",
                nombre = map["nombre"] as? String,
                ubicacion = map["ubicacion"] as? String,
                telefono = map["telefono"] as? String,
                email = map["email"] as? String,
                imagen = map["imagen"] as? String,
                imagenThumbnail = map["imagenThumbnail"] as? String
            )
        }
        else -> {
            Productor(
                id = "",
                nombre = null,
                ubicacion = null,
                telefono = null,
                email = null
            )
        }
    }

    return Producto(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        precio = precio,
        unidad = unidad,
        stock = stock,
        productor = productorObj,
        imagen = buildImageUrl(imagen),
        imagenThumbnail = buildImageUrl(imagenThumbnail)
    )
}

/**
 * Convierte ClienteDto a Cliente
 */
fun ClienteDto.toModel(): Cliente {
    return Cliente(
        id = id,
        nombre = nombre,
        email = email,
        telefono = telefono,
        direccion = direccion
    )
}

/**
 * Convierte PedidoItemDto a PedidoItem
 */
fun PedidoItemDto.toModel(): PedidoItem {
    val productoMinimo = Producto(
        id = producto,
        nombre = "Producto #$producto",
        descripcion = "",
        precio = precio,
        unidad = "",
        stock = 0,
        productor = Productor(
            id = "",
            nombre = null,
            ubicacion = null,
            telefono = null,
            email = null,
            imagen = null,
            imagenThumbnail = null
        )
    )

    return PedidoItem(
        producto = productoMinimo,
        cantidad = cantidad,
        precio = precio
    )
}

/**
 * Convierte PedidoDto a Pedido
 */
fun PedidoDto.toModel(): Pedido? {
    val fecha = createdAt ?: return null

    val clienteObj = Cliente(
        id = cliente.id,
        nombre = cliente.nombre ?: "Cliente #${cliente.id}",
        email = cliente.email ?: "",
        telefono = cliente.telefono ?: "",
        direccion = cliente.direccion ?: direccionEntrega ?: ""
    )

    val itemsConverted = items.map { it.toModel() }

    return Pedido(
        id = id,
        cliente = clienteObj,
        items = itemsConverted,
        total = total,
        estado = EstadoPedido.fromString(estado),
        fecha = fecha
    )
}

/**
 * Extension functions para listas
 */
fun List<ProductorDto>.toProductorList(): List<Productor> {
    return map { it.toModel() }
}

fun List<ProductoDto>.toProductoList(): List<Producto> {
    return map { it.toModel() }
}

fun List<ClienteDto>.toClienteList(): List<Cliente> {
    return map { it.toModel() }
}

fun List<PedidoDto>.toPedidoList(): List<Pedido> {
    return mapNotNull { it.toModel() }
}