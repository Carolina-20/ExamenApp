package com.example.examenapp.crud

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.examenapp.R
import com.example.examenapp.datos.DatosMarcas


@RequiresApi(Build.VERSION_CODES.O)
class ModificarMarcaActv  : AppCompatActivity() {
    private val marcas = DatosMarcas.marcas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modificar_marca_actv)

        loadDataInEditText()

        val saveUpdateDataButton = findViewById<Button>(R.id.btn_update_marca)

        saveUpdateDataButton.setOnClickListener {
            saveUpdateData()
        }
    }

    private fun loadDataInEditText() {
        val inputNombre = findViewById<EditText>(R.id.txt_marca_update_name)
        val inputPais = findViewById<EditText>(R.id.txt_marca_update_pais)
        val inputCiudad= findViewById<EditText>(R.id.txt_marca_update_ciudad)
        val inputConcesionarios = findViewById<EditText>(R.id.txt_marca_update_concesionarios)

        val nombre = intent.getStringExtra("nombre")
        val pais = intent.getStringExtra("pais")
        val ciudad = intent.getStringExtra("ciudad")
        val concesionarios = intent.getStringExtra("concesionarios")

        inputNombre.setText(nombre)
        inputPais.setText(pais.toString())
        inputCiudad.setText(ciudad)
        inputConcesionarios.setText(concesionarios)
    }

    private fun saveUpdateData() {
        val inputNombre = findViewById<EditText>(R.id.txt_marca_update_name)
        val inputPais = findViewById<EditText>(R.id.txt_marca_update_pais)
        val inputCiudad= findViewById<EditText>(R.id.txt_marca_update_ciudad)
        val inputConcesionarios = findViewById<EditText>(R.id.txt_marca_update_concesionarios)

        val nombre = inputNombre.text.toString()
        val pais = inputPais.text.toString()
        val ciudad = inputCiudad.text.toString()
        val concesionarios = inputConcesionarios.text.toString()

        val marcaId = intent.getStringExtra("id")
        println("ANTES")
        val marca = marcas.find { it.getId() == marcaId } ?: return
        println("DESPUES")

        marca.setNombre(nombre)
        marca.setPais(pais)
        marca.setCiudad(ciudad)
        marca.setConcesionarios(concesionarios)

        finish()
    }
}