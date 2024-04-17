package org.example.database.services

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.example.config.Config
import org.lighthousegames.logging.logging
import database.DatabaseQueries
import org.examples.database.AppDatabase

private val logger = logging()

class SqlDatabaseManager(
    val config: Config
) {
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    init {
        logger.debug { "Inicializando el gestor de Bases de Datos con SQLDelight" }
    }

    private fun initQueries(): DatabaseQueries {

        return if (config.databaseInMemory) {
            logger.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            logger.debug { "SqlDeLightClient - File: ${config.databaseUrl}" }
            JdbcSqliteDriver(config.databaseUrl)
        }.let { driver ->
            // Creamos la base de datos
            logger.debug { "Creando Tablas (si es necesario)" }
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries

    }

    /*
    fun initialize() {
        if (config.databaseInitData) {
            removeAllData()

        }
    }
    */


    // limpiamos las tablas
    private fun removeAllData() {
        logger.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction {
            databaseQueries.deleteAlumnos()
        }
    }
}