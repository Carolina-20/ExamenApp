package com.example.examenapp.crud

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.examenapp.R
import com.example.examenapp.datos.DatosMarcas
import com.example.examenapp.modelos.ModeloAutos

@RequiresApi(Build.VERSION_CODES.O)
class ModeloActv : AppCompatActivity() {
    val marcas = DatosMarcas.marcas
    var selectedMarcaId = ""
    var selectedItemId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modeloautos_actv)

        selectedMarcaId = intent.getStringExtra("id") ?: ""
        println("marcaId: $selectedMarcaId")


        loadModeloAutos(selectedMarcaId)



        // Buttons and Listeners
        val goBackButton = findViewById<ImageButton>(
            R.id.btn_go_back_to_marca
        )

        goBackButton.setOnClickListener {
            finish()
        }

        val createSeriesButton = findViewById<Button>(
            R.id.btn_create_modeloAutos
        )

        createSeriesButton.setOnClickListener {
            goToActivity(CrearModeloActv::class.java, Bundle().apply {
                val marca = marcas.find {
                    it.getId() == selectedMarcaId
                }
                if (marca != null) {
                    putString("marcaId", marca.getId())
                    putString("marcaNombre", marca.getNombre())
                }
            })
        }
    }
    override fun onStart() {
        super.onStart()

        loadModeloAutos(selectedMarcaId)
    }
    private fun goToActivity(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        if (params != null) {
            intent.putExtras(params)
        }
        startActivity(intent)
    }


    private fun loadModeloAutos(marcaId: String) {
        if (marcaId != "") {
            val marca = marcas.find {
                it.getId() == marcaId
            }

            if (marca != null) {
                val series = marca.getModeloAutos()

                val mrNombre = findViewById<TextView>(
                    R.id.mr_marca
                )

                mrNombre.text = marca.getNombre()

                val modeloAutosList = findViewById<ListView>(
                    R.id.lv_modeloAutos
                )

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    series
                )

                modeloAutosList.adapter = adapter

                adapter.notifyDataSetChanged()

                registerForContextMenu(modeloAutosList)
            }
        }
    }

    private fun showConfirmDialog(modeloAutos: ModeloAutos) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Se elimnara el modelo: ${modeloAutos.getNombreMod()}")
        builder.setMessage("Este se eliminara definitivamente")
        builder.setPositiveButton("Acepto") { dialog, which ->
            val marca = marcas.find {
                it.getId() == selectedMarcaId
            } ?: return@setPositiveButton

            marca.removeModelo(modeloAutos)
            loadModeloAutos(selectedMarcaId)
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(
            R.menu.menu_modeloautos,
            menu
        )

        // position
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        selectedItemId = position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.mi_edit_modeloAutos -> {
                val marca = marcas.find {
                    it.getId() == selectedMarcaId
                } ?: return false

                val modeloAutos = marca.getModeloAutos()[selectedItemId]

                goToActivity(
                    ModificarModeloActv::class.java,
                    Bundle().apply {
                        putString("marcaId", selectedMarcaId)
                        putString("marcaNombre", marca.getNombre())
                        putString("modeloAutoId", modeloAutos.getId())
                        putString("modeloAutoNombreMod", modeloAutos.getNombreMod())
                        putDouble("modeloPrecio", modeloAutos.getPrecio())
                        putDouble("modeloFuerzaMotor", modeloAutos.getFuerzaMotor())
                        putBoolean("modeloEsSedan", modeloAutos.getEsSedan())
                        putInt("modeloUnidadesDisponibles", modeloAutos.getUnidadesDisponibles())
                        putString("modeloFechaLanzamiento", modeloAutos.getFechaLanzamiento().toString())
                    }
                )
                true
            }
            R.id.mi_delete_modeloAutos -> {
                val marca = marcas.find {
                    it.getId() == selectedMarcaId
                } ?: return false
                showConfirmDialog(marca.getModeloAutos()[selectedItemId])
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }


}