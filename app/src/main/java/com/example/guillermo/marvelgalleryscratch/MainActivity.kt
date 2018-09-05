package com.example.guillermo.marvelgalleryscratch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback

import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val restClient by lazy {
        RestClient.create()
    }
    var pokeList: MutableList<PokemonPreview> = mutableListOf()
    var pokemonLoaded = 0
    val startLoading = 25

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val llm = LinearLayoutManager(applicationContext)
        recyclerview.layoutManager = llm
        recyclerview.adapter = RecyclerPViewAdapter(recyclerview, pokeList)
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var lastVisiblePosition = llm.findLastVisibleItemPosition()
                Log.d("onScrolled", "${lastVisiblePosition}")
                if (lastVisiblePosition == pokemonLoaded + startLoading - 1) {
                    pokemonLoaded += 50
                    loadJSON(pokemonLoaded.toString())
                }
            }
        })
        loadJSON()
    }

    fun loadJSON(query: String = "0") {
        val call = restClient.getData(query)
        call.enqueue(object : Callback<PokemonFeed> {
            override fun onResponse(call: Call<PokemonFeed>, response: Response<PokemonFeed>) {
                when (response.code()) {
                    200 -> {
                        val data = response.body()
                        var pokemonSlice = data?.results?.map(::PokemonPreview)
                        pokeList.addAll(pokemonSlice ?: listOf())
                        Log.d("MisPokemon", pokeList.size.toString())
                        recyclerview.adapter.notifyDataSetChanged()
                    }

                    else -> {
                        Log.d("Error", "Response code: ${response.code()} caused by url: ${call.request().url()}")
                    }
                }
            }

            override fun onFailure(call: Call<PokemonFeed>, t: Throwable) {
                Log.e("error", t.toString())
            }
        })
    }


}


