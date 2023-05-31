package com.example.vmoney_codingchallenge.ui.screens.people.details

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vmoney_codingchallenge.data.model.people.PeopleItemModel
import com.example.vmoney_codingchallenge.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    private val _personDetails = MutableStateFlow(PeopleItemModel())
    val personDetails: StateFlow<PeopleItemModel> = _personDetails

    fun getPeopleById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPeopleById(id)
            _personDetails.value = response
        }
    }
}