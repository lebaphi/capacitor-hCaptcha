package com.capacitorjs.plugins.hcaptcha;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.hcaptcha.sdk.*;
import com.hcaptcha.sdk.tasks.*;

@CapacitorPlugin(name = "hCaptcha")
public class HCaptchaPlugin extends Plugin {

    @PluginMethod()
    public void verifyCaptcha(PluginCall call) {
        Log.d("HCaptchaPlugin", "Trigger hCaptcha Plugin");
        String siteKey = call.getString("siteKey");
        captchaChallenge(siteKey, call);
    }

    private void captchaChallenge(String siteKey, PluginCall call) {
        final HCaptchaConfig config = HCaptchaConfig.builder().siteKey(siteKey).apiEndpoint("https://hcaptcha.com/1/api.js")
            .size(HCaptchaSize.INVISIBLE).build();
        HCaptcha.getClient(getActivity()).verifyWithHCaptcha(config)
            .addOnSuccessListener(new OnSuccessListener<HCaptchaTokenResponse>() {
                @Override
                public void onSuccess(HCaptchaTokenResponse response) {
                    String userResponseToken = response.getTokenResult();
                    Log.d("MainActivity", "hCaptcha success: " + userResponseToken);
                    JSObject ret = new JSObject();
                    ret.put("status", "success");
                    ret.put("data", userResponseToken);
                    call.resolve(ret);
                }
            }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(HCaptchaException e) {
                Log.d("MainActivity", "hCaptcha failed: " + e.getMessage());
                JSObject ret = new JSObject();
                ret.put("status", "failed");
                ret.put("data", e.getMessage());
                call.resolve(ret);
            }
        });
    }
}
