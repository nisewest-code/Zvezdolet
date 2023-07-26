package app.example.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import app.example.R
import app.example.model.BasicModel
import app.example.model.Film
import app.example.model.Planet
import app.example.model.Starship

const val PLANET_HOLDER = R.layout.layout_planet_holder
const val PERSON_HOLDER = R.layout.layout_person_holder
const val STARSHIP_HOLDER = R.layout.layout_starship_holder
const val FILM_HOLDER = R.layout.layout_film_holder
object HolderFactory {

    fun bind(parent: ViewGroup, viewType: Int): BasicHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)
        return when (viewType) {
            PLANET_HOLDER -> PlanetHolder(view)
            PERSON_HOLDER -> PersonHolder(view)
            STARSHIP_HOLDER -> StarshipHolder(view)
            FILM_HOLDER -> FilmHolder(view)
            else -> PlanetHolder(view)
        }
    }

    fun itemType(item: BasicModel): Int{
        return when (item) {
            is Planet -> {
                PLANET_HOLDER
            }
            is Starship -> {
                STARSHIP_HOLDER
            }
            is Film -> {
                FILM_HOLDER
            }
            else -> {
                PERSON_HOLDER
            }
        }
    }
}