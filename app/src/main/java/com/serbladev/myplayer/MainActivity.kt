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

import org.w3c.dom.Text

//Este import hace referencia al XML que está importando nuestra vista, en este caso activity_main (así no necesitamos usar findViewById)
import kotlinx.android.synthetic.main.activity_main.*

//Esta es la manera recomendada de acceder a las vistas
import com.serbladev.myplayer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        toast("Welcome to my fisrt ReCATlerView in Kotlin")

        binding.recyclerViewId.adapter = MediaAdapter(getItems())

    /*    toast("hello")
        //Aquí estamos navegando entre actividades usando la función de extensión de tipo reified startActivity
        startActivity<MainActivity>()

        val intent = Intent(this, MainActivity:: class.java)
        startActivity(intent)
*/




    }
}

