package com.example.quickStock.apiManager

import android.content.Context
import android.widget.Toast
import com.example.quickStock.R
import com.example.quickStock.api.RecipeType
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() {

    fun getRecipeTypes(context: Context, onSuccess: (List<RecipeType>) -> Unit, onFail: () -> Unit, loadingFinished: () -> Unit) {
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
                    onFailure(Exception("Bad request"))
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Can't get recipe types", Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}