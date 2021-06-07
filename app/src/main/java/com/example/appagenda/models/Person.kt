package com.example.appagenda.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="person_id")
    var id: Long,
    @ColumnInfo(name="person_name")
    val name: String,
    @ColumnInfo(name="person_surname")
    val surname: String,
    @ColumnInfo(name="person_email")
    val email: String,
    @ColumnInfo(name="person_tel")
    val telephone: String
)