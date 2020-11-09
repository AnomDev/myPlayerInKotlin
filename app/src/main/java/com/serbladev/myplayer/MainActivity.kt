package com.serbladev.myplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.service.autofill.OnClickAction
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast("First toast")


        //*********** Ejercicio 1.**************
        //Primero se relacionan las vistas XML con el código
        /*val message: TextView = findViewById(R.id.message_TV)
            //Luego se cambia el texto del mesaje de la vista por el que nosotros queremos
        message.setText("Hello Kotlin")*/


        //*********** Ejercicio 2.**************
        //Queremos que al pulsar un botón aparezca un toast que diga "Hello" + el texto que hemos puesto en un EditText
        //Primero se relacionan las vistas XML con el código
        val message: EditText = findViewById(R.id.message_ET)
        val button: Button = findViewById(R.id.button_B)
        //Luego se añade un listener al botón y se le agrega una String template para poder añadirle lo que obtiene del EditText
        button.setOnClickListener {
            toast("What did you say?:\n You said \"" + "${message.text}" + "\"")


        }


    }


    //Aquí se crea una función que nos sirve para generar todos los toast que necesitemos en nuestra aplicación.
    private fun toast (message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}