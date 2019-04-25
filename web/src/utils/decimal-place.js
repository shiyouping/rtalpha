import lodash from "lodash";

export default class DecimalPlace {
  static getPrecision(number) {
    if (lodash.isNil(number)) {
      return 0;
    }

    if (!isFinite(number)) {
      return 0;
    }

    let e = 1;
    let p = 0;

    while (Math.round(number * e) / e !== number) {
      e *= 10;
      p++;
    }

    return p;
  }
}
