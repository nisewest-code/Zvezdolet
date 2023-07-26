package app.example.network

import app.example.model.Film
import app.example.model.Person
import app.example.model.Planet
import app.example.model.Starship
import app.example.utils.Response
import retrofit2.http.*

interface ApiService {

    @GET("people/")
    suspend fun searchPersons(@Query("search") name: String): Response<Person>

    @GET("planets/")
    suspend fun searchPlanets(@Query("search") name: String): Response<Planet>

    @GET("starships/")
    suspend fun searchStarships(@Query("search") name: String): Response<Starship>

    @GET("films/{name}")
    suspend fun getFilm(@Path(value = "name", encoded = true) filmId: String): Film
}