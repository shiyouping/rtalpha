// Add polyfill to support IE9,10,11
import "react-app-polyfill/ie9";
// To polyfill URLSearchParams API
import "@ungap/url-search-params";
import "filepond-polyfill";

import React from "react";
import { render } from "react-dom";
import { Provider } from "react-redux";
import { ToastContainer } from "react-toastify";
import { BrowserRouter as Router } from "react-router-dom";

import App from "app";
import sharedStore from "kernel/shared-store";
import SharedStoreKeys from "constants/shared-store-keys";
import ModalContainer from "components/modal-container/modal-container";

import "normalize.css";
import "bootstrap/dist/css/bootstrap.css";
import "react-toastify/dist/ReactToastify.css";
import GlobalStyle from "constants/global-style";

// Just import to initilize system-scoped service
import "services/app/system-service";
import "services/app/event-service";

const rootElement = document.querySelector("#root");
if (rootElement) {
  const reduxStore = sharedStore.getMemoryVariable(SharedStoreKeys.REDUX_STORE);
  const reactApp = (
    <Provider store={reduxStore}>
      <Router>
        <div>
          <GlobalStyle />
          <App />
          <ModalContainer />
          <ToastContainer
            position="bottom-right"
            autoClose={3000}
            hideProgressBar={false}
            newestOnTop
            closeOnClick
            rtl={false}
            pauseOnVisibilityChange
            draggable
            pauseOnHover
          />
        </div>
      </Router>
    </Provider>
  );

  render(reactApp, document.getElementById("root"));
}
