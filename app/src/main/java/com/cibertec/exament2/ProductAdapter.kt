package com.cibertec.exament2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val products: List<Product>)
    : RecyclerView.Adapter<ProductAdapter.UsuarioViewHolder>() {


    class UsuarioViewHolder(val view: View)
        : RecyclerView.ViewHolder(view) {
        val nombre_tv = view.findViewById<TextView>(R.id.tvNombre)
        val descripcion_tv = view.findViewById<TextView>(R.id.tvDescripcion)

        val precio_tv = view.findViewById<TextView>(R.id.tvPrecio)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_product,
                parent,
                false)
        return UsuarioViewHolder(view)

    }

    override fun onBindViewHolder(
        holder: UsuarioViewHolder,
        position: Int
    ) {
        val usuarioActual = products[position]
        holder.nombre_tv.text = usuarioActual.nombre
        holder.descripcion_tv.text = usuarioActual.descripcion
        holder.precio_tv.text = usuarioActual.precio.toInt().toString()
    }

    override fun getItemCount(): Int {
        return products.size
    }

}