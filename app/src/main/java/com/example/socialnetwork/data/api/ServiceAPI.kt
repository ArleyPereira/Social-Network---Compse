package com.example.socialnetwork.data.api

import com.example.socialnetwork.data.model.PostDto
import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.util.BaseResponse
import com.google.gson.JsonObject
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.http.*

interface ServiceAPI {

    /* ----------------- AUTHENTICATION ----------------- */

    @POST("users/store")
    suspend fun register(
        @Body user: Map<String, String>
    ): BaseResponse<UserDto>

    @POST("auth/login")
    suspend fun login(
        @Body body: Map<String, String>
    ): BaseResponse<UserDto>

    @PUT("confirmations/update")
    suspend fun confirmationAccount(
        @Body body: Map<String, String>
    ): BaseResponse<Unit>

    @POST("auth/recover")
    suspend fun recoverAccount(
        @Body code: String,
        @Header("token") token: String
    ): BaseResponse<Unit>

    @POST("auth/logout")
    suspend fun logout(): BaseResponse<UserDto>

    /* ----------------- PROFILE ----------------- */

    @GET("users")
    suspend fun getUserById(
        @Query("user_id") userId: Long,
    ): BaseResponse<UserDto>

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
        @Body body: Map<String, String?>
    ): BaseResponse<Unit>

    /* ----------------- POST ----------------- */

//    @MOCK(asset = "post_response.json", runDelay = true)
    @POST("posts/list")
    suspend fun getPosts(
        @Body body: JsonObject
    ): BaseResponse<List<PostDto>>

    /* ----------------- FRIENDS ----------------- */

    @GET("friends")
    suspend fun getFriends(): BaseResponse<List<UserDto>>

    /* ----------------- USERS ----------------- */

    @POST("users/list")
    suspend fun getUsers(): BaseResponse<List<UserDto>>

    /* ----------------- FOLLOWERS ----------------- */

    @POST("followers/store")
    suspend fun followUser(
        @Body body: Map<String, String?>
    ): BaseResponse<Unit>

}