package com.example.adoptanimal.networkResponse
import com.google.gson.annotations.SerializedName

data class AnimalInfoList(
    @SerializedName("Data")
    val animalInfos: List<AnimalInfo>,
    @SerializedName("NEXT")
    val isNext: Boolean,
    @SerializedName("RS")
    val result: String
)