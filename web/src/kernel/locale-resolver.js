import http from "axios";
import lodash from "lodash";
import intl from "react-intl-universal";

import Checker from "utils/checker";
import sharedStore from "kernel/shared-store";
import SharedStoreKeys from "constants/shared-store-keys";

const supportedLocales = [
  {
    name: "English",
    value: "en-US"
  },
  {
    name: "简体中文",
    value: "zh-CN"
  },
  {
    name: "繁體中文",
    value: "zh-TW"
  }
];

export default class LocaleResolver {
  /**
   * Return the locale in the following order:
   *
   * 1. Locale cached in local storage. If not, then
   * 2. Locale determined by brower language
   */
  static getCurrentLocale() {
    let cachedLocale = sharedStore.getPersistentVariable(
      SharedStoreKeys.LOCALE
    );

    if (!lodash.isNil(cachedLocale)) {
      console.log(`Use cached locale ${cachedLocale} instead.`);
      return cachedLocale;
    }

    let currentLocale = intl.determineLocale({
      urlLocaleKey: "lang",
      cookieLocaleKey: "lang"
    });
    console.info(`Detected locale ${currentLocale}`);

    if (!lodash.find(supportedLocales, { value: currentLocale })) {
      console.warn(
        `Cannot support locale ${currentLocale}. Use en-US instead.`
      );
      currentLocale = "en-US";
    }

    return currentLocale;
  }

  static async loadLocaleData(locale) {
    Checker.checkNotNil(locale, "locale");

    let res = await http.get(`/resources/locales/${locale}.json`);
    await intl.init({
      currentLocale: locale,
      locales: {
        [locale]: res.data
      }
    });

    console.log(`Locale data ${locale}.json is loaded`);
    return true;
  }
}
