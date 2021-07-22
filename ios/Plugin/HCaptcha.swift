//
//  HCaptcha.swift
//  Plugin
//
//  Created by lebaphi on 7/22/21.
//  Copyright © 2021 Max Lynch. All rights reserved.
//

import Capacitor

@objc(HCaptchaPlugin)
public class HCaptchaPlugin: CAPPlugin {
  @objc func verifyCaptcha(_ call: CAPPluginCall) {
    let siteKey = call.getString("siteKey") ?? ""
    call.resolve([
        "siteKey": siteKey
    ])
  }
}
