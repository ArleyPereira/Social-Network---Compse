package com.example.socialnetwork.presenter.bottombar.feed.event

sealed class FeedEvent {

    /**
     * Tela para exibição dos usuários que curtiram o post
     * @author Arley Santana
     */
    data class NavPostLikeDetailsScreen(val postId: Long) : FeedEvent()

    /**
     * Tela para exibição dos comentaram do post
     * @author Arley Santana
     */
    data class NavPostCommentScreen(val postId: Long) : FeedEvent()

    /**
     * Tela para exibição do perfil que fez o post
     * @author Arley Santana
     */
    data class NavProfileUserScreen(val userId: Long) : FeedEvent()

}
