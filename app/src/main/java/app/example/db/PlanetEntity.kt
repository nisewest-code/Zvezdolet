package app.example.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import app.example.model.Planet

@Entity(tableName = "planets")
@TypeConverters(FilmConverter::class)
data class PlanetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "diameter") val diameter: String,
    @ColumnInfo(name = "population") val population: String,
    @ColumnInfo(name = "films") val films: List<String>
) {
    fun toPlanet() = Planet(
        name = name,
        diameter = diameter,
        population = population,
        films = films,
        filmsOb = null
    )
}