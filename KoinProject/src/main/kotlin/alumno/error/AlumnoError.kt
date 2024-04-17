package org.example.alumno.error

sealed class AlumnoError(msg : String) {
    class AlumnoNoEncontrado(msg: String) : AlumnoError(msg)
    class AlumnoYaExiste(msg: String) : AlumnoError(msg)
}