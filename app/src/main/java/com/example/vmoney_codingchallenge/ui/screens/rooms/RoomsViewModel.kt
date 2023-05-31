package com.example.vmoney_codingchallenge.ui.screens.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vmoney_codingchallenge.data.model.rooms.RoomsItemModel
import com.example.vmoney_codingchallenge.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    private val _rooms = MutableStateFlow<List<RoomsItemModel>>(emptyList())
    val rooms : StateFlow<List<RoomsItemModel>> = _rooms

    fun getRooms(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRooms()
            _rooms.value = response
        }
    }

}