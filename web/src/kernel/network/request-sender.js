import lodash from "lodash";

import Checker from "utils/checker";
import AxiosWrapper from "kernel/network/axios-wrapper";
import ResponseResolver from "kernel/network/response-resolver";
import Config from "common/config";

const VERB_GET = "GET";
const VERB_POST = "POST";

const isRetryNeeded = (rawResponse, retryTime) => {
  if (lodash.isNil(rawResponse) || lodash.isNil(rawResponse.data)) {
    throw new Error("Invalid response due to server error");
  }

  if (lodash.isEqual(rawResponse.data.success, true)) {
    return false;
  }

  if (retryTime === 0) {
    return false;
  }

  // TODO Check other logic

  return false;
};

const resendRequest = async (url, params, verb) => {
  let axios = AxiosWrapper.getInstance();

  if (lodash.isNil(params)) {
    if (verb === VERB_GET) {
      return await axios.get(url);
    } else {
      return await axios.post(url);
    }
  } else {
    if (verb === VERB_GET) {
      return await axios.get(url, { params });
    } else {
      return await axios.post(url, params);
    }
  }
};

const furtherProcess = async (rawResponse, url, params, verb) => {
  let canContinue = true;
  let retryTime = Config.REQUEST_RETRY_TIME;

  while (canContinue) {
    if (isRetryNeeded(rawResponse, retryTime)) {
      rawResponse = await resendRequest(url, params, verb);
    } else {
      canContinue = false;
    }

    retryTime--;
  }

  return rawResponse;
};

export default class RequestSender {
  /**
   *  Perform GET operation to get the complex response
   *
   * @param {string} url the full url the request will be sent to
   * @param {object} params the query parameters
   * @returns the actual payload if success, @example { "name": "ricky" }
   *
   * or error object if error happens, @example {"errCode": 10001 }
   *
   * or `false` if the http status is not 2xx or empty response
   */
  static async sendGet(url, params) {
    Checker.checkNotNil(url, "url");
    Checker.checkString(url, "url");

    let requestCall = async () => {
      let axios = AxiosWrapper.getInstance();
      let rawResponse;

      if (lodash.isNil(params)) {
        rawResponse = await axios.get(url);
      } else {
        rawResponse = await axios.get(url, { params });
      }

      return furtherProcess(rawResponse, url, params, VERB_GET);
    };

    return ResponseResolver.getComplexResponse(requestCall);
  }

  /**
   * Perform POST operation to get the complex response
   *
   * @param {string} url the full url the request will be sent to
   * @param {object} data the post body
   * @returns the actual payload if success, @example { "name": "ricky" }
   *
   * or error object if error happens, @example {"errCode": 10001 }
   *
   * or `false` if the http status is not 2xx or empty response
   */
  static async sendPost(url, data) {
    Checker.checkNotNil(url, "url");
    Checker.checkString(url, "url");

    let requestCall = async () => {
      let axios = AxiosWrapper.getInstance();
      let rawResponse;
      let params;

      if (lodash.isNil(data)) {
        rawResponse = await axios.post(url);
      } else {
        params = new URLSearchParams();
        lodash.forOwn(data, (value, key) => {
          if (!lodash.isNil(value)) {
            params.append(key, value);
          }
        });

        rawResponse = await axios.post(url, params);
      }

      return furtherProcess(rawResponse, url, params, VERB_POST);
    };

    return await ResponseResolver.getComplexResponse(requestCall);
  }

  /**
   * Perform file POST operation to get the complex response
   *
   * @param {string} url the full url the request will be sent to
   * @param {object} data the post body
   * @returns the actual payload if success, @example { "name": "ricky" }
   *
   * or error object if error happens, @example {"errCode": 10001 }
   *
   * or `false` if the http status is not 2xx or empty response
   */
  static async sendFile(url, data) {
    Checker.checkNotNil(url, "url");
    Checker.checkString(url, "url");
    Checker.checkNotNil(data, "data");

    let requestCall = async () => {
      let axios = AxiosWrapper.getFormInstance();
      let rawResponse;
      let params;

      if (lodash.isNil(data)) {
        rawResponse = await axios.post(url);
      } else {
        params = new FormData();
        lodash.forOwn(data, (value, key) => {
          params.set(key, value);
        });

        rawResponse = await axios.post(url, params);
      }

      return furtherProcess(rawResponse, url, params, VERB_POST);
    };

    return await ResponseResolver.getComplexResponse(requestCall);
  }
}
