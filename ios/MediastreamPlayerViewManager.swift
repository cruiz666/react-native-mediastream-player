import Foundation
import UIKit
import AVFoundation
import MediastreamPlatformSDKiOS

@objc(MediastreamPlayerViewManager)
class MediastreamPlayerViewManager: RCTViewManager {

  override func view() -> (MediastreamPlayerView) {
    return MediastreamPlayerView()
  }

  @objc override static func requiresMainQueueSetup() -> Bool {
    return false
  }
}

class MediastreamPlayerView : UIView {

  let mdstrm = MediastreamPlatformSDK()

  override init(frame: CGRect) {
    super.init(frame: frame)
    self.addSubview(mdstrm.view)
  }

  override func layoutSubviews() {
      super.layoutSubviews()
      mdstrm.view.frame = self.bounds
  }

  required init?(coder: NSCoder) {
      fatalError("init(coder:) has not been implemented")
  }

  @objc var color: String = "" {
    didSet {
      self.backgroundColor = hexStringToUIColor(hexColor: color)
    }
  }

  @objc var config: NSDictionary = [:] {
    didSet {
      let config = config as! [String: Any]
      let playerConfig = MediastreamPlayerConfig()
      playerConfig.accountID = config["accountID"] as! String
      playerConfig.id = config["id"] as! String
      playerConfig.autoplay = config["autoplay"] as! Bool
      mdstrm.setup(playerConfig)
    }
  }

  func hexStringToUIColor(hexColor: String) -> UIColor {
    let stringScanner = Scanner(string: hexColor)

    if(hexColor.hasPrefix("#")) {
      stringScanner.scanLocation = 1
    }
    var color: UInt32 = 0
    stringScanner.scanHexInt32(&color)

    let r = CGFloat(Int(color >> 16) & 0x000000FF)
    let g = CGFloat(Int(color >> 8) & 0x000000FF)
    let b = CGFloat(Int(color) & 0x000000FF)

    return UIColor(red: r / 255.0, green: g / 255.0, blue: b / 255.0, alpha: 1)
  }
}
