import UUID from "uuid/v5";
import lodash from "lodash";
import SockJS from "sockjs-client";
import webstomp from "webstomp-client";

export default class StompClient {
  /**
   * @param {string} serverUrl to which stomp client intends to connect
   */
  constructor(serverUrl) {
    this.serverUrl = serverUrl;
    this.client = null;
    this.onReceiveMessage = this.onReceiveMessage.bind(this);
  }

  /**
   * Connects to the desired websocket server and subscribes topics with callbacks
   *
   * @param {object} headers have properties of login, passcode, client-id, etc
   * @param {function} onConnectedCallback which will be called once the connection will be established from stomp server.
   * @param {function} onErrorCallback which will be called once the connection got error from stomp server.
   */
  connect(headers, onConnectedCallback, onErrorCallback, onMessage) {
    if (this.isConnected === true) {
      console.log("Already connected to a remote Websocket server");
      return;
    }
    this.onMessage = onMessage;
    console.log(`Connecting websocket server at ${this.serverUrl} ...`);

    const onConnected = () => {
      console.log("Websocket server connected");
      this.isConnected = true;
      onConnectedCallback();
    };

    const onError = error => {
      console.error(
        `Failed to connect to websocket server at ${this.serverUrl} due to ${
          error.reason
        }`
      );

      this.isConnected = false;
      onErrorCallback();
    };

    this.client = webstomp.over(new SockJS(this.serverUrl), {
      heartbeat: false,
      debug: false
    });

    this.client.connect({ ...headers }, onConnected, onError);
  }

  /**
   *
   * @param {string} topic which topic the client wants to subscribe
   * @param {function} onMessage a callback when a server message is received
   * @param {string} sessionId required when subscribe a private topic
   * @returns {object} an subscription object which can be used to unsubscribe the topic by calling subscription.unsubscribe(headers).
   */
  subscribe(topic, sessionId) {
    if (!this.isConnected) {
      console.error("Websocket connection hasn't established");
      return;
    }

    let id = UUID(topic, "1b671a64-40d5-491e-99b0-da01ff1f3341");
    let headers = undefined;

    if (lodash.isNil(sessionId)) {
      headers = { id };
    } else {
      headers = { sessionId, id };
    }

    let subscription = this.client.subscribe(
      topic,
      this.onReceiveMessage,
      headers
    );

    return subscription;
  }

  onReceiveMessage(message) {
    if (this.onMessage) {
      let topic = message.headers.destination;
      let body = JSON.parse(message.body);
      this.onMessage(topic, body);
    }
  }

  /**
   * Disconnect with the websocket server
   */
  disconnect() {
    if (this.isConnected === false) {
      console.log("Aborted. Websocket already disconnected.");
      return;
    }

    console.log(`Disconnecting websocket server at ${this.serverUrl} ...`);
    this.client.disconnect(() => {
      console.log(`Disconnected websocket server at ${this.serverUrl}`);
      this.isConnected = false;
    });
  }
}
