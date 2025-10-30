package com.cibertec.exament2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var ilNombre: TextInputLayout
    lateinit var ilDescripcion: TextInputLayout
    lateinit var ilIndExtra: TextInputLayout
    lateinit var ilPrecio: TextInputLayout

    lateinit var etNombre: EditText
    lateinit var etDescripcion: EditText
    lateinit var etIndExtra: EditText
    lateinit var etPrecio: EditText


    lateinit var btnRegistrar: Button
    lateinit var btnLista: Button

    private lateinit var database: AppDatabase
    private lateinit var productDao: ProductDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // inicio de la BD
        database = AppDatabase.getInstance(this)
        productDao = database.productDao()

        // Definir los controles
        ilNombre = findViewById(R.id.ilNombre)
        ilDescripcion = findViewById<TextInputLayout>(R.id.ilDecripcion)
        ilIndExtra = findViewById<TextInputLayout>(R.id.ilIndExtra)
        ilPrecio = findViewById<TextInputLayout>(R.id.ilPrecio)

        etNombre = findViewById<EditText>(R.id.etNombre)
        etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        etIndExtra = findViewById<EditText>(R.id.etIndExtra)
        etPrecio = findViewById<EditText>(R.id.etPrecio)

        btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnLista = findViewById<Button>(R.id.btnVerProductos)

        btnRegistrar.setOnClickListener {
            validarFormulario()
        }

        btnLista.setOnClickListener {
            val intent = Intent(this, ListaProductsActivity::class.java)
            startActivity(intent)
        }

    }

//    fun validarFormulario(){
//
//                        Toast.makeText(
//                    this@MainActivity,
//                    "Producto guardado",
//                    Toast.LENGTH_SHORT
//                ).show()
//
//    }


    fun validarFormulario() {
        val nombre = etNombre.text.toString().trim()
        val descripcion = etDescripcion.text.toString().trim()
        val indExtra = etIndExtra.text.toString().trim()
        val precio = etPrecio.text.toString().trim()

        ilNombre.error = null
        ilDescripcion.error = null
        ilIndExtra.error = null
        ilPrecio.error = null

        var esValido = true

        if (nombre.isEmpty()) {
            ilNombre.error = "Campo obligatorio"
            esValido = false
        }
        if (descripcion.isEmpty()) {
            ilDescripcion.error = "Campo obligatorio"
            esValido = false
        }
        if (indExtra.isEmpty()) {
            ilIndExtra.error = "Campo obligatorio"
            esValido = false
        }
        if (precio.isEmpty()) {
            ilPrecio.error = "Campo obligatorio"
            esValido = false
        }

        if (esValido) {
            val nuevoProduct = Product(
                nombre = nombre,
                descripcion = descripcion,
                indicaciones = indExtra,
                precio = precio.toInt()
            )
            lifecycleScope.launch {
                productDao.insert(nuevoProduct)
                Toast.makeText(
                    this@MainActivity,
                    "Producto guardado",
                    Toast.LENGTH_SHORT
                ).show()

                etNombre.text.clear()
                etDescripcion.text.clear()
                etIndExtra.text.clear()
                etPrecio.text.clear()
            }
        }
    }


}