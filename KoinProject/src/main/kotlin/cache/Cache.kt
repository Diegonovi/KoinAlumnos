package org.example.cache

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.alumno.error.AlumnoError
import org.example.alumno.models.Alumno
import org.example.config.Config
import org.lighthousegames.logging.logging

private val log = logging()

class Cache (
        val config : Config){
    var cache : MutableMap<Int,Alumno> = emptyMap<Int, Alumno>().toMutableMap()

    fun add(alumno: Alumno) : Result<Alumno,AlumnoError>{
        if (cache.size >= config.cacheSize && cache[alumno.id] != null){
            log.debug { "Borrando el alumno con id ${alumno.id} de la cache" }
            cache.remove(cache.keys.first())
            cache[alumno.id] = alumno
            log.debug { "Se ha añadido el alumno ${alumno.nombre} a la cache" }
        }
    }

    fun delete(alumno: Alumno) : Result<Alumno,AlumnoError>{
        TODO()
    }

    fun clear(){
        cache = emptyMap<Int, Alumno>().toMutableMap()
    }

    fun get(id : Int) : Result<Alumno,AlumnoError> {
        return
    }
}