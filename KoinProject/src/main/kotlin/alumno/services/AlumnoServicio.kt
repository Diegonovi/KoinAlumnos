package org.example.alumno.services

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.onFailure
import org.example.alumno.error.AlumnoError
import org.example.alumno.models.Alumno
import org.example.alumno.repository.AlumnoRepository
import org.example.cache.Cache
import org.example.database.services.SqlDatabaseManager

class AlumnoServicio(
    val repository: AlumnoRepository,
    val cache: Cache,
    val dataBase : SqlDatabaseManager
) {

    private val db = dataBase.databaseQueries

    fun getById(id : Int) : Result<Alumno,AlumnoError>{
        cache.get(id).onFailure {
            repository.getById(id).let{
                cache.add(alumno = it!!)
            }
        }
    }

    fun getAll() : List<Alumno>{
        val result = db.getEveryone()
    }
}