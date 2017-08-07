package com.app.petstore.service

import com.app.petstore.models.Pet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PetStoreClient {

    @GET("v2/pet/findByStatus")
    fun getPetListByStatus(@Query("status") status: String): Call<List<Pet>>

    @POST("v2/pet")
    fun postNewPet(@Body pet: Pet): Call<Pet>

}