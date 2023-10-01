package com.example.filmshakerkotlin.core.adapter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubePlayerManager(
    private val youtubePlayerView: YouTubePlayerView,
    private val lifecycle: Lifecycle,
    private var videoID: String
) : AbstractYouTubePlayerListener(), LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    override fun onReady(youTubePlayer: YouTubePlayer) {
        youTubePlayer.loadVideo(videoID, 0f)
    }

    override fun onStateChange(
        youTubePlayer: YouTubePlayer,
        state: PlayerConstants.PlayerState
    ) {
        super.onStateChange(youTubePlayer, state)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun releasePlayer() {
        youtubePlayerView.release()
        lifecycle.removeObserver(this)
    }

    fun initPlayer() {
        youtubePlayerView.addYouTubePlayerListener(this)
    }
}
