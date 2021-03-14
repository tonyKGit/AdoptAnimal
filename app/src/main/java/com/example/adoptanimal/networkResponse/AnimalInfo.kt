package com.example.adoptanimal.networkResponse

import com.google.gson.annotations.SerializedName

data class AnimalInfo(
    @SerializedName("animal_id")
    val animalId: Int
)