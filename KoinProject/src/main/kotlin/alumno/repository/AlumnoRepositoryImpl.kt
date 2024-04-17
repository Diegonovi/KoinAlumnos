package org.example.alumno.repository

import org.example.alumno.models.Alumno
import org.example.database.services.SqlDatabaseManager
import org.lighthousegames.logging.logging

class AlumnoRepositoryImpl (
    val dbManager: SqlDatabaseManager
) : AlumnoRepository {

    private val db = dbManager.databaseQueries

    private val log = logging()

    override fun getById(id: Int): Alumno? {
        log.debug { "Searching for Alumno with id: $id" }
        if (db.existsAlumno(id.toLong()).executeAsOne()){
            val alumno = db.getAlumno(id.toLong()).executeAsOne()
            val result = Alumno(
                nombre = alumno.name,
                calificacion = alumno.calificacion!!.toInt()
            )
            result.id = getLastId() + 1
            return result
        }
        return null
    }

    override fun createAlumno(alumno: Alumno): Alumno? {
        log.debug { "Creando $alumno en la base de datos" }
        if (!db.existsAlumno(db.getAlumno(alumno.id.toLong()).executeAsOne().id).executeAsOne()){
            db.createAlumno(
                alumno.id.toLong(),
                alumno.nombre,
                alumno.calificacion.toLong()
            )
            return alumno
        }else return null
    }

    override fun deleteAlumno(id : Int, physical : Boolean): Int? {
        log.debug { "Borrando alumno con id $id en la base de datos" }
        if (db.existsAlumno(id.toLong()).executeAsOne()){
            if (physical) db.deleteAlumnoPhysically(id.toLong())
            else db.deleteAlumnoLogically(id.toLong())
            return id
        }else return null
    }

    override fun updateAlumno(id : Int, alumno: Alumno): Alumno? {
        if (db.existsAlumno(id.toLong()).executeAsOne()){
            db.updateAlumno(alumno.nombre, alumno.calificacion.toLong(), id.toLong())
            alumno.id = id
            return alumno
        }else return null
    }

    fun getLastId() : Int{
        return db.getLargestId().executeAsOne().toInt()
    }
}