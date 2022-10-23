package com.example.socialnetwork.presenter.bottombar.profile.state

sealed class ProfileState {

    object NavFollowingScreen: ProfileState()
    object NavPostsScreen: ProfileState()
    object NavFriendsScreen: ProfileState()

}
