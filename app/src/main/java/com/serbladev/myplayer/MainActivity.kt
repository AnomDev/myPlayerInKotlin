package com.serbladev.myplayer

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.service.autofill.OnClickAction
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.serbladev.myplayer.MediaItem.*

import org.w3c.dom.Text

//Este import hace referencia al XML que está importando nuestra vista, en este caso activity_main (así no necesitamos usar findViewById)
import kotlinx.android.synthetic.main.activity_main.*

//Esta es la manera recomendada de acceder a las vistas
import com.serbladev.myplayer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        MediaAdapter(getItems()) {
            toast(it.title)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        toast("Welcome to my fisrt ReCATlerView in Kotlin")

        //Gracias al lazy de la línea 28, hasta que no llegamos a la esta línea, el adapter no tendrá valor alguno.
        binding.recyclerViewId.adapter = adapter

        adapter.items = getItems()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // En este método hacemos que cuando pulsemos un elemento del menú nos muestre los items de la lista de un tipo, de otro o todos.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        adapter.items = getItems().let { media ->
            when (item.itemId) {
                R.id.filter_photos -> media.filter { it.type == Type.PHOTO }
                R.id.filter_videos -> media.filter { it.type == Type.VIDEO }
                R.id.filter_all -> media
                else -> emptyList()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}



























