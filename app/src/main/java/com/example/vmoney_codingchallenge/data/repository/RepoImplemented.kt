package com.example.vmoney_codingchallenge.data.repository

import com.example.vmoney_codingchallenge.data.model.people.PeopleItemModel
import com.example.vmoney_codingchallenge.data.model.rooms.RoomsItemModel
import com.example.vmoney_codingchallenge.data.remote.ApiRequest
import javax.inject.Inject

class RepoImplemented @Inject constructor(
    val apiRequest: ApiRequest
): Repository{
    override suspend fun getPeople(): List<PeopleItemModel> = apiRequest.getPeople()

    override suspend fun getPeopleById(id: Int): PeopleItemModel = apiRequest.getPeopleById(id)

    override suspend fun getRooms(): List<RoomsItemModel> = apiRequest.getRooms()

}