package com.example.appagenda.viewmodel

import com.example.appagenda.models.Person
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appagenda.config.Constants
import com.example.appagenda.config.PersonApp.Companion.db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormViewModel : ViewModel() {
    var id = MutableLiveData<Long>()
    var name = MutableLiveData<String>()
    var surname = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var telephone = MutableLiveData<String>()
    var op = Constants.OP_INSERT
    var succes = MutableLiveData<Boolean>()

    init {

    }

    fun savePerson() {
        if (infoValidation()){
            val person = Person(0, name.value!!, surname.value!!, email.value!!, telephone.value!!)
            when (op) {
                Constants.OP_INSERT -> {
                    viewModelScope.launch {
                        val result = withContext(Dispatchers.IO) {

                            db.personalDao().insertPersons(arrayListOf(person))
                        }
                        succes.value = result.isNotEmpty()
                    }

                }
                Constants.OP_UPDATE -> {
                    person.id = id.value!!
                    viewModelScope.launch {
                        val result = withContext(Dispatchers.IO) {
                            db.personalDao().update(person)
                        }
                        succes.value = (result > 0)
                    }
                }
            }
        } else{
            succes.value = false
        }
    }

    fun fillFields() {
        viewModelScope.launch {
            val person = withContext(Dispatchers.IO) {
                db.personalDao().getById(id.value!!)
            }
            name.value = person.name
            surname.value = person.surname
            email.value = person.email
            telephone.value = person.telephone
        }
    }

    private fun infoValidation():Boolean{
        return !(name.value.isNullOrEmpty() ||
                surname.value.isNullOrEmpty()
                )
    }

    fun deletePerson() {
        val person = Person(id.value!!,"","","","")
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                db.personalDao().delete(person)
            }
            succes.value = (result > 0)
        }
    }
}