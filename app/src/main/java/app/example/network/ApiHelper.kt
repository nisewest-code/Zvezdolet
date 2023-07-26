package app.example.network

import android.util.Log
import kotlinx.coroutines.flow.flow

class ApiHelper(private val apiService: ApiService) {

    suspend fun searchPersons(name: String) = flow { emit(apiService.searchPersons(name).results) }

    suspend fun searchPlanets(name: String) = flow { emit(apiService.searchPlanets(name).results) }

    suspend fun searchStarships(name: String) = flow { emit(apiService.searchStarships(name).results) }

    suspend fun getFilm(filmId: String) = flow {
        val nFilm = filmId.replace("https://swapi.dev/api/films/","")
        emit(apiService.getFilm(nFilm))
    }
}