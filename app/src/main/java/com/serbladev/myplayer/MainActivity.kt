package com.serbladev.myplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.serbladev.myplayer.MediaItem.*

//Este import hace referencia al XML que está importando nuestra vista, en este caso activity_main (así no necesitamos usar findViewById)
import kotlinx.android.synthetic.main.activity_main.*

//Esta es la manera recomendada de acceder a las vistas
import com.serbladev.myplayer.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private val adapter = MediaAdapter {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to it.id)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        toast("Welcome to my fisrt ReCATlerView in Kotlin")

        binding.recyclerViewId.adapter = adapter
        updateItems()
    }

    private fun updateItems(filter: Filter = Filter.None){
        //Aquí hemos cambiado a una corrutina para que reciba los items con los que tiene que pintar el recycler y así no bloquear el hilo principal
        GlobalScope.launch(Dispatchers.Main)  {
            progress.visibility = View.VISIBLE
            val items = withContext(Dispatchers.IO){getFilteredItems(filter)}
            adapter.items = items
            progress.visibility = View.GONE
        }
    }

        private fun getFilteredItems(filter: Filter): List<MediaItem> {
            return MediaProvider.getItems().let { media ->
                when (filter) {
                   Filter.None -> media
                   is Filter.ByType -> media.filter { it.type == filter.type }
                }
            }
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // En este método hacemos que cuando pulsemos un elemento del menú nos muestre los items de la lista de un tipo, de otro o todos.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter =  when (item.itemId) {
            R.id.filter_photos -> Filter.ByType (Type.PHOTO)
            R.id.filter_videos -> Filter.ByType (Type.VIDEO)
            else -> Filter.None
        }
        updateItems(filter)
        return super.onOptionsItemSelected(item)
    }

}



























