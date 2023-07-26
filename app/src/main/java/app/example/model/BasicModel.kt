package app.example.model

interface BasicModel{
    val name: String
    val films: List<String>
    var filmsOb: List<Film>?
    var isFavorite: Boolean
}