import lodash from "lodash";
import { ModalBody } from "reactstrap";
import intl from "react-intl-universal";
import React, { PureComponent } from "react";

import {
  ZeroMargin,
  ModalWrapper,
  ButtonWrapper,
  ModalFooterWrapper
} from "components/modal-container/modal-container-style";
import EventEmitter from "kernel/event-emitter";
import { EVENT_SHOW_MODAL } from "constants/event-names";

export default class ModalContainer extends PureComponent {
  constructor(props) {
    super(props);
    this.onRequested = this.onRequested.bind(this);
    this.onPositiveButtonClick = this.onPositiveButtonClick.bind(this);
    this.onNegativeButtonClick = this.onNegativeButtonClick.bind(this);
    EventEmitter.addListener(EVENT_SHOW_MODAL, this.onRequested);
  }

  onRequested(event, parameters) {
    this.setState({
      isOpen: true,
      message: parameters.message,
      positiveButton: parameters.positiveButton,
      negativeButton: parameters.negativeButton
    });
  }

  onPositiveButtonClick() {
    if (
      this.state.positiveButton &&
      lodash.isFunction(this.state.positiveButton.onClick)
    ) {
      this.state.positiveButton.onClick();
    }

    this.setState({ isOpen: false });
  }

  async onNegativeButtonClick() {
    if (
      this.state.negativeButton &&
      lodash.isFunction(this.state.negativeButton.onClick)
    ) {
      this.state.negativeButton.onClick();
    }

    this.setState({ isOpen: false });
  }

  getModalFooter() {
    if (this.state.positiveButton && this.state.negativeButton) {
      return (
        <ModalFooterWrapper>
          <ButtonWrapper color="link" onClick={this.onPositiveButtonClick}>
            {this.state.positiveButton.text || intl.get("common.confirm")}
          </ButtonWrapper>
          <ButtonWrapper color="link" onClick={this.onNegativeButtonClick}>
            {this.state.negativeButton.text || intl.get("common.cancel")}
          </ButtonWrapper>
        </ModalFooterWrapper>
      );
    } else if (this.state.positiveButton) {
      return (
        <ModalFooterWrapper>
          <ButtonWrapper color="link" onClick={this.onPositiveButtonClick}>
            {this.state.positiveButton.text || intl.get("common.confirm")}
          </ButtonWrapper>
        </ModalFooterWrapper>
      );
    } else if (this.state.negativeButton) {
      return (
        <ModalFooterWrapper>
          <ButtonWrapper color="link" onClick={this.onNegativeButtonClick}>
            {this.state.negativeButton.text || intl.get("common.cancel")}
          </ButtonWrapper>
        </ModalFooterWrapper>
      );
    } else {
      return (
        <ModalFooterWrapper>
          <ButtonWrapper color="link" onClick={this.onPositiveButtonClick}>
            {intl.get("common.confirm")}
          </ButtonWrapper>
        </ModalFooterWrapper>
      );
    }
  }

  render() {
    if (!this.state) {
      return null;
    }

    return (
      <ModalWrapper centered={true} isOpen={this.state.isOpen}>
        <ModalBody>
          <ZeroMargin>{this.state.message}</ZeroMargin>
        </ModalBody>
        {this.getModalFooter()}
      </ModalWrapper>
    );
  }
}
