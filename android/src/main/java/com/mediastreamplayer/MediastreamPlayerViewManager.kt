package com.mediastreamplayer

import android.graphics.Color
import android.view.View
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.Arguments
import android.util.Log

class MediastreamPlayerViewManager : SimpleViewManager<View>() {
  companion object {
    var mdstrmViewPlayer: MediastreamPlayerViewPlayer? = null
  }

  override fun getName() = "MediastreamPlayerView"

  override fun createViewInstance(reactContext: ThemedReactContext): MediastreamPlayerViewPlayer {
    return MediastreamPlayerViewPlayer(reactContext)
  }

  @ReactProp(name = "color")
  fun setColor(view: View, color: String) {
    view.setBackgroundColor(Color.parseColor(color))
  }

  @ReactProp(name = "config")
  fun setConfig(view: View, config: ReadableMap) {
    mdstrmViewPlayer = view as MediastreamPlayerViewPlayer
    mdstrmViewPlayer!!.configure(config)
  }
}
