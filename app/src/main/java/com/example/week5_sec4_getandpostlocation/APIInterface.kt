package com.example.week5_sec4_getandpostlocation

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @GET("/test/")
    fun getLocation(): Call<ArrayList<Name_LocationItem?>?>

    @POST("/test/")
    fun postNameLocation(@Body userData: Name_LocationItem): Call<Name_LocationItem>

}