import axios from "axios";

import Config from "common/config";
import RequestInterceptor from "kernel/network/request-interceptor";
import ResponseInterceptor from "kernel/network/response-interceptor";

const requestInterceptor = new RequestInterceptor();
const responseInterceptor = new ResponseInterceptor();

export default class AxiosWrapper {
  /**
   * @returns {object} Headers that support Content-Type of `multipart/form-data`
   */
  static getFormDataConfig() {
    return {
      headers: {
        "Content-Type": "multipart/form-data"
      }
    };
  }

  /**
   * @returns {object} An Axios instance with base url pointing to the configured API server
   */
  static getInstance() {
    let instance = axios.create({
      baseURL: Config.API_SERVER_URL
    });

    instance.interceptors.request.use(
      requestInterceptor.interceptRequestConfig,
      requestInterceptor.interceptRequestError
    );

    instance.interceptors.response.use(
      responseInterceptor.intercept2xxResponse,
      responseInterceptor.interceptNon2xxResponse
    );

    return instance;
  }

  /**
   * @returns An Axios instance with supports of Content-Type of `multipart/form-data`
   * and base url pointing to the configured API server
   */
  static getFormInstance() {
    let instance = axios.create({
      baseURL: Config.API_SERVER_URL,
      headers: {
        "Content-Type": "multipart/form-data"
      }
    });

    instance.interceptors.request.use(
      requestInterceptor.interceptRequestConfig,
      requestInterceptor.interceptRequestError
    );

    instance.interceptors.response.use(
      responseInterceptor.intercept2xxResponse,
      responseInterceptor.interceptNon2xxResponse
    );

    return instance;
  }
}
