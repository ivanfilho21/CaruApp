package com.example.caruapp

class PokemonRepository {
    private val baseUrl = "https://pokeapi.co/api/v2"

    fun get(id: Int): Pokemon {
        val endpoint = "pokemon/$id"
        val path = "$baseUrl/$endpoint/"

        return Repository().communicate(Repository.GET, path, Pokemon::class.java)
    }

    // TODO: trazer uma lista de Pokémons
    fun get1stGen(): List<Pokemon> {
        val endpoint = "pokemon?limit=151"
        val path = "$baseUrl/$endpoint/"
//        return Repository().communicate(Repository.GET, path, Pokemon::class.java)
        return Repository().communicate(Repository.GET, path, PokemonList::class.java).results
    }
}