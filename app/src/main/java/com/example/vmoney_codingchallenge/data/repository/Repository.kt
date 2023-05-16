package com.example.vmoney_codingchallenge.data.repository

import com.example.vmoney_codingchallenge.data.model.people.PeopleItemModel
import com.example.vmoney_codingchallenge.data.model.rooms.RoomsItemModel

interface Repository {

    suspend fun getPeople(): List<PeopleItemModel>

    suspend fun getRooms(): List<RoomsItemModel>

}