package com.example.dcheroes.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dcheroes.R
import com.example.dcheroes.detalle.DetalleActivity
import com.example.dcheroes.model.Superheroe
import com.example.dcheroes.model.SuperheroeItem
import com.google.gson.Gson

class ListSuperheroesActivity : AppCompatActivity() {

    private lateinit var listSuperheroes : ArrayList<SuperheroeItem>
    private lateinit var superHeroesAdapter: SuperHeroesAdapter
    private lateinit var superHeroesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_superheroes)

        superHeroesRecyclerView = findViewById(R.id.superheroes_recycler_view)

        //listSuperheroes = createMockSuperHeroes()
        listSuperheroes = loadMockSuperHeroesFromJson()

        superHeroesAdapter = SuperHeroesAdapter(listSuperheroes, onItemClicked = { onSuperHeroeClicked(it) })

        /*superHeroesRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))*/
        superHeroesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = superHeroesAdapter
            setHasFixedSize(false)
        }
    }

    private fun onSuperHeroeClicked(superHeroe: SuperheroeItem) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("superheroe", superHeroe)
        startActivity(intent)
    }

    private fun loadMockSuperHeroesFromJson(): ArrayList<SuperheroeItem> {
        val superHeroesString: String =
            applicationContext.assets.open("superHeroes.json").bufferedReader()
                .use { it.readText() }
        val gson = Gson()
        val data = gson.fromJson(superHeroesString, Superheroe::class.java)

        return data
    }

    /*private fun createMockSuperHeroes() : ArrayList<Superheroe>{
        /*var lista: ArrayList<Superheroe> = arrayListOf()
        var superheroe = Superheroe(
            name = "Superman",
            powers = "Super strength, flight, invulnerability, super speed, heat vision, freeze breath, x-ray vision, superhuman hearing, healing factor",
            alias = "Clark Kent, Kal-El",
            city = "Metropolis",
            facebook = "https://facebook.com/superman",
            occupation = "Reporter"
        )
        lista.add(superheroe)*/

        return arrayListOf(
            Superheroe(
                name = "Superman",
                powers = "Super strength, flight, invulnerability, super speed, heat vision, freeze breath, x-ray vision, superhuman hearing, healing factor",
                alias = "Clark Kent, Kal-El",
                city = "Metropolis",
                facebook = "https://facebook.com/superman",
                occupation = "Reporter",
                urlPicture = "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_GreenLantern_20200721_5f173ad01724e2.92436411.jpg"
            ),
            Superheroe(
                name = "Batman",
                powers = "Exceptional martial artist, combat strategy, inexhaustible wealth, brilliant deductive skill, advanced technology",
                alias = "Bruce Wayne",
                city = "Gotham City",
                facebook = "https://facebook.com/batman",
                occupation = "CEO of Wayne Enterprises",
                urlPicture = "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_GreenLantern_20200721_5f173ad01724e2.92436411.jpg"
            ),
            Superheroe(
                name = "Wonder Woman",
                powers = "Super strength, invulnerability, flight, combat skill, combat strategy, superhuman agility, healing factor, magic weaponry",
                alias = "Diana Prince",
                city = "Themyscira",
                facebook = "https://facebook.com/wonderwoman",
                occupation = "Secretary",
                urlPicture = "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_GreenLantern_20200721_5f173ad01724e2.92436411.jpg"
            ),
            Superheroe(
                name = "Flash",
                powers = "Super speed, intangibility, superhuman agility",
                alias = "Barry Allen, Jay Garrick, Wally West",
                city = "Central City",
                facebook = "https://facebook.com/theflash",
                occupation = "Forensic scientist",
                urlPicture = "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_GreenLantern_20200721_5f173ad01724e2.92436411.jpg"
            ),
            Superheroe(
                name = "Green Lantern",
                powers = "Hard light constructs, instant weaponry, force fields, flight, durability, alien technology",
                alias = "Hal Jordan",
                city = "Coast City",
                facebook = "https://facebook.com/greenlantern",
                occupation = "Test pilot",
                urlPicture = "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_GreenLantern_20200721_5f173ad01724e2.92436411.jpg"
            )
        )
    }*/
}