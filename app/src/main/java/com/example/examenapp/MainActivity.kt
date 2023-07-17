package com.example.examenapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.examenapp.crud.CrearMarcaActv
import com.example.examenapp.crud.ModeloActv
import com.example.examenapp.crud.ModificarMarcaActv
import com.example.examenapp.datos.DatosMarcas
import com.example.examenapp.modelos.Marca

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity: AppCompatActivity() {
    val marcas = DatosMarcas.marcas
    var selectedItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_actv)

        loadMarcas()

        val btnCreate = findViewById<Button>(
            R.id.btn_create_marca
        )

        btnCreate.setOnClickListener {
            goToActivity(CrearMarcaActv::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
        loadMarcas()
    }

    private fun loadMarcas() {
        val listView = findViewById<ListView>(
            R.id.lv_marcas
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, marcas)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

    private fun goToActivity(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        if (params != null) {
            intent.putExtras(params)
        }
        startActivity(intent)
    }

    private fun showConfirmDeleteDialog(marca: Marca) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Se eliminara la marca ${marca.getNombre()}?")
        builder.setMessage("Este se eliminará definitivamente")
        builder.setPositiveButton("Aceptar") { _, _ ->
            marcas.removeAt(selectedItemId)
            loadMarcas()
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_marca, menu)

        //position
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        selectedItemId = position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.mis_modelos -> {
                "Ver: ${selectedItemId}"
                val params = Bundle()
                val marca = marcas[selectedItemId]
                params.putString("id", marca.getId())
                params.putString("nombre", marca.getNombre())
                params.putString("pais", marca.getPais())
                params.putString("ciudad", marca.getCiudad())
                params.putString("concesionarios", marca.getConcesionarios())

                goToActivity(ModeloActv::class.java, params)
                return true
            }
            R.id.mi_update -> {
                "Modificar: ${selectedItemId}"
                val params = Bundle()
                val marca = marcas[selectedItemId]
                params.putString("id", marca.getId())
                params.putString("nombre", marca.getNombre())
                params.putString("pais", marca.getPais())
                params.putString("ciudad", marca.getCiudad())
                params.putString("concesionarios", marca.getConcesionarios())

                goToActivity(ModificarMarcaActv::class.java, params)
                return true
            }
            R.id.mi_delete -> {
                val marca = marcas[selectedItemId]
                showConfirmDeleteDialog(marca)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}