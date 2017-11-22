import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {TechnicalParameter} from "../domain/technical-parameter";

@Injectable()
export class SortCarParameterService {

  constructor() {
  }

  sortCarParameters(sortedByName: boolean, parameters: TechnicalParameter[]) {
    if (sortedByName) {
      return this.sortByName(parameters)
    }
    return this.sortById(parameters)
  }

  private sortByName(parameters) {
    return parameters.sort((a, b) => {
      if (a.name < b.name) {
        return -1;
      } else if (a.name > b.name) {
        return 1;
      } else {
        return 0;
      }
    });
  }

  private sortById(parameters) {
    return parameters.sort((a, b) => {
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
