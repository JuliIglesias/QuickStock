package com.example.quickStock.apiManager

import android.content.Context
import android.widget.Toast
import com.example.quickStock.R
import com.example.quickStock.api.Meal
import com.example.quickStock.api.RecipeType
import com.example.quickStock.apiManager.responses.MealDetail
import com.example.quickStock.apiManager.responses.MealDetailResponse
import com.example.quickStock.apiManager.responses.MealResponse
import com.example.quickStock.apiManager.responses.RecipeTypesResponse
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() {

    fun getRecipeTypes(
        context: Context,
        onSuccess: (List<RecipeType>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit)
    {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.recipes_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)

        val call: Call<RecipeTypesResponse> = service.getRecipeTypes()

        call.enqueue(object : Callback<RecipeTypesResponse> {
            override fun onResponse(response: Response<RecipeTypesResponse>?, retrofit: Retrofit?) {
                loadingFinished()
                if(response?.isSuccess == true) {
                    val recipeTypes: List<RecipeType> = response.body()?.categories ?: emptyList()
                    onSuccess(recipeTypes)
                } else {
                    onFailure(Exception(context.getString(R.string.bad_request)))
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context,
                    context.getString(R.string.can_t_get_recipe_types), Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }

    fun getMealsByCategory(
        context: Context,
        category: String,
        onSuccess: (List<Meal>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.recipes_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)
        val call: Call<MealResponse> = service.getMealsByCategory(category)

        call.enqueue(object : Callback<MealResponse> {
            override fun onResponse(response: Response<MealResponse>?, retrofit: Retrofit?) {
                loadingFinished()
                if(response?.isSuccess == true) {
                    val meals: List<Meal> = response.body()?.meals ?: emptyList()
                    onSuccess(meals)
                } else {
                    onFailure(Exception(context.getString(R.string.bad_request)))
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context,
                    context.getString(R.string.can_t_get_meals_for, category), Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }


    fun getMealById(
        context: Context,
        mealId: String,
        onSuccess: (MealDetail?) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.recipes_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)
        val call: Call<MealDetailResponse> = service.getMealById(mealId)

        call.enqueue(object : Callback<MealDetailResponse> {
            override fun onResponse(response: Response<MealDetailResponse>?, retrofit: Retrofit?) {
                loadingFinished()
                if(response?.isSuccess == true) {
                    val mealDetail = response.body()?.meals?.firstOrNull()
                    onSuccess(mealDetail)
                } else {
                    onFailure(Exception(context.getString(R.string.bad_request)))
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context,
                    context.getString(R.string.can_t_get_meal_details_for_id, mealId), Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}