package com.app.petstore.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient



object Clients {
    val petStoreClient = ServiceGenerator.createService(PetStoreClient::class.java)
}