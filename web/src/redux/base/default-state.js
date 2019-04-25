import sharedStore from "kernel/shared-store";
import SharedStoreKeys from "constants/shared-store-keys";

const DefaultState = {
  locale: sharedStore.getPersistentVariable(SharedStoreKeys.LOCALE) || "en-US"
};

export default DefaultState;
