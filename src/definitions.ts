/// <reference types="@capacitor/cli" />

export interface HCaptchaPlugin {
  /**
   * verify captcha
   * @param options
   */
  verifyCaptcha(options: { siteKey: string }): Promise<HCaptchaResult>;
}

export interface HCaptchaResult {
  status: 'success' | 'failed';
  data: any;
}
