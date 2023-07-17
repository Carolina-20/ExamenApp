package com.example.examenapp.crud

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.examenapp.R
import com.example.examenapp.datos.DatosMarcas
import com.example.examenapp.modelos.Marca

@RequiresApi(Build.VERSION_CODES.O)
class CrearMarcaActv : AppCompatActivity(){
    val marcas = DatosMarcas.marcas

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_marca_actv)

        val goBackButton = findViewById<ImageButton>(R.id.btn_go_back)

        goBackButton.setOnClickListener{
            finish()
        }

        val saveNuevaMarcaButton = findViewById<Button>(
            R.id.btn_new_marca
        )

        saveNuevaMarcaButton.setOnClickListener {
            createMarca()
        }
    }

    private fun createMarca() {
        val inputNombre = findViewById<EditText>(R.id.txt_marca_nombre)
        val inputPais = findViewById<EditText>(R.id.txt_marca_pais)
        val inputCiudad = findViewById<EditText>(R.id.txt_marca_ciudad)
        val inputConcesionarios = findViewById<EditText>(R.id.txt_marca_concesionarios)

        val nombre = inputNombre.text.toString()
        val pais = inputPais.text.toString()
        val ciudad = inputCiudad.text.toString()
        val concesionarios = inputConcesionarios.text.toString()

        val newMarca = Marca(
            DatosMarcas.obetnerId(),
            nombre,
            pais,
            ciudad,
            concesionarios,
            mutableListOf()
        )

        marcas.add(newMarca)
        finish()
    }

}