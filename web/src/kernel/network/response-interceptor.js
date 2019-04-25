import intl from "react-intl-universal";

import EventEmitter from "kernel/event-emitter";
import FalseResponseHandler from "./false-response-handler";
import { EVENT_ERROR_RESPONSE } from "constants/event-names";

const processNon2xxResponse = statusCode => {
  let errorMessage = intl.get("httpError.general");

  if ((statusCode >= 300) & (statusCode < 400)) {
    errorMessage = intl.get("httpError.redirection");
  } else if ((statusCode >= 400) & (statusCode < 500)) {
    errorMessage = intl.get("httpError.client");
  } else if (statusCode >= 500) {
    errorMessage = intl.get("httpError.server");
  }

  EventEmitter.emit(EVENT_ERROR_RESPONSE, errorMessage);
};

const falseResponseHandler = new FalseResponseHandler();

export default class ResponseInterceptor {
  interceptNon2xxResponse(error) {
    if (error.name === "SyntaxError") {
      processNon2xxResponse(error.response.status);
    } else {
      EventEmitter.emit(EVENT_ERROR_RESPONSE, intl.get("httpError.network"));
    }

    Promise.reject(error);
  }

  intercept2xxResponse(response) {
    if (response.data.success === true) {
      // TODO finish this part
    } else {
      falseResponseHandler.handle(response);
    }

    return response;
  }
}
