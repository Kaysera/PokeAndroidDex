package com.example.guillermo.marvelgalleryscratch

data class PokemonFeed(val count: Integer, val previous: Object, val results: List<Result>, val next: String)
data class Result(var name: String, val url: String) {
    override fun toString(): String {
        return "Soy $name"
    }
}

data class Pokemon(val name: String, val sprites: Sprite)
data class Sprite(val front_default: String, val back_default: String)

class PokemonPreview {
    var name: String
    var sprite: String = ""
    var nationalDexNumber: String

    constructor (result: Result) {
        this.name = result.name.toCamelCase()
        this.nationalDexNumber = result.url.split('/').takeLast(2).first()
        this.sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + this.nationalDexNumber + ".png"
    }

    override fun toString(): String {
        return "$name es el numero $nationalDexNumber"
    }
}

