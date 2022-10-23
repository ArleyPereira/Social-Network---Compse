package com.example.socialnetwork.presenter.bottombar.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.R
import com.example.socialnetwork.data.db.entity.toDomain
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.data.model.toDomain
import com.example.socialnetwork.domain.model.User
import com.example.socialnetwork.domain.usecase.api.follow.FollowUserUseCase
import com.example.socialnetwork.domain.usecase.api.user.GetUsersUseCase
import com.example.socialnetwork.domain.usecase.room.user.GetUserDbUseCase
import com.example.socialnetwork.presenter.auth.state.TextFieldState
import com.example.socialnetwork.presenter.bottombar.search.event.SearchEvent
import com.example.socialnetwork.presenter.bottombar.search.state.SearchState
import com.example.socialnetwork.util.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val followUserUseCase: FollowUserUseCase,
    private val getUserDbUseCase: GetUserDbUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private val _searchField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_search_view_friends_screen
        )
    )
    val searchField: State<TextFieldState> = _searchField

    init {
        getUserLocal()
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.EnteredSearch -> {
                _searchField.value = searchField.value.copy(text = event.value)
            }
            SearchEvent.ClearTextSearch -> {
                _searchField.value = searchField.value.copy(text = "")
            }
            is SearchEvent.FollowUser -> {
                followUser(userId = event.userId, followedId = event.followedId)
            }
            SearchEvent.ClickedButtonSheetError -> {
                _state.value = state.value.copy(error = null)
            }
        }
    }

    private fun getUsers(userLocal: User) = viewModelScope.launch {
        try {
            _state.value = SearchState(isLoading = true)

            val result = getUsersUseCase.invoke()

            result.data?.let { user ->
                _state.value = SearchState(
                    users = user.map { it.toDomain() },
                    userLocal = userLocal
                )
            }

        } catch (ex: HttpException) {
            val errorApi = ex.getErrorResponse<ErrorAPI>()
            errorApi?.let {
                _state.value = SearchState(error = it)
            }
        } catch (ex: Exception) {
            _state.value = SearchState(
                error = ErrorAPI(
                    error = true,
                    message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                )
            )
        }
    }

    private fun getUserLocal() = viewModelScope.launch {
        try {
            val user = getUserDbUseCase.invoke()

            getUsers(user.toDomain())
        } catch (e: Exception) {
            _state.value = SearchState(
                error = ErrorAPI(
                    error = true,
                    message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                )
            )
        }
    }

    private fun followUser(
        userId: Long,
        followedId: Long
    ) = viewModelScope.launch {
        try {

            Log.i("INFOTESTE", "followUser: $userId")

            followUserUseCase.invoke(
                mapOf(
                    "user_id" to userId.toString(),
                    "followed_id" to followedId.toString()
                )
            )

        } catch (ex: HttpException) {
            val errorApi = ex.getErrorResponse<ErrorAPI>()
            errorApi?.let {
                _state.value = state.value.copy(
                    error = it
                )
            }
        } catch (ex: Exception) {
            _state.value = SearchState(
                error = ErrorAPI(
                    error = true,
                    message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                )
            )
        }
    }

}