export interface HCaptchaPlugin {
    /**
     * verify captcha
     * @param options
     */
    verifyCaptcha(options: {
        siteKey: string;
    }): Promise<any>;
}