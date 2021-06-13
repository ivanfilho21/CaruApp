package com.example.caruapp

import android.util.Log
import com.example.caruapp.models.Pokemon
import com.example.caruapp.models.PokemonList

class PokemonRepository {
    private val baseUrl = "https://pokeapi.co/api/v2"

    fun get(id: Int): Pokemon {
        val endpoint = "pokemon/$id"
        val path = "$baseUrl/$endpoint/"

        when (val result = Repository().communicate(Repository.GET, path, Pokemon::class.java)) {
            is Repository.Result.Success -> {
                return result.data
            }
            is Repository.Result.Error -> {
                Log.e(javaClass.simpleName, "Erro na comunicação com a API.", result.exception)
            }
        }

        return Pokemon()
    }

    fun get1stGen(): List<Pokemon> {
        val endpoint = "pokemon?limit=151"
        val path = "$baseUrl/$endpoint/"

        when (val result = Repository().communicate(Repository.GET, path, PokemonList::class.java)) {
            is Repository.Result.Success -> {
                return result.data.results
            }
            is Repository.Result.Error -> {
                Log.e(javaClass.simpleName, "Erro na comunicação com a API.", result.exception)
            }
        }

        return listOf()
    }
}