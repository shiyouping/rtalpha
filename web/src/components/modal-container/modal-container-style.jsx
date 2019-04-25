import styled from "styled-components";
import { Modal, Button, ModalFooter } from "reactstrap";

export const ModalWrapper = styled(Modal)`
  && {
    padding-top: 24px;
    padding-bottom: 15px;
    width: 270px;
    .modal-body {
      text-align: center;
    }
  }
`;

export const ZeroMargin = styled.p`
  margin: 0;
`;

export const ModalFooterWrapper = styled(ModalFooter)`
  && {
    justify-content: center;
    padding-top: 7px;
    padding-bottom: 7px;
  }
`;
export const ButtonWrapper = styled(Button)`
  && {
    border: none;
    width: 50%;
    border-radius: 0;
    &:hover,
    &:focus {
      text-decoration: none;
    }
    &:last-child {
      ${props => (props.border ? "border-right: 1px solid #e9ecef;" : "")}
    }
  }
`;
