package com.example.adoptanimal.networkResponse
import com.google.gson.annotations.SerializedName

data class TestResponse(
    @SerializedName("Data")
    var animalInfos: List<AnimalInfo>,
    @SerializedName("NEXT")
    val isNext: Boolean,
    @SerializedName("RS")
    val result: String
)