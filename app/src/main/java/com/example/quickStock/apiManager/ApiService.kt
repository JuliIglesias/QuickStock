package com.example.quickStock.apiManager

import com.example.quickStock.api.RecipeType
import retrofit.Call
import retrofit.http.GET

interface ApiService {

    @GET("categories.php")
    fun getRecipeTypes(): Call<RecipeTypesResponse>
}