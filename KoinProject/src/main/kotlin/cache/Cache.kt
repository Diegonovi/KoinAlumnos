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
        if (cache[alumno.id] != null){
            if (cache.size >= config.cacheSize){
                log.debug { "Borrando el alumno con id ${alumno.id} de la cache" }
                cache.remove(cache.keys.first())
            }
            cache[alumno.id] = alumno
            log.debug { "Se ha añadido el alumno ${alumno.nombre} a la cache" }
            return Ok(alumno)
        }else return Err(AlumnoError.AlumnoYaExiste("El alumno con id ${alumno.id} ya existe en la cache"))
    }

    fun delete(alumno: Alumno) : Result<Alumno,AlumnoError>{
        if (cache[alumno.id] != null){
            cache.remove(alumno.id)
            return Ok(alumno)
        }
        else return Err(AlumnoError.AlumnoNoEncontrado("No se pudo encontrar el alumno con id ${alumno.id} en la cache"))
    }

    fun clear(){
        cache = emptyMap<Int, Alumno>().toMutableMap()
    }

    fun get(id : Int) : Result<Alumno,AlumnoError> {
        if (cache[id] != null) return Ok(cache[id]!!)
        else return Err(AlumnoError.AlumnoNoEncontrado("No se pudo encontrar el alumno con id ${id} en la cache"))
    }
}