package com.serbladev.myplayer

// Aquí estamos creando una función fuera de ninguna clase pero que podremos utilizarla desde cualquier clase de la aplicación.
// Esta función obtiene cada uno de los items desde las URLs asociadas.
fun getItems(): List<MediaItem> = listOf(
    MediaItem("Title 1", "https://placekitten.com/200/200?image=1"),
    MediaItem("Title 2", "https://placekitten.com/200/200?image=2"),
    MediaItem("Title 3", "https://placekitten.com/200/200?image=3"),
    MediaItem("Title 4", "https://placekitten.com/200/200?image=4"),
    MediaItem("Title 5", "https://placekitten.com/200/200?image=5"),
    MediaItem("Title 6", "https://placekitten.com/200/200?image=6"),
    MediaItem("Title 7", "https://placekitten.com/200/200?image=7"),
    MediaItem("Title 8", "https://placekitten.com/200/200?image=8"),
    MediaItem("Title 9", "https://placekitten.com/200/200?image=9"),
    MediaItem("Title 10", "https://placekitten.com/200/200?image=10"),
    MediaItem("Title 11", "https://placekitten.com/200/200?image=11"),
    MediaItem("Title 12", "https://placekitten.com/200/200?image=12"),
    MediaItem("Title 13", "https://placekitten.com/200/200?image=13"),
    MediaItem("Title 14", "https://placekitten.com/200/200?image=14"),
    MediaItem("Title 15", "https://placekitten.com/200/200?image=15"),
    MediaItem("Title 16", "https://placekitten.com/200/200?image=16")
)