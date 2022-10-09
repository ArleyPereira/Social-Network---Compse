package com.example.socialnetwork.domain.usecase.api.friend

import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.data.repository.api.friend.FriendApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class GetFriendsUseCase @Inject constructor(
    private val repository: FriendApiDataSource
) {

    suspend operator fun invoke(): BaseResponse<List<UserDto>> {
        return repository.getFriends()
    }

}