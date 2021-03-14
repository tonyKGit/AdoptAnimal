package com.example.adoptanimal.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adoptanimal.Network.IRetrofit
import com.example.adoptanimal.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val animalPostsAdapter = AnimalPostsAdapter()
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {
        //observe live data emitted by view model
        viewModel.getPosts().observe(this, Observer {
            animalPostsAdapter.submitList(it)
        })
    }

    private fun initializeList() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = animalPostsAdapter
    }
}