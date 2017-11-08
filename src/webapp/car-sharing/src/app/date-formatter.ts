import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap"

export class DateFormatter {
  toDate(date: NgbDateStruct) {
    if (date === null) {
      return null;
    }
    return new Date(date.year, date.month - 1, date.day + 1);
  }

  fromDate(date: Date) {
    let d: any = {};
    if (date != null) {
      d.year = +(date[0] + date[1] + date[2] + date[3]);
      d.month = +(date[5] + date[6]);
      d.day = +(date[8] + date[9]);
    }
    return d;
  }
}
