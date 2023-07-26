package app.example.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ModelDao {

    @Insert(entity = PlanetEntity::class)
    fun insertNewPlanet(statistic: PlanetEntity)

    @Insert(entity = PersonEntity::class)
    fun insertNewPerson(statistic: PersonEntity)

    @Insert(entity = StarshipEntity::class)
    fun insertNewStarship(statistic: StarshipEntity)

    @Query("SELECT * FROM persons")
    fun getAllPersons(): List<PersonEntity>

    @Query("SELECT * FROM planets")
    fun getAllPlanets(): List<PlanetEntity>

    @Query("SELECT * FROM starships")
    fun getAllStarships(): List<StarshipEntity>

}