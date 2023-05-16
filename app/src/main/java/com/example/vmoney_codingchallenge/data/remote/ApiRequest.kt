package com.example.vmoney_codingchallenge.data.remote

import com.example.vmoney_codingchallenge.data.model.people.PeopleItemModel
import com.example.vmoney_codingchallenge.data.model.rooms.RoomsItemModel
import retrofit2.http.GET

interface ApiRequest {

    @GET(ApiDetails.PEOPLE)
    suspend fun getPeople(): List<PeopleItemModel>

    @GET(ApiDetails.ROOMS)
    suspend fun getRooms(): List<RoomsItemModel>

}