import PubSub from "pubsub-js";

import Checker from "utils/checker";

export default class EventEmitter {
  /**
   * Adds the listener function for the event named eventName
   *
   * @param {symbol} eventName The name of the event.
   * @param {function} listener The callback function
   * @returns A token which can be used to unlisten the event
   */
  static addListener(eventName, listener) {
    Checker.checkNotNil(eventName, "eventName");
    Checker.checkSymbol(eventName, "eventName");
    Checker.checkNotNil(listener, "listener");
    return PubSub.subscribe(eventName, listener);
  }

  /**
   * Emit a message to the given eventName
   *
   * @param {symbol} eventName The name of the event.
   * @param {object} message The message attached to the event
   * @returns {object} a token which can be used for unlisten the event
   */
  static emit(eventName, message) {
    Checker.checkNotNil(eventName, "eventName");
    Checker.checkSymbol(eventName, "eventName");
    PubSub.publish(eventName, message);
  }

  /**
   * Remove listener by token
   *
   * @param {object} token returned by addListener method
   */
  static removeListenerByToken(token) {
    Checker.checkNotNil(token, "token");
    PubSub.unsubscribe(token);
  }

  /**
   * Remove listener by listener
   *
   * @param {function} listener The callback function
   */
  static removeListenerByListener(listener) {
    Checker.checkNotNil(listener, "listener");
    PubSub.unsubscribe(listener);
  }

  /**
   * Remove listener by eventName
   *
   * @param {symbol} eventName The name of the event.
   */
  static removeListenerByEventName(eventName) {
    Checker.checkNotNil(eventName, "eventName");
    PubSub.unsubscribe(eventName);
  }
}
