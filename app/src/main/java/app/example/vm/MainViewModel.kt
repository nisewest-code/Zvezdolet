package app.example.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import app.example.db.Dependencies
import app.example.model.BasicModel
import app.example.model.Film
import app.example.network.MainRepository
import app.example.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val liveDataSearch = MutableLiveData<Resource<List<BasicModel>?>>()

    fun liveDataSearch() = liveDataSearch

    fun researchData(data: String) =
        viewModelScope.launch(Dispatchers.IO) {
            flow {
                emit(Resource.loading(data = null))
                try {
                    val flow1 = mainRepository.searchPersons(data)
                    val flow2 = mainRepository.searchPlanets(data)
                    val flow3 = mainRepository.searchStarships(data)
                    val res = merge(flow1, flow2, flow3)
                        .reduce { accumulator, value ->
                            return@reduce accumulator.plus(value)
                        }
                    emit(Resource.success(data = res))
//            emit(Resource.success(data = mainRepository.searchPersons("")))
                } catch (exception: Exception) {
                    emit(
                        Resource.error(
                            data = null,
                            message = exception.message ?: "Error Occurred!"
                        )
                    )
                }
            }.collect {
                liveDataSearch.postValue(it)
            }
        }

    fun getFavorite() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val flow1 = Dependencies.statisticRepository.getAllPerson()
            val flow2 = Dependencies.statisticRepository.getAllPlanet()
            val flow3 = Dependencies.statisticRepository.getAllStarship()
            val res = merge(flow1, flow2, flow3)
                .reduce { accumulator, value ->
                    return@reduce accumulator.plus(value)
                }.map {
                    loadFilms(it)
                }
            emit(Resource.success(data = res))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }


    private suspend fun loadFilms(item: BasicModel): BasicModel {
        try {
            val flows = mutableListOf<Flow<Film>>()
            item.films.forEach {
                flows.add(mainRepository.getFilm(it))
            }

            val res = flows.merge().toList()

            item.filmsOb = res
        } catch (e: Exception) {
            e.printStackTrace()
            item.filmsOb = listOf()
        }
        return item
    }
}