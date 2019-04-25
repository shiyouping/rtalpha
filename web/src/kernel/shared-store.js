import Checker from "utils/checker";
import lodash from "lodash";
/**
 * A singleton class that stores global variables which do not need to refersh web pages.
 *
 * If shared variables need to cause web pages to self-auto-refresh, please store them in redux store.
 */
class SharedStore {
  constructor() {
    this.map = new Map();
  }

  /**
   *  Save the key-value pair in the store
   *
   * @param {string} key must be of type string
   * @param value primitive values or objects
   */
  setPersistentVariable(key, value) {
    Checker.checkNotNil(key, "key");
    Checker.checkString(key, "key");
    localStorage.setItem(key, JSON.stringify(value));
  }

  /**
   *  Save the key-value pair in the store
   *
   * @param {string} key must be of type string
   * @param value primitive values or objects
   */
  setSessionVariable(key, value) {
    Checker.checkNotNil(key, "key");
    Checker.checkString(key, "key");
    sessionStorage.setItem(key, JSON.stringify(value));
  }

  /**
   *  Save the key-value pair in memory
   *
   * @param {string} key must be of type string
   * @param value primitive values or objects
   */
  setMemoryVariable(key, value) {
    Checker.checkNotNil(key, "key");
    Checker.checkString(key, "key");
    this.map.set(key, value);
  }

  /**
   * Retrieve the value associated with the given key
   *
   * @param {string} key must be of type string
   * @returns value primitive values or objects
   */
  getPersistentVariable(key) {
    Checker.checkNotNil(key, "key");
    Checker.checkString(key, "key");

    let value = localStorage.getItem(key);
    if (lodash.isNil(value)) {
      return value;
    } else {
      return JSON.parse(value);
    }
  }

  /**
   * Retrieve the value associated with the given key
   *
   * @param {string} key must be of type string
   * @returns value primitive values or objects
   */
  getSessionVariable(key) {
    Checker.checkNotNil(key, "key");
    Checker.checkString(key, "key");

    let value = sessionStorage.getItem(key);
    if (lodash.isNil(value)) {
      return value;
    } else {
      return JSON.parse(value);
    }
  }

  /**
   * Retrieve the value associated with the given key
   *
   * @param {string} key must be of type string
   * @returns value primitive values or objects
   */
  getMemoryVariable(key) {
    Checker.checkNotNil(key, "key");
    Checker.checkString(key, "key");

    return this.map.get(key);
  }

  /**
   * Remove the variable for the given key
   *
   * @param {string} key
   */
  removePersistentVariable(key) {
    Checker.checkNotNil(key, "key");
    Checker.checkString(key, "key");
    localStorage.removeItem(key);
  }

  /**
   * Remove the variable for the given key
   *
   * @param {string} key
   */
  removeSessionVariable(key) {
    Checker.checkNotNil(key, "key");
    Checker.checkString(key, "key");
    sessionStorage.removeItem(key);
  }

  /**
   * Remove the variable for the given key
   *
   * @param {string} key
   */
  removeMemoryVariable(key) {
    Checker.checkNotNil(key, "key");
    Checker.checkString(key, "key");
    this.map.delete(key);
  }
}

const sharedStore = new SharedStore();
export default sharedStore;
