package com.example.vmoney_codingchallenge.domain.di

import com.example.vmoney_codingchallenge.data.remote.ApiDetails
import com.example.vmoney_codingchallenge.data.remote.ApiRequest
import com.example.vmoney_codingchallenge.data.repository.RepoImplemented
import com.example.vmoney_codingchallenge.data.repository.Repository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    //Creating the OKHttpClient for interception
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    //Creating the Retrofit client
    @Provides
    @Singleton
    fun provideRetrofit(
        provideOkHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(provideOkHttpClient)
            .build()
    }

    //Using Retrofit to get the API call
    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): ApiRequest {
        return retrofit.create(ApiRequest::class.java)
    }

    //Merging the repository class together
    @Provides
    fun provideRepository(
        apiRequest: ApiRequest
    ): Repository {
        return RepoImplemented(apiRequest)
    }

}
