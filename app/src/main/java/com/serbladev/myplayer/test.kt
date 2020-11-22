package com.serbladev.myplayer

import android.view.View
import android.view.ViewGroup
import android.widget.TextView

//Aquí la clase Persona tiene un constructor que necesita un name de tipo String y un age de tipo Int. La creamos de tipo "open" porque
//luego vamos a necesitarlo para crear clases que extiendan de Person.
open class Person( name: String,  age: Int) {
    //Añadimos unas Properties (getter/setter) a nuestra clase Person
       var name: String = name
        // Si queremos modificar el get de esta Property lo haríamos así.
        // field es la palabra reservada al campo que está guardando el valor de la Property
            get()= "Name:$field"

            //Es necesario que sea una variable tipo var para que tenga un setter y poder modificarse
            set(value){
                field = value
            }
       val age: Int = age
}

//Al ser Person de tipo Open, podemos crear la clase Developer cuyo padre sea Person. En el caso de abajo estamos diciendo que sólo tenga un
//name ya que la age va a heredarla de Person y siempre va a ser 20.
class Developer(name: String) : Person(name, 20)

//Si queremos hacer que nuestra clase Developer tenga más de un constructor necesitamos hacer lo siguiente
class ItStaff : Person {
    constructor(name: String) : super(name, 30)
    constructor(age: Int) : super("Sergio", age)
}

fun test() {
//Aquí la variable p es de tipo clase Person y por tanto necesita los argumentos name y age que definimos arriba
    val p = Person("John", 18)

//Aquí la variable d es de tipo clase Developer y por tanto necesita el argumento name, ya que la age siempre es 20
    val d = Developer("Tom")
    val name = d.name // Esto nos devolverá, Name: Tom
    //Si queremos modificar las getter y setter

//Aquí al haber creado dos constructores en la clase ItStaff, podemos usar las dos opciones: que nos pida el nombre pero la edad sea 30
// o que el nombre sea Sergio pero nos pida la edad.
    val itname = ItStaff("Sergio")
    val itage = ItStaff(30)

}

//HERRAMIENTAS DE CONTROL DE FLUJO
fun flowControlToolWhen(view: View){

    //el When es como el Switch de Java pero puede recibir cualquier tipo de dato y no necesita break
    //Podemos asignarle el valor que devuelva el when a una variable, por eso lo ponemos lo primero "val result"
    val result = when (view) {

        //Aquí, en el lado izquierdo pondremos lo que queremos comprobar y en el lado derecho lo que ocurrirá cuando la comprobación se cumpla
        //En Kotlin no hace falta que casteemos que es un TV o un VG porque al decirselo en la izquierda, él hace un "smartcast" y nos permite acceder
        //a todos los atributos que tenga el elemento en sí (por eso se pone subrayado en verde)
        //En este caso es "cuando la vista sea un textView, entonces haremos que guarde en val result el texto del mismo"
        is TextView -> view.text.toString()

        //En este caso es "cuando la vista sea un viewGroup, entonces haremos que guarde en val result el listado de hijos del VG"
        is ViewGroup -> view.childCount.toString()

        //Si queremos tener un caso por defecto para cuando no entre en ninguna de las comprobaciones anteriores no tenemos que poner default como
        //en Java sino "else"
        else -> "Nothing found"
    }
}

