package com.serbladev.myplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.serbladev.myplayer.databinding.ActivityDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    //Esto es como un Estático en java. Sirve para que se pueda coger desde cualquier lado
    companion object {
        //const sirve para decirle que lo que viene ahí es una Constante (como los final de java)
      const val EXTRA_ID = "DetailAtivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val itemId = intent.getIntExtra(EXTRA_ID, -1)

        lifecycleScope.launch{
            val items = withContext(Dispatchers.IO){MediaProvider.getItems()}
            val item = items.firstOrNull{it.id==itemId}

            item?.let{
                supportActionBar?.title = item.title
                binding.detailThumb.loadUrl(item.url)
                binding.detailVideoIndicator.visibility = when(item.type) {
                    MediaItem.Type.PHOTO -> View.GONE
                    MediaItem.Type.VIDEO -> View.VISIBLE
                }
            }
        }
    }
}