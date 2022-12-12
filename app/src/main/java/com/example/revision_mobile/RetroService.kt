package com.example.revision_mobile

import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    @GET("employees")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getEmployeeList(): Call<EmployeeList>

    @GET("employee/{id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getEmployee(@Path("id") id: String): Call<EmployeeResponse>

    @POST("create")
    @Headers("Accept:application/json", "Content-Type:application/json"
    )
    fun createEmployee(@Body params: Employee): Call<EmployeeResponse>

    @PUT("update/{id}")
    @Headers("Accept:application/json", "Content-Type:application/json"
    )
    fun updateEmployee(@Path("id") id: String, @Body params: Employee): Call<EmployeeResponse>

    @DELETE("delete/{id}")
    @Headers("Accept:application/json", "Content-Type:application/json"
    )
    fun deleteEmployee(@Path("id") id: String): Call<EmployeeResponse>

}