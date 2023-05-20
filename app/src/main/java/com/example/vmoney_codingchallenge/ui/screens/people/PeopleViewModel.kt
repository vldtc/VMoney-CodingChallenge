package com.example.vmoney_codingchallenge.ui.screens.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vmoney_codingchallenge.data.model.people.PeopleItemModel
import com.example.vmoney_codingchallenge.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    val repository: Repository
): ViewModel(){

    private val _people = MutableStateFlow<List<PeopleItemModel>>(emptyList())
    val people: StateFlow<List<PeopleItemModel>> = _people



    fun getPeopleData(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable){
                val response = repository.getPeople()
                _people.value = response
            }
        }
    }
}