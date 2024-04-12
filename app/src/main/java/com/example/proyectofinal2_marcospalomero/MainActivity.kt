package com.example.proyectofinal2_marcospalomero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal2_marcospalomero.adapters.ColoresAdapter
import com.example.proyectofinal2_marcospalomero.basedatos.Favorito
import com.example.proyectofinal2_marcospalomero.databinding.ActivityMainBinding
import com.example.proyectofinal2_marcospalomero.conexion.Api
import com.example.proyectofinal2_marcospalomero.conexion.Cliente
import com.example.proyectofinal2_marcospalomero.modelo.Palabras
import com.example.proyectofinal2_marcospalomero.modelo.Traduccion
import com.example.proyectofinal2_marcospalomero.viewmodel.FavoritoViewModel
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private var palabras: Palabras? = null
    private var retrofit: Retrofit? = null
    private lateinit var favoritoViewModel: FavoritoViewModel


    companion object {
        var numeros: ArrayList<Traduccion>? = null
        var dias: ArrayList<Traduccion>? = null
        var colores: ArrayList<Traduccion>? = null
        var favoritos: ArrayList<Favorito>? = null
    }

    private var posicionBorrar: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.navicon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout = binding.drawerLayout

        binding.navview.setNavigationItemSelectedListener {
            var fragmentTransaction = false
            lateinit var fragment: Fragment

            when (it.itemId) {
                R.id.menu_numeros, R.id.menu_numeros -> {
                    fragment = NumerosFragment(this)
                    fragmentTransaction = true
                }

                R.id.menu_dias, R.id.menu_dias -> {
                    fragment = DiasFragment(this)
                    fragmentTransaction = true
                }

                R.id.menu_colores -> {
                    fragment = ColoresFragment(this)
                    fragmentTransaction = true
                }

                R.id.menu_favoritos -> {
                    fragment = FavoritosFragment()
                    fragmentTransaction = true
                }
            }

            if (fragmentTransaction) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, fragment)
                    .addToBackStack(null)
                    .commit()

                it.isChecked = true

                title = it.title
            }
            drawerLayout.closeDrawer(GravityCompat.START)

            true
        }

        retrofit = Cliente.obtenerCliente()

        obtenerDatos()

        if (intent.hasExtra("TraduccionInsertar") && intent.hasExtra("Item")) {
            val bundle = intent.getBundleExtra("TraduccionInsertar")
            val traduccion = bundle?.getParcelable<Traduccion>("TraduccionInsertar")
            val item = intent.getStringExtra("Item")

            when (item) {
                "numeros" -> numeros?.add(traduccion!!)
                "dias" -> dias?.add(traduccion!!)
                "colores" -> colores?.add(traduccion!!)
            }

            val palabras = Palabras(numeros, dias, colores)
            insertarTraduccion(palabras)

        }

        if (intent.hasExtra("TraduccionActualizar") && intent.hasExtra("Item") && intent.hasExtra("Position")) {
            val bundle = intent.getBundleExtra("TraduccionActualizar")
            val traduccion: Traduccion? = bundle?.getParcelable("TraduccionActualizar")
            val item = intent.getStringExtra("Item")
            val positionAct = intent.getIntExtra("Position", 0)

            when (item) {
                "numeros" -> numeros?.set(positionAct, traduccion!!)
                "dias" -> dias?.set(positionAct, traduccion!!)
                "colores" -> colores?.set(positionAct, traduccion!!)
            }

            val palabras = Palabras(numeros, dias, colores)

            actualizarDatos(palabras)
        }

        if (intent.hasExtra("IdBorrar") && intent.hasExtra("PosicionBorrar") && intent.hasExtra("Item")) {
            val idBorrar: Int = intent.getIntExtra("IdBorrar", 0)
            posicionBorrar = intent.getIntExtra("PosicionBorrar", 0)
            val item = intent.getStringExtra("Item")

            when (item) {
                "numeros" -> numeros?.removeAt(posicionBorrar)
                "dias" -> dias?.removeAt(posicionBorrar)
                "colores" -> colores?.removeAt(posicionBorrar)
            }
            Toast.makeText(this, "Id traduccion: " + idBorrar, Toast.LENGTH_SHORT).show()

            val palabras = Palabras(numeros, dias, colores)
            borrarTraduccion(palabras)
        }

        favoritos = ArrayList()
        favoritoViewModel = ViewModelProvider(this).get(FavoritoViewModel::class.java)

        //Room
        if (intent.hasExtra("espanol") && intent.hasExtra("ingles")) {
            val esp = intent?.getStringExtra("espanol")
            val ing = intent?.getStringExtra("ingles")

            val favorito = Favorito(esp!!, ing!!)
            favoritos?.add(favorito)

            favoritoViewModel.insertar(favorito)
        }

        //Room
        if (intent.hasExtra("id")) {
            val id = intent?.getIntExtra("id", 0)

            favoritoViewModel.borrar(id!!)
        }

        obtenerDatos()

    }

    private fun obtenerDatos() {

        val api: Api? = retrofit?.create(Api::class.java)

        api?.obtenerPalabras()?.enqueue(object : Callback<Palabras> {
            override fun onResponse(
                call: Call<Palabras>,
                response: Response<Palabras>
            ) {
                if (response.isSuccessful) {
                    palabras = response.body()

                    if (palabras != null) {
                        numeros = palabras!!.numeros
                        dias = palabras!!.dias
                        colores = palabras!!.colores
                    }
                } else
                    Toast.makeText(
                        getApplicationContext(),
                        "Fallo en la respuesta",
                        Toast.LENGTH_SHORT
                    ).show()
            }

            override fun onFailure(call: Call<Palabras>, t: Throwable) {
                Toast.makeText(getApplicationContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun insertarTraduccion(palabras: Palabras) {

        val api: Api? = retrofit?.create(Api::class.java)

        api?.guardarTraduccion(palabras)?.enqueue(object : Callback<Palabras> {
            override fun onResponse(call: Call<Palabras>, response: Response<Palabras>) {
                if (response.isSuccessful) {
                    val traduccionCreada = response.body()

                    if (traduccionCreada != null) {
                        Snackbar.make(binding.root, "Traduccion insertada", Snackbar.LENGTH_LONG)
                            .setAction("Aceptar") {
                            }
                            .show()
                        actualizarListasDespuesOperacionExitosa()
                    }

                }
            }

            override fun onFailure(call: Call<Palabras>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun actualizarDatos(palabras: Palabras) {

        val api: Api? = retrofit?.create(Api::class.java)

        api?.actualizarTraduccion(palabras)?.enqueue(object : Callback<Palabras> {

            override fun onResponse(call: Call<Palabras>, response: Response<Palabras>) {
                if (response.isSuccessful) {
                    val traduccionAct = response.body()

                    if (traduccionAct != null) {
                        Snackbar.make(binding.root, "Traduccion actualizada", Snackbar.LENGTH_LONG)
                            .setAction("Aceptar") {
                            }
                            .show()
                        actualizarListasDespuesOperacionExitosa()
                    }

                } else Toast.makeText(
                    applicationContext,
                    "Fallo en la respuesta",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<Palabras>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun borrarTraduccion(palabras: Palabras) {

        val api: Api? = retrofit?.create(Api::class.java)

        api?.borrarTraduccion(palabras)?.enqueue(object : Callback<Palabras> {

            override fun onResponse(call: Call<Palabras>, response: Response<Palabras>) {
                if (response.isSuccessful) {
                    val traduccionBorrar = response.body()

                    if (traduccionBorrar != null) {
                        Snackbar.make(binding.root, "Traduccion borrada", Snackbar.LENGTH_LONG)
                            .setAction("Aceptar") {
                            }
                            .show()
                        actualizarListasDespuesOperacionExitosa()
                    }

                } else Toast.makeText(
                    applicationContext,
                    "Fallo en la respuesta",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<Palabras>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun actualizarListasDespuesOperacionExitosa() {
        // Actualizar las listas en los fragmentos correspondientes
        val numerosFragment = supportFragmentManager.findFragmentByTag(NumerosFragment::class.java.simpleName) as NumerosFragment?
        numerosFragment?.actualizarLista(numeros!!)

        val diasFragment = supportFragmentManager.findFragmentByTag(DiasFragment::class.java.simpleName) as DiasFragment?
        diasFragment?.actualizarLista(dias!!)

        val coloresFragment = supportFragmentManager.findFragmentByTag(ColoresFragment::class.java.simpleName) as ColoresFragment?
        coloresFragment?.actualizarLista(colores!!)
    }

    override fun onBackPressed() {
        // Si está abierto al pulsar "Atrás" lo cerramos.
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Si está abierto lo cerramos sino lo abrimos.
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START)
                else
                    drawerLayout.openDrawer(GravityCompat.START)
                return true
            }

            R.id.administrar -> {
                val intent = Intent(this, AdministrarActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.preferencias -> {
                val intent = Intent(this, PreferenciasActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}
