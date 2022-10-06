package com.example.socialnetwork.presenter.bottombar.friends

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.auth.state.TextFieldState
import com.example.socialnetwork.presenter.bottombar.friends.event.FriendsEvent
import javax.inject.Inject

class FriendsViewModel @Inject constructor(

): ViewModel() {

    private val _searchField = mutableStateOf(
        TextFieldState(
            hint = R.string.text_hint_search_view_friends_screen
        )
    )
    val searchField: State<TextFieldState> = _searchField

    fun onEvent(event: FriendsEvent){
        when(event){
            is FriendsEvent.EnteredSearch -> {
                _searchField.value = searchField.value.copy(text = event.value)
            }
            FriendsEvent.ClearTextSearch -> {
                _searchField.value = searchField.value.copy(text = "")
            }
        }
    }

}