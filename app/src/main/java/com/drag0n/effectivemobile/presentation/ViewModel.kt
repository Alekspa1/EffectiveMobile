package com.drag0n.effectivemobile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drag0n.effectivemobile.data.room.DataBase
import com.drag0n.effectivemobile.domain.useCase.GetJsonFileUseCase
import com.drag0n.effectivemobile.model.Offer
import com.drag0n.effectivemobile.model.Vacancy
import com.drag0n.effectivemobile.model.VacancyRoom
import com.drag0n.effectivemobile.presentation.adapters.VacanciesAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val getJson: GetJsonFileUseCase,
    private val db: DataBase
) : ViewModel() {


    private val _listOffers = MutableLiveData<List<Offer>>()
    val listOffers: LiveData<List<Offer>> = _listOffers

    private val _listVacascies = MutableLiveData<List<Vacancy>>()
    val listVacascies: LiveData<List<Vacancy>> = _listVacascies

    private val _listIsFavorite = MutableLiveData<List<VacancyRoom>>()
    val listIsFavorite: LiveData<List<VacancyRoom>> = _listIsFavorite



    fun insertVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            val vacancyRoom = VacancyRoom(vacancy.id)
            db.Dao().insertVacancies(vacancyRoom)
        }
    }

    fun deleteVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            val vacancyRoom = VacancyRoom(vacancy.id)
            db.Dao().deleteVacancies(vacancyRoom)
        }
    }

    fun getAll()  {
     viewModelScope.launch { _listIsFavorite.value = db.Dao().getAll() }
    }


    fun getOffers() {
        _listOffers.value = getJson.exum().offers
    }

    fun getVacancies() {
        _listVacascies.value = getJson.exum().vacancies
    }

    fun getCount() = getJson.exum().vacancies.size
}