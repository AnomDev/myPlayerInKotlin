package com.serbladev.myplayer















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