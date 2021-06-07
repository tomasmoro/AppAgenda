package com.example.appagenda.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appagenda.config.PersonApp.Companion.db
import com.example.appagenda.models.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleViewModel : ViewModel() {
    val personList = MutableLiveData<List<Person>>()
    var searchParam = MutableLiveData<String>()

    fun init() {
        viewModelScope.launch {
            personList.value = withContext(Dispatchers.IO) {
                db.personalDao().getAll()
            }
        }
    }

    fun searchPerson() {
        viewModelScope.launch {
            personList.value = withContext(Dispatchers.IO) {
                db.personalDao().getByName(searchParam.value!!)
            }
        }
    }
}