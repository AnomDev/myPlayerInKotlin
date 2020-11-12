package com.serbladev.myplayer

import android.app.Activity
import android.content.Context
import android.content.Intent
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
        toast("Welcome to my fisrt ReCATlerView in Kotlin")

        val recycler: RecyclerView = findViewById(R.id.recycler)
        recycler.adapter = MediaAdapter(getItems())

    /*    toast("hello")
        //Aquí estamos navegando entre actividades usando la función de extensión de tipo reified startActivity
        startActivity<MainActivity>()

        val intent = Intent(this, MainActivity:: class.java)
        startActivity(intent)
*/




    }
}

