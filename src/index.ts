import { registerPlugin } from '@capacitor/core';

import type { HCaptchaPlugin } from './definitions';

const HCaptcha = registerPlugin<HCaptchaPlugin>('hCaptcha');

export * from './definitions';
export { HCaptcha };
