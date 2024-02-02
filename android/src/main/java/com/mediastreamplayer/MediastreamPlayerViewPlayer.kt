package com.mediastreamplayer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.timerTask
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayer
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayerConfig
import androidx.media3.ui.PlayerView

class MediastreamPlayerViewPlayer : RelativeLayout {
  companion object {
    const val EVENT_NAME = "topChange"
    const val TAG = "MediastreamPlayerViewPlayer"
  }

  private var mReactContext: ReactContext? = null
  private lateinit var playerView: PlayerView
  private lateinit var container: FrameLayout
  private lateinit var mdstrmPlayer: MediastreamPlayer

  constructor(context: ReactContext) : super(context) {
    mReactContext = context
    inflate(context, R.layout.mediastream_player_view_player, this)
  }

  fun configure(config: ReadableMap) {
    try {
      Log.d(TAG, "configure: $config")
      val activity: Activity = mReactContext!!.currentActivity!!
      val playerConfig = MediastreamPlayerConfig()

      playerConfig.id = if (config.hasKey("id")) config.getString("id") else null
      playerConfig.accountID = if (config.hasKey("accountID")) config.getString("accountID") else null
      playerConfig.autoplay = if (config.hasKey("autoplay")) config.getBoolean("autoplay") else true

      if (config.hasKey("live")) {
        val live = config.getBoolean("live")
        playerConfig.type = if (live) MediastreamPlayerConfig.VideoTypes.LIVE else MediastreamPlayerConfig.VideoTypes.VOD
      }

      playerView = findViewById(R.id.player_view)
      container = findViewById(R.id.main_media_frame)
      mdstrmPlayer = MediastreamPlayer(activity, playerConfig, container, playerView)
    } catch (e: Exception) {
      Log.e(TAG, "Error on setup config: $e")
    }
  }
}
