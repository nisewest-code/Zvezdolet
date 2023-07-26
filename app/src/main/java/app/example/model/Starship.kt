package app.example.model

import app.example.db.StarshipEntity

data class Starship(
    override val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
    override val films: List<String>,
    override var filmsOb: List<Film>?,
    override var isFavorite: Boolean = false
): BasicModel{
    fun toStarshipEntity(): StarshipEntity = StarshipEntity(
        id = 0,
        name = name,
        model = model,
        manufacturer = manufacturer,
        passengers = passengers,
        films = films
    )
}
