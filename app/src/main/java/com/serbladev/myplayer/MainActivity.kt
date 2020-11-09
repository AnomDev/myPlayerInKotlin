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
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast("First toast")

        val recycler: RecyclerView = findViewById(R.id.recycler)
        recycler.adapter = MediaAdapter(getItems())


    }


    //Aquí se crea una función que nos sirve para generar todos los toast que necesitemos en nuestra aplicación.
    private fun toast (message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}