package com.example.socialnetwork.domain.usecase.api.auth

import com.example.socialnetwork.domain.repository.api.user.auth.AuthenticationApiDataSource
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthenticationApiDataSource
) {



}