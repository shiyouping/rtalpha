import lodash from "lodash";
import Immutable from "immutable";
import thunkMiddleware from "redux-thunk";
import { createStore, applyMiddleware } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";

import Config from "common/config";
import rootReducer from "redux/root-reducer";
import sharedStore from "kernel/shared-store";
import { unregister } from "kernel/service-worker";
import DefaultState from "redux/base/default-state";
import SharedStoreKeys from "constants/shared-store-keys";

const configureServiceWorker = () => {
  unregister();
};

const configureReduxStore = () => {
  if (
    !lodash.isNil(sharedStore.getMemoryVariable(SharedStoreKeys.REDUX_STORE))
  ) {
    console.warn("Already initialized system redux store");
    return;
  }

  if (Config.isProduction) {
    let reduxStore = createStore(
      rootReducer,
      DefaultState,
      applyMiddleware(thunkMiddleware)
    );
    sharedStore.setMemoryVariable(SharedStoreKeys.REDUX_STORE, reduxStore);
  } else {
    // Show map data in the ReduxDevTool.
    import("map.prototype.tojson");

    const options = {
      serialize: {
        immutable: Immutable
      }
    };

    const composeEnhancers = composeWithDevTools(options);

    let reduxStore = createStore(
      rootReducer,
      DefaultState,
      composeEnhancers(applyMiddleware(thunkMiddleware))
    );
    sharedStore.setMemoryVariable(SharedStoreKeys.REDUX_STORE, reduxStore);
  }
};

const configureConsole = () => {
  if (Config.isProduction) {
    const noop = function() {};
    const methods = [
      "assert",
      "clear",
      "count",
      "debug",
      "dir",
      "dirxml",
      "error",
      "exception",
      "group",
      "groupCollapsed",
      "groupEnd",
      "info",
      "log",
      "markTimeline",
      "profile",
      "profileEnd",
      "table",
      "time",
      "timeEnd",
      "timeStamp",
      "trace",
      "warn"
    ];

    if (window.console) {
      let length = methods.length;

      while (length--) {
        let method = methods[length];
        if (console[method]) {
          console[method] = noop;
        }
      }
    }
  }
};

/**
 * The primary service to perform system level initialization work after the application starts up
 */
configureServiceWorker();
configureReduxStore();
configureConsole();
