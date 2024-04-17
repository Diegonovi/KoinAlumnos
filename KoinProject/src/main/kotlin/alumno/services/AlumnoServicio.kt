package org.example.alumno.services

import org.example.alumno.repository.AlumnoRepository
import org.example.cache.Cache

class AlumnoServicio(
    val repository: AlumnoRepository,
    val cache: Cache
) {
    fun getById(id : Int){
        repository.getById(id).let {
            cache.c
        }
    }
}