package com.example.socialnetwork.presenter.bottombar.feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetwork.data.model.ErrorAPI
import com.example.socialnetwork.data.model.toDomain
import com.example.socialnetwork.domain.usecase.api.post.GetPostUseCase
import com.example.socialnetwork.presenter.bottombar.feed.state.FeedState
import com.example.socialnetwork.util.Associations
import com.example.socialnetwork.util.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FeedState())
    val state: State<FeedState> = _state

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        try {
            _state.value = FeedState(isLoading = true)

            val body = Associations.generate(
                Associations.ASSOCIATION_TYPE_USER,
                Associations.ASSOCIATION_TYPE_PHOTOS,
            )

            val posts = getPostUseCase.invoke(body)

            _state.value = FeedState(
                posts = posts.data?.map { it.toDomain() } ?: emptyList()
            )
        } catch (ex: HttpException) {
            val errorApi = ex.getErrorResponse<ErrorAPI>()

            errorApi?.let {
                _state.value = FeedState(
                    error = it
                )
            }
        } catch (ex: Exception) {
            _state.value = FeedState(
                error = ErrorAPI(
                    error = true,
                    message = "Ocorreu um erro inesperado. Por favor, feche o aplicativo e abra novamente."
                )
            )
        }
    }

}