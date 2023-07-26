package app.example.model

import app.example.db.PersonEntity
import com.google.gson.annotations.SerializedName

data class Person(
    override val name: String,
    val gender: String,
    val starships: List<String>,
    override val films: List<String>,
    override var filmsOb: List<Film>?,
    override var isFavorite: Boolean = false
): BasicModel {
    fun toPersonEntity(): PersonEntity = PersonEntity(
        id = 0,
        name = name,
        gender = gender,
        starships = starships,
        films = films
    )
}
