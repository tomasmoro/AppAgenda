package com.example.appagenda.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appagenda.DAO.ScheduleDAO
import com.example.appagenda.models.Person

@Database(
    entities = [Person::class],
    version = 1
)
abstract class ScheduleDatabase: RoomDatabase() {
    abstract fun personalDao():ScheduleDAO
}