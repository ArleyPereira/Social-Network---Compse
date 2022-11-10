package com.example.socialnetwork.domain.usecase.api.follow

import com.example.socialnetwork.domain.repository.api.follow.FollowApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class FollowUserUseCase @Inject constructor(
    private val repository: FollowApiDataSource
) {

    suspend operator fun invoke(
        body: Map<String, String?>
    ): BaseResponse<Unit> {
        return repository.followUser(body)
    }

}