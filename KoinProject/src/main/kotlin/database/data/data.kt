package org.example.database.data

import org.example.alumno.models.Alumno
import org.example.database.services.SqlDatabaseManager

val sampleStudents = listOf<Alumno>(
    Alumno(id = 0, nombre = "Aurora", calificacion = 9),
    Alumno(id=1, nombre="Bianca", calificacion=8),
    Alumno(id=2, nombre="Caleb", calificacion=7),
    Alumno(id=3, nombre="Diana", calificacion=6),
    Alumno(id=4, nombre="Ethan", calificacion=5),
    Alumno(id=5, nombre="Fiona", calificacion=9),
    Alumno(id=6, nombre="Gavin", calificacion=8),
    Alumno(id=7, nombre="Hannah", calificacion=7),
    Alumno(id=8, nombre="Isaac", calificacion=6),
    Alumno(id=9, nombre="Jasmine", calificacion=5),
    Alumno(id=10, nombre="Kieran", calificacion=9)


)