package com.example.guillermo.marvelgalleryscratch

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.support.v7.widget.CardView
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.my_cardview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var pokemonName = itemView.name
    var dexNumber = itemView.description
    var pokemonPhoto = itemView.picture

    val restClient by lazy {
        RestClient.create()
    }

    fun bind(pokemon: PokemonPreview){
        pokemonName.text = pokemon.name
        dexNumber.text = pokemon.nationalDexNumber
        pokemonPhoto.loadImage(pokemon.sprite)


        itemView.setOnClickListener{
            it.toast("I am ${pokemon.name}")
            val call = restClient.getPokemon(pokemon.nationalDexNumber)
            call.enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    when (response.code()) {
                        200 -> {

                            Log.d("MisPokemon", "Code 200")

                        }

                        else -> {
                            Log.d("Error", "Response code: ${response.code()} caused by url: ${call.request().url()}")
                        }
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.e("error", t.toString())
                }
            })

        }

    }





}