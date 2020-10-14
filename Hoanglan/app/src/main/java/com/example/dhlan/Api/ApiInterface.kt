package com.example.dhlan.Api

import com.example.dhlan.Model.Comment.HomeModelbody
import com.example.dhlan.Model.Company.Objectdata
import com.example.dhlan.Model.Comment.ObjectHome
import com.example.dhlan.Model.Loadcomment.Loadmodelbody
import com.example.dhlan.Model.Loadcomment.ObjectLoad
import com.example.dhlan.Model.Login.LoginModelbody
import com.example.dhlan.Model.Login.ObjectLogin
import com.example.dhlan.Model.RefreshToken.ObjectToken
import com.example.dhlan.Model.RefreshToken.Refreshtokenbody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
interface ApiInterface {
    // gọi api company
    @Headers("Content-type: application/json")
    @POST("company/get-all")
    fun Company(): Call<Objectdata>

    //post apicomment
    @Headers("Content-Type: application/json")
    @POST("mydocuments/get-all")
    fun home(@Header("Authorization") token: String, @Body userhome: HomeModelbody): Call<ObjectHome>

    @Headers("Content-Type: application/json")
    @POST("mydocuments/get-all")
    fun Load(@Header("Authorization") token: String, @Body userhome: Loadmodelbody): Call<ObjectLoad>
    // post api login
    @Headers("Content-Type: application/json")
    @POST("authenticate")
    fun addUser(@Body userData: LoginModelbody): Call<ObjectLogin>

    //refreshtoken
    @Headers("Content-Type: application/json")
    @POST("refresh-token")
    fun refreshToken(@Body usertoken: Refreshtokenbody) :Call<ObjectToken>

}