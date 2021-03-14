package com.example.adoptanimal.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.adoptanimal.networkResponse.AnimalDataSource
import com.example.adoptanimal.networkResponse.AnimalInfo

class MainViewModel : ViewModel() {
    private var postsLiveData: LiveData<PagedList<AnimalInfo>>

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .build()

        postsLiveData = initializedPagedListBuilder(config).build()
    }

    fun getPosts(): LiveData<PagedList<AnimalInfo>> = postsLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<String,AnimalInfo>{
        val dataSourceFactory = object : DataSource.Factory<String, AnimalInfo>(){
            override fun create(): DataSource<String, AnimalInfo> {
                return AnimalDataSource(viewModelScope)
            }
        }
        return LivePagedListBuilder<String, AnimalInfo>(dataSourceFactory, config)
    }
}