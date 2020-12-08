package com.serbladev.myplayer

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

//PRUEBAS CON FUNCIONES LAMBDA

//La siguiente funcion realiza una suma con dos enteros que le damos.
fun operaciones(){
    /* val sum: (Int, Int) -> Int = { x, y -> x+ y} */
    //También se podría poner de la siguiente manera:
    val sum = {x: Int, y: Int -> x + y}
    val mul = {x: Int, y: Int -> x * y}
    //Uniendo suma y doOp, quedaría algo como:
    val resultSum = doOp(2,3, sum) //lo que devolvería como resultado 5
    val resultMul = doOp(2,3, mul) //lo que devolvería como resultado 6

    //Incluso se podría realizar esto en una misma línea, por ejemplo para la resta
    val res = doOp(2,3, {x,y -> x - y}) // lo que devolvería como resultado -1.

    //Esta operación se podría sacar del paréntesis para poder trabajar con ella. Algo muy utilizado en los callbacks P. Ej:
    val resAgain = doOp(6,3) { x, y ->
        x - y
    } // lo que devolvería como resultado 3.

    //Una función, no es exactamente lo mismo que una lambda, con lo que yo no puedo llamarla directamente en otra función (en este caso no puedo pasar sumFunction como atributo
    // dentro de resWithFunction) sino que tengo que hacer una llamada a la referencia de la funcion, eso se hace con los :: antes del nombre de la función)
    val resWithFunction = doOp(2,3, ::sumaFunction)


}

//La siguiente función recibe dos enteros y una operación que queremos que realice:
fun doOp(x: Int, y: Int, op: (Int,Int) -> Int) = op(x,y)

fun sumaFunction(x: Int, y: Int): Int = x + y


fun delegateLazy() {
    //Aquí gracias a poner by lazy, lo que hacemos es que no le asignamos el valor 20 a la constante lazyVal hasta el momento en que la estemos
    //usando. En este caso, lazyVal no tendría ningun valor hasta que la llamamos en lazyVal.toString().
    //Esto es útil, por ejemplo, si tenemos una operación muy pesada, no la haríamos hasta el momento en que realmente lo necesitasemos.
    val lazyVal by lazy {20}
    lazyVal.toString()
}

// FUNCIONES LAMBDA WITH REIVERS (funcionan como funciones lambda de extensión)

//************ APPLY

/*fun TextView.apply2(body: TextView.() -> Unit): TextView {
    this.body()
    return this
}*/

//Para hacer genérica la función anterior se haría lo siguiente:
fun <T> T.apply2(body: T.() -> Unit): T {
    this.body()
    return this
}

//************ RUN
fun <T,U> T.run2(body: T.() -> U): U {
    return this.body()
}

//************ LET
fun <T,U> T.let2(body: (T) -> U): U {
    return body(this)
}

//************ LET
fun <T,U> T.with2(receiver: T, body: T.() -> U): U {
    return receiver.body()
}

//************ ALSO
fun <T> T.also2(body: (T) -> Unit): T {
    body(this)
    return this
}


//CREANDO UNA COLECCIÓN DESDE CERO
fun collectionTest() {
    //La colección de tipo listOf es una lista inmutable, es decir que no podremos hacer cosas como añadir otros números o cambiar los que ya están
    // Hacer una lista inmutable hará que cuando estemos llamandola (lo que hacemos en la var result de abajo) nos devolverá una lista identica pero
    // modificada con lo que hayamos dicho (abajó tendrá que crear 3 listas identicas que luego añade la peticion nueva, una por.filter, otra para .map
    // y la ultima para .sorted. Esto, si la lista es grande y se hacen muchas llamadas, es una cagada en cuanto al rendimiento.
    val listOfInt: List<Int> = listOf(2, 1, 5, 6)

    // Guardamos en una variable result los filtros siguientes || .filter  nos filtraría sólo los números pares con el % 2
    val result = listOfInt
        //Para evitar la "cagada" explicada arriba usamos .asSequence, esto transforma la coleccion inmutable en una secuencia,
        // que gestiona mejor los resultados y sólo crea 1 lista nueva, no 3. Pero luego necesitamos volver a transformala en una lista inmutable.
        .asSequence()
        .filter{ it % 2 == 0 }

        //.map convierte el listado de int a listado de string
        .map{it.toString()}

        //.sorted nos ordena el listado
        .sorted()

        // Existen muchos más tipos de operadores que se pueden ver si pones .algo, es decir "listOfInt.XXXXX"

        // Aquí le decimos que transforme la secuencia de nuevo a una lista inmutable
        .toList()

    //La colección  de tipo mutableListOf es una lista mutable, es decir que podremos hacer cosas como añadir otros números o cambiar los que ya están
    val mutable = mutableListOf(3,2,5)
}

// CORUTINAS
// Sirven para desarrollar tareas asíncronas de manera asíncrona (cambiando de hilo para no bloquear el principal)
fun coroutines(viewGroup: ViewGroup){
    //El GlobalScope nos dice en qué hilo estamos: el principal
    GlobalScope.launch(Dispatchers.Main){
        //Esta siguiente línea nos lleva el trabajo a otro hilo, en este caso llamado IO (porque es como si hicieramos una petición a un server)
        val result = withContext(Dispatchers.IO){ heavyTask()}
        print(result)
    }
}
//Función fake para que parezca que estamos pidiendo información a un servidor.
fun heavyTask(): String = "Hello"


//NULLABLES
fun nullable(){
    val x: Int? = null
    val l: Long = if(x!= null) x.toLong() else 0
    //para comprobar que la X no es nula antes de hacer la operacion se podría hacer también esto:
    // val l: Long = x?.toLong() ?: 0
}

//CLASES SELLADAS

sealed class Op{
    class Add(val value: Int): Op()
    class Sub(val value: Int): Op()
    class Mul(val value: Int): Op()
    object Inc: Op()
}

fun sealedClassesDoOp(x: Int, op: Op): Int = when (op){
    is Op.Add -> x + op.value
    is Op.Sub -> x - op.value
    is Op.Mul -> x * op.value
    Op.Inc -> x + 1
}