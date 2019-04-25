const REQUEST_RETRY_TIME = 1;
const IDLE_TIMEOUT = 1000 * 60 * 5;
const API_SERVER_URL = process.env.REACT_APP_API_SERVER_URL;
const WEBSOCKET_SERVER_URL = process.env.REACT_APP_WEB_SOCKET_SERVER_URL;
const APP_VERSION = process.env.REACT_APP_VERSION;

export default class Config {
  static get IDLE_TIMEOUT() {
    return IDLE_TIMEOUT;
  }

  static get REQUEST_RETRY_TIME() {
    return REQUEST_RETRY_TIME;
  }

  static get APP_VERSION() {
    return APP_VERSION;
  }

  static get API_SERVER_URL() {
    return API_SERVER_URL;
  }

  static get WEBSOCKET_SERVER_URL() {
    return WEBSOCKET_SERVER_URL;
  }

  static get isProduction() {
    return process.env.NODE_ENV === "production";
  }
}
