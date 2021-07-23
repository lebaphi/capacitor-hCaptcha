//
//  HCaptcha.swift
//  Plugin
//
//  Created by lebaphi on 7/22/21.
//  Copyright © 2021 Max Lynch. All rights reserved.
//

import Capacitor
import HCaptcha

@objc(HCaptchaPlugin)
public class HCaptchaPlugin: CAPPlugin {
  
    
    @objc func verifyCaptcha(_ call: CAPPluginCall) {
    let siteKey = call.getString("siteKey") ?? ""
    
    DispatchQueue.main.sync {
        let hcaptcha = try? HCaptcha(
            apiKey: siteKey,
            baseURL: URL(string: "https://hcaptcha.com")!
        )
        
        let view = self.bridge?.webView
        
        hcaptcha?.configureWebView { [weak self] webview in
            webview.frame = view?.bounds ?? CGRect.zero
            }
        
        hcaptcha?.validate(on: view as! UIView) { [weak self] (result: HCaptchaResult) in
            call.resolve([
                "data": result,
                "status": "success"
            ])
        }
    }
  }
}
