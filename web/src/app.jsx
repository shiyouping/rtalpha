import { connect } from "react-redux";
import intl from "react-intl-universal";
import React, { PureComponent, Fragment } from "react";
import { withRouter } from "react-router-dom";
import { Alert, Button, Container, Row, Col } from "reactstrap";

import sharedStore from "kernel/shared-store";
import LocaleResolver from "kernel/locale-resolver";
import SharedStoreKeys from "constants/shared-store-keys";

const isChrome = () => {
  const isGoogleBot =
    navigator.userAgent.toLowerCase().indexOf("googlebot") !== -1;
  const isIE = false || !!document.documentMode;
  const isEdge = !isIE && !!window.StyleMedia;
  const isOpera =
    (!!window.opr && !!window.opr.addons) ||
    !!window.opera ||
    navigator.userAgent.indexOf(" OPR/") >= 0;
  const isChrome =
    !isGoogleBot &&
    !isEdge &&
    !isOpera &&
    !!window.chrome &&
    (!!window.chrome.webstore ||
      navigator.vendor.toLowerCase().indexOf("google inc.") !== -1);

  return isChrome;
};

class App extends PureComponent {
  constructor(props) {
    super(props);

    let isCookiePolicyAccepted =
      sharedStore.getPersistentVariable(
        SharedStoreKeys.COOKIE_POLICY_ACCEPTED
      ) || false;

    this.state = {
      initDone: false,
      browserHint: !isChrome(),
      cookieHint: !isCookiePolicyAccepted
    };

    this.onBrowserHintButtonClick = this.onBrowserHintButtonClick.bind(this);
    this.onCookieHintButtonClick = this.onCookieHintButtonClick.bind(this);
  }

  getEntry() {
    return <Fragment>{this.getCookieAndBrowerHint()}</Fragment>;
  }

  getCookieAndBrowerHint() {
    if (!this.state.cookieHint && !this.state.browserHint) {
      return null;
    }

    return (
      <Alert color="warning">
        <Container>
          {this.state.cookieHint === true ? (
            <Row>
              <Col>
                {intl.get("common.cookiePolicy")}
                &nbsp;&nbsp;&nbsp;&nbsp;
                <Button color="primary" onClick={this.onCookieHintButtonClick}>
                  {intl.get("common.accept")}
                </Button>
              </Col>
            </Row>
          ) : null}

          {this.state.browserHint === true ? (
            <Row>
              <Col>
                {intl.getHTML("common.unsupportedBrowser")}
                &nbsp;&nbsp;&nbsp;&nbsp;
                <Button color="primary" onClick={this.onBrowserHintButtonClick}>
                  {intl.get("common.confirm")}
                </Button>
              </Col>
            </Row>
          ) : null}
        </Container>
      </Alert>
    );
  }

  onBrowserHintButtonClick() {
    this.setState({ browserHint: false });
  }

  onCookieHintButtonClick() {
    this.setState({ cookieHint: false });
    sharedStore.setPersistentVariable(
      SharedStoreKeys.COOKIE_POLICY_ACCEPTED,
      true
    );
  }

  async componentDidMount() {
    let currentLocale = LocaleResolver.getCurrentLocale();
    let isLoaded = await LocaleResolver.loadLocaleData(currentLocale);
    this.setState({ initDone: isLoaded });
  }

  render() {
    return this.state.initDone && this.getEntry();
  }
}

const mapStateToProps = state => {
  return {
    // This component doesn't need locale, but need to put it
    // here to refresh the entire component tree once locale is changed.
    locale: state.locale
  };
};

export default withRouter(connect(mapStateToProps)(App));
