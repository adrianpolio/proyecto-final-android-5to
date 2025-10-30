package com.cibertec.exament2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ListaProductsActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var userDao: ProductDao
    private lateinit var rvLista: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_products)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = AppDatabase.getInstance(this)
        userDao = database.productDao()

        rvLista = findViewById(R.id.rvUsuarios)
        rvLista.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val lista = userDao.getList()
            adapter = ProductAdapter(lista)
            rvLista.adapter = adapter
        }

    }
}