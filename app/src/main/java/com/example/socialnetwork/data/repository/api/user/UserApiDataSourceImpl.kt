package com.example.socialnetwork.data.repository.api.user

import com.example.socialnetwork.data.api.ServiceAPI
import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.domain.repository.api.user.UserApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class UserApiDataSourceImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : UserApiDataSource {

    /**
     * @return Retorna um objeto User com todas as informações do usuário
     * @param [userId] Id do usuário a ser retornado
     * @author Arley Santana
     */
    override suspend fun getUserById(userId: Long): BaseResponse<UserDto> {
        return serviceAPI.getUserById(userId)
    }

    /**
     * @return Retorna todos os usuários cadastrados no app
     * @author Arley Santana
     */
    override suspend fun getUsers(): BaseResponse<List<UserDto>> {
        return serviceAPI.getUsers()
    }

}