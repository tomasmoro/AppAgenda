package com.example.appagenda.DAO

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.appagenda.models.Person

@Dao
interface ScheduleDAO {
    @Query("SELECT * FROM Person")
    suspend fun getAll():List<Person>

    @Query("SELECT * FROM Person WHERE person_id = :id")
    suspend fun getById(id: Long):Person

    @Query("SELECT * FROM Person WHERE person_name LIKE '%'|| :name || '%' OR  person_surname LIKE '%'|| :name || '%'")
    suspend fun getByName(name: String):List<Person>

    @Insert
    suspend fun insertPersons(persons: List<Person>): List<Long>

    @Update
    suspend fun update(person: Person):Int

    @Delete
    suspend fun delete(person: Person):Int
}