import lodash from "lodash";

export default class ResponseResolver {
  /**
   * Check if the response returned by resolve(requestCall) is positive or not.
   *
   * @param {boolean} response the response returned by getComplexResponse(requestCall)
   * @returns true if possitive otherwise false
   * @example
   * { "name": "ricky" } is positive
   * {"errCode": 10001 } is negative
   * Http codes other than 2xx is negative
   */
  static isPositive(response) {
    if (lodash.isEqual(response, false)) {
      return false;
    }

    if (lodash.isPlainObject(response) && response.hasOwnProperty("errCode")) {
      return false;
    }

    return true;
  }

  /**
   * The return value of requestCall will be like:
   * @example
   * { "success": true, "value": { "name": "ricky" } }
   * { "success": false, "errCode": 10001 }
   *
   * @param {function} requestCall This function will return an instance of `AxiosResponse`
   * @returns the actual payload if success, @example { "name": "ricky" }
   *
   * or error object if error happens, @example {"errCode": 10001}
   *
   * or `false` if the http status is not 2xx or empty response
   */
  static async getComplexResponse(requestCall) {
    try {
      let res = await requestCall();

      if (lodash.isNil(res) || lodash.isNil(res.data)) {
        return false;
      }

      if (lodash.isEqual(res.data.success, true)) {
        return res.data.value;
      }

      return {
        errCode: res.data.errCode,
        message: res.data.message
      };
    } catch (error) {
      return false;
    }
  }

  /**
   * Check if the result of requestCall is true or false
   * @param {function} requestCall This function will return an instance of `AxiosResponse`
   * @returns true if possitive otherwise false
   * @example
   * { "name": "ricky" } is positive
   * {"errCode": 10001 } is negative
   * Http codes other than 2xx is negative
   */
  static async getSimpleResponse(requestCall) {
    let response = await ResponseResolver.getComplexResponse(requestCall);
    return ResponseResolver.isPositive(response);
  }
}
