import EventEmitter from "kernel/event-emitter";
import { EVENT_SHOW_MODAL } from "constants/event-names";
import Checker from "utils/checker";

export default class Modal {
  static show(
    message,
    positiveButtonText,
    positiveButtonCallback,
    negativeButtonText,
    negativeButtonCallback
  ) {
    Checker.checkNotNil(message, "message");

    let positiveButton;
    if (positiveButtonText || positiveButtonCallback) {
      positiveButton = {
        text: positiveButtonText,
        onClick: positiveButtonCallback
      };
    }

    let negativeButton;
    if (negativeButtonText || negativeButtonCallback) {
      negativeButton = {
        text: negativeButtonText,
        onClick: negativeButtonCallback
      };
    }

    EventEmitter.emit(EVENT_SHOW_MODAL, {
      message,
      positiveButton,
      negativeButton
    });
  }
}
