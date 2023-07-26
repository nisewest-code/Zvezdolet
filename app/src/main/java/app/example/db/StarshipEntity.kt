package app.example.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import app.example.model.Starship

@Entity(tableName = "starships")
@TypeConverters(FilmConverter::class)
data class StarshipEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "manufacturer") val manufacturer: String,
    @ColumnInfo(name = "passengers") val passengers: String,
    @ColumnInfo(name = "films") val films: List<String>
) {
    fun toStarship() = Starship(
        name = name,
        model = model,
        manufacturer = manufacturer,
        passengers = passengers,
        films = films,
        filmsOb = null
    )
}