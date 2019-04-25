import { toast } from "react-toastify";
import Checker from "utils/checker";

export const toastConfig = {
  position: "bottom-right",
  autoClose: 3000,
  hideProgressBar: false,
  closeOnClick: true,
  pauseOnHover: true,
  draggable: true
};

export default class Toast {
  /**
   * Display the message in INFO level
   *
   * @param {string} message required. Text to be displayed
   * @param {object} config toast custom configuration
   */
  static info(message, config) {
    Checker.checkNotNil(message, "message");

    if (config) {
      toast.info(message, config);
    } else {
      toast.info(message);
    }
  }

  /**
   * Display the message in SUCCESS level
   *
   * @param {string} message required. Text to be displayed
   * @param {object} config toast custom configuration
   */
  static success(message, config) {
    Checker.checkNotNil(message, "message");

    if (config) {
      toast.success(message, config);
    } else {
      toast.success(message);
    }
  }

  /**
   * Display the message in WARNING level
   *
   * @param {string} message required. Text to be displayed
   * @param {object} config toast custom configuration
   */
  static warning(message, config) {
    Checker.checkNotNil(message, "message");

    if (config) {
      toast.warning(message, config);
    } else {
      toast.warning(message);
    }
  }

  /**
   * Display the message in ERROR level
   *
   * @param {string} message required. Text to be displayed
   * @param {object} config toast custom configuration
   */
  static error(message, config) {
    Checker.checkNotNil(message, "message");

    if (config) {
      toast.error(message, config);
    } else {
      toast.error(message);
    }
  }

  /**
   * Display the message in DEFAULT level
   *
   * @param {string} message required. Text to be displayed
   * @param {object} config toast custom configuration
   */
  static default(message, config) {
    Checker.checkNotNil(message, "message");

    if (config) {
      toast(message, config);
    } else {
      toast(message);
    }
  }
}
