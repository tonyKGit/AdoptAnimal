package com.example.adoptanimal.Network

import com.example.adoptanimal.networkResponse.AnimalInfo
import com.example.adoptanimal.networkResponse.AnimalInfoList
import com.example.adoptanimal.networkResponse.TestResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface IRetrofit {
    @GET("AnimalRecognition")
    fun getAnimalRecognitionInfo(
        @Header("api_key") apiKey: String,
        @Query("Page") page: Int
    ): Deferred<AnimalInfoList>

    //@Headers("api_key:" + "IRKNR8B0NKGMN7DK4DQE2PC0H18FK0")
    @GET("AnimalRecognition")
    fun getAnimalRecognitionInfo2(
//        @Header("api_key") apiKey: String,
        @Query("Page") page: Int
    ): Deferred<TestResponse>

    companion object{
        fun mRetrofit(): IRetrofit{
//            val okHttpBuilder = super.getOkHttpClientBuilder()
//            okHttpBuilder.addInterceptor { chain ->
//                val request = chain.request().newBuilder()
//                val originalHttpUrl = chain.request().url
//                val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", "your api key value").build()
//                request.url(url)
//                val response = chain.proceed(request.build())
//                return@addInterceptor response
//            }

            val requestInterceptor = Interceptor{
                    chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("api_key","IRKNR8B0NKGMN7DK4DQE2PC0H18FK0")
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(32, 5, TimeUnit.MINUTES))
                .build()

            return Retrofit.Builder()
                //.client(okHttpClient)
                .baseUrl("https://agridata.coa.gov.tw/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(IRetrofit::class.java)
        }
    }
}