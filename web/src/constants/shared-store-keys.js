const LOCALE = "locale";
const REDUX_STORE = "reduxStore";
const COOKIE_POLICY_ACCEPTED = "cookiePolicyAccepted";

export default class SharedStoreKeys {
  static get COOKIE_POLICY_ACCEPTED() {
    return COOKIE_POLICY_ACCEPTED;
  }

  static get LOCALE() {
    return LOCALE;
  }

  static get REDUX_STORE() {
    return REDUX_STORE;
  }
}
