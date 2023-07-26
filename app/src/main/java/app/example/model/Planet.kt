package app.example.model

import app.example.db.PlanetEntity

data class Planet(
    override val name: String,
    val diameter: String,
    val population: String,
    override val films: List<String>,
    override var filmsOb: List<Film>?,
    override var isFavorite: Boolean = false
): BasicModel {
    fun toPlanetEntity(): PlanetEntity = PlanetEntity(
        id = 0,
        name = name,
        diameter = diameter,
        population = population,
        films = films
    )
}
