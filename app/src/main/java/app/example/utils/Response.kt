package app.example.utils

import app.example.model.BasicModel
import app.example.model.Planet

data class Response<T: BasicModel>(
    val count: Int,
    val results: List<T>
)