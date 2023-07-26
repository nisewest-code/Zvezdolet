package app.example.model

import com.google.gson.annotations.SerializedName


data class Film(
    @SerializedName("title")
    override val name: String,
    val director: String,
    val producer: String,
    override val films: List<String> = listOf(),
    override var filmsOb: List<Film>?,
    override var isFavorite: Boolean = false

): BasicModel
