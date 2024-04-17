package org.example.config

class Config {
    var databaseInMemory: Boolean = true
        private set
    var databaseUrl : String = "jdbc:sqlite:alumnos.db"
        private set
    var cacheSize : Int = -1
        private set

    init {

    }
}