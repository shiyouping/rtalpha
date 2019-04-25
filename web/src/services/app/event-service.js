import Toast from "common/toast";
import EventEmitter from "kernel/event-emitter";
import { EVENT_ERROR_RESPONSE } from "constants/event-names";

const handleErrorResponseEvent = (eventName, message) => {
  Toast.error(message);
};

EventEmitter.addListener(EVENT_ERROR_RESPONSE, handleErrorResponseEvent);
