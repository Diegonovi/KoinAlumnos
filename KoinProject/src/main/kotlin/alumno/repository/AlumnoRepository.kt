package org.example.alumno.repository

import org.example.alumno.models.Alumno

interface AlumnoRepository {
    fun getById(id : Int) : Alumno?
    fun createAlumno(alumno: Alumno) : Alumno?
    fun deleteAlumno(id : Int, physical : Boolean) : Int?
    fun updateAlumno(id : Int, alumno: Alumno) : Alumno?
}