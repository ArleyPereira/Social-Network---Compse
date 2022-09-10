package com.example.socialnetwork.presenter.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.domain.usecase.api.auth.LoginUsecase
import com.example.socialnetwork.util.StateView
import com.example.socialnetwork.util.getErrorResponse
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class LoginViewModel(
    private val loginUsecase: LoginUsecase
): ViewModel() {

    fun loginApp(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val body = mapOf(
                "email" to email,
                "password" to password
            )

            val result = loginUsecase.invoke(body)

            emit(StateView.Sucess(data = result.data))

        } catch (exception: HttpException) {
            val errorApi = exception.getErrorResponse<ErrorAPI>()

            emit(StateView.Error(message = errorApi?.message, action = errorApi?.action))
        } catch (exception: Exception) {
            emit(StateView.Error(null))
        }
    }

}