package com.serbladev.myplayer

import androidx.annotation.WorkerThread
import com.serbladev.myplayer.MediaItem.*

// Aquí estamos creando una función fuera de ninguna clase pero que podremos utilizarla desde cualquier clase de la aplicación.
// Esta función obtiene cada uno de los items desde las URLs asociadas.
// Para evitarnos el tocho de meter una a una, vamos a usar un rango y mapearlo:

object MediaProvider{
    //Este WorkerThread avisa de que lo que viene a continuación NO VA A SER ejecutado en el hilo principal
    @WorkerThread
    fun getItems(): List<MediaItem> {
        Thread.sleep(800)
        return (1..10).map{
        MediaItem(
            it,
            "Title $it",
            "https://placekitten.com/200/200?image=$it",
            if(it%3==0) Type.VIDEO else Type.PHOTO
        )}
    }
}


  /*  listOf(
    MediaItem("Title 1", "https://placekitten.com/200/200?image=1", Type.PHOTO),
    MediaItem("Title 2", "https://placekitten.com/200/200?image=2", Type.PHOTO),
    MediaItem("Title 3", "https://placekitten.com/200/200?image=3", Type.PHOTO),
    MediaItem("Title 4", "https://placekitten.com/200/200?image=4", Type.VIDEO),
    MediaItem("Title 5", "https://placekitten.com/200/200?image=5", Type.PHOTO),
    MediaItem("Title 6", "https://placekitten.com/200/200?image=6", Type.VIDEO),
    MediaItem("Title 7", "https://placekitten.com/200/200?image=7", Type.PHOTO),
    MediaItem("Title 8", "https://placekitten.com/200/200?image=8", Type.PHOTO),
    MediaItem("Title 9", "https://placekitten.com/200/200?image=9", Type.VIDEO),
    MediaItem("Title 10", "https://placekitten.com/200/200?image=10", Type.PHOTO),
    MediaItem("Title 11", "https://placekitten.com/200/200?image=11", Type.PHOTO),
    MediaItem("Title 12", "https://placekitten.com/200/200?image=12", Type.VIDEO),
    MediaItem("Title 13", "https://placekitten.com/200/200?image=13", Type.PHOTO),
    MediaItem("Title 14", "https://placekitten.com/200/200?image=14", Type.PHOTO),
    MediaItem("Title 15", "https://placekitten.com/200/200?image=15", Type.PHOTO),
    MediaItem("Title 16", "https://placekitten.com/200/200?image=16", Type.VIDEO)
)*/