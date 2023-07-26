package app.example.db

import androidx.room.*
import app.example.model.Person
// usage

@Entity(tableName = "persons")
@TypeConverters(FilmConverter::class)
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "starships") val starships: List<String>,
    @ColumnInfo(name = "films") val films: List<String>
) {
    fun toPerson() = Person(
        name = name,
        gender = gender,
        starships = starships,
        films = films,
        filmsOb = null
    )
}