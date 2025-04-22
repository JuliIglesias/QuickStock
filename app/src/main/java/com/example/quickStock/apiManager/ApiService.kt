package com.example.quickStock.apiManager

import com.example.quickStock.apiManager.responses.MealDetailResponse
import com.example.quickStock.apiManager.responses.MealResponse
import com.example.quickStock.apiManager.responses.RecipeTypesResponse
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query

interface ApiService {

    @GET("categories.php")
    fun getRecipeTypes(): Call<RecipeTypesResponse>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") category: String): Call<MealResponse>

    @GET("lookup.php")
    fun getMealById(@Query("i") id: String): Call<MealDetailResponse>

}