package com.example.socialnetwork.data.repository.api.user.profile

import com.example.socialnetwork.data.api.ServiceAPI
import com.example.socialnetwork.data.model.UserDto
import com.example.socialnetwork.domain.repository.api.user.profile.ProfileApiDataSource
import com.example.socialnetwork.util.BaseResponse
import javax.inject.Inject

class ProfileApiDataSourceImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : ProfileApiDataSource {

    /**
     * @return Retorna um objeto User com todas as informações do usuário
     * @param [userId] Id do usuário a ser retornado
     * @author Arley Santana
     */
    override suspend fun getProfile(userId: Long): BaseResponse<UserDto> {
        return serviceAPI.getUserProfile(userId)
    }

    /**
     * Chamada utilizada para enviar o SMS para o número informado no body
     * @return Retorna o campo error ( sucess ou false/null )
     * @author Arley Santana
     */
    override suspend fun phoneUpdate(body: Map<String, String>): BaseResponse<Unit> {
        return serviceAPI.phoneUpdate(body)
    }

    /**
     * Chamada utilizada para enviar o código para o e-mail informado no body
     * @return Retorna o campo error ( sucess ou false/null )
     * @author Arley Santana
     */
    override suspend fun emailUpdate(body: Map<String, String>): BaseResponse<Unit> {
        return serviceAPI.emailUpdate(body)
    }

    /**
     * Chamada utilizada para confirmar o código SMS enviado para o telefone do usuário
     * @return Retorna o campo error ( sucess ou false/null )
     * @author Arley Santana
     */
    override suspend fun phoneConfirmUpdate(body: Map<String, String>): BaseResponse<Unit> {
        return serviceAPI.phoneConfirmUpdate(body)
    }

    /**
     * Chamada utilizada para confirmar o código enviado via e-mail para o e-mail do usuário
     * @return Retorna o campo error ( sucess ou false/null )
     * @author Arley Santana
     */
    override suspend fun emailConfirmUpdate(body: Map<String, String?>): BaseResponse<Unit> {
        return serviceAPI.emailConfirmUpdate(body)
    }

}