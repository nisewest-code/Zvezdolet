package app.example.network


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun searchPersons(name: String) = apiHelper.searchPersons(name)

    suspend fun searchPlanets(name: String) = apiHelper.searchPlanets(name)

    suspend fun searchStarships(name: String) = apiHelper.searchStarships(name)

    suspend fun getFilm(filmId: String) = apiHelper.getFilm(filmId)
}