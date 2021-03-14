package com.example.adoptanimal.networkResponse

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.adoptanimal.Network.IRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AnimalDataSource(
    private val scope: CoroutineScope): PageKeyedDataSource<String, AnimalInfo>() {

    private val apiService = IRetrofit.mRetrofit()

    private var currentPage = 1
    private var nextPage = 0
    private var lastPage = 0

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, AnimalInfo>) {
        scope.launch {
            try {
                val response = apiService.getAnimalRecognitionInfo(
                    "IRKNR8B0NKGMN7DK4DQE2PC0H18FK0",
                    currentPage
                ).await()

                Log.e("1111111",response.animalInfos[0].animalId.toString())

                if (response.isNext) {
                    nextPage = currentPage + 1
                }

                if( currentPage > 0){
                    lastPage = currentPage - 1
                }

                callback.onResult(response.animalInfos,lastPage.toString(),nextPage.toString())

//
//                when{
//                    response.isSuccessful -> {
//                        val listing = response.body()?.data
//                        val redditPosts = listing?.children?.map { it.data }
//                        callback.onResult(redditPosts ?: listOf(), listing?.before, listing?.after)
//                    }
//                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, AnimalInfo>) {
        scope.launch {
            try {
//                val response =
//                    apiService.fetchPosts(loadSize = params.requestedLoadSize, after = params.key)

                val response = apiService.getAnimalRecognitionInfo(
                    "IRKNR8B0NKGMN7DK4DQE2PC0H18FK0",
                    nextPage
                ).await()

                currentPage = params.key.toInt()

                if (response.isNext) {
                    nextPage = currentPage + 1
                }

                if( currentPage > 0){
                    lastPage = currentPage - 1
                }

                callback.onResult(response.animalInfos,nextPage.toString())
//                when{
//                    response.isSuccessful -> {
//                        val listing = response.body()?.data
//                        val items = listing?.children?.map { it.data }
//                        callback.onResult(items ?: listOf(), listing?.after)
//                    }
//                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, AnimalInfo>) {
        scope.launch {
            try {
                val response = apiService.getAnimalRecognitionInfo(
                    "IRKNR8B0NKGMN7DK4DQE2PC0H18FK0",
                    lastPage
                ).await()

                currentPage = params.key.toInt()

                if (response.isNext) {
                    nextPage = currentPage + 1
                }

                if( currentPage > 0){
                    lastPage = currentPage - 1
                }

                callback.onResult(response.animalInfos,nextPage.toString())
//                val response =
//                    apiService.fetchPosts(loadSize = params.requestedLoadSize, before = params.key)
//                when{
//                    response.isSuccessful -> {
//                        val listing = response.body()?.data
//                        val items = listing?.children?.map { it.data }
//                        callback.onResult(items ?: listOf(), listing?.after)
//                    }
//                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}