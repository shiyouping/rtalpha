import LocaleResolver from "kernel/locale-resolver";
import SystemConstants from "constants/system-constants";

export default class RequestInterceptor {
  interceptRequestConfig(config) {
    let locale = LocaleResolver.getCurrentLocale();
    config.headers.common[SystemConstants.HTTP_HEADER_LOCALE] = locale;
    return config;
  }

  interceptRequestError(error) {
    Promise.reject(error);
  }
}
