package com.example.appagenda.config

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class PersonApp: Application() {
    companion object{
        lateinit var db: ScheduleDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            ScheduleDatabase::class.java,
            "schedule_db"
        ).build()
    }
}