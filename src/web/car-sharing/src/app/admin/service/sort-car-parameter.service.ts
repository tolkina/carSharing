import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {TechnicalParameter} from "../domain/technical-parameter";
import {Brand_} from "../domain/brand_";

@Injectable()
export class SortCarParameterService {

  constructor() {
  }

  sortBrands(sortedByName: boolean, parameters: Brand_[]) {
    let brands: Brand_[] = [];
    if (sortedByName) {
      parameters = this.sortByName(parameters)
    }
    else {
      parameters = this.sortById(parameters)
    }
    parameters.forEach(brand => brands.push(this.sortModelsInBrand(sortedByName, brand)));
    return brands
  }

  sortCarParameters(sortedByName: boolean, parameters: TechnicalParameter[]) {
    if (sortedByName) {
      return this.sortByName(parameters)
    }
    return this.sortById(parameters)
  }

  private sortModelsInBrand(sortedByName: boolean, brand: Brand_) {
    if (sortedByName) {
      brand.models = this.sortByName(brand.models)
    }
    else {
      brand.models = this.sortById(brand.models)
    }
    return brand
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
