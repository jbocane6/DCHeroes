package com.example.dcheroes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dcheroes.databinding.FragmentListBinding
import com.example.dcheroes.main.MainActivity
import com.example.dcheroes.model.Superheroe
import com.example.dcheroes.model.SuperheroeItem
import com.google.gson.Gson

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var superHeroesAdapter: SuperHeroesAdapter
    private lateinit var listSuperHeroes: ArrayList<SuperheroeItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)

        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideIcon()
        listSuperHeroes = loadMockSuperHeroesFromJson()
        superHeroesAdapter =
            SuperHeroesAdapter(listSuperHeroes, onItemClicked = { onSuperHeroeClicked(it) })
        listBinding.superheroesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = superHeroesAdapter
            setHasFixedSize(false)
        }
    }

    private fun onSuperHeroeClicked(superheroe: SuperheroeItem) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(superheroe = superheroe))
    }

    private fun loadMockSuperHeroesFromJson(): ArrayList<SuperheroeItem> {
        val superHeroesString: String = context?.assets?.open("superHeroes.json")?.bufferedReader()
            .use { it!!.readText() } //TODO reparar
        val gson = Gson()
        val data = gson.fromJson(superHeroesString, Superheroe::class.java)

        return data
    }
}