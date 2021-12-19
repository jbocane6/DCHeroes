package com.example.dcheroes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dcheroes.databinding.FragmentListBinding
import com.example.dcheroes.main.MainActivity
import com.example.dcheroes.model.SuperheroeItem

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var listViewModel: ListViewModel
    private lateinit var superHeroesAdapter: SuperHeroesAdapter
    private var listSuperheroes: ArrayList<SuperheroeItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideIcon()
        listViewModel.loadMockSuperHeroesFromJson(context?.assets?.open("superHeroes.json"))

        listViewModel.onSuperheroesLoaded.observe(viewLifecycleOwner, { result ->
            onSuperheroesLoadedSubscribe(result)
        })

        superHeroesAdapter = SuperHeroesAdapter(listSuperheroes, onItemClicked = { onSuperHeroeClicked(it) })
        listBinding.superheroesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = superHeroesAdapter
            setHasFixedSize(false)
        }
    }

    private fun onSuperheroesLoadedSubscribe(result: ArrayList<SuperheroeItem>?) {
        result?.let { listSuperheroes ->

            superHeroesAdapter.appendItems(listSuperheroes)
        /*
            this.listSuperheroes = listSuperheroes
            superHeroesAdapter.notifyDataSetChanged()

             */
        }
    }

    private fun onSuperHeroeClicked(superheroe: SuperheroeItem) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(superheroe = superheroe))
    }

}