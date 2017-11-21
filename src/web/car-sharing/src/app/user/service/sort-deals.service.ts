import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Deal} from "../domain/deal";
import {DealStatus} from "../domain/deal-status";

@Injectable()
export class SortDealsService {
  dealStatus = new DealStatus;

  constructor() {
  }

  sort(sortedByStatus: boolean, deals: Deal[]) {
    if (sortedByStatus) {
      return this.sortByStatus(deals)
    }
    return this.sortById(deals)
  }

  private getStatus(a: Deal) {
    if (a.status == this.dealStatus.booking[0]) {
      return 0
    }
    if (a.status == this.dealStatus.rentalStart[0]) {
      return 1
    }
    if (a.status == this.dealStatus.overdueBooking[0]) {
      return 2
    }
    if (a.status == this.dealStatus.rentalEnd[0]) {
      return 3
    }
    return 4
  }

  private sortByStatus(deals: Deal[]) {
    return deals.sort((a: Deal, b: Deal) => {
      if (this.getStatus(a) < this.getStatus(b)) {
        return -1;
      } else if (this.getStatus(a) > this.getStatus(b)) {
        return 1;
      } else {
        return 0;
      }
    });
  }

  private sortById(deals: Deal[]) {
    return deals.sort((a: Deal, b: Deal) => {
      if (a.id < b.id) {
        return -1;
      } else if (a.id > b.id) {
        return 1;
      } else {
        return 0;
      }
    });
  }
}
