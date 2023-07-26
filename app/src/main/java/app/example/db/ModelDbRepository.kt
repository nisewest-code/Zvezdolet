package app.example.db

import android.util.Log
import app.example.model.Person
import app.example.model.Planet
import app.example.model.Starship
import app.example.utils.Resource
import kotlinx.coroutines.flow.flow

class ModelDbRepository(private val modelDao: ModelDao) {

    suspend fun insertNewPlanet(statistic: Planet) =
        flow {
            emit(Resource.loading(data = null))
            val result = try {
                Resource.success(modelDao.insertNewPlanet(statistic.toPlanetEntity()))
            } catch (e: Exception) {

                Resource.error(
                    data = null,
                    message = e.message ?: "Error Occurred!"
                )

            }
            emit(result)
        }

    suspend fun insertNewPerson(statistic: Person) =
        flow {
            emit(Resource.loading(data = null))
            val result = try {
                Resource.success(modelDao.insertNewPerson(statistic.toPersonEntity()))
            } catch (e: Exception) {

                Resource.error(
                    data = null,
                    message = e.message ?: "Error Occurred!"
                )

            }

            emit(result)
        }

    suspend fun insertNewStarship(statistic: Starship) =
        flow {
            emit(Resource.loading(data = null))
            val result = try {
                Resource.success(modelDao.insertNewStarship(statistic.toStarshipEntity()))
            } catch (e: Exception) {

                Resource.error(
                    data = null,
                    message = e.message ?: "Error Occurred!"
                )

            }
            emit(result)

        }

    suspend fun getAllPlanet() = flow {
        emit(modelDao.getAllPlanets().map {
            val item = it.toPlanet()
            item.isFavorite = true
            item
        }.toList())
    }

    suspend fun getAllPerson() = flow {
        emit(modelDao.getAllPersons().map {
            val item = it.toPerson()
            item.isFavorite = true
            item
        })
    }

    suspend fun getAllStarship() = flow {
        emit(modelDao.getAllStarships().map {
            val item = it.toStarship()
            item.isFavorite = true
            item
        }.toList())
    }
}