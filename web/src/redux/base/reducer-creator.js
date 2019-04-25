import { handleAction } from "redux-actions";

import DefaultState from "redux/base/default-state";

export default class ReducerCreator {
  /**
   * Create a reducer to handle normal actions
   *
   * @param {function} actionCreator defines how to create the action
   * @param {string} stateName defines where the action payload will be stored
   */
  static create(actionCreator, stateName) {
    return handleAction(
      actionCreator,
      (state, action) => ({
        ...state,
        [stateName]: action.payload
      }),
      DefaultState
    );
  }
}
