package com.htk.assginment2.api

import com.htk.assginment2.data.request.RequestLoginData
import com.htk.assginment2.data.request.RequestSignUpData
import com.htk.assginment2.data.response.ResponseLoginData
import com.htk.assginment2.data.response.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("/login/signin")
    fun postLogin(
        @Body body: RequestLoginData
    ) : Call<ResponseLoginData>

    @POST("/login/signup")
    fun postSignUp(
        @Body body: RequestSignUpData
    ): Call<ResponseSignUpData>
}
