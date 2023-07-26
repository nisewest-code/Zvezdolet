package app.example

import android.app.Application
import app.example.db.Dependencies

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Dependencies.init(this)
    }
}