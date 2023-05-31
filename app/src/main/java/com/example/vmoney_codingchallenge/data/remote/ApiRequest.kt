package com.example.vmoney_codingchallenge.data.remote

import com.example.vmoney_codingchallenge.data.model.people.PeopleItemModel
import com.example.vmoney_codingchallenge.data.model.rooms.RoomsItemModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {

    @GET(ApiDetails.PEOPLE)
    suspend fun getPeople(): List<PeopleItemModel>

    @GET(ApiDetails.PEOPLE_BY_ID)
    suspend fun getPeopleById(@Path("id") id: Int): PeopleItemModel

    @GET(ApiDetails.ROOMS)
    suspend fun getRooms(): List<RoomsItemModel>

}