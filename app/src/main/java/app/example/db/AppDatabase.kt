package app.example.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        PlanetEntity::class,
        PersonEntity::class,
        StarshipEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getModelDao(): ModelDao

}