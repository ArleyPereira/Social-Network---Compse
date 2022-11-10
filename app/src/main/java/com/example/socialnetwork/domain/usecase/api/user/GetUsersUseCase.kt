package com.example.socialnetwork.domain.usecase.api.user

import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.domain.repository.api.user.UserApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserApiDataSource
) {

    suspend operator fun invoke(): BaseResponse<List<UserDto>> {
        return repository.getUsers()
    }

}