import lodash from "lodash";

export default class Checker {
  /**
   * Check if the given object is null or undefined.
   *
   * If null or undefined, throw an exception
   *
   * @param {*} object to be checked
   * @param {string} name the object name
   *
   */
  static checkNotNil(object, name) {
    if (lodash.isNil(object)) {
      let message = lodash.isString(name)
        ? `${name} cannot be undefined or null`
        : "Encounter undefined or null";

      throw new Error(message);
    }
  }

  /**
   * Check if the given object is an array.
   *
   * If not an array, throw an exception
   *
   * @param {*} object to be checked
   * @param {string} name the object name
   *
   */
  static checkArray(object, name) {
    if (!lodash.isArray(object)) {
      let message = lodash.isString(name)
        ? `${name} is not an Array`
        : "Target is not an Array";

      throw new Error(message);
    }
  }

  /**
   * Check if the given object is of type string
   *
   * If not a string, throw an exception
   *
   * @param {*} object to be checked
   * @param {*} name the object name
   */
  static checkString(object, name) {
    if (!lodash.isString(object)) {
      let message = lodash.isString(name)
        ? `${name} is not a string`
        : "Encounter non-string";

      throw new Error(message);
    }
  }

  /**
   * Check if the given object is of type string
   *
   * If not a symbol, throw an exception
   *
   * @param {*} object to be checked
   * @param {*} name the object name
   */
  static checkSymbol(object, name) {
    if (typeof object !== "symbol" && typeof object !== "object") {
      let message = lodash.isString(name)
        ? `${name} is not a symbol`
        : "Encounter non-symbol";

      throw new Error(message);
    }
  }

  /**
   * Check if the given number not null && undefined && type is number
   */
  static checkIsNumber(number) {
    if (number !== null && number !== undefined && typeof number == "number") {
      return true;
    }
    return false;
  }
}
