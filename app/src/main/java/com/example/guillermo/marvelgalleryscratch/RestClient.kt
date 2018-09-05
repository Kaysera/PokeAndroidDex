package com.example.guillermo.marvelgalleryscratch

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface RestClient {
    @GET("pokemon")
    fun getData(@Query("offset") offset: String, @Query("limit") limit: String = "50"): Call<PokemonFeed>

    @GET("pokemon/{number}")
    fun getPokemon(@Path("number") number: String): Call<Pokemon>

    companion object {
        fun create(): RestClient {

            val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.1.52:8000/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(RestClient::class.java)
        }
    }
}