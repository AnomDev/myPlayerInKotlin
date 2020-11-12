package com.serbladev.myplayer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//Aquí se crea una función de extensión que nos sirve para generar todos los toast que necesitemos en nuestra aplicación (vale en cualquier Contexto).
fun Context.toast (message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
//Aquí se crea una función de extensión que nos sirve para generar los toast dentro del ViewHolder del RV.
fun RecyclerView.ViewHolder.toast (message: String){
    itemView.context.toast(message)
}

//Con esta f. de ext. estamos construyendo o inflando un layout donde se añadirá cada item de nuestro RV.
fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater
        .from(this.context)
        .inflate(R.layout.view_media_item, this,false)
}
//Con esta f. de ext. estamos añadiendo una imagen a través de una URL.
//Hacerlo de esta forma nos permite que, si necesitamos cambiar la libreria (usar Picasso por ejemplo) sólo habría que cambiar esta extensión
//y no habría que tocar para nada el resto de código donde se esté usando esta función
fun ImageView.loadUrl (url: String) {
    Glide.with(this).load(url).into(this)
}

//Las funciones reified son muy útiles por ejemplo para simplificar el tema de la librería GSON o en 'testing'
//cuando necesitas mockear una clase y hay que pasársela como argumento.
inline fun <reified T: Activity>Context.startActivity(){
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}
//La palabra clave 'inline' sirve para que en vez de hacer una llamada a la función que queremos, se sustituya la llamada por el código que dicha función
//tiene en su interior