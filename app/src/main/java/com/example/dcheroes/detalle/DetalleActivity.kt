package com.example.dcheroes.detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dcheroes.R
import com.example.dcheroes.databinding.ActivityDetalleBinding
import com.example.dcheroes.model.SuperheroeItem
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {
    private lateinit var detalleBinding: ActivityDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detalleBinding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(detalleBinding.root)

        val superheroe: SuperheroeItem = intent.extras?.getSerializable("superheroe") as SuperheroeItem
        with(detalleBinding){
            textSuperhero.text = superheroe.name
            textAlias.text = superheroe.alias
            textPower.text = superheroe.powers
            Picasso.get().load(superheroe.urlPicture).into(imgSuperheroe)
        }
    }
}