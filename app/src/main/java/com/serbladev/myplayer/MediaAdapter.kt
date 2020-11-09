package com.serbladev.myplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

// Aquí el MediaAdapter trabajará con item (privado porque no se va a usar nunca desde fuera), que es un listado de MediaItem
class MediaAdapter(private val items: List<MediaItem>) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {


    //2º: Implementamos los métodos obligatorios de nuestro MediaAdapter.Viewholder

    // Cada vez que queramos crear una nueva celda, vendrá al método onCreateViewHolder:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_media_item, parent,false)
        return ViewHolder(view)
    }

    // Cuando no necesite crear las celdas porque ya pueda reciclar las que "desaparecen" al hacer scroll, tirará de onBindViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    // Aquí devolveremos el número de items que tiene este MediaAdapter
    override fun getItemCount(): Int = items.size


    //1º: Creamos nuestro viewHolder. El contenedor donde va a pintarse la RecyclerView
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //Relacionamos código con su XML
        private val title : TextView = view.findViewById(R.id.mediaTitle)
        private val thumb : ImageView = view.findViewById(R.id.mediaThumb)
        //Este método recibirá un MediaItem y cogerá el título y lo pondrá en el title, y cogerá la imágen y la inflará en el ImageView
        fun bind(mediaItem: MediaItem){

            //Aqui cargamos el texto del título en el TextView llamado title.
            title.text = mediaItem.title

            //Aquí cargamos una foto en el imageView llamado Thumb
            Glide.with(thumb).load(mediaItem.url).into(thumb)
        }
    }

}