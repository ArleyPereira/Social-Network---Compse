package com.example.socialnetwork.presenter.bottombar.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.R
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.data.model.toDomain
import com.example.socialnetwork.domain.usecase.api.user.GetUsersUseCase
import com.example.socialnetwork.presenter.auth.state.TextFieldState
import com.example.socialnetwork.presenter.bottombar.friends.event.FriendsEvent
import com.example.socialnetwork.presenter.bottombar.search.event.SearchUIEvent
import com.example.socialnetwork.util.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<SearchUIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _searchField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_search_view_friends_screen
        )
    )
    val searchField: State<TextFieldState> = _searchField

    init {
        getUsers()
    }

    fun onEvent(event: FriendsEvent) {
        when (event) {
            is FriendsEvent.EnteredSearch -> {
                _searchField.value = searchField.value.copy(text = event.value)
            }
            FriendsEvent.ClearTextSearch -> {
                _searchField.value = searchField.value.copy(text = "")
            }
        }
    }

    private fun getUsers() = viewModelScope.launch {
        try {
            _eventFlow.emit(SearchUIEvent.SearchLoading)

            val result = getUsersUseCase.invoke()

            result.data?.let { user ->
                _eventFlow.emit(SearchUIEvent.SearchSucess(user.map { it.toDomain() }))
            }

        } catch (ex: HttpException) {
            val errorApi = ex.getErrorResponse<ErrorAPI>()
            errorApi?.let {
                _eventFlow.emit(SearchUIEvent.SearchError(it))
            }
        } catch (ex: Exception) {
            _eventFlow.emit(
                SearchUIEvent.SearchError(
                    value = ErrorAPI(
                        error = true,
                        message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                    )
                )
            )
        }
    }

}