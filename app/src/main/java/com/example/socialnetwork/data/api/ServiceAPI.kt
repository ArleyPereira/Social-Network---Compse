package com.example.socialnetwork.data.api

import com.example.socialnetwork.data.model.Post
import com.example.socialnetwork.data.model.User
import com.example.socialnetwork.util.BaseResponse
import retrofit2.http.*

interface ServiceAPI {

    /* ----------------- AUTHENTICATION ----------------- */

    @POST("users")
    suspend fun register(
        @Body user: Map<String, String>
    ): BaseResponse<User>

    @POST("auth/login")
    suspend fun login(
        @Body body: Map<String, String>
    ): BaseResponse<User>

    @PUT("confirmations")
    suspend fun emailConfirm(
        @Body body: Map<String, String>,
    ): BaseResponse<Unit>

    @POST("auth/recover")
    suspend fun recover(
        @Body code: String,
        @Header("token") token: String
    ): BaseResponse<Unit>

    @POST("auth/logout")
    suspend fun logout(): BaseResponse<User>

    /* ----------------- PROFILE ----------------- */

    @GET("users")
    suspend fun getUserProfile(
        @Query("user_id") userId: Long,
    ): BaseResponse<User>

    @POST("confirmations")
    suspend fun phoneUpdate(
        @Body body: Map<String, String>,
    ): BaseResponse<Unit>

    @POST("confirmations")
    suspend fun emailUpdate(
        @Body body: Map<String, String>,
    ): BaseResponse<Unit>

    @PUT("confirmations")
    suspend fun phoneConfirmUpdate(
        @Body body: Map<String, String>,
    ): BaseResponse<Unit>

    @PUT("confirmations")
    suspend fun emailConfirmUpdate(
        @Body body: Map<String, String>
    ): BaseResponse<Unit>

    /* ----------------- POST ----------------- */

    @GET("posts")
    suspend fun getPosts(): BaseResponse<List<Post>>

}